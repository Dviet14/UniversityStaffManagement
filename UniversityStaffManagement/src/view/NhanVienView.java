package view;

import model.PhongBan;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class NhanVienView extends JFrame {
    private JTextField txtMaNV, txtHoTen, txtNgaySinh, txtDiaChi, txtSoDT, txtChucVu, txtHeSoLuong, txtPhuCap;
    private JComboBox<String> cboGioiTinh;
    private JComboBox<PhongBan> cboPhongBan;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnThem, btnSua, btnXoa, btnTimKiem, btnLamMoi, btnSapXepTheoTen, btnSapXepTheoLuong;

    public NhanVienView() {
        initComponents();
    }

    private void initComponents() {
        setTitle("QUẢN LÝ NHÂN VIÊN");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        getContentPane().setBackground(new Color(240, 240, 240));

        add(createInputPanel(), BorderLayout.NORTH);
        add(createButtonPanel(), BorderLayout.CENTER);
        add(createTablePanel(), BorderLayout.SOUTH);

        setSize(1200, 800);
        setLocationRelativeTo(null);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                "THÔNG TIN NHÂN VIÊN",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Arial", Font.BOLD, 14),
                Color.BLACK));
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Hàng 1
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(createLabel("Mã nhân viên:"), gbc);
        gbc.gridx = 1;
        txtMaNV = createTextField();
        panel.add(txtMaNV, gbc);

        gbc.gridx = 2;
        panel.add(createLabel("Họ tên:"), gbc);
        gbc.gridx = 3;
        txtHoTen = createTextField();
        panel.add(txtHoTen, gbc);

        // Hàng 2
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(createLabel("Ngày sinh (dd/MM/yyyy):"), gbc);
        gbc.gridx = 1;
        txtNgaySinh = createTextField();
        panel.add(txtNgaySinh, gbc);

        gbc.gridx = 2;
        panel.add(createLabel("Giới tính:"), gbc);
        gbc.gridx = 3;
        cboGioiTinh = createComboBox(new String[]{"Nam", "Nữ", "Khác"});
        panel.add(cboGioiTinh, gbc);

        // Hàng 3
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(createLabel("Địa chỉ:"), gbc);
        gbc.gridx = 1;
        txtDiaChi = createTextField();
        panel.add(txtDiaChi, gbc);

        gbc.gridx = 2;
        panel.add(createLabel("Số điện thoại:"), gbc);
        gbc.gridx = 3;
        txtSoDT = createTextField();
        panel.add(txtSoDT, gbc);

        // Hàng 4
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(createLabel("Chức vụ:"), gbc);
        gbc.gridx = 1;
        txtChucVu = createTextField();
        panel.add(txtChucVu, gbc);

        gbc.gridx = 2;
        panel.add(createLabel("Phòng ban:"), gbc);
        gbc.gridx = 3;
        cboPhongBan = new JComboBox<>();
        cboPhongBan.setBackground(Color.WHITE);
        cboPhongBan.setForeground(Color.BLACK);
        cboPhongBan.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(cboPhongBan, gbc);

        // Hàng 5
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(createLabel("Hệ số lương:"), gbc);
        gbc.gridx = 1;
        txtHeSoLuong = createTextField("1.0");
        panel.add(txtHeSoLuong, gbc);

        gbc.gridx = 2;
        panel.add(createLabel("Phụ cấp:"), gbc);
        gbc.gridx = 3;
        txtPhuCap = createTextField("0");
        panel.add(txtPhuCap, gbc);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setBackground(new Color(240, 240, 240));

        btnThem = createButton("THÊM");
        btnSua = createButton("SỬA");
        btnXoa = createButton("XÓA");
        btnTimKiem = createButton("TÌM KIẾM");
        btnLamMoi = createButton("LÀM MỚI");
        btnSapXepTheoTen = createButton("SẮP XẾP THEO TÊN");
        btnSapXepTheoLuong = createButton("SẮP XẾP THEO LƯƠNG");

        panel.add(btnThem); 
        panel.add(btnSua); 
        panel.add(btnXoa);
        panel.add(btnTimKiem); 
        panel.add(btnLamMoi);
        panel.add(btnSapXepTheoTen); 
        panel.add(btnSapXepTheoLuong);

        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                "DANH SÁCH NHÂN VIÊN",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
                new Font("Arial", Font.BOLD, 14),
                Color.BLACK));
        panel.setBackground(Color.WHITE);

        String[] columns = {"Mã NV", "Họ tên", "Ngày sinh", "SĐT", "Chức vụ", 
                           "Phòng ban", "Hệ số lương", "Phụ cấp", "Lương"};
        tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
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
        scrollPane.setPreferredSize(new Dimension(1150, 250));
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 13));
        label.setForeground(Color.BLACK);
        return label;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField(15);
        field.setFont(new Font("Arial", Font.PLAIN, 13));
        field.setForeground(Color.BLACK);
        field.setBackground(Color.WHITE);
        field.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return field;
    }

    private JTextField createTextField(String text) {
        JTextField field = createTextField();
        field.setText(text);
        return field;
    }

    private JComboBox<String> createComboBox(String[] items) {
        JComboBox<String> combo = new JComboBox<>(items);
        combo.setFont(new Font("Arial", Font.PLAIN, 13));
        combo.setForeground(Color.BLACK);
        combo.setBackground(Color.WHITE);
        combo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return combo;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 102, 204));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setPreferredSize(new Dimension(130, 35));
        
        return button;
    }

    // Getters
    public JTextField getTxtMaNV() { return txtMaNV; }
    public JTextField getTxtHoTen() { return txtHoTen; }
    public JTextField getTxtNgaySinh() { return txtNgaySinh; }
    public JComboBox<String> getCboGioiTinh() { return cboGioiTinh; }
    public JTextField getTxtDiaChi() { return txtDiaChi; }
    public JTextField getTxtSoDT() { return txtSoDT; }
    public JTextField getTxtChucVu() { return txtChucVu; }
    public JComboBox<PhongBan> getCboPhongBan() { return cboPhongBan; }
    public JTextField getTxtHeSoLuong() { return txtHeSoLuong; }
    public JTextField getTxtPhuCap() { return txtPhuCap; }
    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tableModel; }
    public JButton getBtnThem() { return btnThem; }
    public JButton getBtnSua() { return btnSua; }
    public JButton getBtnXoa() { return btnXoa; }
    public JButton getBtnTimKiem() { return btnTimKiem; }
    public JButton getBtnLamMoi() { return btnLamMoi; }
    public JButton getBtnSapXepTheoTen() { return btnSapXepTheoTen; }
    public JButton getBtnSapXepTheoLuong() { return btnSapXepTheoLuong; }
}