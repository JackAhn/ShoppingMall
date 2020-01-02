package kr.hs.dgsw.shoppingmall.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UserDBHelper extends SQLiteOpenHelper {

    public UserDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table user (" +
                " id integer primary key autoincrement not null," +
                " name text not null," +
                " userid text not null, " +
                " userpw text not null, " +
                " gender integer not null, " +
                " age integer not null )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table user";
        db.execSQL(sql);
        onCreate(db);
    }

    public long insertDB(User user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", user.getName());
        cv.put("userid", user.getUserId());
        cv.put("userpw", user.getUserPw());
        cv.put("gender", user.getGender());
        cv.put("age", user.getAge());
        return db.insert("user", null, cv);
    }

    public User getUser(String id, String pw){
        SQLiteDatabase db = getReadableDatabase();
        String [] whereArgs = {id, pw};
        Cursor cursor = db.query("user", null, "userid = ? and userpw = ?", whereArgs, null, null, null);
        if(cursor.moveToNext()){
            User findUser = new User();
            findUser.setName(cursor.getString(cursor.getColumnIndex("name")));
            return findUser;
        }
        else{
            return null;
        }
    }

}
