package model;

public class Khoa extends DonVi {
    private GiangVien truongKhoa;

    public Khoa() {
        super();
    }

    public Khoa(String maDonVi, String tenDonVi) {
        super(maDonVi, tenDonVi);
    }

    public Khoa(String maDonVi, String tenDonVi, GiangVien truongKhoa) {
        super(maDonVi, tenDonVi);
        this.truongKhoa = truongKhoa;
    }

    public GiangVien getTruongKhoa() {
        return truongKhoa;
    }

    public void setTruongKhoa(GiangVien truongKhoa) {
        this.truongKhoa = truongKhoa;
    }

    @Override
    public boolean kiemTraNhanSuHopLe(NhanSu ns) {
        return ns instanceof GiangVien;
    }

    public int getSoLuongGiangVien() {
        return danhSachNhanSu.size();
    }

    public double tinhTongLuongGiangVien() {
        return tinhTongLuong();
    }
}