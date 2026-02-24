package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class NhanSu {
    protected String maNhanSu;
    protected String hoTen;
    protected Date ngaySinh;
    protected String gioiTinh;
    protected String diaChi;
    protected String soDienThoai;
    protected double luongCoBan;

    public NhanSu() {
        this.luongCoBan = 5000000; // Lương cơ bản mặc định
    }

    public NhanSu(String maNhanSu, String hoTen, Date ngaySinh, String gioiTinh, 
                  String diaChi, String soDienThoai) {
        this.maNhanSu = maNhanSu;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.luongCoBan = 5000000;
    }

    // Getters and Setters
    public String getMaNhanSu() {
        return maNhanSu;
    }

    public void setMaNhanSu(String maNhanSu) {
        this.maNhanSu = maNhanSu;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public double getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public void nhapThongTin(String maNhanSu, String hoTen, Date ngaySinh, 
                             String gioiTinh, String diaChi, String soDienThoai) {
        this.maNhanSu = maNhanSu;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
    }

    public String hienThiThongTin() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("Mã: %s | Họ tên: %s | Ngày sinh: %s | Giới tính: %s | Địa chỉ: %s | SĐT: %s",
                maNhanSu, hoTen, sdf.format(ngaySinh), gioiTinh, diaChi, soDienThoai);
    }

    public abstract double tinhLuong();

    @Override
    public String toString() {
        return hienThiThongTin();
    }
}