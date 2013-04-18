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
import android.widget.RelativeLayout;
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

public class TuitionsFragment extends Fragment{

private TableLayout tuitions;

public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
	View rootView=inflater.inflate(R.layout.tuitionsfragment,container, false);
	tuitions=(TableLayout)rootView.findViewById(R.id.tuitions);
	populateTuitions();
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
    }
    
    void populateTuitions(){
    	tuitions.removeAllViews();
    	String[][] t=MainActivity.fm.getTuitions();
    	for(int i=0;i<t.length;i++){
    		TableRow tr=new TableRow(getActivity());
    		TextView tuition=new TextView(getActivity());
    		tuition.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
    		tuition.setText(t[i][0]);
    		TextView fees=new TextView(getActivity());
    		fees.setText(t[i][1]);
    		tr.addView(tuition);
    		tr.addView(fees);
    		tuitions.addView(tr);
    	}
    	tuitions.requestLayout();
    }

}
