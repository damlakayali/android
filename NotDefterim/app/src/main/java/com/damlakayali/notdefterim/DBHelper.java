package com.damlakayali.notdefterim;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by damla on 29.06.2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME   = "damlaDB";
    // Contacts table name
    private static final String TABLE_USERS = "users";
    private  static final String TABLE_NOTES= "notlar";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    String sql = "CREATE TABLE " + TABLE_USERS + "(id INTEGER PRIMARY KEY,email TEXT,pass TEXT,name TEXT, surname TEXT" + ")";
    String noteSql= "CREATE TABLE " + TABLE_NOTES + "(id INTEGER PRIMARY KEY, header TEXT, content TEXT,priority INTEGER"+")";
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(sql);
        db.execSQL(noteSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }

    public void insertNotes(Note note){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("header",note.getHeader());
        values.put("content",note.getContent());
        values.put("priority",note.getPriority());

        db.insert(TABLE_NOTES,null,values);
        db.close();
    }

    public List<Note> getAllNotes(){

        List<Note> notes = new ArrayList<Note>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM notlar",null);

        while (cursor.moveToNext()) {
            Note note = new Note();
            note.setId(cursor.getInt(0));
            note.setHeader(cursor.getString(1));
            note.setContent(cursor.getString(2));
            note.setPriority(cursor.getInt(3));

            notes.add(note);
        }


        return  notes;
    }

    public void  insertUser(User user){
        SQLiteDatabase db= this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("email",user.getEmail());
        values.put("pass",user.getPassword());
        values.put("name",user.getName());
        values.put("surname",user.getSurname());

        db.insert(TABLE_USERS,null,values);
        db.close();
    }

    public boolean queryUser(String email,String pass){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor= db.rawQuery("SELECT * FROM users WHERE email = '"+email+"' AND pass = '"+pass+"' ",null);

        cursor.moveToFirst();
        if(cursor.getCount() > 0){
           return true;
        }
        else return false;
    }

    public boolean queryNote(String baslik){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor= db.rawQuery("SELECT * FROM notlar WHERE header = '"+baslik+"' ",null);

        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            return true;
        }
        else return false;
    }
    public User getUser(String email){
        User user=new User();
        SQLiteDatabase db= this.getWritableDatabase();

        Cursor cursor=db.rawQuery("SELECT * FROM users WHERE email = '"+email+"' ",null);

        if(cursor!=null){
           user.setId(cursor.getInt(0));
            user.setEmail(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setName(cursor.getString(3));
            user.setSurname(cursor.getString(4));
        }

        return user;
    }

    public  Note getNote(int id){

        Note n;

        List<Note> notes = new ArrayList<Note>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM notlar",null);

        while (cursor.moveToNext()) {
            Note note = new Note();
            note.setId(cursor.getInt(0));
            note.setHeader(cursor.getString(1));
            note.setContent(cursor.getString(2));
            note.setPriority(cursor.getInt(3));

            notes.add(note);
        }

        n=notes.get(id);


        return n;
    }

    public void NoteDelete(int id){
        SQLiteDatabase db= this.getWritableDatabase();

        /*
         long asd = id;
  // asd=Integer.parseInt(id);
  ContentValues cv = new ContentValues();
  cv.put("ad", adi);
  SQLiteDatabase db = veritabani.getWritableDatabase();
  db.update("ARKTABLE", cv, "id" + "=" + asd, null);
         */
        db.delete("notlar","id"+"="+id,null);

    }

    public void NoteUpdata(int id,String header,String content,int priority){

        ContentValues cv = new ContentValues();
        cv.put("header",header);
        cv.put("content",content);
        cv.put("priority",priority);
        SQLiteDatabase db = this.getWritableDatabase();
        db.update("ARKTABLE", cv, "id" + "=" + id, null);
    }
}
