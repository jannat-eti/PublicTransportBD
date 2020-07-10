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

public class StoppageDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "publicTransportDB";
    private static final int DATABASE_VERSION = 1;



    Context context;

    private static final String COLUMN_1 = "ID";
    private static final String COLUMN_2 = "STATION_NAME";
    private static final String COLUMN_3 = "ORDERS";
    private static final String COLUMN_4 = "DISTANCE_FROM_UTTARA";
    private static final String COLUMN_5 = "BUS_LIST";

    private static final String TABLE_NAME = "stoppage_table";



    public StoppageDbHelper(Context context){


        super( context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;


    }





    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_STOPPAGE = "CREATE TABLE "
                + TABLE_NAME + "("
                + COLUMN_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_2 + " INT,"
                + COLUMN_3 + " FLOAT,"
                + COLUMN_4 + " TEXT" + ")";

        db.execSQL(CREATE_TABLE_STOPPAGE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }


    public void insertData(Stopage station){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_2, station.getStationName());
        values.put(COLUMN_3, station.getOrder());
        values.put(COLUMN_4, station.getDistanceFromUttara());
        values.put(COLUMN_5, station.getBusList());
        db.insert(TABLE_NAME, null, values);
        db.close();


    }


    public List<Stopage> getAllData (){

        List<Stopage> stopageList = new ArrayList<Stopage>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);



        if (cursor.moveToFirst()){

            do {

                Stopage stopage = new Stopage();
                stopage.setId(Integer.parseInt(cursor.getString(0)));
                stopage.setStationName(cursor.getString(1));
                stopage.setOrder(Integer.parseInt(cursor.getString(2)));
                stopage.setDistanceFromUttara(Float.parseFloat(cursor.getString(3)));
                stopage.setBusList(cursor.getString(4));


                stopageList.add(stopage);

            }while (cursor.moveToNext());
        }


        return stopageList;

    }


    public Stopage getSingleStopageByLocation (String location){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                new String[] {
                        COLUMN_1,
                        COLUMN_2,
                        COLUMN_3,},
                COLUMN_2 + "=?",
                new String[] { location }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Stopage stopage = new Stopage(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                Integer.parseInt(cursor.getString(2)),
                Float.parseFloat(cursor.getString(3)),
                cursor.getString(4)
        );

        return stopage;
    }




}
