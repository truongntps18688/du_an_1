package com.example.du_an_1.dataBase;

import static com.example.du_an_1.utilities.contans.*;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {
    public MyDatabase(Context context) {
        super(context, DB_NAME , null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String q = "CREATE TABLE IF NOT EXISTS "+TABLE_USER+"" +
                "("+COLUMN_USER_TK+" TEXT PRIMARY KEY," +
                ""+COLUMN_USER_MK+" TEXT ," +
                ""+COLUMN_USER_CauHoi+" TEXT ," +
                ""+COLUMN_USER_TL+" TEXT )";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS "+TABLE_PHANLOAI+"" +
                "("+COLUMN_PHANLOAI_ID+" INTEGER PRIMARY KEY autoincrement," +
                ""+COLUMN_PHANLOAI_HINH+" TEXT ," +
                ""+COLUMN_PHANLOAI_NAME+" TEXT ," +
                ""+COLUMN_PHANLOAI_TRANG_THAI+" INTEGER)";
        db.execSQL(q);

        q = "CREATE TABLE IF NOT EXISTS "+TABLE_GIAODICH+"" +
                "("+COLUMN_GIAODICH_ID+" INTEGER PRIMARY KEY autoincrement," +
                ""+COLUMN_GIAODICH_NGAY+" LONG ," +
                ""+COLUMN_GIAODICH_TIME+" TEXT ," +
                " "+COLUMN_GIAODICH_TIEN+" REAL," +
                ""+COLUMN_GIAODICH_MOTA+" TEXT," +
                ""+COLUMN_GIAODICH_PHANLOAI_ID+" TEXT," +
                ""+COLUMN_GIAODICH_PHANLOAI_TRANGTHAI+" INTEGER, " +
                "FOREIGN KEY ("+COLUMN_GIAODICH_PHANLOAI_ID+")" +
                " REFERENCES  "+TABLE_PHANLOAI+"("+COLUMN_PHANLOAI_ID+")  )";
        db.execSQL(q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        int onUpgrade = oldVersion + 1;
        while (onUpgrade <= newVersion){
            switch (onUpgrade){
                case 2:
                    break;
                case  3:
                    break;
                default:
                    break;
            }
            onUpgrade++;
        }
    }
}
