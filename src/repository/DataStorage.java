package repository;

import entity.Diem;
import entity.MonHoc;
import entity.SinhVien;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DataStorage implements Serializable {
        private static final long serialVersionUID = 1L;
        private static final String FILE_NAME = "storage.dat";

        //hashmap la danh sanh ko dc trung, trong do masv ko dc trung HashMap<a1, a2> (a1 ko dc trung)
        private HashMap<String, SinhVien> dsSinhVien;
        private HashMap<String, MonHoc> dsMonHoc;
        private ArrayList<Diem> dsDiem;

    public DataStorage(HashMap<String, SinhVien> dsSinhVien, HashMap<String, MonHoc> dsMonHoc, ArrayList<Diem> dsDiem) {
        this.dsSinhVien = dsSinhVien;
        this.dsMonHoc = dsMonHoc;
        this.dsDiem = dsDiem;
    }

    public DataStorage(){
        dsSinhVien = new HashMap<>();
        dsMonHoc = new HashMap<>();
        dsDiem = new ArrayList<>();
    }

    //tao ham lay danh sach SinhVien
    public HashMap<String, SinhVien> getDsSinhVien() {
        return dsSinhVien;
    }

    //Them sinh vien sau khi kiem tra dung dinh dang sv
    public boolean themSinhVien(SinhVien sv){
        if (!dsSinhVien.containsKey(sv.getMaSv())) {
            dsSinhVien.put(sv.getMaSv(), sv);
            return true;
        }
        return false;
    }

    //Xoa sinh vien neu nhu nhap dung dinh dang mssv
    public boolean xoaSinhVien(String maSV) {
        if (dsSinhVien.containsKey(maSV)) {
            dsSinhVien.remove(maSV);
            return true;
        }
        return false;
    }
    //Tra ve sinh vien tu mssv
    public SinhVien timSinhVien(String maSV) {
        return dsSinhVien.get(maSV);
    }

    //Neu nhu da co mssv thi PUT se tu ghi de
    public boolean suaSinhVien(SinhVien sv) {
        if (dsSinhVien.containsKey(sv.getMaSv())) {
            dsSinhVien.put(sv.getMaSv(), sv);
            return true;
        }
        return false;
    }

    //them mon hoc neu nhu dung dinh dang mon hoc
    public boolean themMonHoc(MonHoc monHoc) {
        if(dsMonHoc.containsKey(monHoc.getMaMonHoc())) {
            return false; // Môn học đã tồn tại
        }
        dsMonHoc.put(monHoc.getMaMonHoc(), monHoc);
        return true;
    }

    //Xoa mon hoc neu nhu nhap dung dinh dang mamonhoc
    public boolean xoaMonHoc(String maMonHoc) {
        if (dsMonHoc.containsKey(maMonHoc)) {
            dsMonHoc.remove(maMonHoc);
            return true;
        }
        return false;
    }

    //Sua mon hoc neu nhu nhap dung dinh dang mamonhoc
    public boolean suaMonHoc(MonHoc monHoc) {
        if(dsMonHoc.containsKey(monHoc.getMaMonHoc())) {
            dsMonHoc.put(monHoc.getMaMonHoc(), monHoc);
            return true;
        }
        return false;
    }

    //Tim mon hoc neu nhu da nhap dung dinh dang
    public MonHoc timMonHoc(String maMonHoc) {
        return dsMonHoc.get(maMonHoc);
    }

    //lay danh sach mon hoc
    public HashMap<String, MonHoc> getDsMonHoc() {
        return dsMonHoc;
    }

    //Lay danh sach Diem
    public ArrayList<Diem> getDsDiem() {
        return dsDiem;
    }
    //Them diem neu nhu da nhap dung het tren tang control
    public boolean themDiem(Diem diem) {
        dsDiem.add(diem);
        return true;
    }

    //Xoa diem neu nhu da nhap dung het tren tang control
    public  boolean xoaDiem(Diem diem){
        return dsDiem.remove(diem);
    }

    //Sua diem neu nhu da nhap dung dinh dang tren phan contol
    public boolean suaDiem(String maSV,
                           String maMH,
                           ArrayList<Float> thuongKy,
                           float diemGiuaKy,
                           float diemCuoiKy) {

        Diem diem = timDiem(maSV, maMH);

        if (diem == null) {
            return false;
        }

        diem.setThuongKy(thuongKy);
        diem.setDiemGiuaKy(diemGiuaKy);
        diem.setDiemCuoiKy(diemCuoiKy);

        return true;
    }
     //Kiem tra tung thang diem trong danh sach diem
     public Diem timDiem(String maSv, String maMonHoc) {
         for (Diem diem : dsDiem) {
             if (diem.getSinhVien().getMaSv().equals(maSv) && diem.getMonHoc().getMaMonHoc().equals(maMonHoc)) {
                return diem;
            }
        }
        return null;
    }
    //Luu file
    public void saveToFile() {
           try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
               out.writeObject(this);
           } catch (Exception e) {
               e.printStackTrace();
           }
    }

    //docFile
    public static DataStorage loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new DataStorage(); //Tra ve  1 file DataStorage trong neu nhu no chua ton tai
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (DataStorage) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return new DataStorage(); // tra ve DataStorage neu nhu load len ko dc
        }
    }


}
