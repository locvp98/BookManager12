package com.example.dell.bookmanager.model;

public class TheLoai {
    private int mid;
    private String mMa;
    private String mTentheloai;
    private String mVitri;
    private String mMota;

    public TheLoai() {
    }

    public TheLoai(String mMa, String mTentheloai, String mVitri, String mMota) {
        this.mMa = mMa;
        this.mTentheloai = mTentheloai;
        this.mVitri = mVitri;
        this.mMota = mMota;
    }

    public TheLoai(int mid, String mMa, String mTentheloai, String mVitri, String mMota) {
        this.mid = mid;
        this.mMa = mMa;
        this.mTentheloai = mTentheloai;
        this.mVitri = mVitri;
        this.mMota = mMota;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getmMa() {
        return mMa;
    }

    public void setmMa(String mMa) {
        this.mMa = mMa;
    }

    public String getmTentheloai() {
        return mTentheloai;
    }

    public void setmTentheloai(String mTentheloai) {
        this.mTentheloai = mTentheloai;
    }

    public String getmVitri() {
        return mVitri;
    }

    public void setmVitri(String mVitri) {
        this.mVitri = mVitri;
    }

    public String getmMota() {
        return mMota;
    }

    public void setmMota(String mMota) {
        this.mMota = mMota;
    }
}
