package org.example.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Date;
import java.util.Properties;
import java.awt.event.MouseAdapter;

import org.example.controller.LocationBillDetailCtr;
import org.example.model.RevenueLocationStatistic;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.impl.DateComponentFormatter;

public class RevenueLocationStatisticFrm extends JFrame {
    private JDatePickerImpl datePickerStart, datePickerEnd;
    private JButton btnStatistic, btnBack;
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel lblSummary;

    public RevenueLocationStatisticFrm() {
        super("Thống kê doanh thu theo địa điểm");
        setLayout(new BorderLayout(10, 10));

        // Panel chứa các điều khiển ngày tháng và nút
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Ngày bắt đầu:"));
        
        UtilDateModel modelStart = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Hôm nay");
        p.put("text.month", "Tháng");
        p.put("text.year", "Năm");
        JDatePanelImpl datePanelStart = new JDatePanelImpl(modelStart, p);
        datePickerStart = new JDatePickerImpl(datePanelStart, new DateComponentFormatter());
        topPanel.add(datePickerStart);

        topPanel.add(new JLabel("Ngày kết thúc:"));
        
        UtilDateModel modelEnd = new UtilDateModel();
        JDatePanelImpl datePanelEnd = new JDatePanelImpl(modelEnd, p);
        datePickerEnd = new JDatePickerImpl(datePanelEnd, new DateComponentFormatter());
        topPanel.add(datePickerEnd);

        btnStatistic = new JButton("Thống kê");
        topPanel.add(btnStatistic);
        btnBack = new JButton("Quay lại");
        topPanel.add(btnBack);

        add(topPanel, BorderLayout.NORTH);

        // Panel chứa kết quả thống kê
        JPanel summaryPanel = new JPanel(new BorderLayout());
        lblSummary = new JLabel("Thống kê:");
        lblSummary.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        summaryPanel.add(lblSummary, BorderLayout.CENTER);
        add(summaryPanel, BorderLayout.CENTER);

        // Panel chứa bảng dữ liệu
        JPanel tablePanel = new JPanel(new BorderLayout());
        String[] columns = {"STT", "Mã tour", "Tên", "Số lượng tour đến địa điểm", "Tổng số khách đến địa điểm", "Tổng doanh thu"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        
        // Điều chỉnh độ rộng các cột
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);  // STT
        columnModel.getColumn(1).setPreferredWidth(100); // Mã tour
        columnModel.getColumn(2).setPreferredWidth(150); // Tên
        columnModel.getColumn(3).setPreferredWidth(150); // Số lượng tour
        columnModel.getColumn(4).setPreferredWidth(150); // Tổng số khách
        columnModel.getColumn(5).setPreferredWidth(150); // Tổng doanh thu
        
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
        add(tablePanel, BorderLayout.SOUTH);

        // Thêm sự kiện click vào dòng trong bảng
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Double click
                    int row = table.getSelectedRow();
                    if (row != -1) {
                        String locationName = (String) table.getValueAt(row, 2); // Lấy tên địa điểm từ cột thứ 3
                        new LocationBillDetailCtr(locationName, RevenueLocationStatisticFrm.this);
                    }
                }
            }
        });

        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public Date getStartDate() { 
        return (Date) datePickerStart.getModel().getValue(); 
    }
    
    public Date getEndDate() { 
        return (Date) datePickerEnd.getModel().getValue(); 
    }
    
    public void addStatisticListener(ActionListener listener) { 
        btnStatistic.addActionListener(listener); 
    }
    
    public void addBackListener(ActionListener listener) { 
        btnBack.addActionListener(listener); 
    }

    public void showSummary(String summary) { 
        lblSummary.setText(summary); 
    }

    public void setTableData(List<RevenueLocationStatistic> data) {
        tableModel.setRowCount(0);
        int stt = 1;
        for (RevenueLocationStatistic stat : data) {
            tableModel.addRow(new Object[]{
                stt++,
                stat.getCodeTour(),
                stat.getTourName(),
                stat.getTotalToursByLocation(),
                stat.getTotalGuestsByLocation(),
                String.format("%,.0f đồng", stat.getTotalRevenue())
            });
        }
    }
}