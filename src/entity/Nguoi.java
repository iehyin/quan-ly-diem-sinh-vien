package entity;

import java.io.Serializable;

public class Nguoi implements Serializable {
    private String hoTen;
    private String ngaySinh;

    public Nguoi() {
    }

    public Nguoi(String hoTen, String ngaySinh) {
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
}
