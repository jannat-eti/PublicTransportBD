package com.example.jsonconvert.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jsonconvert.Bus;
import com.example.jsonconvert.Stopage;

import java.util.ArrayList;
import java.util.List;

public class BusDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "publicTransportDB2";
    private static final int DATABASE_VERSION = 1;



    private static final String COLUMN_1 = "ID";
    private static final String COLUMN_2 = "STATION_NAME";

    private static final String TABLE_NAME = "bus_table";

    public BusDbHelper(Context context){


        super( context, DATABASE_NAME, null, DATABASE_VERSION);

    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_STOPPAGE = "CREATE TABLE "
                + TABLE_NAME + "("
                + COLUMN_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_2 + " TEXT" + ")";

        db.execSQL(CREATE_TABLE_STOPPAGE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void insertData(Bus bus){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(COLUMN_2, bus.getBusName());

        db.insert(TABLE_NAME, null, values);
        db.close();


    }

    public List<Bus> getAllBus(){

        List<Bus> busList = new ArrayList<Bus>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){

            do {

                Bus bus = new Bus();
                bus.setId(Integer.parseInt(cursor.getString(0)));
                bus.setBusName(cursor.getString(1));


                busList.add(bus);

            }while (cursor.moveToNext());
        }


        return busList;
    }

}
