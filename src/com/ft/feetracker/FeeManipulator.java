package com.ft.feetracker;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.SQLException;
import android.database.Cursor;
import android.util.Log;

public class FeeManipulator{
private FeeDatabaseHelper fdh;
private SQLiteDatabase db;

public FeeManipulator(Context context){
	fdh=new FeeDatabaseHelper(context);
}

public void open() throws SQLException{
	db=fdh.getWritableDatabase();
}

public void close(){
	fdh.close();
}

public void addTuition(String name, int fee, String desc){
	ContentValues val=new ContentValues();
	val.put(FeeDatabaseHelper.TT_COL_NAM,name);
	val.put(FeeDatabaseHelper.TT_COL_FEE,new Integer(fee));
	val.put(FeeDatabaseHelper.TT_COL_DESC,desc);
	long in=db.insert(FeeDatabaseHelper.TUITION_TABLE,null,val);
}

public String[] getTuitions(){
	Cursor cur=db.query(FeeDatabaseHelper.TUITION_TABLE,new String[]{FeeDatabaseHelper.TT_COL_NAM},null,null,null,null,null,null);
	String[] t=new String[cur.getCount()];
	int c=0;
	while(cur.moveToNext()){
		t[c++]=cur.getString(0);
	}
	return t;
}

public String[][] getHistory(){
	//select only those which have delStatus 0 ie, not deleted
	Cursor cur=db.query(FeeDatabaseHelper.FEE_SUBMIT_TABLE,new String[]{FeeDatabaseHelper.ST_COL_NAM,FeeDatabaseHelper.ST_COL_MON,FeeDatabaseHelper.ST_COL_DAT},ST_COL_DEL_STATUS+"=",new String[]{"0"},null,null,null,null);
	String[][] t=new String[cur.getCount()][3];
	int c=0;
	while(cur.moveToNext()){
		
		t[c][0]=cur.getString(0);
		t[c][1]=cur.getString(1);
		t[c][2]=cur.getString(2);
		c++;
	}
	return t;
}

public String[] getHistory(String tuition){
	
	Cursor cur=db.query(FeeDatabaseHelper.FEE_SUBMIT_TABLE,new String[]{FeeDatabaseHelper.ST_COL_MON,FeeDatabaseHelper.ST_COL_DAT},FeeDatabaseHelper.ST_COL_NAM+"=",new String[]{tuition},null,null,null,null);
	String[] t=new String[cur.getCount()];
	int c=0;
	while(cur.moveToNext()){
		t[c++]=cur.getString(0)+" : "+cur.getString(1);
	}
	return t;
}

public void payTuition(String name, String month, String date){
	ContentValues val=new ContentValues();
	val.put(FeeDatabaseHelper.ST_COL_NAM,name);
	val.put(FeeDatabaseHelper.ST_COL_MON,month);
	val.put(FeeDatabaseHelper.ST_COL_DAT,date);
	long in=db.insert(FeeDatabaseHelper.FEE_SUBMIT_TABLE,null,val);
}

public void removeTuition(String name){
	String sql="UPDATE "+FeeDatabaseHelper.TUITION_TABLE+" SET "+FeeDatabaseHelper.TT_COL_DEL_STATUS+" = 1 WHERE "+FeeDatabaseHelper.TT_COL_NAM+" LIKE \'"+name+"\'";
	db.execSQL(sql);
}


}
