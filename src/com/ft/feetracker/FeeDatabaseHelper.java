package com.ft.feetracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FeeDatabaseHelper extends SQLiteOpenHelper{

private static final String DB_NAME="fee.db";
private static final int DB_VERSION=1;

 static final String TUITION_TABLE="tuitions";
 static final String FEE_SUBMIT_TABLE="submssions";
 static final String TT_COL_ID="id";
 static final String TT_COL_NAM="name";
 static final String TT_COL_FEE="fee";
 static final String TT_COL_DESC="description";
 static final String ST_COL_ID="pay_id";
 static final String ST_COL_NAM="name";
 static final String ST_COL_MON="month";
 static final String ST_COL_DAT="date";

public FeeDatabaseHelper(Context con){
	super(con,DB_NAME,null,DB_VERSION);
}

public void onCreate(SQLiteDatabase db){
	String  createTTSQL="create table if not exists "+TUITION_TABLE+" ("+TT_COL_ID+" integer primary key autoincrement, "+TT_COL_NAM+" text not null, "+TT_COL_FEE+" integer not null, "+TT_COL_DESC+" text);";
	String createSTSQL=" create table if not exists "+FEE_SUBMIT_TABLE+" ("+ST_COL_ID+" integer primary key autoincrement, "+ST_COL_NAM+" text not null, "+ST_COL_MON+" text not null, "+ST_COL_DAT+" text not null"+");";
	db.execSQL(createTTSQL);
	db.execSQL(createSTSQL);
}

public void onUpgrade(SQLiteDatabase db, int old, int nw){
	Log.w(FeeDatabaseHelper.class.getName(),
        "Upgrading database from version " + old + " to "
            + nw + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TUITION_TABLE);
    db.execSQL("DROP TABLE IF EXISTS " + FEE_SUBMIT_TABLE);
    onCreate(db);
}

}
