package ui;

import control.BusinessControl;
import entity.Diem;
import entity.MonHoc;
import entity.SinhVien;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ConsoleUI {
    private Scanner scanner = new Scanner(System.in);
    private BusinessControl control;

    public ConsoleUI(BusinessControl control) {
        this.control = control;
    }

    public void start() {
        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Danh sách sinh viên");
            System.out.println("2. Thêm sinh viên mới");
            System.out.println("3. Nhập điểm theo MSSV");
            System.out.println("4. Tra cứu điểm theo MSSV");
            System.out.println("5. Thoát");
            System.out.print("Chọn: ");

            int chon = Integer.parseInt(scanner.nextLine());

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
                System.out.println("Đã lưu và thoát!");
                return;
            }
        }
    }

    private void hienThiDanhSachSinhVien() {
        System.out.println("\n--- DANH SÁCH SINH VIÊN ---");
        HashMap <String , SinhVien> dsSinhVien = control.layDanhSachSinhVien();
        if (dsSinhVien.isEmpty()) {
            System.out.println("Danh sách trống.");
            return;
        }
        for (SinhVien sv : dsSinhVien.values()) {
            System.out.println("MSSV: " + sv.getMaSv() +
                    " | Họ tên: " + sv.getHoTen() +
                    " | Ngày sinh: " + sv.getNgaySinh() +
                    " | Lớp: " + sv.getLop());
        }
    }

    private void giaoDienNhapSinhVien() {
        System.out.print("Nhập MSSV: ");
        String maSv = scanner.nextLine();
        System.out.print("Nhập họ tên: ");
        String hoTen = scanner.nextLine();
        System.out.print("Nhập ngày sinh: ");
        String ngaySinh = scanner.nextLine();
        System.out.print("Nhập lớp: ");
        String lop = scanner.nextLine();

        boolean ok = control.themSinhVien(hoTen, ngaySinh, lop, maSv);
        System.out.println(ok ? "Đã thêm sinh viên." : "MSSV đã tồn tại!");
    }

    // Chức năng 3: Nhập điểm và hiển thị danh sách môn học tối giản
    private void giaoDienNhapDiemTheoMSSV() {
        System.out.print("Nhập MSSV cần nhập điểm: ");
        String maSv = scanner.nextLine();
        SinhVien sv = control.timSinhVien(maSv);

        // Hiển thị danh sách môn học không theo format nhãn chữ cũ
        System.out.println("\n--- DANH SÁCH MÔN HỌC ---");
        for (MonHoc mh : control.layDanhSachMonHoc().values()) {
            System.out.println(mh.getMaMonHoc() + " | " + mh.getTenMonHoc() + " | " + mh.getSoTinChi() + " TC");
        }
        System.out.println("-------------------------");
        System.out.print("Nhập mã môn học: ");
        String maMh = scanner.nextLine();
        MonHoc mh = control.layDanhSachMonHoc().get(maMh);

        System.out.print("Nhập điểm Thường Kỳ: ");
        float diemTK = Float.parseFloat(scanner.nextLine());
        ArrayList<Float> thuongKy = new ArrayList<>();
        thuongKy.add(diemTK);

        System.out.print("Nhập điểm Giữa Kỳ: ");
        float diemGK = Float.parseFloat(scanner.nextLine());
        System.out.print("Nhập điểm Cuối Kỳ: ");
        float diemCK = Float.parseFloat(scanner.nextLine());

        boolean ok = control.themDiem(maSv, maMh, thuongKy, diemGK, diemCK);
        System.out.println(ok ? "Đã thêm điểm." : "Không tìm thấy sinh viên hoặc môn học!");
    }

    private void giaoDienTraCuuDiemTheoMSSV() {
        System.out.print("Nhập MSSV cần tra cứu: ");
        String maSv = scanner.nextLine();

        System.out.println("\n--- BẢNG ĐIỂM CỦA SINH VIÊN: " + maSv + " ---");
        boolean coDiem = false;

        for (Diem diem : control.layDanhSachDiem()) {
            if (diem.getSinhVien().getMaSv().equals(maSv)) {
                System.out.println("Tên: " + diem.getSinhVien().getHoTen());
                // Đổi diem.getMonHoc().getMaMonHoc() thành gọi thêm cả getTenMonHoc()
                System.out.println("Môn: " + diem.getMonHoc().getMaMonHoc() + " - " + diem.getMonHoc().getTenMonHoc() +
                        " | Thường kỳ: " + diem.getThuongKy() +
                        " | Giữa kỳ: " + diem.getDiemGiuaKy() +
                        " | Cuối kỳ: " + diem.getDiemCuoiKy());
                coDiem = true;
            }
        }

        if (!coDiem) {
            System.out.println("Sinh viên này chưa có dữ liệu điểm.");
        }
    }
}