import controller.*;
import javax.swing.*;
import model.*;
import view.*;

public class Main {
    private TruongDaiHoc truongDaiHoc;
    private MainView mainView;

    public Main() {
        truongDaiHoc = new TruongDaiHoc("ĐẠI HỌC PHENIKAA");
        mainView = new MainView();
        
        initData();
        initControllers();
        showMainView();
    }

    private void initData() {
        // Tạo dữ liệu mẫu cho các khoa
        Khoa khoaCNTT = new Khoa("K001", "Công nghệ thông tin");
        Khoa khoaQTKD = new Khoa("K002", "Quản trị kinh doanh");
        Khoa khoaNN = new Khoa("K003", "Ngoại ngữ");
        
        truongDaiHoc.themKhoa(khoaCNTT);
        truongDaiHoc.themKhoa(khoaQTKD);
        truongDaiHoc.themKhoa(khoaNN);

        // Tạo dữ liệu mẫu cho các phòng ban
        PhongBan pbDaoTao = new PhongBan("PB001", "Đào tạo");
        PhongBan pbHanhChinh = new PhongBan("PB002", "Hành chính");
        PhongBan pbTaiVu = new PhongBan("PB003", "Tài vụ");
        
        truongDaiHoc.themPhongBan(pbDaoTao);
        truongDaiHoc.themPhongBan(pbHanhChinh);
        truongDaiHoc.themPhongBan(pbTaiVu);
    }

    private void initControllers() {
        mainView.getBtnQLGiangVien().addActionListener(e -> openGiangVienView());
        mainView.getBtnQLNhanVien().addActionListener(e -> openNhanVienView());
        mainView.getBtnQLKhoa().addActionListener(e -> openKhoaView());
        mainView.getBtnQLPhongBan().addActionListener(e -> openPhongBanView());
        mainView.getBtnThongKe().addActionListener(e -> openThongKeView());
        mainView.getBtnThoat().addActionListener(e -> System.exit(0));

        updateDashboardInfo();
    }

    private void updateDashboardInfo() {
        mainView.getLblTongGV().setText(String.valueOf(truongDaiHoc.thongKeTongGiangVien()));
        mainView.getLblTongNV().setText(String.valueOf(truongDaiHoc.thongKeTongNhanVien()));
        mainView.getLblTongQuyLuong().setText(String.format("%,.0f VNĐ", 
                truongDaiHoc.tinhTongQuyLuongToanTruong()));
    }

    private void openGiangVienView() {
        GiangVienView view = new GiangVienView();
        for (Khoa khoa : truongDaiHoc.getDanhSachKhoa()) {
            view.getCboKhoa().addItem(khoa);
        }
        new GiangVienController(truongDaiHoc, view);
        view.setVisible(true);
    }

    private void openNhanVienView() {
        NhanVienView view = new NhanVienView();
        for (PhongBan pb : truongDaiHoc.getDanhSachPhongBan()) {
            view.getCboPhongBan().addItem(pb);
        }
        new NhanVienController(truongDaiHoc, view);
        view.setVisible(true);
    }

    private void openKhoaView() {
        KhoaView view = new KhoaView();
        view.setVisible(true);
    }

    private void openPhongBanView() {
        PhongBanView view = new PhongBanView();
        view.setVisible(true);
    }

    private void openThongKeView() {
        ThongKeView view = new ThongKeView();
        new ThongKeController(truongDaiHoc, view);
        view.setVisible(true);
    }

    private void showMainView() {
        mainView.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new Main();
        });
    }
}