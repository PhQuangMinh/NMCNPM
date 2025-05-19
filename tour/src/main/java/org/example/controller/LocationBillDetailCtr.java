package org.example.controller;

import org.example.model.Client;
import org.example.view.LocationBillDetailFrm;
import org.example.model.Bill;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationBillDetailCtr {
    private LocationBillDetailFrm frm;
    private final JFrame parentFrm;
    private Connection con;

    public LocationBillDetailCtr(String locationName, JFrame parentFrm) {
        this.parentFrm = parentFrm;
        frm = new LocationBillDetailFrm();

        // Lấy và hiển thị danh sách hóa đơn
        List<Bill> billList = getBillData(locationName);

        // Tính toán tổng hợp
        int totalBills = billList.size();
        int totalGuests = 0;
        double totalRevenue = 0;
        for (Bill bill : billList) {
            totalGuests += bill.getQuantityTicket();
            totalRevenue += bill.getTotalPrice();
        }

        // Tính tổng tiền hoàn
        double totalRefund = getTotalRefund(locationName);
        double realRevenue = totalRevenue - totalRefund;

        // Hiển thị thông tin tổng hợp
        if (billList.isEmpty()) {
            frm.setInfoText("<html><font color='red'>Không có dữ liệu hóa đơn cho địa điểm này!</font></html>");
            frm.setTableData(new ArrayList<>());
        } else {
            String info = String.format(
                    "<html>Địa điểm: %s<br>" +
                "<html>Số lượng hóa đơn: %d Bill.<br>" +
                "Số lượng khách đến địa điểm: %d khách.<br>" +
                "Tổng doanh thu hóa đơn: %,.0f đồng.<br>" +
                "Tổng tiền hoàn: %,.0f đồng.<br>" +
                "<b>Tổng doanh thu: %,.0f đồng.</b></html>",
                    locationName,
                totalBills,
                totalGuests,
                totalRevenue,
                totalRefund,
                realRevenue
            );
            frm.setInfoText(info);
            frm.setTableData(billList);
        }

        frm.addBackListener(e -> {
            frm.dispose();
            parentFrm.setVisible(true);
        });
        frm.setVisible(true);
        parentFrm.setVisible(false);
    }

    private List<Bill> getBillData(String locationName) {
        List<Bill> bills = new ArrayList<>();
        String sql = """
                WITH bill_group_size AS (
                    SELECT 
                        b.code_bill,
                        td.code_tour,
                        td.id AS id_tour_departure,
                        td.departure_date,
                        COUNT(tk.code_ticket) AS group_size
                    FROM tblBill b
                    JOIN tblTicket tk ON b.code_bill = tk.code_bill
                    JOIN tblTourDeparture td ON tk.id_tour_departure = td.id
                    GROUP BY 
                        b.code_bill, 
                        td.code_tour, 
                        td.id, 
                        td.departure_date
                ),
                bill_with_price AS (
                    SELECT 
                        bgs.code_bill,
                        bgs.code_tour,
                        bgs.id_tour_departure,
                        bgs.group_size,
                        bgs.departure_date,
                        pt.price_per_person
                    FROM bill_group_size bgs
                    JOIN tblPriceTicket pt ON pt.id_departure = bgs.id_tour_departure
                        AND bgs.group_size BETWEEN pt.min_group_size AND pt.max_group_size
                )
                SELECT 
                    bwp.code_bill AS id_hoa_don,
                    c.client_name AS ten_khach,
                    bwp.departure_date AS ngay_xuat_phat,
                    t.tourname AS ten_tour,
                    bwp.group_size AS tong_so_khach,
                    bwp.group_size * bwp.price_per_person AS tong_so_tien
                FROM bill_with_price bwp
                JOIN tblBill b ON bwp.code_bill = b.code_bill
                JOIN tblClient c ON b.code_client = c.code_client
                JOIN tblTour t ON bwp.code_tour = t.code_tour
                WHERE t.destination = ?
                ORDER BY bwp.departure_date
                """;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, locationName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setCodeBill(rs.getString("id_hoa_don"));
                bill.setRepresentativeName(rs.getString("ten_khach"));
                bill.setBillDate(rs.getTimestamp("ngay_xuat_phat"));
                bill.setTourInfo(rs.getString("ten_tour"));
                bill.setQuantityTicket(rs.getInt("tong_so_khach"));
                bill.setTotalPrice((float) rs.getDouble("tong_so_tien"));
                bills.add(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frm, "Lỗi khi lấy dữ liệu: " + e.getMessage());
        }
        return bills;
    }

    private double getTotalRefund(String locationName) {
        double totalRefund = 0;
        String sql = """
                WITH bill_group_size AS (
                    SELECT
                        b.code_bill,
                        td.code_tour,
                        td.id AS id_tour_departure,
                        td.departure_date,
                        COUNT(tk.code_ticket) AS group_size
                    FROM tblBill b
                    JOIN tblTicket tk ON b.code_bill = tk.code_bill
                    JOIN tblTourDeparture td ON tk.id_tour_departure = td.id
                    GROUP BY b.code_bill, td.code_tour, td.id, td.departure_date
                ),
                bill_with_price AS (
                    SELECT
                        bgs.code_bill,
                        bgs.code_tour,
                        bgs.id_tour_departure,
                        bgs.group_size,
                        bgs.departure_date,
                        pt.price_per_person
                    FROM bill_group_size bgs
                    JOIN tblPriceTicket pt
                        ON pt.id_departure = bgs.id_tour_departure
                        AND bgs.group_size BETWEEN pt.min_group_size AND pt.max_group_size
                ),
                penalty_calc AS (
                    SELECT
                        pb.code_bill,
                        pb.cancellation_date,
                        pb.quantity_ticket,
                        bwp.id_tour_departure,
                        bwp.price_per_person,
                        bwp.departure_date,
                        DATEDIFF(bwp.departure_date, pb.cancellation_date) AS days_before_departure,
                        pb.quantity_ticket * bwp.price_per_person *
                        CASE
                            WHEN DATEDIFF(bwp.departure_date, pb.cancellation_date) >= 7 THEN 0.9
                            WHEN DATEDIFF(bwp.departure_date, pb.cancellation_date) >= 5 THEN 0.8
                            WHEN DATEDIFF(bwp.departure_date, pb.cancellation_date) >= 3 THEN 0.5
                            ELSE 0
                        END AS penalty_amount
                    FROM tblPenaltyBill pb
                    JOIN bill_with_price bwp ON pb.code_bill = bwp.code_bill
                )
                SELECT IFNULL(SUM(pc.penalty_amount), 0) AS total_refund
                FROM bill_with_price bwp
                JOIN tblTour t ON bwp.code_tour = t.code_tour AND t.destination = ?
                LEFT JOIN penalty_calc pc ON bwp.code_bill = pc.code_bill AND bwp.id_tour_departure = pc.id_tour_departure
                """;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, locationName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                totalRefund = rs.getDouble("total_refund");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalRefund;
    }

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/tour";
        String user = "root";
        String password = "123456";
        return DriverManager.getConnection(url, user, password);
    }
}