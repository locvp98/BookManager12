package com.example.dell.bookmanager.model;

public class Sachban {
    private int mid;
    private String mma;
    private String mten;
    private String mtacgia;
    private String mgia;
    private String msoluong;

    public Sachban() {
    }

    public Sachban(String mma, String mten, String mtacgia, String mgia, String msoluong) {
        this.mma = mma;
        this.mten = mten;
        this.mtacgia = mtacgia;
        this.mgia = mgia;
        this.msoluong = msoluong;
    }

    public Sachban(int mid, String mma, String mten, String mtacgia, String mgia, String msoluong) {
        this.mid = mid;
        this.mma = mma;
        this.mten = mten;
        this.mtacgia = mtacgia;
        this.mgia = mgia;
        this.msoluong = msoluong;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getMma() {
        return mma;
    }

    public void setMma(String mma) {
        this.mma = mma;
    }

    public String getMten() {
        return mten;
    }

    public void setMten(String mten) {
        this.mten = mten;
    }

    public String getMtacgia() {
        return mtacgia;
    }

    public void setMtacgia(String mtacgia) {
        this.mtacgia = mtacgia;
    }

    public String getMgia() {
        return mgia;
    }

    public void setMgia(String mgia) {
        this.mgia = mgia;
    }

    public String getMsoluong() {
        return msoluong;
    }

    public void setMsoluong(String msoluong) {
        this.msoluong = msoluong;
    }
}
