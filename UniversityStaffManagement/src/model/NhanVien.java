package model;

import java.util.Date;

public class NhanVien extends NhanSu {
    private String chucVu;
    private PhongBan phongBan;
    private int soNgayCong;
    private double heSoLuong;
    private double phuCap;

    public NhanVien() {
        super();
        this.heSoLuong = 1.0;
    }

    public NhanVien(String maNhanSu, String hoTen, Date ngaySinh, String gioiTinh,
                    String diaChi, String soDienThoai, String chucVu, 
                    PhongBan phongBan, double heSoLuong, double phuCap) {
        super(maNhanSu, hoTen, ngaySinh, gioiTinh, diaChi, soDienThoai);
        this.chucVu = chucVu;
        this.phongBan = phongBan;
        this.heSoLuong = heSoLuong;
        this.phuCap = phuCap;
        this.soNgayCong = 0;
    }

    // Getters and Setters
    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public PhongBan getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(PhongBan phongBan) {
        this.phongBan = phongBan;
    }

    public int getSoNgayCong() {
        return soNgayCong;
    }

    public void setSoNgayCong(int soNgayCong) {
        this.soNgayCong = soNgayCong;
    }

    public double getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(double heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    public double getPhuCap() {
        return phuCap;
    }

    public void setPhuCap(double phuCap) {
        this.phuCap = phuCap;
    }

    @Override
    public double tinhLuong() {
        return luongCoBan * heSoLuong + phuCap;
    }

    @Override
    public String hienThiThongTin() {
        return super.hienThiThongTin() +
               String.format(" | Chức vụ: %s | Phòng ban: %s | Hệ số lương: %.2f | Phụ cấp: %.0f | Lương: %.0f",
                       chucVu, phongBan != null ? phongBan.getTenDonVi() : "Chưa phân công",
                       heSoLuong, phuCap, tinhLuong());
    }
}