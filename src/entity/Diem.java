package entity;

import java.util.ArrayList;

public class Diem {
    private SinhVien sinhVien;
    private MonHoc monHoc;
    private ArrayList<Float> thuongKy;
    private float diemGiuaKy;
    private float diemCuoiKy;

    public Diem() {
        thuongKy = new ArrayList<>();
    }

    public Diem(SinhVien sinhVien, MonHoc monHoc, ArrayList<Float> thuongKy, float diemGiuaKy, float diemCuoiKy) {
        this.sinhVien = sinhVien;
        this.monHoc = monHoc;
        this.thuongKy = thuongKy;
        this.diemGiuaKy = diemGiuaKy;
        this.diemCuoiKy = diemCuoiKy;
    }

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }

    public MonHoc getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
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
