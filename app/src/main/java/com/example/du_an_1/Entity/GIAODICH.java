package com.example.du_an_1.Entity;

import android.graphics.Bitmap;

import java.util.Date;

public class GIAODICH {

    private int id;
    private Date ngay;
    private String gio;
    private Double tien;
    private String mota;
    private int phan_loai_id;
    private int trang_thai;

    public GIAODICH() {
    }

    public GIAODICH(int id, Date ngay, String gio, Double tien, String mota, int phan_loai_id, int trang_thai) {
        this.id = id;
        this.ngay = ngay;
        this.gio = gio;
        this.tien = tien;
        this.mota = mota;
        this.phan_loai_id = phan_loai_id;
        this.trang_thai = trang_thai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }

    public Double getTien() {
        return tien;
    }

    public void setTien(Double tien) {
        this.tien = tien;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getPhan_loai_id() {
        return phan_loai_id;
    }

    public void setPhan_loai_id(int phan_loai_id) {
        this.phan_loai_id = phan_loai_id;
    }

    public int getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(int trang_thai) {
        this.trang_thai = trang_thai;
    }
}
