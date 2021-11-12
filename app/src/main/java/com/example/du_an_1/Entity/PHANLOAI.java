package com.example.du_an_1.Entity;

public class PHANLOAI {
    private int id;
    private int src;
    private String name;
    private int trangthai;

    public PHANLOAI() {
    }
    public PHANLOAI(int src, String name, int trangthai) {
        this.src = src;
        this.name = name;
        this.trangthai = trangthai;
    }
    public PHANLOAI(int id, int src, String name, int trangthai) {
        this.id = id;
        this.src = src;
        this.name = name;
        this.trangthai = trangthai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
}
