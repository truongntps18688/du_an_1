package com.example.du_an_1.DAO;

import static com.example.du_an_1.utilities.contans.*;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ImageView;

import com.example.du_an_1.Database.MyDatabase;
import com.example.du_an_1.Entity.PHANLOAI;
import java.util.ArrayList;

public class PhanLoai_DAO {
    MyDatabase myDatabase;
    SQLiteDatabase db;

    public PhanLoai_DAO(Context c){
        myDatabase = new MyDatabase(c);
    }
    public ArrayList<PHANLOAI> select(){
        ImageView imageView;

        db = myDatabase.getReadableDatabase();
        ArrayList<PHANLOAI> ds = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from "+TABLE_PHANLOAI+"",null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            int src = cursor.getInt(1);
            String name = cursor.getString(2);
            int trangthai = cursor.getInt(3);
            ds.add(new PHANLOAI(id,src,name,trangthai));
        }
        cursor.close();
        return ds;
    }
    public ArrayList<PHANLOAI> select(int _trangthai){
        db = myDatabase.getReadableDatabase();
        ArrayList<PHANLOAI> ds = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from "+TABLE_PHANLOAI+" WHERE "
                +COLUMN_PHANLOAI_TRANG_THAI+ " = ?", new String[]{String.valueOf(_trangthai)});
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            int src = cursor.getInt(1);
            String name = cursor.getString(2);
            int trangthai = cursor.getInt(3);
            ds.add(new PHANLOAI(id,src,name,trangthai));
        }
        cursor.close();
        return ds;
    }
    @SuppressLint("Range")
    public int getAnh(int id){
        int _id = 0;
        db = myDatabase.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_PHANLOAI+" WHERE "
                +COLUMN_PHANLOAI_ID+ " = ?", new String[]{String.valueOf(id)});
        while (cursor.moveToNext()){
             _id = cursor.getInt(cursor.getColumnIndex(COLUMN_PHANLOAI_HINH));
        }
        cursor.close();
        return _id;
    }
    public boolean insert(PHANLOAI phanloai ){
        db= myDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PHANLOAI_HINH,phanloai.getSrc());
        values.put(COLUMN_PHANLOAI_NAME,phanloai.getName());
        values.put(COLUMN_PHANLOAI_TRANG_THAI,phanloai.getTrangthai());
        long row = db.insert(TABLE_PHANLOAI,null,values);
        return (row > 0);
    }
    public boolean delete(int id){
        db= myDatabase.getWritableDatabase();
        int row = db.delete(TABLE_PHANLOAI,COLUMN_PHANLOAI_ID + " = ? ",new String[]{ id +""});
        return (row > 0);
    }
    public boolean updata(PHANLOAI phanloai){
        db= myDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PHANLOAI_HINH,phanloai.getSrc());
        values.put(COLUMN_PHANLOAI_NAME,phanloai.getName());
        values.put(COLUMN_PHANLOAI_TRANG_THAI,phanloai.getTrangthai());
        int row = db.update(TABLE_PHANLOAI,values,COLUMN_PHANLOAI_ID + " = ?",new String[]{phanloai.getId() + ""});
        return (row > 0);
    }
    public PHANLOAI getItemPL(int _id){
        PHANLOAI phanloai = null;
        db = myDatabase.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_PHANLOAI+" WHERE "
                +COLUMN_PHANLOAI_ID+ " = ?", new String[]{String.valueOf(_id)});
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            int src = cursor.getInt(1);
            String name = cursor.getString(2);
            int trangthai = cursor.getInt(3);
            phanloai = new PHANLOAI(id,src,name,trangthai);
        }
        return phanloai;
    }
    public int check(String _name) {
        db= myDatabase.getReadableDatabase();
        ArrayList<PHANLOAI> ds = new ArrayList<>();
        Cursor c = db.rawQuery("select * from "+TABLE_PHANLOAI+" WHERE "+COLUMN_PHANLOAI_NAME+" = ? "
                ,new String[]{String.valueOf(_name)});
        while (c.moveToNext()) {
            int id = c.getInt(0);
            int src = c.getInt(1);
            String name = c.getString(2);
            int trangthai = c.getInt(3);
            PHANLOAI phanloai = new PHANLOAI(id,src,name,trangthai);
            ds.add(phanloai);
        }
        c.close();
        if (ds.size()==0){
            return -1;
        }
        return 1;
    }
}
