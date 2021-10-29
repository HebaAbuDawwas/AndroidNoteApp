package com.intent.noteapp.modelsP;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseSqliteMain extends SQLiteOpenHelper {

    public static final String DataBaseName = "noteapp.db";

    public DatabaseSqliteMain(Context con) {
        super(con, DataBaseName, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE User (id INTEGER PRIMARY KEY AUTOINCREMENT ,fullname TEXT ,username TEXT , email TEXT , password TEXT )");
        db.execSQL("CREATE TABLE Notes (id INTEGER PRIMARY KEY AUTOINCREMENT, note TEXT,notetitle TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL("DROP TABLE IF EXISTS Notes");
        onCreate(db);
    }
//---------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------USER---------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------
    public String insertDataUser(String nameT, String usernameT, String emailT, String passwordT) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("fullname", nameT);
        values.put("username", usernameT);
        values.put("email", emailT);
        values.put("password", passwordT);

        long re = sqLiteDatabase.insert("User", null, values);
        if (re == -1) return "Error ";
        else return "Data Saved successfuly";
    }

    public ArrayList getDataUser(){
        ArrayList <User> arrayList= new ArrayList<User>();
        SQLiteDatabase sqLiteDatabase =this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM User",null);
        cursor.moveToFirst();

        while(cursor.isAfterLast()==false){

            arrayList.add(new User(
                    cursor.getInt(0)
                    ,cursor.getString(1)
                    ,cursor.getString(2),
                    cursor.getString(3)
                    ,cursor.getString(4)));
            cursor.moveToNext();

        }
        return   arrayList;
    }

    public  void UpdateUser(String id , String name ,String username ,String email, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("username",username);
        values.put("email",email);
        values.put("password", password);
        sqLiteDatabase.update("User", values,"id=?" , new String[]{id} );

    }

    public void DeleteUser(String id){
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        sqLiteDatabase.delete("User","id=?",new String []{id});
    }

//---------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------NOTES---------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------

    public String insertDataNote(String note,String notetitle) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("note", note);
        values.put("notetitle",notetitle);
        long re = sqLiteDatabase.insert("Notes", null, values);
        if(re==-1) return "Error ";
        else return "Data Saved successfuly";

    }

    public ArrayList getDataNote(){
        ArrayList <Notes> arrayList= new ArrayList<Notes>();
        SQLiteDatabase sqLiteDatabase =this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Notes",null);
        cursor.moveToFirst();

        while(cursor.isAfterLast()==false){

            arrayList.add(new Notes(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)));
            cursor.moveToNext();

        }
        return   arrayList;
    }



    public  void UpdateNote(String id ,String note,String notetitle){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("note",note);
        values.put("notetitle",notetitle);
        sqLiteDatabase.update("Notes", values,"id=?" , new String[]{id} );

    }


    public void DeleteNote(String id){
        SQLiteDatabase sqLiteDatabase =this.getWritableDatabase();
        sqLiteDatabase.delete("Notes","id=?",new String []{id});
    }






}
