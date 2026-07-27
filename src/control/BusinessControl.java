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

        if (storage.timDiem(maMh, maSv) != null) {
            thongBaoLoi = "Đã có điểm môn này rồi";
            return false;
        }
        Diem diem = new Diem(sv, mh, thuongKy, gk, ck);
        return storage.themDiem(diem);
    }

    public ArrayList<Diem> layDanhSachDiem() {
        return storage.getDsDiem();
    }

    public boolean suaSinhVien(String maSv, String hoTenMoi, String ngaySinhMoi, String lopMoi) {
        thongBaoLoi = "";
        SinhVien sv = storage.timSinhVien(maSv);
        if (sv == null) {
            thongBaoLoi = "Không tìm thấy sinh viên";
            return false;
        }
        if (hoTenMoi == null || hoTenMoi.trim().isEmpty()) {
            thongBaoLoi = "Họ tên không được rỗng";
            return false;
        }
        if (ngaySinhMoi == null || ngaySinhMoi.trim().isEmpty()) {
            thongBaoLoi = "Ngày sinh không được rỗng";
            return false;
        }
        if (lopMoi == null || lopMoi.trim().isEmpty()) {
            thongBaoLoi = "Lớp không được rỗng";
            return false;
        }

        sv.setHoTen(hoTenMoi);
        sv.setNgaySinh(ngaySinhMoi);
        sv.setLop(lopMoi);
        return storage.suaSinhVien(sv);
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


        for (Diem d : storage.getDsDiem()) {
            if (d.getSinhVien().getMaSv().equals(maSv)) {
                thongBaoLoi = "Sinh viên đã có điểm, không thể xóa";
                return false;
            }
        }


        return storage.xoaSinhVien(maSv);
    }

// ================= MÔN HỌC =================

    public boolean suaMonHoc(String ma, String tenMoi, int tcMoi) {
        thongBaoLoi = "";
        MonHoc mh = storage.timMonHoc(ma);
        if (mh == null) {
            thongBaoLoi = "Không tìm thấy môn học";
            return false;
        }
        if (tenMoi == null || tenMoi.trim().isEmpty()) {
            thongBaoLoi = "Tên môn học không được rỗng";
            return false;
        }
        if (tcMoi <= 0) {
            thongBaoLoi = "Tín chỉ phải > 0";
            return false;
        }

        mh.setTenMonHoc(tenMoi);
        mh.setSoTinChi(tcMoi);
        return storage.suaMonHoc(mh);
    }

    public boolean xoaMonHoc(String ma) {
        thongBaoLoi = "";
        if (storage.timMonHoc(ma) == null) {
            thongBaoLoi = "Không tìm thấy môn học";
            return false;
        }
        // Chặn xóa nếu môn học đã có điểm (tránh dữ liệu điểm mồ côi)
        for (Diem d : storage.getDsDiem()) {
            if (d.getMonHoc().getMaMonHoc().equals(ma)) {
                thongBaoLoi = "Môn học đã có điểm, không thể xóa";
                return false;
            }
        }
        return storage.xoaMonHoc(ma);
    }

// ================= ĐIỂM =================

    public boolean suaDiem(String maSv, String maMh, ArrayList<Float> thuongKy, float gk, float ck) {
        thongBaoLoi = "";
        if (storage.timDiem(maMh, maSv) == null) {
            thongBaoLoi = "Chưa có điểm để sửa";
            return false;
        }
        if (gk < 0 || gk > 10 || ck < 0 || ck > 10) {
            thongBaoLoi = "Điểm phải từ 0-10";
            return false;
        }
        if (thuongKy == null || thuongKy.isEmpty()) {
            thongBaoLoi = "Danh sách điểm thường kỳ không được rỗng";
            return false;
        }
        for (Float d : thuongKy) {
            if (d == null || d < 0 || d > 10) {
                thongBaoLoi = "Điểm thường kỳ sai";
                return false;
            }
        }
        return storage.suaDiem(maSv, maMh, thuongKy, gk, ck);
    }

    public boolean xoaDiem(String maSv, String maMh) {
        thongBaoLoi = "";
        Diem diem = storage.timDiem(maMh, maSv);
        if (diem == null) {
            thongBaoLoi = "Không tìm thấy điểm để xóa";
            return false;
        }
        return storage.xoaDiem(diem);
    }
}