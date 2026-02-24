package model;

import java.util.ArrayList;
import java.util.List;

public class TruongDaiHoc {
    private String tenTruong;
    private List<Khoa> danhSachKhoa;
    private List<PhongBan> danhSachPhongBan;

    public TruongDaiHoc() {
        this.danhSachKhoa = new ArrayList<>();
        this.danhSachPhongBan = new ArrayList<>();
    }

    public TruongDaiHoc(String tenTruong) {
        this.tenTruong = tenTruong;
        this.danhSachKhoa = new ArrayList<>();
        this.danhSachPhongBan = new ArrayList<>();
    }

    // Getters and Setters
    public String getTenTruong() {
        return tenTruong;
    }

    public void setTenTruong(String tenTruong) {
        this.tenTruong = tenTruong;
    }

    public List<Khoa> getDanhSachKhoa() {
        return danhSachKhoa;
    }

    public List<PhongBan> getDanhSachPhongBan() {
        return danhSachPhongBan;
    }

    public void themKhoa(Khoa khoa) {
        danhSachKhoa.add(khoa);
    }

    public void themPhongBan(PhongBan phongBan) {
        danhSachPhongBan.add(phongBan);
    }

    public int thongKeTongGiangVien() {
        int tong = 0;
        for (Khoa khoa : danhSachKhoa) {
            tong += khoa.getDanhSachNhanSu().size();
        }
        return tong;
    }

    public int thongKeTongNhanVien() {
        int tong = 0;
        for (PhongBan phongBan : danhSachPhongBan) {
            tong += phongBan.getDanhSachNhanSu().size();
        }
        return tong;
    }

    public double tinhTongQuyLuongToanTruong() {
        double tong = 0;
        for (Khoa khoa : danhSachKhoa) {
            tong += khoa.tinhTongLuong();
        }
        for (PhongBan phongBan : danhSachPhongBan) {
            tong += phongBan.tinhTongLuong();
        }
        return tong;
    }

    public List<NhanSu> timNhanSuToanTruong(String tuKhoa) {
        List<NhanSu> ketQua = new ArrayList<>();
        
        // Tìm trong các khoa
        for (Khoa khoa : danhSachKhoa) {
            ketQua.addAll(khoa.timTheoTen(tuKhoa));
            NhanSu ns = khoa.timTheoMa(tuKhoa);
            if (ns != null) {
                ketQua.add(ns);
            }
        }
        
        // Tìm trong các phòng ban
        for (PhongBan phongBan : danhSachPhongBan) {
            ketQua.addAll(phongBan.timTheoTen(tuKhoa));
            NhanSu ns = phongBan.timTheoMa(tuKhoa);
            if (ns != null) {
                ketQua.add(ns);
            }
        }
        
        return ketQua;
    }

    public double tinhTongQuyLuongTheoKhoa(String maKhoa) {
        for (Khoa khoa : danhSachKhoa) {
            if (khoa.getMaDonVi().equals(maKhoa)) {
                return khoa.tinhTongLuong();
            }
        }
        return 0;
    }

    public double tinhTongQuyLuongTheoPhongBan(String maPhongBan) {
        for (PhongBan phongBan : danhSachPhongBan) {
            if (phongBan.getMaDonVi().equals(maPhongBan)) {
                return phongBan.tinhTongLuong();
            }
        }
        return 0;
    }
}