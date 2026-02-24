package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class PhongBanView extends JFrame {
    private JTextField txtMaPhong;
    private JTextField txtTenPhong;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnLamMoi;

    public PhongBanView() {
        initComponents();
    }

    private void initComponents() {
        setTitle("QUẢN LÝ PHÒNG BAN");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        getContentPane().setBackground(new Color(240, 240, 240));

        add(createInputPanel(), BorderLayout.NORTH);
        add(createButtonPanel(), BorderLayout.CENTER);
        add(createTablePanel(), BorderLayout.SOUTH);

        setSize(800, 600);
        setLocationRelativeTo(null);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                "THÔNG TIN PHÒNG BAN",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Arial", Font.BOLD, 14),
                Color.BLACK));
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        JLabel lblMaPhong = new JLabel("Mã phòng:");
        lblMaPhong.setFont(new Font("Arial", Font.PLAIN, 13));
        lblMaPhong.setForeground(Color.BLACK);
        panel.add(lblMaPhong, gbc);

        gbc.gridx = 1;
        txtMaPhong = new JTextField(15);
        txtMaPhong.setFont(new Font("Arial", Font.PLAIN, 13));
        txtMaPhong.setForeground(Color.BLACK);
        txtMaPhong.setBackground(Color.WHITE);
        txtMaPhong.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(txtMaPhong, gbc);

        gbc.gridx = 2;
        JLabel lblTenPhong = new JLabel("Tên phòng:");
        lblTenPhong.setFont(new Font("Arial", Font.PLAIN, 13));
        lblTenPhong.setForeground(Color.BLACK);
        panel.add(lblTenPhong, gbc);

        gbc.gridx = 3;
        txtTenPhong = new JTextField(15);
        txtTenPhong.setFont(new Font("Arial", Font.PLAIN, 13));
        txtTenPhong.setForeground(Color.BLACK);
        txtTenPhong.setBackground(Color.WHITE);
        txtTenPhong.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(txtTenPhong, gbc);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setBackground(new Color(240, 240, 240));

        btnThem = createButton("THÊM");
        btnSua = createButton("SỬA");
        btnXoa = createButton("XÓA");
        btnLamMoi = createButton("LÀM MỚI");

        panel.add(btnThem);
        panel.add(btnSua);
        panel.add(btnXoa);
        panel.add(btnLamMoi);

        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                "DANH SÁCH PHÒNG BAN",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Arial", Font.BOLD, 14),
                Color.BLACK));
        panel.setBackground(Color.WHITE);

        String[] columns = {"Mã phòng", "Tên phòng ban"};
        
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setForeground(Color.BLACK);
        table.setBackground(Color.WHITE);
        table.setGridColor(Color.BLACK);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        table.getTableHeader().setForeground(Color.BLACK);
        table.getTableHeader().setBackground(new Color(220, 220, 220));
        table.setRowHeight(30);
        table.setSelectionBackground(new Color(200, 200, 255));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        scrollPane.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setPreferredSize(new Dimension(750, 300));
        
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 102, 204));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setPreferredSize(new Dimension(100, 35));
        
        return button;
    }

    // Getters
    public JTextField getTxtMaPhong() { return txtMaPhong; }
    public JTextField getTxtTenPhong() { return txtTenPhong; }
    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tableModel; }
    public JButton getBtnThem() { return btnThem; }
    public JButton getBtnSua() { return btnSua; }
    public JButton getBtnXoa() { return btnXoa; }
    public JButton getBtnLamMoi() { return btnLamMoi; }
}