package entity;

public class Diem {
    private String maSv;
    private String maMonHoc;
    private float diemThuongKy;
    private float diemGiuaKy;
    private float diemCuoiKy;

    public Diem(String maSv, String maMonHoc, float diemThuongKy, float diemGiuaKy, float diemCuoiKy) {
        this.maSv = maSv;
        this.maMonHoc = maMonHoc;
        this.diemThuongKy = diemThuongKy;
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

    public float getDiemThuongKy() {
        return diemThuongKy;
    }

    public void setDiemThuongKy(float diemThuongKy) {
        this.diemThuongKy = diemThuongKy;
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
