package entity;

import java.util.ArrayList;

public class Diem {
    private String maSv;
    private String maMonHoc;
    private ArrayList<Float> thuongKy;
    private float diemGiuaKy;
    private float diemCuoiKy;

    public Diem() {
        thuongKy = new ArrayList<>();
    }

    public Diem(String maSv, String maMonHoc, ArrayList<Float> thuongKy, float diemGiuaKy, float diemCuoiKy) {
        this.maSv = maSv;
        this.maMonHoc = maMonHoc;
        this.thuongKy = thuongKy;
        this.diemGiuaKy = diemGiuaKy;
        this.diemCuoiKy = diemCuoiKy;
    }

    public String getMaSv() {
        return maSv;
    }

    public void setMaSv(String maSv) {
        this.maSv = maSv;
    }

    public String getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public ArrayList<Float> getThuongKy() {
        return thuongKy;
    }

    public void setThuongKy(ArrayList<Float> thuongKy) {
        this.thuongKy = thuongKy;
    }

    public float getDiemGiuaKy() {
        return diemGiuaKy;
    }

    public void setDiemGiuaKy(float diemGiuaKy) {
        this.diemGiuaKy = diemGiuaKy;
    }

    public float getDiemCuoiKy() {
        return diemCuoiKy;
    }

    public void setDiemCuoiKy(float diemCuoiKy) {
        this.diemCuoiKy = diemCuoiKy;
    }
}
