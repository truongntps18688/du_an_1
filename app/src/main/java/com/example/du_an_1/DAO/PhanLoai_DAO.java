package com.example.du_an_1.DAO;

import static com.example.du_an_1.utilities.contans.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.du_an_1.dataBase.MyDatabase;
import com.example.du_an_1.model.PHANLOAI;
import java.util.ArrayList;

public class PhanLoai_DAO {
    MyDatabase myDatabase;
    SQLiteDatabase db;

    public PhanLoai_DAO(Context c){
        myDatabase = new MyDatabase(c);
    }
    public ArrayList<PHANLOAI> select(){
        db = myDatabase.getReadableDatabase();
        ArrayList<PHANLOAI> ds = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from "+TABLE_PHANLOAI+"",null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String src = cursor.getString(1);
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
            String src = cursor.getString(1);
            String name = cursor.getString(2);
            int trangthai = cursor.getInt(3);
            ds.add(new PHANLOAI(id,src,name,trangthai));
        }
        cursor.close();
        return ds;
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
}
