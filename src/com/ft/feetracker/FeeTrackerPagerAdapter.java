package com.ft.feetracker;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.ViewGroup;
import android.content.Intent;
import java.util.Calendar;
import android.graphics.Color;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class FeeTrackerPagerAdapter extends FragmentStatePagerAdapter{

public static final int NUM_FRAGMENTS=2;
private Fragment history;
private Fragment tuitions;
public FeeTrackerPagerAdapter(FragmentManager fm){
	super(fm);
	history=new HistoryFragment();
	tuitions=new TuitionsFragment();
}

public Fragment getItem(int i){
	//Fragment frag=new HistoryFragment();
	//Bundle args=new Bundle();
	//args.putInt(HistoryFragment.ARG_OBJECT, i + 1);
        //frag.setArguments(args);
	switch(i){
		case 0:
			return history;
		case 1:
			return tuitions;
		default:
			return null;
	}
	
}

public int getCount(){
	return NUM_FRAGMENTS;
}

public CharSequence getPageTitle(int pos){
	return new String("title");
}
}
