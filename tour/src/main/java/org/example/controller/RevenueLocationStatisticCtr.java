package org.example.controller;

import org.example.model.RevenueLocationStatistic;
import org.example.view.RevenueLocationStatisticFrm;
import org.example.view.HomeFrm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import java.util.Date;
import javax.swing.JOptionPane;

public class RevenueLocationStatisticCtr {
    private RevenueLocationStatisticFrm frm;
    private HomeFrm homeFrm;
    private Connection con;

    public RevenueLocationStatisticCtr(HomeFrm homeFrm) {
        this.homeFrm = homeFrm;
        frm = new RevenueLocationStatisticFrm();
        frm.addStatisticListener(new StatisticListener());
        frm.addBackListener(e -> {
            frm.dispose();
            homeFrm.setVisible(true);
        });
        frm.setVisible(true);
    }

    // Inner class xử lý sự kiện thống kê
    class StatisticListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Lấy dữ liệu từ view
            Date start = frm.getStartDate();
            Date end = frm.getEndDate();
            
            // Kiểm tra ngày hợp lệ
            if (start == null || end == null) {
                JOptionPane.showMessageDialog(frm, "Vui lòng chọn đầy đủ ngày bắt đầu và kết thúc!");
                return;
            }
            
            if (start.after(end)) {
                JOptionPane.showMessageDialog(frm, "Ngày bắt đầu phải trước ngày kết thúc!");
                return;
            }

            // Lấy dữ liệu thống kê
            List<RevenueLocationStatistic> data = getStatisticData(start, end);

            // Kiểm tra nếu không có dữ liệu
            if (data.isEmpty()) {
                frm.setTableData(new ArrayList<>()); // Xóa dữ liệu cũ trong bảng
                frm.showSummary("<html><font color='red'>Không có dữ liệu thống kê trong khoảng thời gian này!</font></html>");
                return;
            }

            // Tính tổng
            int totalTours = data.stream().mapToInt(RevenueLocationStatistic::getTotalToursByLocation).sum();
            int totalGuests = data.stream().mapToInt(RevenueLocationStatistic::getTotalGuestsByLocation).sum();
            double totalRevenue = data.stream().mapToDouble(RevenueLocationStatistic::getTotalRevenue).sum();

            // Sắp xếp giảm dần theo doanh thu
            data.sort((a, b) -> Double.compare(b.getTotalRevenue(), a.getTotalRevenue()));

            // Hiển thị lên view
            frm.setTableData(data);
            frm.showSummary(String.format(
                "<html>Thông kê doanh thu theo địa điểm<br>Tổng số lượng Tour đến địa điểm: %d tour.<br>Tổng số khách đến địa điểm: %d người.<br>Tổng doanh thu: %,.0f đồng.</html>",
                totalTours, totalGuests, totalRevenue
            ));
        }
    }

    private List<RevenueLocationStatistic> getStatisticData(Date start, Date end) {
        List<RevenueLocationStatistic> list = new ArrayList<>();
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
                    WHERE b.bill_date BETWEEN ? AND ?
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
                    WHERE pb.cancellation_date BETWEEN ? AND ?
                )
                SELECT 
                    t.code_tour,
                    t.destination,
                    COUNT(DISTINCT bwp.id_tour_departure) AS total_tours,
                    SUM(bwp.group_size) AS total_guests,
                    SUM(bwp.price_per_person * bwp.group_size) - IFNULL(SUM(pc.penalty_amount), 0) AS total_revenue
                FROM bill_with_price bwp
                JOIN tblTour t ON bwp.code_tour = t.code_tour
                LEFT JOIN penalty_calc pc ON bwp.code_bill = pc.code_bill 
                    AND bwp.id_tour_departure = pc.id_tour_departure
                GROUP BY 
                    t.code_tour, 
                    t.destination
                ORDER BY total_revenue DESC
                """;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(start.getTime()));
            ps.setDate(2, new java.sql.Date(end.getTime()));
            ps.setDate(3, new java.sql.Date(start.getTime()));
            ps.setDate(4, new java.sql.Date(end.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RevenueLocationStatistic stat = new RevenueLocationStatistic(
                        rs.getString("code_tour"),
                        rs.getString("destination"),
                        "",
                        rs.getString("destination"),
                        "",
                        rs.getInt("total_guests"),
                        rs.getDouble("total_revenue")
                );
                stat.setTotalToursByLocation(rs.getInt("total_tours"));
                list.add(stat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/tour";
        String user = "root";
        String password = "123456";
        return DriverManager.getConnection(url, user, password);
    }
}