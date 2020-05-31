package com.example.marvel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseData extends SQLiteOpenHelper {
    private static final String TAG = "Marvelusers";
    private static final String TABLE_NAME = "users";
    private static final String COL1 ="ID";
    private static final String COL2 ="firstname";
    private static final String COL3 ="lastname";
    private static final String COL4 ="nickname";
    private static final String COL5 ="email";
    private static final String COL6 ="password";

    public DatabaseData(Context context){
        super(context, TABLE_NAME, null,1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createtable();
    }

    public void createtable(){
        SQLiteDatabase db = this.getWritableDatabase();
        String createTable  = "CREATE TABLE "+ TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,"+ COL2 +" VARCHAR(30), " + COL3 + " VARCHAR(30), "+ COL4 +" VARCHAR(30) UNIQUE, "+ COL5 +" VARCHAR(30) UNIQUE,"+ COL6 +" VARCHAR(30)) " ;
        db.execSQL(createTable);
        Log.d(TAG, "Created Table");
    }

    public Cursor getData(String item, String item2){
        SQLiteDatabase db = this.getReadableDatabase();
        String loginquery = "SELECT nickname FROM users WHERE email='"+ item+"' AND password= '"+item2+"'";

        try{
            Cursor data = db.rawQuery(loginquery,null);
            return data;
        }
        catch (SQLException e){
        return null;
        }
    }
    public void cleartable(){
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteeverything = "DROP TABLE users";
        db.execSQL(deleteeverything);
    }


    public boolean addData(String item,String item2,String item3,String item4,String item5){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,item);
        contentValues.put(COL3,item2);
        contentValues.put(COL4,item3);
        contentValues.put(COL5,item4);
        contentValues.put(COL6,item5);
        Log.d(TAG, "addData: Adding "+ item + " , "+ item2 + " , "+ item3 + " , "+ item4 + " , "+ item5 + " to "+ TABLE_NAME);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if (result ==-1){
            return false;
        }else{
            return true;
        }
    }

}
