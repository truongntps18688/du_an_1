package com.example.du_an_1.DAO;

import static com.example.du_an_1.utilities.contans.*;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.du_an_1.Database.MyDatabase;
import com.example.du_an_1.Entity.USER;

import java.util.ArrayList;
import java.util.List;

public class user_DAO {
    private MyDatabase myDatabase;
    private SQLiteDatabase db;
    public user_DAO(Context c) {
        myDatabase = new MyDatabase(c);
    }
    public List<USER> select() {
        db = myDatabase.getReadableDatabase();
        ArrayList<USER> ds = new ArrayList<>();
        Cursor c = db.rawQuery("select * from " + TABLE_USER + "", null);
        while (c.moveToNext()) {
            String tk = c.getString(0);
            String mk = c.getString(1);
            String ch = c.getString(2);
            String tl = c.getString(3);
            USER user = new USER(tk,mk,ch,tl);
            ds.add(user);
        }
        c.close();
        return ds;
    }
    public Boolean insert(USER u) {
        db = myDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_TK, u.getTk());
        values.put(COLUMN_USER_MK, u.getMk());
        values.put(COLUMN_USER_CauHoi, u.getCauhoi());
        values.put(COLUMN_USER_TL, u.getTl());
        long row = db.insert(TABLE_USER, null, values);
        return (row > 0);
    }
    public Boolean updata(USER u) {
        db = myDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_MK, u.getMk());
        values.put(COLUMN_USER_CauHoi, u.getCauhoi());
        values.put(COLUMN_USER_TL, u.getTl());
        int row = db.update(TABLE_USER, values, COLUMN_USER_TK + " = ?", new String[]{u.getTk()});
        return (row > 0);
    }
    public Boolean updata_quen_mk(USER u) {
        db = myDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_MK, u.getMk());
        int row = db.update(TABLE_USER, values, COLUMN_USER_TK + " = ?", new String[]{u.getTk()});
        return (row > 0);
    }
    public Boolean updata_CauHoi(USER u) {
        db = myDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_CauHoi, u.getCauhoi());
        int row = db.update(TABLE_USER, values, COLUMN_USER_TK + " = ?", new String[]{u.getTk()});
        return (row > 0);
    }
    public Boolean updata_CauTL(USER u) {
        db = myDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_TL, u.getTl());
        int row = db.update(TABLE_USER, values, COLUMN_USER_TK + " = ?", new String[]{u.getTk()});
        return (row > 0);
    }
}
