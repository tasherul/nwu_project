package com.example.time_parallel;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by User on 2/28/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private  static final String DataBaseName = "TimeParallel.db";
    private static final String TABLE_NAME = "TimeTableManagement";

    private static final String ID = "ID";
    private static final String Title = "Title";
    private static final String Discription   =  "Discription";
    private static final String Weekly = "Weekly";
    private static final String StartTime = "StartTime";
    private static final String Endtime = "Endtime";
    private static final String Type = "Type";
    private static final String SDate = "SDate";
    public DatabaseHelper(Context context) {

        super(context, DataBaseName, null, 1);
         //SQLiteDatabase db  = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable =   "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Title +" varchar(500)   , " + Discription +" varchar(1000)  , "+ Weekly +" varchar(500) " +
                ",  "+ StartTime + " varchar(500),  "+ Endtime + " varchar(500),   "+ Type +  " varchar(500) , "+ SDate +   " varchar(500) )";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP  TABLE " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String title,String discription,String weekly,String startTime, String endtime, String type, String sDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        //onCreate(db);
        ContentValues contentValues = new ContentValues();
        contentValues.put(Title, title);
        contentValues.put(Discription, discription);
        contentValues.put(Weekly, weekly);
        contentValues.put(StartTime, startTime);
        contentValues.put(Endtime, endtime);
        contentValues.put(Type, type);
        contentValues.put(SDate, sDate);
        Log.d(TAG, "addData: Adding a Schedule" );

        long result = db.insert(TABLE_NAME, null, contentValues);

         //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME ;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getDataByType(String Types){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME +" WHERE Type='" +Types+"'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    /**
     * Returns only the ID that matches the name passed in

     * @return
     */
    public Cursor getItemID(String ID){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE ID=" + ID ;
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    public void UpdateData(  String id, String Query){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + Query +
                " WHERE  ID="  + id ;
        Log.d(TAG, "updateName: query: " + query);
        //Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    /**
     * Delete from database
     * @param id
     *  @param
     */
    public void DeleteData(String  id ){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE ID="  + id ;
        Log.d(TAG, "deleteName: query: " + query);
        //Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }
 /**
     public void MessageShow(String Massege)
    {
        Toast.makeText(this, Massege, Toast.LENGTH_SHORT).show();
    }
    */


}























