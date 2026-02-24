package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class ThongKeView extends JFrame {
    private JLabel lblTongGV;
    private JLabel lblTongNV;
    private JLabel lblTongQuyLuong;
    private JTable tableKhoa;
    private DefaultTableModel tableModelKhoa;
    private JTable tablePhongBan;
    private DefaultTableModel tableModelPhongBan;
    private JButton btnCapNhat;
    private JButton btnThoat;

    public ThongKeView() {
        initComponents();
    }

    private void initComponents() {
        setTitle("THỐNG KÊ NHÂN SỰ");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        getContentPane().setBackground(new Color(240, 240, 240));

        JPanel summaryPanel = createSummaryPanel();
        add(summaryPanel, BorderLayout.NORTH);

        JPanel tablesPanel = createTablesPanel();
        add(tablesPanel, BorderLayout.CENTER);

        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(1000, 600);
        setLocationRelativeTo(null);
    }

    private JPanel createSummaryPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 20, 0));
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                "THÔNG TIN TỔNG HỢP",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Arial", Font.BOLD, 14),
                Color.BLACK));
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(1000, 100));

        JPanel gvPanel = createSummaryItem("Tổng số giảng viên:", "0");
        panel.add(gvPanel);

        JPanel nvPanel = createSummaryItem("Tổng số nhân viên:", "0");
        panel.add(nvPanel);

        JPanel luongPanel = createSummaryItem("Tổng quỹ lương:", "0 VNĐ");
        panel.add(luongPanel);

        return panel;
    }

    private JPanel createSummaryItem(String title, String value) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        JLabel lblTitle = new JLabel(title, SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitle.setForeground(Color.BLACK);
        panel.add(lblTitle, BorderLayout.NORTH);

        JLabel lblValue = new JLabel(value, SwingConstants.CENTER);
        lblValue.setFont(new Font("Arial", Font.BOLD, 18));
        lblValue.setForeground(Color.BLACK);
        panel.add(lblValue, BorderLayout.CENTER);

        if (title.contains("giảng viên")) lblTongGV = lblValue;
        else if (title.contains("nhân viên")) lblTongNV = lblValue;
        else lblTongQuyLuong = lblValue;

        return panel;
    }

    private JPanel createTablesPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(new Color(240, 240, 240));

        JPanel khoaPanel = createTablePanel("THỐNG KÊ THEO KHOA", 
            new String[]{"Mã khoa", "Tên khoa", "Số GV", "Tổng quỹ lương"});
        tableKhoa = (JTable) ((JScrollPane) khoaPanel.getComponent(1)).getViewport().getView();
        tableModelKhoa = (DefaultTableModel) tableKhoa.getModel();
        panel.add(khoaPanel);

        JPanel pbPanel = createTablePanel("THỐNG KÊ THEO PHÒNG BAN", 
            new String[]{"Mã phòng", "Tên phòng ban", "Số NV", "Tổng quỹ lương"});
        tablePhongBan = (JTable) ((JScrollPane) pbPanel.getComponent(1)).getViewport().getView();
        tableModelPhongBan = (DefaultTableModel) tablePhongBan.getModel();
        panel.add(pbPanel);

        return panel;
    }

    private JPanel createTablePanel(String title, String[] columns) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                title,
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Arial", Font.BOLD, 13),
                Color.BLACK));
        panel.setBackground(Color.WHITE);

        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setForeground(Color.BLACK);
        table.setBackground(Color.WHITE);
        table.setGridColor(Color.BLACK);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        table.getTableHeader().setForeground(Color.BLACK);
        table.getTableHeader().setBackground(new Color(220, 220, 220));
        table.setRowHeight(25);
        table.setSelectionBackground(new Color(200, 200, 255));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scrollPane.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panel.setBackground(new Color(240, 240, 240));

        btnCapNhat = createButton("CẬP NHẬT");
        btnThoat = createButton("THOÁT");

        panel.add(btnCapNhat);
        panel.add(btnThoat);

        return panel;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 102, 204));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setPreferredSize(new Dimension(120, 40));
        
        return button;
    }

    // Getters
    public JLabel getLblTongGV() { return lblTongGV; }
    public JLabel getLblTongNV() { return lblTongNV; }
    public JLabel getLblTongQuyLuong() { return lblTongQuyLuong; }
    public JTable getTableKhoa() { return tableKhoa; }
    public DefaultTableModel getTableModelKhoa() { return tableModelKhoa; }
    public JTable getTablePhongBan() { return tablePhongBan; }
    public DefaultTableModel getTableModelPhongBan() { return tableModelPhongBan; }
    public JButton getBtnCapNhat() { return btnCapNhat; }
    public JButton getBtnThoat() { return btnThoat; }
}