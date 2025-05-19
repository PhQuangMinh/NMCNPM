package org.example.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import org.example.model.Bill;

public class LocationBillDetailFrm extends JFrame {
    private JButton btnBack;
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel lblInfo;

    public LocationBillDetailFrm() {
        super("Chi tiết hóa đơn theo địa điểm");
        setLayout(new BorderLayout(10, 10));

        // Nút quay lại
        btnBack = new JButton("Quay lại");
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(btnBack);
        add(topPanel, BorderLayout.NORTH);

        // Thông tin địa điểm
        lblInfo = new JLabel();
        lblInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lblInfo.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblInfo, BorderLayout.CENTER);

        // Bảng hóa đơn
        String[] columns = {"STT", "Tên khách", "Ngày giờ xuất phát", "Tên tour", "Tổng số khách", "Tổng số tiền"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        
        // Điều chỉnh độ rộng các cột
        table.getColumnModel().getColumn(0).setPreferredWidth(50);  // STT
        table.getColumnModel().getColumn(1).setPreferredWidth(150); // Tên khách
        table.getColumnModel().getColumn(2).setPreferredWidth(150); // Ngày giờ xuất phát
        table.getColumnModel().getColumn(3).setPreferredWidth(200); // Tên tour
        table.getColumnModel().getColumn(4).setPreferredWidth(100); // Tổng số khách
        table.getColumnModel().getColumn(5).setPreferredWidth(150); // Tổng số tiền
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 300));
        add(scrollPane, BorderLayout.SOUTH);

        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void setInfoText(String text) {
        lblInfo.setText(text);
    }

    public void setTableData(List<Bill> bills) {
        tableModel.setRowCount(0);
        int stt = 1;
        for (Bill bill : bills) {
            tableModel.addRow(new Object[]{
                stt++,
                bill.getRepresentativeName(),
                bill.getBillDate() != null ? new java.text.SimpleDateFormat("HH:mm dd/MM/yyyy").format(bill.getBillDate()) : "",
                bill.getTourInfo(),
                bill.getQuantityTicket(),
                String.format("%,.0f đồng", bill.getTotalPrice())
            });
        }
    }

    public void addBackListener(ActionListener listener) {
        btnBack.addActionListener(listener);
    }
}