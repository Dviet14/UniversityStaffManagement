package model;

import java.util.Date;

public class GiangVien extends NhanSu {
    private String hocHam;
    private String hocVi;
    private String chuyenNganh;
    private int soTietGiang;
    private double tienMotTiet;
    private Khoa khoaCongTac;

    public GiangVien() {
        super();
        this.tienMotTiet = 150000; // Giá mỗi tiết mặc định
    }

    public GiangVien(String maNhanSu, String hoTen, Date ngaySinh, String gioiTinh,
                     String diaChi, String soDienThoai, String hocHam, String hocVi,
                     String chuyenNganh, Khoa khoaCongTac) {
        super(maNhanSu, hoTen, ngaySinh, gioiTinh, diaChi, soDienThoai);
        this.hocHam = hocHam;
        this.hocVi = hocVi;
        this.chuyenNganh = chuyenNganh;
        this.khoaCongTac = khoaCongTac;
        this.tienMotTiet = 150000;
        this.soTietGiang = 0;
    }

    // Getters and Setters
    public String getHocHam() {
        return hocHam;
    }

    public void setHocHam(String hocHam) {
        this.hocHam = hocHam;
    }

    public String getHocVi() {
        return hocVi;
    }

    public void setHocVi(String hocVi) {
        this.hocVi = hocVi;
    }

    public String getChuyenNganh() {
        return chuyenNganh;
    }

    public void setChuyenNganh(String chuyenNganh) {
        this.chuyenNganh = chuyenNganh;
    }

    public int getSoTietGiang() {
        return soTietGiang;
    }

    public void setSoTietGiang(int soTietGiang) {
        this.soTietGiang = soTietGiang;
    }

    public double getTienMotTiet() {
        return tienMotTiet;
    }

    public void setTienMotTiet(double tienMotTiet) {
        this.tienMotTiet = tienMotTiet;
    }

    public Khoa getKhoaCongTac() {
        return khoaCongTac;
    }

    public void setKhoaCongTac(Khoa khoaCongTac) {
        this.khoaCongTac = khoaCongTac;
    }

    @Override
    public double tinhLuong() {
        return luongCoBan + (soTietGiang * tienMotTiet);
    }

    @Override
    public String hienThiThongTin() {
        return super.hienThiThongTin() + 
               String.format(" | Học hàm: %s | Học vị: %s | Chuyên ngành: %s | Khoa: %s | Lương: %.0f",
                       hocHam, hocVi, chuyenNganh, 
                       khoaCongTac != null ? khoaCongTac.getTenDonVi() : "Chưa phân công",
                       tinhLuong());
    }
}