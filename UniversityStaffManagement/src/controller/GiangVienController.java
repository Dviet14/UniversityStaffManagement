package controller;

import model.*;
import view.GiangVienView;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GiangVienController {
    private TruongDaiHoc truongDaiHoc;
    private GiangVienView view;
    private DefaultTableModel tableModel;

    public GiangVienController(TruongDaiHoc truongDaiHoc, GiangVienView view) {
        this.truongDaiHoc = truongDaiHoc;
        this.view = view;
        this.tableModel = view.getTableModel();
        
        initController();
        loadDuLieuMau();
        capNhatBang();
    }

    private void initController() {
        view.getBtnThem().addActionListener(e -> themGiangVien());
        view.getBtnSua().addActionListener(e -> suaGiangVien());
        view.getBtnXoa().addActionListener(e -> xoaGiangVien());
        view.getBtnTimKiem().addActionListener(e -> timKiemGiangVien());
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
        // Tạo dữ liệu mẫu cho các khoa nếu chưa có
        if (truongDaiHoc.getDanhSachKhoa().isEmpty()) {
            Khoa khoaCNTT = new Khoa("K001", "Công nghệ thông tin");
            Khoa khoaQTKD = new Khoa("K002", "Quản trị kinh doanh");
            Khoa khoaNN = new Khoa("K003", "Ngoại ngữ");
            
            truongDaiHoc.themKhoa(khoaCNTT);
            truongDaiHoc.themKhoa(khoaQTKD);
            truongDaiHoc.themKhoa(khoaNN);
        }

        // Tạo dữ liệu mẫu cho giảng viên
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            GiangVien gv1 = new GiangVien("GV001", "Nguyễn Văn A", sdf.parse("15/05/1980"), 
                    "Nam", "Hà Nội", "0901234567", "Phó giáo sư", "Tiến sĩ", 
                    "Khoa học máy tính", truongDaiHoc.getDanhSachKhoa().get(0));
            gv1.setSoTietGiang(40);
            
            GiangVien gv2 = new GiangVien("GV002", "Trần Thị B", sdf.parse("20/08/1985"), 
                    "Nữ", "Hồ Chí Minh", "0902345678", "Giáo sư", "Tiến sĩ", 
                    "Quản trị kinh doanh", truongDaiHoc.getDanhSachKhoa().get(1));
            gv2.setSoTietGiang(35);
            
            GiangVien gv3 = new GiangVien("GV003", "Lê Văn C", sdf.parse("10/03/1990"), 
                    "Nam", "Đà Nẵng", "0903456789", "Thạc sĩ", "Thạc sĩ", 
                    "Tiếng Anh", truongDaiHoc.getDanhSachKhoa().get(2));
            gv3.setSoTietGiang(30);
            
            truongDaiHoc.getDanhSachKhoa().get(0).themNhanSu(gv1);
            truongDaiHoc.getDanhSachKhoa().get(1).themNhanSu(gv2);
            truongDaiHoc.getDanhSachKhoa().get(2).themNhanSu(gv3);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void themGiangVien() {
        try {
            // Validate dữ liệu nhập
            String maGV = view.getTxtMaGV().getText().trim();
            if (maGV.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng nhập mã giảng viên!");
                return;
            }

            // Kiểm tra mã đã tồn tại
            for (Khoa khoa : truongDaiHoc.getDanhSachKhoa()) {
                if (khoa.timTheoMa(maGV) != null) {
                    JOptionPane.showMessageDialog(view, "Mã giảng viên đã tồn tại!");
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

            String hocHam = view.getCboHocHam().getSelectedItem().toString();
            String hocVi = view.getCboHocVi().getSelectedItem().toString();
            String chuyenNganh = view.getTxtChuyenNganh().getText().trim();
            Khoa khoa = (Khoa) view.getCboKhoa().getSelectedItem();
            
            int soTiet = Integer.parseInt(view.getTxtSoTiet().getText().trim());
            if (soTiet < 0) {
                JOptionPane.showMessageDialog(view, "Số tiết giảng không hợp lệ!");
                return;
            }

            GiangVien gv = new GiangVien(maGV, hoTen, ngaySinh, gioiTinh, diaChi, soDT,
                                         hocHam, hocVi, chuyenNganh, khoa);
            gv.setSoTietGiang(soTiet);

            khoa.themNhanSu(gv);
            capNhatBang();
            lamMoi();
            
            JOptionPane.showMessageDialog(view, "Thêm giảng viên thành công!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + ex.getMessage());
        }
    }

    private void suaGiangVien() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn giảng viên cần sửa!");
            return;
        }

        try {
            String maGV = view.getTxtMaGV().getText().trim();
            GiangVien gvCu = null;
            Khoa khoaCu = null;

            // Tìm giảng viên cũ
            for (Khoa khoa : truongDaiHoc.getDanhSachKhoa()) {
                NhanSu ns = khoa.timTheoMa(maGV);
                if (ns instanceof GiangVien) {
                    gvCu = (GiangVien) ns;
                    khoaCu = khoa;
                    break;
                }
            }

            if (gvCu == null) {
                JOptionPane.showMessageDialog(view, "Không tìm thấy giảng viên!");
                return;
            }

            // Xóa khỏi khoa cũ
            khoaCu.xoaNhanSu(maGV);

            // Thêm vào khoa mới
            String hoTen = view.getTxtHoTen().getText().trim();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date ngaySinh = sdf.parse(view.getTxtNgaySinh().getText().trim());
            String gioiTinh = view.getCboGioiTinh().getSelectedItem().toString();
            String diaChi = view.getTxtDiaChi().getText().trim();
            String soDT = view.getTxtSoDT().getText().trim();
            String hocHam = view.getCboHocHam().getSelectedItem().toString();
            String hocVi = view.getCboHocVi().getSelectedItem().toString();
            String chuyenNganh = view.getTxtChuyenNganh().getText().trim();
            Khoa khoaMoi = (Khoa) view.getCboKhoa().getSelectedItem();
            int soTiet = Integer.parseInt(view.getTxtSoTiet().getText().trim());

            GiangVien gvMoi = new GiangVien(maGV, hoTen, ngaySinh, gioiTinh, diaChi, soDT,
                                           hocHam, hocVi, chuyenNganh, khoaMoi);
            gvMoi.setSoTietGiang(soTiet);

            khoaMoi.themNhanSu(gvMoi);
            capNhatBang();
            lamMoi();
            
            JOptionPane.showMessageDialog(view, "Cập nhật giảng viên thành công!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + ex.getMessage());
        }
    }

    private void xoaGiangVien() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn giảng viên cần xóa!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(view, 
                "Bạn có chắc chắn muốn xóa giảng viên này?", 
                "Xác nhận xóa", 
                JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            String maGV = view.getTable().getValueAt(selectedRow, 0).toString();
            
            for (Khoa khoa : truongDaiHoc.getDanhSachKhoa()) {
                if (khoa.xoaNhanSu(maGV)) {
                    capNhatBang();
                    lamMoi();
                    JOptionPane.showMessageDialog(view, "Xóa giảng viên thành công!");
                    return;
                }
            }
            
            JOptionPane.showMessageDialog(view, "Không tìm thấy giảng viên!");
        }
    }

    private void timKiemGiangVien() {
        String tuKhoa = JOptionPane.showInputDialog(view, "Nhập mã hoặc tên giảng viên cần tìm:");
        if (tuKhoa != null && !tuKhoa.trim().isEmpty()) {
            tableModel.setRowCount(0);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            
            for (Khoa khoa : truongDaiHoc.getDanhSachKhoa()) {
                // Tìm theo mã
                NhanSu ns = khoa.timTheoMa(tuKhoa);
                if (ns instanceof GiangVien) {
                    themVaoBang((GiangVien) ns, sdf);
                }
                
                // Tìm theo tên
                List<NhanSu> ketQua = khoa.timTheoTen(tuKhoa);
                for (NhanSu n : ketQua) {
                    if (n instanceof GiangVien) {
                        themVaoBang((GiangVien) n, sdf);
                    }
                }
            }
        }
    }

    private void sapXepTheoTen() {
        for (Khoa khoa : truongDaiHoc.getDanhSachKhoa()) {
            khoa.sapXepTheoHoTen();
        }
        capNhatBang();
    }

    private void sapXepTheoLuong() {
        for (Khoa khoa : truongDaiHoc.getDanhSachKhoa()) {
            khoa.sapXepTheoLuong();
        }
        capNhatBang();
    }

    private void lamMoi() {
        view.getTxtMaGV().setText("");
        view.getTxtHoTen().setText("");
        view.getTxtNgaySinh().setText("");
        view.getCboGioiTinh().setSelectedIndex(0);
        view.getTxtDiaChi().setText("");
        view.getTxtSoDT().setText("");
        view.getCboHocHam().setSelectedIndex(0);
        view.getCboHocVi().setSelectedIndex(0);
        view.getTxtChuyenNganh().setText("");
        view.getCboKhoa().setSelectedIndex(0);
        view.getTxtSoTiet().setText("0");
        capNhatBang();
    }

    private void hienThiThongTinChon() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow >= 0) {
            view.getTxtMaGV().setText(view.getTable().getValueAt(selectedRow, 0).toString());
            view.getTxtHoTen().setText(view.getTable().getValueAt(selectedRow, 1).toString());
            view.getTxtNgaySinh().setText(view.getTable().getValueAt(selectedRow, 2).toString());
            view.getTxtSoDT().setText(view.getTable().getValueAt(selectedRow, 3).toString());
            // Có thể set thêm các thông tin khác nếu cần
        }
    }

    private void capNhatBang() {
        tableModel.setRowCount(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        for (Khoa khoa : truongDaiHoc.getDanhSachKhoa()) {
            for (NhanSu ns : khoa.getDanhSachNhanSu()) {
                if (ns instanceof GiangVien) {
                    themVaoBang((GiangVien) ns, sdf);
                }
            }
        }
    }

    private void themVaoBang(GiangVien gv, SimpleDateFormat sdf) {
        Object[] row = {
            gv.getMaNhanSu(),
            gv.getHoTen(),
            sdf.format(gv.getNgaySinh()),
            gv.getSoDienThoai(),
            gv.getHocHam(),
            gv.getHocVi(),
            gv.getChuyenNganh(),
            gv.getKhoaCongTac() != null ? gv.getKhoaCongTac().getTenDonVi() : "",
            gv.getSoTietGiang(),
            gv.tinhLuong()
        };
        tableModel.addRow(row);
    }
}