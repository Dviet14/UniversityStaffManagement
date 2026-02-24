package controller;

import model.*;
import view.ThongKeView;
import javax.swing.*;
import java.text.DecimalFormat;

public class ThongKeController {
    private TruongDaiHoc truongDaiHoc;
    private ThongKeView view;

    public ThongKeController(TruongDaiHoc truongDaiHoc, ThongKeView view) {
        this.truongDaiHoc = truongDaiHoc;
        this.view = view;
        
        initController();
        capNhatThongKe();
    }

    private void initController() {
        view.getBtnCapNhat().addActionListener(e -> capNhatThongKe());
        view.getBtnThoat().addActionListener(e -> view.dispose());
    }

    private void capNhatThongKe() {
        // Cập nhật thông tin tổng quan
        view.getLblTongGV().setText(String.valueOf(truongDaiHoc.thongKeTongGiangVien()));
        view.getLblTongNV().setText(String.valueOf(truongDaiHoc.thongKeTongNhanVien()));
        
        DecimalFormat df = new DecimalFormat("#,###.##");
        view.getLblTongQuyLuong().setText(df.format(truongDaiHoc.tinhTongQuyLuongToanTruong()) + " VNĐ");

        // Cập nhật bảng thống kê theo khoa
        updateBangThongKeKhoa(df);
        
        // Cập nhật bảng thống kê theo phòng ban
        updateBangThongKePhongBan(df);
    }

    private void updateBangThongKeKhoa(DecimalFormat df) {
        view.getTableModelKhoa().setRowCount(0);
        
        for (Khoa khoa : truongDaiHoc.getDanhSachKhoa()) {
            Object[] row = {
                khoa.getMaDonVi(),
                khoa.getTenDonVi(),
                khoa.getSoLuongGiangVien(),
                df.format(khoa.tinhTongLuong()) + " VNĐ"
            };
            view.getTableModelKhoa().addRow(row);
        }
    }

    private void updateBangThongKePhongBan(DecimalFormat df) {
        view.getTableModelPhongBan().setRowCount(0);
        
        for (PhongBan pb : truongDaiHoc.getDanhSachPhongBan()) {
            Object[] row = {
                pb.getMaDonVi(),
                pb.getTenDonVi(),
                pb.getSoLuongNhanVien(),
                df.format(pb.tinhTongLuong()) + " VNĐ"
            };
            view.getTableModelPhongBan().addRow(row);
        }
    }
}