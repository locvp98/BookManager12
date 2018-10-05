package com.example.dell.bookmanager.sqliteDAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dell.bookmanager.database.DBManager;
import com.example.dell.bookmanager.model.HoaDonChiTiet;

import java.util.ArrayList;
import java.util.List;

public class HDCTDAO {

    private DBManager dbManager;

    public HDCTDAO(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public void themhdct(HoaDonChiTiet hoaDonChiTiet){

        SQLiteDatabase db=dbManager.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(DBManager.HDCT_MA, hoaDonChiTiet.getMma());
        contentValues.put(DBManager.HDCT_MAsACH, hoaDonChiTiet.getMmasach());
        contentValues.put(DBManager.HDCT_GIABIA, hoaDonChiTiet.getMgia());
        contentValues.put(DBManager.HDCT_SOLUONG, hoaDonChiTiet.getMsoluong());
        contentValues.put(DBManager.HDCT_THANHTIEN, hoaDonChiTiet.getMthanhtien());

        db.insert(DBManager.TB_HOADONCHITIET,null,contentValues);
        db.close();


    }

    public List<HoaDonChiTiet> getallhdct() {
        List<HoaDonChiTiet> hdctlist = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + DBManager.TB_HOADONCHITIET;

        SQLiteDatabase hd = dbManager.getWritableDatabase();
        Cursor cursor = hd.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                hoaDonChiTiet.setMid(cursor.getInt(cursor.getColumnIndex(DBManager.IDHDCT)));
                hoaDonChiTiet.setMma(cursor.getString(1));
                hoaDonChiTiet.setMmasach(cursor.getString(2));
                hoaDonChiTiet.setMsoluong(cursor.getString(3));
                hoaDonChiTiet.setMgia(cursor.getString(4));
                hoaDonChiTiet.setMthanhtien(cursor.getString(5));
                hdctlist.add(hoaDonChiTiet);

            }
            while (cursor.moveToNext());

        }
        hd.close();
        return hdctlist;
    }

    public int deletehodonct(int id) {
        SQLiteDatabase db = dbManager.getWritableDatabase();
        return db.delete(DBManager.TB_HOADONCHITIET, DBManager.IDHDCT + "=?", new String[]{String.valueOf(id)});

    }

    public int updatehoadeon(HoaDonChiTiet hoaDon) {
        SQLiteDatabase db = dbManager.getWritableDatabase();

      return 0;
    }



}
