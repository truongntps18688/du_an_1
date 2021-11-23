package com.example.du_an_1.DAO;

import static com.example.du_an_1.utilities.contans.COLUMN_GIAODICH_ID;
import static com.example.du_an_1.utilities.contans.COLUMN_GIAODICH_MOTA;
import static com.example.du_an_1.utilities.contans.COLUMN_GIAODICH_NGAY;
import static com.example.du_an_1.utilities.contans.COLUMN_GIAODICH_PHANLOAI_ID;
import static com.example.du_an_1.utilities.contans.COLUMN_GIAODICH_PHANLOAI_TRANGTHAI;
import static com.example.du_an_1.utilities.contans.COLUMN_GIAODICH_TIEN;
import static com.example.du_an_1.utilities.contans.COLUMN_GIAODICH_TIME;
import static com.example.du_an_1.utilities.contans.TABLE_GIAODICH;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.du_an_1.Entity.GIAODICH;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GiaoDich_DAO {
    com.example.du_an_1.Database.MyDatabase myDatabase;
    SQLiteDatabase db;

    public GiaoDich_DAO(Context c){
        myDatabase = new com.example.du_an_1.Database.MyDatabase(c);
    }
    public ArrayList<GIAODICH> select(){
        db = myDatabase.getReadableDatabase();
        ArrayList<GIAODICH> ds = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from "+TABLE_GIAODICH+"",null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            Long ngay = cursor.getLong(1);
            String gio = cursor.getString(2);
            int tien = cursor.getInt(3);
            String mota = cursor.getString(4);
            int id_phanloai = cursor.getInt(5);
            int trangthai = cursor.getInt(6);
            ds.add(new GIAODICH(id,new Date(ngay),gio,tien,mota,id_phanloai,trangthai));
        }
        cursor.close();
        return ds;
    }
    public ArrayList<GIAODICH> select(int _trangthai){
        db = myDatabase.getReadableDatabase();
        ArrayList<GIAODICH> ds = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from "+TABLE_GIAODICH+" WHERE "+COLUMN_GIAODICH_PHANLOAI_TRANGTHAI+ " = ?", new String[]{String.valueOf(_trangthai)});
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            Long ngay = cursor.getLong(1);
            String gio = cursor.getString(2);
            int tien = cursor.getInt(3);
            String mota = cursor.getString(4);
            int id_phanloai = cursor.getInt(5);
            int trangthai = cursor.getInt(6);
            ds.add(new GIAODICH(id,new Date(ngay),gio,tien,mota,id_phanloai,trangthai));
        }
        cursor.close();
        return ds;
    }
    public boolean insert(GIAODICH giaoDich){
        db= myDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_GIAODICH_NGAY,giaoDich.getNgay().getTime());
        values.put(COLUMN_GIAODICH_TIEN,giaoDich.getTien());
        values.put(COLUMN_GIAODICH_TIME,giaoDich.getGio());
        values.put(COLUMN_GIAODICH_MOTA,giaoDich.getMota());
        values.put(COLUMN_GIAODICH_PHANLOAI_ID,giaoDich.getPhan_loai_id());
        values.put(COLUMN_GIAODICH_PHANLOAI_TRANGTHAI,giaoDich.getTrang_thai());
        long row = db.insert(TABLE_GIAODICH,null,values);
        return (row > 0);
    }
    public boolean delete(int id){
        db= myDatabase.getWritableDatabase();
        int row = db.delete(TABLE_GIAODICH,COLUMN_GIAODICH_ID + " = ? ",new String[]{ id +""});
        return (row > 0);
    }
    public boolean updata(GIAODICH giaoDich){
        db= myDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_GIAODICH_NGAY,giaoDich.getNgay().getTime());
        values.put(COLUMN_GIAODICH_TIEN,giaoDich.getTien());
        values.put(COLUMN_GIAODICH_TIME,giaoDich.getGio());
        values.put(COLUMN_GIAODICH_MOTA,giaoDich.getMota());
        values.put(COLUMN_GIAODICH_PHANLOAI_ID,giaoDich.getPhan_loai_id());
        values.put(COLUMN_GIAODICH_PHANLOAI_TRANGTHAI,giaoDich.getTrang_thai());
        int row = db.update(TABLE_GIAODICH,values,COLUMN_GIAODICH_ID + " = ?",new String[]{giaoDich.getId() + ""});
        return (row > 0);
    }
    public Long tong(Long truoc, Long sau, int phanloai){
        db = myDatabase.getReadableDatabase();
        List<Long> ds = new ArrayList<>();
        String sql = "SELECT SUM("+COLUMN_GIAODICH_TIEN+") as doanhthu FROM "+TABLE_GIAODICH+"" +
                " WHERE "+COLUMN_GIAODICH_PHANLOAI_TRANGTHAI+" = ? AND "+COLUMN_GIAODICH_NGAY+" BETWEEN ? AND ?";
        Cursor cursor = db.rawQuery(sql,new String[]{String.valueOf(phanloai), String.valueOf(truoc), String.valueOf(sau)});
        while (cursor.moveToNext()){
            try {
                Long a = cursor.getLong(0);
                ds.add(a);
            }catch (Exception e){
                ds.add(0L);
            }
        }
        return ds.get(0);
    }

}
