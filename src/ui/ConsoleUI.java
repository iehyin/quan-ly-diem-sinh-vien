package ui;

import control.BusinessControl;
import entity.Diem;
import entity.MonHoc;
import entity.SinhVien;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ConsoleUI {
    private BusinessControl control;

    public ConsoleUI(BusinessControl control) {
        this.control = control;
    }

    public void start() {
        while (true) {
            TUI.clear();
            TUI.title("QUẢN LÝ ĐIỂM SINH VIÊN");
            TUI.box(
                    "1. Danh sách sinh viên",
                    "2. Thêm sinh viên mới",
                    "3. Nhập điểm theo MSSV",
                    "4. Tra cứu điểm theo MSSV",
                    "5. Thoát"
            );

            int chon = TUI.number( "Chọn ", 1 ,5);

            if (chon == 1) {
                hienThiDanhSachSinhVien();
            } else if (chon == 2) {
                giaoDienNhapSinhVien();
            } else if (chon == 3) {
                giaoDienNhapDiemTheoMSSV();
            } else if (chon == 4) {
                giaoDienTraCuuDiemTheoMSSV();
            } else if (chon == 5) {
                control.luuDuLieuTruocKhiThoat();
                TUI.box( "Đã lưu và thoát!");
                return;
            }
            TUI.pause();
        }
    }

    private void hienThiDanhSachSinhVien() {
        TUI.title("DANH SÁCH SINH VIÊN");
        HashMap<String, SinhVien> dsSinhVien = control.layDanhSachSinhVien();

        if (dsSinhVien.isEmpty()) {
            TUI.box("Danh sách trống.");
            return;
        }

        TUI.tableHeader("Sinh viên", new String[]{"MSSV", "Họ tên", "Ngày sinh", "Lớp"}, new int[]{10, 20, 10, 8});
        for (SinhVien sv : dsSinhVien.values()) {
            TUI.tableRow(sv.getMaSv(), sv.getHoTen(), sv.getNgaySinh(), sv.getLop());
        }
        TUI.tableFooter();
    }

    private void giaoDienNhapSinhVien() {
        TUI.title("THÊM SINH VIÊN MỚI");

        String maSv = TUI.text("Nhập MSSV: ");
        String hoTen = TUI.text("Nhập họ tên: ");
        String ngaySinh = TUI.text("Nhập ngày sinh: ");
        String lop = TUI.text("Nhập lớp: ");

        boolean ok = control.themSinhVien(hoTen, ngaySinh, lop, maSv);
        if(ok) {
            TUI.success("Đã thêm sinh viên");
        } else {
            TUI.error("MSSV đã tồn tại");
        };
    }

    // Chức năng 3: Nhập điểm và hiển thị danh sách môn học tối giản
    private void giaoDienNhapDiemTheoMSSV() {
        TUI.title("NHẬP ĐIỂM THEO MSSV");

        String maSv = TUI.text("Nhập MSSV cần nhập điểm: ");

        TUI.tableHeader("Danh sách môn học", new String[]{"Mã MH", "Tên môn học", "Số TC"}, new int[]{6, 30, 5});
        for (MonHoc mh : control.layDanhSachMonHoc().values()) {
            TUI.tableRow(mh.getMaMonHoc(), mh.getTenMonHoc(), String.valueOf(mh.getSoTinChi()));
        }
        TUI.tableFooter();

        String maMh = TUI.text("Nhập mã môn học: ");

        float diemTK = TUI.score("Nhập điểm Thường Kỳ: ");
        ArrayList<Float> thuongKy = new ArrayList<>();
        thuongKy.add(diemTK);

        float diemGK = TUI.score("Nhập điểm Giữa Kỳ: ");
        float diemCK = TUI.score("Nhập điểm Cuối Kỳ: ");

        boolean ok = control.themDiem(maSv, maMh, thuongKy, diemGK, diemCK);
        if(ok) {
            TUI.success("Đã thêm sinh viên");
        } else {
            TUI.error("Không tìm thấy sinh viên hoặc môn học!");
        };
    }

    private void giaoDienTraCuuDiemTheoMSSV() {
        TUI.title("TRA CỨU ĐIỂM THEO MSSV");

        String maSv = TUI.text("Nhập MSSV cần tra cứu: ");
        boolean coDiem = false;

        TUI.tableHeader("Kết quả học tập", new String[]{"Mã MH", "Tên môn học", "TK", "GK", "CK"}, new int[]{6, 20, 4, 4, 4});
        for (Diem diem : control.layDanhSachDiem()) {
            if (diem.getSinhVien().getMaSv().equals(maSv)) {
                TUI.tableRow(
                        diem.getMonHoc().getMaMonHoc(),
                        diem.getMonHoc().getTenMonHoc(),
                        String.valueOf(diem.getThuongKy()),
                        String.valueOf(diem.getDiemGiuaKy()),
                        String.valueOf(diem.getDiemCuoiKy())
                );
                coDiem = true;
            }
        }
        TUI.tableFooter();

        if (!coDiem) {
            TUI.load("Sinh viên này chưa có dữ liệu điểm.");
        }
    }
}