package org.example.view;

import org.example.controller.RevenueLocationStatisticCtr;

import javax.swing.*;
import java.awt.*;

public class HomeFrm extends JFrame {
    private final JButton btnRevenueByLocation;
    private JButton btnRevenueByTour;
    private JButton btnTourManager;

    public HomeFrm() {
        super("Trang chủ quản lý tour");
        btnRevenueByLocation = new JButton("Thống kê doanh thu theo địa điểm");
        btnRevenueByTour = new JButton("Thống kê tour theo doanh thu");
        btnTourManager = new JButton("Quản lý tour");

        setLayout(new GridLayout(3, 1, 10, 10));
        add(btnRevenueByLocation);
        add(btnRevenueByTour);
        add(btnTourManager);

        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Thêm xử lý sự kiện trực tiếp
        btnRevenueByLocation.addActionListener(e -> openRevenueLocationStatisticFrm());
    }

    private void openRevenueLocationStatisticFrm() {
        new RevenueLocationStatisticCtr(this);
        this.setVisible(false);
    }
}