package com.example.dell.bookmanager.model;

public class HoaDon  {
    private int mid;
    private String mma;
    private String mngaythang;

    public HoaDon() {
    }

    public HoaDon(String mma,  String mngaythang) {
        this.mma = mma;
        this.mngaythang = mngaythang;
    }

    public HoaDon(int mid, String mma,  String mngaythang) {
        this.mid = mid;
        this.mma = mma;
        this.mngaythang = mngaythang;
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

    public String getMngaythang() {
        return mngaythang;
    }

    public void setMngaythang(String mngaythang) {
        this.mngaythang = mngaythang;
    }


}
