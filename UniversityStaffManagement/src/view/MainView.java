package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class MainView extends JFrame {
    private JLabel lblTenTruong;
    private JLabel lblTongGV;
    private JLabel lblTongNV;
    private JLabel lblTongQuyLuong;
    private JButton btnQLGiangVien;
    private JButton btnQLNhanVien;
    private JButton btnQLKhoa;
    private JButton btnQLPhongBan;
    private JButton btnThongKe;
    private JButton btnThoat;

    public MainView() {
        initComponents();
    }

    private void initComponents() {
        setTitle("HỆ THỐNG QUẢN LÝ NHÂN SỰ TRƯỜNG ĐẠI HỌC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Nền xám nhạt
        getContentPane().setBackground(new Color(240, 240, 240));

        JPanel headerPanel = createHeaderPanel();
        add(headerPanel, BorderLayout.NORTH);

        JPanel dashboardPanel = createDashboardPanel();
        add(dashboardPanel, BorderLayout.CENTER);

        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(900, 600);
        setLocationRelativeTo(null);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.BLACK);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        lblTenTruong = new JLabel("ĐẠI HỌC QUỐC GIA HÀ NỘI");
        lblTenTruong.setFont(new Font("Arial", Font.BOLD, 28));
        lblTenTruong.setForeground(Color.WHITE);
        panel.add(lblTenTruong);

        return panel;
    }

    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                "THÔNG TIN TỔNG QUAN",
                TitledBorder.CENTER, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16),
                Color.BLACK));
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(createInfoPanel("Tổng số giảng viên:", "0"), gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(createInfoPanel("Tổng số nhân viên:", "0"), gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(createInfoPanel("Tổng quỹ lương toàn trường:", "0 VNĐ"), gbc);

        return panel;
    }

    private JPanel createInfoPanel(String title, String value) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JLabel lblTitle = new JLabel(title, SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitle.setForeground(Color.BLACK);
        panel.add(lblTitle, BorderLayout.NORTH);

        JLabel lblValue = new JLabel(value, SwingConstants.CENTER);
        lblValue.setFont(new Font("Arial", Font.BOLD, 20));
        lblValue.setForeground(new Color(0, 0, 150));
        panel.add(lblValue, BorderLayout.CENTER);

        if (title.contains("giảng viên")) lblTongGV = lblValue;
        else if (title.contains("nhân viên")) lblTongNV = lblValue;
        else lblTongQuyLuong = lblValue;

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 3, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        panel.setBackground(new Color(240, 240, 240));

        btnQLGiangVien = createButton("QUẢN LÝ GIẢNG VIÊN");
        btnQLNhanVien = createButton("QUẢN LÝ NHÂN VIÊN");
        btnQLKhoa = createButton("QUẢN LÝ KHOA");
        btnQLPhongBan = createButton("QUẢN LÝ PHÒNG BAN");
        btnThongKe = createButton("THỐNG KÊ");
        btnThoat = createButton("THOÁT");

        panel.add(btnQLGiangVien);
        panel.add(btnQLNhanVien);
        panel.add(btnQLKhoa);
        panel.add(btnQLPhongBan);
        panel.add(btnThongKe);
        panel.add(btnThoat);

        return panel;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        button.setPreferredSize(new Dimension(180, 50));
        
        // TẮT HOÀN TOÀN HIỆU ỨNG
        button.setRolloverEnabled(false);
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        button.setBorderPainted(true);
        
        return button;
    }

    public JLabel getLblTongGV() { return lblTongGV; }
    public JLabel getLblTongNV() { return lblTongNV; }
    public JLabel getLblTongQuyLuong() { return lblTongQuyLuong; }
    public JButton getBtnQLGiangVien() { return btnQLGiangVien; }
    public JButton getBtnQLNhanVien() { return btnQLNhanVien; }
    public JButton getBtnQLKhoa() { return btnQLKhoa; }
    public JButton getBtnQLPhongBan() { return btnQLPhongBan; }
    public JButton getBtnThongKe() { return btnThongKe; }
    public JButton getBtnThoat() { return btnThoat; }
}