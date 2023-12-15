package com.example.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLiteHelper extends SQLiteOpenHelper {


    //constructor
    SQLiteHelper(Context context,
                     String name,
                     SQLiteDatabase.CursorFactory factory,
                     int version){
        super(context,name,factory,version);

    }

    public void queryData(String sql)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql);
    }

    public void insertData(String name, String age, String phone, byte[] image){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String sql = "INSERT INTO RECORD VALUES(NULL,?,?,?,?)";

        SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(sql);
        sqLiteStatement.clearBindings();

        sqLiteStatement.bindString(1, name);
        sqLiteStatement.bindString(2, age);
        sqLiteStatement.bindString(3,phone);
        sqLiteStatement.bindBlob(4, image);

        sqLiteStatement.executeInsert();

    }

    public void UpdateData(String name, String age, String phone, byte[] image, int id){

        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE RECORD SET name=?, age=?, phone=?, image=? WHERE id=?";

        SQLiteStatement sqLiteStatement = database.compileStatement(sql);

        sqLiteStatement.bindString(1, name);
        sqLiteStatement.bindString(2, age);
        sqLiteStatement.bindString(3,phone);
        sqLiteStatement.bindBlob(4, image);
        sqLiteStatement.bindDouble(5,(double)id);

        sqLiteStatement.execute();
        database.close();

    }

    public void deleteData(int id) {

        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM RECORD WHERE id=?";
        SQLiteStatement sqLiteStatement = database.compileStatement(sql);
        sqLiteStatement.clearBindings();
        sqLiteStatement.bindDouble(1,(double)id);

        sqLiteStatement.execute();
        database.close();
    }

    public Cursor getData(String sql){

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery(sql,null);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
