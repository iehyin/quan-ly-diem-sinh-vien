package control;

import entity.Diem;
import entity.MonHoc;
import entity.SinhVien;
import repository.DataStorage;

import java.util.ArrayList;
import java.util.HashMap;

public class BusinessControl {
    private DataStorage storage;
    private String thongBaoLoi = "";

    public BusinessControl() {
        storage = DataStorage.loadFromFile();
    }

    public String getThongBaoLoi() {
        return thongBaoLoi;
    }

    public void luuDuLieuTruocKhiThoat() {
        storage.saveToFile();
    }

    // ================= SINH VIÊN =================

    public boolean themSinhVien(String hoTen, String ngaySinh, String lop, String maSv) {
        thongBaoLoi = "";
        if (hoTen == null || hoTen.trim().isEmpty()) {
            thongBaoLoi = "Họ tên không được rỗng";
            return false;
        }

        if (ngaySinh == null || ngaySinh.trim().isEmpty()) {
            thongBaoLoi = "Ngày sinh không được rỗng";
            return false;
        }

        if (lop == null || lop.trim().isEmpty()) {
            thongBaoLoi = "Lớp không được rỗng";
            return false;
        }

        if (maSv == null || maSv.trim().isEmpty()) {
            thongBaoLoi = "MSSV không được rỗng";
            return false;
        }

        if (maSv.length() < 3) {
            thongBaoLoi = "MSSV quá ngắn";
            return false;
        }

        if (storage.timSinhVien(maSv) != null) {
            thongBaoLoi = "MSSV đã tồn tại";
            return false;
        }

        SinhVien sv = new SinhVien(hoTen, ngaySinh, lop, maSv);
        return storage.themSinhVien(sv);
    }

    public boolean xoaSinhVien(String maSv) {

        thongBaoLoi = "";
        if (maSv == null || maSv.trim().isEmpty()) {
            thongBaoLoi = "MSSV không hợp lệ";
            return false;
        }

        if (storage.timSinhVien(maSv) == null) {
            thongBaoLoi = "Không tìm thấy sinh viên";
            return false;
        }

        return storage.xoaSinhVien(maSv);
    }

    public HashMap<String, SinhVien> layDanhSachSinhVien() {
        return storage.getDsSinhVien();
    }

    // ================= MÔN HỌC =================

    public boolean themMonHoc(String ma, String ten, int tc) {
        thongBaoLoi = "";
        if (ma == null || ma.trim().isEmpty() || ten == null || ten.trim().isEmpty()) {
            thongBaoLoi = "Thiếu thông tin môn học";
            return false;
        }

        if (tc <= 0) {
            thongBaoLoi = "Tín chỉ phải > 0";
            return false;
        }

        if (storage.timMonHoc(ma) != null) {
            thongBaoLoi = "Môn học đã tồn tại";
            return false;
        }

        MonHoc mh = new MonHoc(ma, ten, tc);
        return storage.themMonHoc(mh);
    }

    public HashMap<String, MonHoc> layDanhSachMonHoc() {
        return storage.getDsMonHoc();
    }

    // ================= ĐIỂM =================

    public boolean themDiem(String maSv, String maMh,
                            ArrayList<Float> thuongKy,
                            float gk, float ck) {

        SinhVien sv = storage.timSinhVien(maSv);
        MonHoc mh = storage.timMonHoc(maMh);
        thongBaoLoi = "";
        if (sv == null) {
            thongBaoLoi = "Không có sinh viên";
            return false;
        }

        if (mh == null) {
            thongBaoLoi = "Không có môn học";
            return false;
        }

        //  kiểm tra điểm
        if (gk < 0 || gk > 10 || ck < 0 || ck > 10) {
            thongBaoLoi = "Điểm phải từ 0-10";
            return false;
        }

        if (thuongKy == null || thuongKy.isEmpty()) {
            thongBaoLoi = "Danh sách điểm thường kỳ không được rỗng";
            return false;
        }
        for (Float d : thuongKy) {
            if (d == null || d < 0 || d > 10) {   // thêm d == null để tránh crash nếu list có phần tử null
                thongBaoLoi = "Điểm thường kỳ sai";
                return false;
            }
        }

        // chặn trùng
        if (storage.timDiem(maSv, maMh) != null) {
            thongBaoLoi = "Đã có điểm môn này rồi";
        }

        Diem diem = new Diem(sv, mh, thuongKy, gk, ck);
        return storage.themDiem(diem);
    }

    public ArrayList<Diem> layDanhSachDiem() {
        return storage.getDsDiem();
    }
}