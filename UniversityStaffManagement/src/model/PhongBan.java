package model;

public class PhongBan extends DonVi {
    private NhanVien truongPhong;

    public PhongBan() {
        super();
    }

    public PhongBan(String maDonVi, String tenDonVi) {
        super(maDonVi, tenDonVi);
    }

    public PhongBan(String maDonVi, String tenDonVi, NhanVien truongPhong) {
        super(maDonVi, tenDonVi);
        this.truongPhong = truongPhong;
    }

    public NhanVien getTruongPhong() {
        return truongPhong;
    }

    public void setTruongPhong(NhanVien truongPhong) {
        this.truongPhong = truongPhong;
    }

    @Override
    public boolean kiemTraNhanSuHopLe(NhanSu ns) {
        return ns instanceof NhanVien;
    }

    public int getSoLuongNhanVien() {
        return danhSachNhanSu.size();
    }

    public double tinhTongLuongNhanVien() {
        return tinhTongLuong();
    }
}