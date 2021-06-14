package com.example.btlversion1.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.btlversion1.data.models.Daluu;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="luutruFa.db";
    private static final int DATABASE_VERSION=1;
    public DataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE IF Not Exists tblTrangchuOne(" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "img TEXT," +
                "LinK TEXT," +
                "time TEXT," +
                "noidung TEXT)";
        db.execSQL(sql);
    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addTinTuc(Daluu daLuu){
            ContentValues contentValues= new ContentValues();
            contentValues.put("title",daLuu.getTitle());
            contentValues.put("img",daLuu.getImg());
            contentValues.put("LinK",daLuu.getLink());
            contentValues.put("time",daLuu.getTime());
            SQLiteDatabase db = getWritableDatabase();
            db.insert("tblTrangchuOne",null,contentValues);
          //  db.close();
    }
    public ArrayList<Daluu> allDaLuu(){
        ArrayList<Daluu> list=new ArrayList<>();
        SQLiteDatabase st=getReadableDatabase();
        Cursor rs=st.query("tblTrangchuOne",null,
                null,null,null,null,null);
        while(rs.moveToNext()){
            String TenItem=rs.getString(1);
            String img=rs.getString(2);
            String LinK=rs.getString(3);
            String ngay=rs.getString(4);
            list.add(new Daluu(TenItem,img,LinK,ngay));
        }
        return list;
    }
    public ArrayList<Daluu> readItemBySearchKey(String key){
        ArrayList<Daluu> list = new ArrayList<>();
        String clause = "title LIKE ?";
        String[] args = {"%" + key + "%"};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs=st.query("tblTrangchuOne",null,
                clause,args,null,null,null);
        while(rs.moveToNext()){
            String TenItem=rs.getString(1);
            String img=rs.getString(2);
            String LinK=rs.getString(3);
            String ngay=rs.getString(4);
            list.add(new Daluu(TenItem,img,LinK,ngay));
        }
        return list;
    }
    public void delete(int id){
        SQLiteDatabase st=this.getWritableDatabase();
        String query="DELETE FROM tblTrangchuOne WHERE Id='"+id+"'";
        st.execSQL(query);
    }
}
