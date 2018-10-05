package com.example.dell.bookmanager.sqliteDAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dell.bookmanager.database.DBManager;
import com.example.dell.bookmanager.model.HoaDon;

import java.net.IDN;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {

    private DBManager dbManager;

    public HoaDonDAO(DBManager dbManager) {
        this.dbManager = dbManager;

    }

    public void themhoadon(HoaDon hoaDon) {


        SQLiteDatabase db = dbManager.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBManager.HOADON_MA, hoaDon.getMma());
        contentValues.put(DBManager.HOADON_NGAYTHANG, hoaDon.getMngaythang());

        db.insert(DBManager.TB_HOADON, null, contentValues);
        db.close();

    }

    public List<HoaDon> getallhoadon() {
        List<HoaDon> hoaDonList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + DBManager.TB_HOADON;

        SQLiteDatabase hd = dbManager.getWritableDatabase();
        Cursor cursor = hd.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMid(cursor.getInt(cursor.getColumnIndex(DBManager.IDHD)));
                hoaDon.setMma(cursor.getString(1));
                hoaDon.setMngaythang(cursor.getString(2));
                hoaDonList.add(hoaDon);

            }
            while (cursor.moveToNext());

        }
        hd.close();
        return hoaDonList;
    }

    public int deletehodon(int id) {
        SQLiteDatabase db = dbManager.getWritableDatabase();
        return db.delete(DBManager.TB_HOADON, DBManager.IDHD + "=?", new String[]{String.valueOf(id)});

    }

    public int updatehoadeon(HoaDon hoaDon) {
        SQLiteDatabase db = dbManager.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBManager.HOADON_MA, hoaDon.getMma());
        contentValues.put(DBManager.HOADON_NGAYTHANG, hoaDon.getMngaythang());
        return db.update(DBManager.TB_HOADON, contentValues, DBManager.IDHD + "=?", new String[]{String.valueOf(hoaDon.getMid())});
    }

}
