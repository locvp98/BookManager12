package com.example.dell.bookmanager.model;

public class Nguoidung {
    private int mid;
    private String mname;
    private String mpassword;
    private String mphone;
    private String mhoten;

    public Nguoidung() {
    }

    public Nguoidung(String mname, String mpassword, String mphone, String mhoten) {
        this.mname = mname;
        this.mpassword = mpassword;
        this.mphone = mphone;
        this.mhoten = mhoten;
    }

    public Nguoidung( String mname, String mpassword) {
        this.mname = mname;
        this.mpassword = mpassword;
    }

    public Nguoidung(int mid, String mname, String mpassword, String mphone, String mhoten) {
        this.mid = mid;
        this.mname = mname;
        this.mpassword = mpassword;
        this.mphone = mphone;
        this.mhoten = mhoten;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMpassword() {
        return mpassword;
    }

    public void setMpassword(String mpassword) {
        this.mpassword = mpassword;
    }

    public String getMphone() {
        return mphone;
    }

    public void setMphone(String mphone) {
        this.mphone = mphone;
    }

    public String getMhoten() {
        return mhoten;
    }

    public void setMhoten(String mhoten) {
        this.mhoten = mhoten;
    }
}
