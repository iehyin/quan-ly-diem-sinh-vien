package entity;

import java.io.Serializable;

public class SinhVien extends Nguoi implements Serializable {
        private String lop;
        private String maSv;

    public SinhVien()  {
        }

    public SinhVien(String hoTen, String ngaySinh, String lop, String maSv) {
            super(hoTen, ngaySinh);
            this.lop = lop;
            this.maSv = maSv;
        }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getMaSv() {
        return maSv;
    }

    public void setMaSv(String maSv) {
        this.maSv = maSv;
    }
}
