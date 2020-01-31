package kr.hs.dgsw.shoppingmall.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ItemDBHelpler extends SQLiteOpenHelper {

    public ItemDBHelpler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table item(" +
                "id integer primary key autoincrement not null," +
                "imageId integer not null," +
                "name string not null," +
                "price integer not null," +
                "stock integer not null" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table item";
        db.execSQL(sql);
        onCreate(db);
    }

    public long insertDB(Item item){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", item.getId());
        cv.put("imageId", item.getImageId());
        cv.put("name", item.getName());
        cv.put("price", item.getPrice());
        cv.put("stock", item.getStock());
        return db.insert("item", null, cv);
    }

    public ArrayList<Item> getAllItems(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Item> itemList = new ArrayList<Item>();

        Cursor cursor = db.query("item", null, null,null, null, null, null);
        while(cursor.moveToNext()) {
            Item item = new Item();
            item.setId(cursor.getInt(cursor.getColumnIndex("id")));
            item.setImageId(cursor.getInt(cursor.getColumnIndex("imageId")));
            item.setName(cursor.getString(cursor.getColumnIndex("name")));
            item.setPrice(cursor.getInt(cursor.getColumnIndex("price")));
            item.setStock(cursor.getInt(cursor.getColumnIndex("stock")));
            itemList.add(item);
        }
        return itemList;
    }

    public void createDB(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "create table item(" +
                "id integer primary key autoincrement not null," +
                "imageId integer not null," +
                "name string not null," +
                "price integer not null," +
                "stock integer not null" +
                ")";
        db.execSQL(sql);
    }

    public void resetDB(){
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("drop table if exists item");
    }
}
