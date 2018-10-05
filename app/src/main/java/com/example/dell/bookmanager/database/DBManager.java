package com.example.dell.bookmanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dell.bookmanager.model.HoaDon;
import com.example.dell.bookmanager.model.Nguoidung;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {

    public static final String DB_BOOKMANAGER="bookmanager";
    private static final int VERSION = 1;

    public static final String TB_NGUOIDUNG="nguoidung";
    public static final String TB_HOADON="hoadon";
    public static final  String TB_THELOAI="theloai";
    public static final String TB_SACH="sach";
    public static  final String TB_HOADONCHITIET="hoadonchitiet";



    //nguoi dung
    private static final String ID = "_id";
    public static final String NGUOIDUNG_NAME="usename";
    public static final String NGUOIDUNG_PASSWORD="password";
    public static final String NGUOIDUNG_PHONE="phone";
    public static final String NGUOIDUNG_HOVATEN="hovaten";

    //hoadon
    public static String IDHD = "_id";
    public static final String HOADON_MA="ma";
    public static final String HOADON_NGAYTHANG="ngaythang";

    //the loai
    public static String IDTL = "_id";
    public static final String THELOAI_MA="ma";
    public static final String THELOAI_TENTHELOAI="tentheloai";
    public static final String THELOAI_VITRI="vitri";
    public static final String THELOAI_MOTA="mota";


    //sach

    public static final String IDSACH = "_id";
    public static final String SACH_MA="ma";
    public static final String SACH_TENSACH="tesach";
    public static final String SACH_TACGIA="tacgia";
    public static final String SACH_GIA="gia";
    public static final String SACH_SOLUONG="soluong";
    public static final String SACH_NGAY="ngay";


    public static final String IDHDCT = "_id";
    public static final String HDCT_MA="ma";
    public static final String HDCT_MAsACH="masach";
    public static final String HDCT_SOLUONG="soluong";
    public static final String HDCT_GIABIA="giabia";
    public static final String HDCT_THANHTIEN="thanhtien";


    private Context context;
    private String SQLQuery = "CREATE TABLE " + TB_NGUOIDUNG + " ( " +
            ID + " integer primary key, " +
            NGUOIDUNG_NAME + " TEXT, " +
            NGUOIDUNG_PASSWORD + " TEXT, " +
            NGUOIDUNG_PHONE + " TEXT, " +
            NGUOIDUNG_HOVATEN + " TEXT)";

    private String SQLhoadon="CREATE TABLE " + TB_HOADON + "( " +
            IDHD + " integer primary key, " +
            HOADON_MA + " TEXT, " +
            HOADON_NGAYTHANG + " TEXT )";


    private String SQLTheLoai=" CREATE TABLE " + TB_THELOAI + " ( " +
            IDTL + " integer primary key, " +
            THELOAI_MA + " TEXT, " +
            THELOAI_TENTHELOAI + " TEXT, " +
            THELOAI_VITRI + " TEXT, " +
            THELOAI_MOTA + " TEXT )";


    private String SqliteSach="CREATE TABLE " + TB_SACH +  " ( " +
            IDSACH + " integer PRIMARY KEY, " +
            SACH_MA + " TEXT, " +
            SACH_TENSACH + " TEXT, " +
            SACH_TACGIA + " TEXT, " +
            SACH_GIA + " TEXT, " +
            SACH_SOLUONG + " TEXT )";


    private String SQLiteHDCT =" CREATE TABLE " + TB_HOADONCHITIET + " ( " +
            IDHD + " INTEGER PRIMARY KEY, " +
            HDCT_MA + " TEXT, " +
            HDCT_MAsACH + " TEXT, " +
            HDCT_SOLUONG + " TEXT, " +
            HDCT_GIABIA + " TEXT, " +
            HDCT_THANHTIEN + " TEXT )";

    public DBManager(Context context) {
        super(context, DB_BOOKMANAGER, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
        db.execSQL(SQLhoadon);
        db.execSQL(SQLTheLoai);
        db.execSQL(SqliteSach);
        db.execSQL(SQLiteHDCT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void themnguoidung(Nguoidung nguoidung){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NGUOIDUNG_NAME, nguoidung.getMname());
        values.put(NGUOIDUNG_PASSWORD, nguoidung.getMpassword());
        values.put(NGUOIDUNG_PHONE, nguoidung.getMphone());
        values.put(NGUOIDUNG_HOVATEN, nguoidung.getMhoten());

        db.insert(TB_NGUOIDUNG, null, values);
        db.close();
    }


    public List<Nguoidung> getallnguoidung(){

        List<Nguoidung> listnguoidung =new ArrayList<>();

        String selectQuery=" SELECT * FROM " + TB_NGUOIDUNG;
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){

            do {
                Nguoidung nguoidung=new Nguoidung();
                nguoidung.setMid(cursor.getInt(0));
                nguoidung.setMname(cursor.getString(1));
                nguoidung.setMpassword(cursor.getString(2));
                nguoidung.setMphone(cursor.getString(3));
                nguoidung.setMhoten(cursor.getString(4));
                listnguoidung.add(nguoidung);

            }while (cursor.moveToNext());
        }
        db.close();
        return listnguoidung;

    }
    public int deleteStudent(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TB_NGUOIDUNG,ID+"=?",new String[] {String.valueOf(id)});
    }

    public int updateNguoidung(Nguoidung nguoidung){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NGUOIDUNG_NAME,nguoidung.getMname());
        contentValues.put(NGUOIDUNG_PASSWORD,nguoidung.getMpassword());
        contentValues.put(NGUOIDUNG_PHONE,nguoidung.getMphone());
        contentValues.put(NGUOIDUNG_HOVATEN,nguoidung.getMhoten());
        return db.update(TB_NGUOIDUNG,contentValues,ID+"=?",new String[]{String.valueOf(nguoidung.getMid())});
    }


        // Su ly lay thong tin nguoi dung

    public Nguoidung getUserByUsername(String username) {
        Nguoidung nguoidung = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TB_NGUOIDUNG,
                new String[]{NGUOIDUNG_NAME, NGUOIDUNG_PASSWORD, NGUOIDUNG_PHONE, NGUOIDUNG_HOVATEN},
                NGUOIDUNG_NAME + "=?", new String[]{username},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            String user_name = cursor.getString(cursor.getColumnIndex(NGUOIDUNG_NAME));
            String password = cursor.getString(cursor.getColumnIndex(NGUOIDUNG_PASSWORD));
            String phone = cursor.getString(cursor.getColumnIndex(NGUOIDUNG_PHONE));
            String name = cursor.getString(cursor.getColumnIndex(NGUOIDUNG_HOVATEN));
            nguoidung = new Nguoidung(user_name, password, name, phone);

        }
        return nguoidung;
    }

}
