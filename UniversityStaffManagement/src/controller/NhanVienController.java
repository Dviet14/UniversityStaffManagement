package controller;

import model.*;
import view.NhanVienView;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NhanVienController {
    private TruongDaiHoc truongDaiHoc;
    private NhanVienView view;
    private DefaultTableModel tableModel;

    public NhanVienController(TruongDaiHoc truongDaiHoc, NhanVienView view) {
        this.truongDaiHoc = truongDaiHoc;
        this.view = view;
        this.tableModel = view.getTableModel();
        
        initController();
        loadDuLieuMau();
        capNhatBang();
    }

    private void initController() {
        view.getBtnThem().addActionListener(e -> themNhanVien());
        view.getBtnSua().addActionListener(e -> suaNhanVien());
        view.getBtnXoa().addActionListener(e -> xoaNhanVien());
        view.getBtnTimKiem().addActionListener(e -> timKiemNhanVien());
        view.getBtnLamMoi().addActionListener(e -> lamMoi());
        view.getBtnSapXepTheoTen().addActionListener(e -> sapXepTheoTen());
        view.getBtnSapXepTheoLuong().addActionListener(e -> sapXepTheoLuong());
        
        view.getTable().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                hienThiThongTinChon();
            }
        });
    }

    private void loadDuLieuMau() {
        // Tạo dữ liệu mẫu cho các phòng ban nếu chưa có
        if (truongDaiHoc.getDanhSachPhongBan().isEmpty()) {
            PhongBan pbDaoTao = new PhongBan("PB001", "Đào tạo");
            PhongBan pbHanhChinh = new PhongBan("PB002", "Hành chính");
            PhongBan pbTaiVu = new PhongBan("PB003", "Tài vụ");
            
            truongDaiHoc.themPhongBan(pbDaoTao);
            truongDaiHoc.themPhongBan(pbHanhChinh);
            truongDaiHoc.themPhongBan(pbTaiVu);
        }

        // Tạo dữ liệu mẫu cho nhân viên
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            NhanVien nv1 = new NhanVien("NV001", "Phạm Thị D", sdf.parse("12/04/1988"), 
                    "Nữ", "Hà Nội", "0912345678", "Chuyên viên", 
                    truongDaiHoc.getDanhSachPhongBan().get(0), 2.5, 1000000);
            
            NhanVien nv2 = new NhanVien("NV002", "Hoàng Văn E", sdf.parse("25/09/1992"), 
                    "Nam", "Hồ Chí Minh", "0923456789", "Trưởng phòng", 
                    truongDaiHoc.getDanhSachPhongBan().get(1), 3.5, 2000000);
            
            NhanVien nv3 = new NhanVien("NV003", "Ngô Thị F", sdf.parse("18/11/1995"), 
                    "Nữ", "Đà Nẵng", "0934567890", "Kế toán", 
                    truongDaiHoc.getDanhSachPhongBan().get(2), 2.8, 1500000);
            
            truongDaiHoc.getDanhSachPhongBan().get(0).themNhanSu(nv1);
            truongDaiHoc.getDanhSachPhongBan().get(1).themNhanSu(nv2);
            truongDaiHoc.getDanhSachPhongBan().get(2).themNhanSu(nv3);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void themNhanVien() {
        try {
            String maNV = view.getTxtMaNV().getText().trim();
            if (maNV.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng nhập mã nhân viên!");
                return;
            }

            // Kiểm tra mã đã tồn tại
            for (PhongBan pb : truongDaiHoc.getDanhSachPhongBan()) {
                if (pb.timTheoMa(maNV) != null) {
                    JOptionPane.showMessageDialog(view, "Mã nhân viên đã tồn tại!");
                    return;
                }
            }

            String hoTen = view.getTxtHoTen().getText().trim();
            if (hoTen.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng nhập họ tên!");
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date ngaySinh = sdf.parse(view.getTxtNgaySinh().getText().trim());
            
            String gioiTinh = view.getCboGioiTinh().getSelectedItem().toString();
            String diaChi = view.getTxtDiaChi().getText().trim();
            String soDT = view.getTxtSoDT().getText().trim();
            
            if (!soDT.matches("\\d{10,11}")) {
                JOptionPane.showMessageDialog(view, "Số điện thoại không hợp lệ!");
                return;
            }

            String chucVu = view.getTxtChucVu().getText().trim();
            PhongBan phongBan = (PhongBan) view.getCboPhongBan().getSelectedItem();
            
            double heSoLuong;
            try {
                heSoLuong = Double.parseDouble(view.getTxtHeSoLuong().getText().trim());
                if (heSoLuong <= 0) {
                    JOptionPane.showMessageDialog(view, "Hệ số lương phải lớn hơn 0!");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Hệ số lương không hợp lệ!");
                return;
            }

            double phuCap;
            try {
                phuCap = Double.parseDouble(view.getTxtPhuCap().getText().trim());
                if (phuCap < 0) {
                    JOptionPane.showMessageDialog(view, "Phụ cấp không được âm!");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Phụ cấp không hợp lệ!");
                return;
            }

            NhanVien nv = new NhanVien(maNV, hoTen, ngaySinh, gioiTinh, diaChi, soDT,
                                       chucVu, phongBan, heSoLuong, phuCap);

            phongBan.themNhanSu(nv);
            capNhatBang();
            lamMoi();
            
            JOptionPane.showMessageDialog(view, "Thêm nhân viên thành công!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + ex.getMessage());
        }
    }

    private void suaNhanVien() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn nhân viên cần sửa!");
            return;
        }

        try {
            String maNV = view.getTxtMaNV().getText().trim();
            NhanVien nvCu = null;
            PhongBan pbCu = null;

            // Tìm nhân viên cũ
            for (PhongBan pb : truongDaiHoc.getDanhSachPhongBan()) {
                NhanSu ns = pb.timTheoMa(maNV);
                if (ns instanceof NhanVien) {
                    nvCu = (NhanVien) ns;
                    pbCu = pb;
                    break;
                }
            }

            if (nvCu == null) {
                JOptionPane.showMessageDialog(view, "Không tìm thấy nhân viên!");
                return;
            }

            // Xóa khỏi phòng ban cũ
            pbCu.xoaNhanSu(maNV);

            // Thêm vào phòng ban mới
            String hoTen = view.getTxtHoTen().getText().trim();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date ngaySinh = sdf.parse(view.getTxtNgaySinh().getText().trim());
            String gioiTinh = view.getCboGioiTinh().getSelectedItem().toString();
            String diaChi = view.getTxtDiaChi().getText().trim();
            String soDT = view.getTxtSoDT().getText().trim();
            String chucVu = view.getTxtChucVu().getText().trim();
            PhongBan phongBanMoi = (PhongBan) view.getCboPhongBan().getSelectedItem();
            double heSoLuong = Double.parseDouble(view.getTxtHeSoLuong().getText().trim());
            double phuCap = Double.parseDouble(view.getTxtPhuCap().getText().trim());

            NhanVien nvMoi = new NhanVien(maNV, hoTen, ngaySinh, gioiTinh, diaChi, soDT,
                                          chucVu, phongBanMoi, heSoLuong, phuCap);

            phongBanMoi.themNhanSu(nvMoi);
            capNhatBang();
            lamMoi();
            
            JOptionPane.showMessageDialog(view, "Cập nhật nhân viên thành công!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + ex.getMessage());
        }
    }

    private void xoaNhanVien() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn nhân viên cần xóa!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(view, 
                "Bạn có chắc chắn muốn xóa nhân viên này?", 
                "Xác nhận xóa", 
                JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            String maNV = view.getTable().getValueAt(selectedRow, 0).toString();
            
            for (PhongBan pb : truongDaiHoc.getDanhSachPhongBan()) {
                if (pb.xoaNhanSu(maNV)) {
                    capNhatBang();
                    lamMoi();
                    JOptionPane.showMessageDialog(view, "Xóa nhân viên thành công!");
                    return;
                }
            }
            
            JOptionPane.showMessageDialog(view, "Không tìm thấy nhân viên!");
        }
    }

    private void timKiemNhanVien() {
        String tuKhoa = JOptionPane.showInputDialog(view, "Nhập mã hoặc tên nhân viên cần tìm:");
        if (tuKhoa != null && !tuKhoa.trim().isEmpty()) {
            tableModel.setRowCount(0);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            for (PhongBan pb : truongDaiHoc.getDanhSachPhongBan()) {
                // Tìm theo mã
                NhanSu ns = pb.timTheoMa(tuKhoa);
                if (ns instanceof NhanVien) {
                    themVaoBang((NhanVien) ns, sdf);
                }
                
                // Tìm theo tên
                List<NhanSu> ketQua = pb.timTheoTen(tuKhoa);
                for (NhanSu n : ketQua) {
                    if (n instanceof NhanVien) {
                        themVaoBang((NhanVien) n, sdf);
                    }
                }
            }
        }
    }

    private void sapXepTheoTen() {
        for (PhongBan pb : truongDaiHoc.getDanhSachPhongBan()) {
            pb.sapXepTheoHoTen();
        }
        capNhatBang();
    }

    private void sapXepTheoLuong() {
        for (PhongBan pb : truongDaiHoc.getDanhSachPhongBan()) {
            pb.sapXepTheoLuong();
        }
        capNhatBang();
    }

    private void lamMoi() {
        view.getTxtMaNV().setText("");
        view.getTxtHoTen().setText("");
        view.getTxtNgaySinh().setText("");
        view.getCboGioiTinh().setSelectedIndex(0);
        view.getTxtDiaChi().setText("");
        view.getTxtSoDT().setText("");
        view.getTxtChucVu().setText("");
        view.getCboPhongBan().setSelectedIndex(0);
        view.getTxtHeSoLuong().setText("1.0");
        view.getTxtPhuCap().setText("0");
        capNhatBang();
    }

    private void hienThiThongTinChon() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow >= 0) {
            view.getTxtMaNV().setText(view.getTable().getValueAt(selectedRow, 0).toString());
            view.getTxtHoTen().setText(view.getTable().getValueAt(selectedRow, 1).toString());
            view.getTxtNgaySinh().setText(view.getTable().getValueAt(selectedRow, 2).toString());
            view.getTxtSoDT().setText(view.getTable().getValueAt(selectedRow, 3).toString());
        }
    }

    private void capNhatBang() {
        tableModel.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        for (PhongBan pb : truongDaiHoc.getDanhSachPhongBan()) {
            for (NhanSu ns : pb.getDanhSachNhanSu()) {
                if (ns instanceof NhanVien) {
                    themVaoBang((NhanVien) ns, sdf);
                }
            }
        }
    }

    private void themVaoBang(NhanVien nv, SimpleDateFormat sdf) {
        Object[] row = {
            nv.getMaNhanSu(),
            nv.getHoTen(),
            sdf.format(nv.getNgaySinh()),
            nv.getSoDienThoai(),
            nv.getChucVu(),
            nv.getPhongBan() != null ? nv.getPhongBan().getTenDonVi() : "",
            nv.getHeSoLuong(),
            nv.getPhuCap(),
            nv.tinhLuong()
        };
        tableModel.addRow(row);
    }
}