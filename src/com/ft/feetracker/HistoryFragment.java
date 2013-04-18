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
import android.view.LayoutInflater;
import android.content.Intent;
import android.util.TypedValue;
import java.util.Calendar;
import android.graphics.Color;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class HistoryFragment extends Fragment{

private TableLayout history;
private static String selectedTuition="";
private static String selectedMonth="";

public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
	View rootView=inflater.inflate(R.layout.historyfragment,container, false);
	history=(TableLayout)rootView.findViewById(R.id.history);
	populateHistory();
	return rootView;
}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);       
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	populateHistory();
    }

    
    void populateHistory(){
        history.removeAllViews();
        String[][] t=MainActivity.fm.getHistory();
        
        
        for(int i=0;i<t.length;i++){
        	TableRow tr=new TableRow(getActivity());
        	//tr.setLayoutParams(lp);
        	TextView tuition=new TextView(getActivity());
        	tuition.setText(t[i][0]);
        	tuition.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
        	TextView paid=new TextView(getActivity());
        	paid.setText(t[i][1]);
        	TextView month=new TextView(getActivity());
        	month.setText(t[i][2]);
        	tr.addView(tuition);
        	tr.addView(paid);
        	tr.addView(month);
        	history.addView(tr);
        }
        
        history.requestLayout();
        
    }
public void refreshHistory(View view){
    	populateHistory();
    }

}
