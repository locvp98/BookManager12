package com.example.dell.bookmanager.sqliteDAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dell.bookmanager.database.DBManager;
import com.example.dell.bookmanager.model.Nguoidung;
import com.example.dell.bookmanager.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiDAO {

    private DBManager dbManager;

    public TheLoaiDAO(DBManager dbManager) {
        this.dbManager = dbManager;


    }

    public void themtheloai(TheLoai theLoai) {

        SQLiteDatabase db = dbManager.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBManager.THELOAI_MA, theLoai.getmMa());
        contentValues.put(DBManager.THELOAI_TENTHELOAI, theLoai.getmTentheloai());
        contentValues.put(DBManager.THELOAI_VITRI, theLoai.getmVitri());
        contentValues.put(DBManager.THELOAI_MOTA, theLoai.getmMota());

        db.insert(DBManager.TB_THELOAI, null, contentValues);
        db.close();

    }

    public List<TheLoai> getalltheloai() {
        SQLiteDatabase db = dbManager.getWritableDatabase();

        List<TheLoai> theLoaiList = new ArrayList<>();
        String truvan = " SELECT * FROM " + DBManager.TB_THELOAI;
        Cursor cursor = db.rawQuery(truvan, null);

        if (cursor.moveToFirst()) {

            do {
                TheLoai theLoai = new TheLoai();
                theLoai.setMid(cursor.getInt(0));
                theLoai.setmMa(cursor.getString(1));
                theLoai.setmTentheloai(cursor.getString(2));
                theLoai.setmVitri(cursor.getString(3));
                theLoai.setmMota(cursor.getString(4));
                theLoaiList.add(theLoai);

            } while (cursor.moveToNext());

        }
        return theLoaiList;


    }

    public int deletetheloai(int id) {

        SQLiteDatabase db = dbManager.getWritableDatabase();
        return db.delete(DBManager.TB_THELOAI, DBManager.IDHD + "=?", new String[]{String.valueOf(id)});


    }

    public int updatetheloai(TheLoai theLoai) {
        SQLiteDatabase db = dbManager.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBManager.THELOAI_MA, theLoai.getmMa());
        contentValues.put(DBManager.THELOAI_TENTHELOAI, theLoai.getmTentheloai());
        contentValues.put(DBManager.THELOAI_VITRI, theLoai.getmVitri());
        contentValues.put(DBManager.THELOAI_MOTA, theLoai.getmMota());

        return  db.update(DBManager.TB_THELOAI, contentValues, DBManager.IDTL + "=?", new String[]{String.valueOf(theLoai.getMid())});


    }



}
