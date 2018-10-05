package com.example.dell.bookmanager.sqliteDAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.dell.bookmanager.database.DBManager;
import com.example.dell.bookmanager.model.Sachban;
import com.example.dell.bookmanager.model.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    private DBManager dbManager;

    public SachDAO(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public void themsach(Sachban sachban){
        SQLiteDatabase db=dbManager.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DBManager.SACH_MA,sachban.getMma());
        contentValues.put(DBManager.SACH_TENSACH,sachban.getMten());
        contentValues.put(DBManager.SACH_TACGIA,sachban.getMtacgia());
        contentValues.put(DBManager.SACH_GIA,sachban.getMgia());
        contentValues.put(DBManager.SACH_SOLUONG,sachban.getMsoluong());

        db.insert(DBManager.TB_SACH,null,contentValues);
        db.close();

    }

    public List<Sachban> getallsachban(){
        List<Sachban> sachbanList =new ArrayList<>();
        SQLiteDatabase db=dbManager.getWritableDatabase();
        String truvan =" SELECT * FROM " + DBManager.TB_SACH;
        Cursor cursor =db.rawQuery(truvan,null);

        if (cursor.moveToFirst()){
            do {
                Sachban sachban=new Sachban();
                sachban.setMid(cursor.getInt(0));
                sachban.setMma(cursor.getString(1));
                sachban.setMten(cursor.getString(2));
                sachban.setMtacgia(cursor.getString(3));
                sachban.setMgia(cursor.getString(4));
                sachban.setMsoluong(cursor.getString(5));
                sachbanList.add(sachban);

            }while (cursor.moveToNext());
        }
        db.close();
        return sachbanList;
    }

    public int deletesach(int id) {
        SQLiteDatabase db = dbManager.getWritableDatabase();
        return db.delete(DBManager.TB_SACH, DBManager.IDSACH + "=?", new String[]{String.valueOf(id)});

    }

    public int updatesach(Sachban sachban) {
        SQLiteDatabase db = dbManager.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DBManager.SACH_MA, sachban.getMma());
        contentValues.put(DBManager.SACH_TENSACH, sachban.getMten());
        contentValues.put(DBManager.SACH_TACGIA, sachban.getMtacgia());
        contentValues.put(DBManager.SACH_GIA, sachban.getMgia());
        contentValues.put(DBManager.SACH_SOLUONG, sachban.getMsoluong());

        return  db.update(DBManager.TB_SACH, contentValues, DBManager.IDSACH + "=?", new String[]{String.valueOf(sachban.getMid())});


    }

}
