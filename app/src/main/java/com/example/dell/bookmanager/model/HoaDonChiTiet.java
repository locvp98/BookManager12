package com.example.dell.bookmanager.model;

public class HoaDonChiTiet {
    private int mid;
    private String mma;
    private String mgia;
    private String mthanhtien;
    private String mmasach;
    private String msoluong;


    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(String mma, String mgia, String mthanhtien, String mmasach, String msoluong) {
        this.mma = mma;
        this.mgia = mgia;
        this.mthanhtien = mthanhtien;
        this.mmasach = mmasach;
        this.msoluong = msoluong;
    }

    public HoaDonChiTiet(int mid, String mma, String mgia, String mthanhtien, String mmasach, String msoluong) {
        this.mid = mid;
        this.mma = mma;
        this.mgia = mgia;
        this.mthanhtien = mthanhtien;
        this.mmasach = mmasach;
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

    public String getMgia() {
        return mgia;
    }

    public void setMgia(String mgia) {
        this.mgia = mgia;
    }

    public String getMthanhtien() {
        return mthanhtien;
    }

    public void setMthanhtien(String mthanhtien) {
        this.mthanhtien = mthanhtien;
    }

    public String getMmasach() {
        return mmasach;
    }

    public void setMmasach(String mmasach) {
        this.mmasach = mmasach;
    }

    public String getMsoluong() {
        return msoluong;
    }

    public void setMsoluong(String msoluong) {
        this.msoluong = msoluong;
    }
}
