package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class DonVi {
    protected String maDonVi;
    protected String tenDonVi;
    protected List<NhanSu> danhSachNhanSu;

    public DonVi() {
        this.danhSachNhanSu = new ArrayList<>();
    }

    public DonVi(String maDonVi, String tenDonVi) {
        this.maDonVi = maDonVi;
        this.tenDonVi = tenDonVi;
        this.danhSachNhanSu = new ArrayList<>();
    }

    // Getters and Setters
    public String getMaDonVi() {
        return maDonVi;
    }

    public void setMaDonVi(String maDonVi) {
        this.maDonVi = maDonVi;
    }

    public String getTenDonVi() {
        return tenDonVi;
    }

    public void setTenDonVi(String tenDonVi) {
        this.tenDonVi = tenDonVi;
    }

    public List<NhanSu> getDanhSachNhanSu() {
        return danhSachNhanSu;
    }

    public void themNhanSu(NhanSu ns) {
        if (kiemTraNhanSuHopLe(ns)) {
            danhSachNhanSu.add(ns);
        }
    }

    public boolean xoaNhanSu(String maNhanSu) {
        return danhSachNhanSu.removeIf(ns -> ns.getMaNhanSu().equals(maNhanSu));
    }

    public NhanSu timTheoMa(String maNhanSu) {
        for (NhanSu ns : danhSachNhanSu) {
            if (ns.getMaNhanSu().equals(maNhanSu)) {
                return ns;
            }
        }
        return null;
    }

    public List<NhanSu> timTheoTen(String ten) {
        List<NhanSu> ketQua = new ArrayList<>();
        for (NhanSu ns : danhSachNhanSu) {
            if (ns.getHoTen().toLowerCase().contains(ten.toLowerCase())) {
                ketQua.add(ns);
            }
        }
        return ketQua;
    }

    public void sapXepTheoLuong() {
        Collections.sort(danhSachNhanSu, new Comparator<NhanSu>() {
            @Override
            public int compare(NhanSu ns1, NhanSu ns2) {
                return Double.compare(ns1.tinhLuong(), ns2.tinhLuong());
            }
        });
    }

    public void sapXepTheoHoTen() {
        Collections.sort(danhSachNhanSu, new Comparator<NhanSu>() {
            @Override
            public int compare(NhanSu ns1, NhanSu ns2) {
                return ns1.getHoTen().compareTo(ns2.getHoTen());
            }
        });
    }

    public double tinhTongLuong() {
        double tong = 0;
        for (NhanSu ns : danhSachNhanSu) {
            tong += ns.tinhLuong();
        }
        return tong;
    }

    public void hienThiDanhSach() {
        System.out.println("Danh sách nhân sự của " + tenDonVi + ":");
        for (NhanSu ns : danhSachNhanSu) {
            System.out.println(ns.hienThiThongTin());
        }
    }

    public abstract boolean kiemTraNhanSuHopLe(NhanSu ns);
}