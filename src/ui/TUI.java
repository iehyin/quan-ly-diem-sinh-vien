package ui;
import java.util.Scanner;
public final class TUI {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int TITLE_WIDTH = 64;
    //TONG BE RONG KHUNG TITLE
    private TUI(){}
    //color
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RESET = "\u001B[0m";
    public static String Colorize(String text, String color){
        return color + text + RESET;
    }
    public static void success(String message){
        System.out.println(GREEN + message + RESET);
    }
    public static void error(String message){
        System.out.println(RED + message + RESET);
    }
    public static void load(String message){
        System.out.println(YELLOW + message + RESET);
    }
    /* ========================= SCREEN ========================= */

    /** Xóa màn hình console bằng cách đẩy 50 dòng trống */
    public static void clear() {
        for (int i = 0; i < 50; i++) System.out.println();
    }

    /** In khung tiêu đề lớn, dùng cho đầu mỗi màn hình */
    public static void title(String title) {
        if (title.length() > 60) title = title.substring(0, 57) + "...";
        System.out.println("╔" + "═".repeat(TITLE_WIDTH - 2) + "╗");
        System.out.printf("║ %-60s ║%n", title);
        System.out.println("╚" + "═".repeat(TITLE_WIDTH - 2) + "╝");
    }

    /* ========================= MESSAGE BOX ========================= */

    /** In hộp thông báo đơn giản (không có cột), dùng cho lời nhắc / kết quả / lỗi */
    public static void box(String... lines) {
        int width = 60;
        for (String l : lines) width = Math.max(width, Math.min(l.length(), 60));
        System.out.println("┌" + "─".repeat(width + 2) + "┐");
        for (String l : lines) {
            System.out.printf("│ %-" + width + "s │%n", truncate(l, width));
        }
        System.out.println("└" + "─".repeat(width + 2) + "┘");
    }
    public static void boxColor(String color, String... lines) {
        int width = 60;
        for (String l : lines) width = Math.max(width, Math.min(l.length(), 58));
        System.out.println("┌" + "─".repeat(width + 2) + "┐");
        for (String l : lines) {
            System.out.println("│ " + color + String.format("%-" + width + "s", truncate(l, width)) + RESET + " │");
        }
        System.out.println("└" + "─".repeat(width + 2) + "┘");
    }


    /* ========================= TABLE (số cột tùy ý) ========================= */

    private static int[] currentWidths;

    /**
     * In phần đầu bảng: tiêu đề bảng + tên các cột.
     * @param title   Tên bảng (vd: "Danh sách sinh viên")
     * @param headers Tên các cột (vd: {"MSSV","Họ tên","Ngày sinh","Lớp"})
     * @param widths  Bề rộng từng cột tương ứng (vd: {8,22,12,8})
     */
    public static void tableHeader(String title, String[] headers, int[] widths) {
        currentWidths = widths;
        int innerWidth = sumWidth(widths);

        System.out.println("┌" + "─".repeat(innerWidth) + "┐");
        System.out.printf("│ %-" + (innerWidth - 2) + "s │%n", truncate(title, innerWidth - 2));
        System.out.println(divider('├', '┬', '┤'));

        StringBuilder row = new StringBuilder("│");
        for (int i = 0; i < headers.length; i++) {
            row.append(String.format(" %-" + widths[i] + "s │", truncate(headers[i], widths[i])));
        }
        System.out.println(row);
        System.out.println(divider('├', '┼', '┤'));
    }

    /** In một dòng dữ liệu vào bảng, số phần tử phải khớp số cột đã khai báo ở tableHeader */
    public static void tableRow(String... cells) {
        StringBuilder row = new StringBuilder("│");
        for (int i = 0; i < cells.length; i++) {
            row.append(String.format(" %-" + currentWidths[i] + "s │", truncate(cells[i], currentWidths[i])));
        }
        System.out.println(row);
    }

    /** Đóng bảng lại bằng đường viền đáy */
    public static void tableFooter() {
        System.out.println(divider('└', '┴', '┘'));
    }

    private static int sumWidth(int[] widths) {
        int total = 1; // ký tự "│" bên trái đầu tiên
        for (int w : widths) total += w + 3; // "space + w + space + │"
        return total - 1; // trừ đi 1 vì cạnh phải dùng riêng, giữ đối xứng với "┐"
    }

    private static String divider(char left, char mid, char right) {
        StringBuilder sb = new StringBuilder();
        sb.append(left);
        for (int i = 0; i < currentWidths.length; i++) {
            sb.append("─".repeat(currentWidths[i] + 2));
            sb.append(i == currentWidths.length - 1 ? right : mid);
        }
        return sb.toString();
    }

    /* ========================= INPUT ========================= */

    /** Nhập một chuỗi văn bản bất kỳ */
    public static String text(String prompt) {
        System.out.print(prompt);
        return SCANNER.nextLine();
    }

    /** Nhập số nguyên trong khoảng [min, max], lặp lại nếu nhập sai */
    public static int number(String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(SCANNER.nextLine().trim());
                if (value >= min && value <= max) return value;
            } catch (Exception ignored) {}
            System.out.printf("Vui lòng nhập từ %d đến %d%n", min, max);
        }
    }

    /** Nhập điểm số dạng thập phân trong khoảng [0, 10], lặp lại nếu nhập sai */
    public static float score(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                float value = Float.parseFloat(SCANNER.nextLine().trim().replace(",", "."));
                if (value >= 0 && value <= 10) return value;
            } catch (Exception ignored) {}
            System.out.println("Vui lòng nhập điểm từ 0 đến 10");
        }
    }

    /** Hỏi xác nhận Y/N */
    public static boolean confirm(String prompt) {
        while (true) {
            System.out.print(prompt + " (Y/N): ");
            String input = SCANNER.nextLine().trim().toUpperCase();
            if ("Y".equals(input)) return true;
            if ("N".equals(input)) return false;
        }
    }

    /** Tạm dừng chờ Enter */
    public static void pause() {
        System.out.print("Nhấn Enter để tiếp tục...");
        SCANNER.nextLine();
    }

    /* ========================= UTILITY ========================= */

    private static String truncate(String text, int max) {
        if (text == null) return "";
        if (text.length() <= max) return text;
        return max <= 3 ? text.substring(0, max) : text.substring(0, max - 3) + "...";
    }
}

