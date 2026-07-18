//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import repository.DataStorage;
import ui.ConsoleUI;
import entity.MonHoc;

public class Main {
    public static void main(String[] args) {
// Thay vì load dữ liệu cũ, ta luôn tạo mới một storage trống mỗi khi chạy
        DataStorage storage = new DataStorage();

        // Tự động nạp sẵn 3 môn học giả định vào kho dữ liệu mới này
        storage.themMonHoc(new entity.MonHoc("S01", "Lập trình WEB", 3));
        storage.themMonHoc(new entity.MonHoc("S02", "Lập trình hướng đối tượng", 2));
        storage.themMonHoc(new entity.MonHoc("S03", "Toán cao cấp", 1));

        // Khởi chạy Menu chương trình
        ConsoleUI ui = new ConsoleUI(storage);
        ui.start();
    }
}