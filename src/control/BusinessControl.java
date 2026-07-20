package control;

import entity.Diem;
import entity.MonHoc;
import entity.SinhVien;
import repository.DataStorage;

import java.util.ArrayList;
import java.util.HashMap;

public class BusinessControl {
    private DataStorage storage;

    public BusinessControl() {
        // Tự động load dữ liệu từ file storage.dat khi chương trình bật lên
        storage = DataStorage.loadFromFile();
    }

    // =================================================================
    // CÁC HÀM API DÀNH CHO TẦNG UI (THẢO VY GỌI CÁC HÀM NÀY)
    // =================================================================

    // 1. Lưu toàn bộ dữ liệu xuống file (GỌI HÀM NÀY KHI CHỌN CHỨC NĂNG THOÁT APP)
    public void luuDuLieuTruocKhiThoat() {
        storage.saveToFile();
    }

    // --- MODULE SINH VIÊN ---
    public boolean themSinhVien(String hoTen, String ngaySinh, String lop, String maSv) {
        SinhVien sv = new SinhVien(hoTen, ngaySinh, lop, maSv);
        return storage.themSinhVien(sv);
    }

    public boolean xoaSinhVien(String maSv) {
        return storage.xoaSinhVien(maSv);
    }

    public SinhVien timSinhVien(String maSv) {
        return storage.timSinhVien(maSv);
    }

    public HashMap<String, SinhVien> layDanhSachSinhVien() {
        return storage.getDsSinhVien();
    }

    // --- MODULE MÔN HỌC ---
    public boolean themMonHoc(String maMonHoc, String tenMonHoc, int soTinChi) {
        MonHoc mh = new MonHoc(maMonHoc, tenMonHoc, soTinChi);
        return storage.themMonHoc(mh);
    }

    public HashMap<String, MonHoc> layDanhSachMonHoc() {
        return storage.getDsMonHoc();
    }

    // --- MODULE ĐIỂM SỐ ---
    public boolean themDiem(String maSv, String maMonHoc, ArrayList<Float> thuongKy, float giuaKy, float cuoiKy) {
        // Validation cơ bản: Phải tồn tại SV và Môn học mới cho nhập điểm
        SinhVien sv = storage.timSinhVien(maSv);
        MonHoc mh = storage.timMonHoc(maMonHoc);

        if (sv == null || mh == null) {
            return false; // Không tồn tại SV hoặc Môn học
        }

        Diem diemMoi = new Diem(sv, mh, thuongKy, giuaKy, cuoiKy);
        return storage.themDiem(diemMoi);
    }

    public ArrayList<Diem> layDanhSachDiem() {
        return storage.getDsDiem();
    }
}