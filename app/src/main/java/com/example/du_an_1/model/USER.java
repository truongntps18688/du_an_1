package com.example.du_an_1.model;

public class USER {
    private String tk;
    private String mk;
    private String cauhoi;
    private String tl;
    public USER() {
    }

    public USER(String tk, String mk, String cauhoi, String tl) {
        this.tk = tk;
        this.mk = mk;
        this.cauhoi = cauhoi;
        this.tl = tl;
    }

    public String getTk() {
        return tk;
    }

    public void setTk(String tk) {
        this.tk = tk;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }

    public String getCauhoi() {
        return cauhoi;
    }

    public void setCauhoi(String cauhoi) {
        this.cauhoi = cauhoi;
    }

    public String getTl() {
        return tl;
    }

    public void setTl(String tl) {
        this.tl = tl;
    }
}
