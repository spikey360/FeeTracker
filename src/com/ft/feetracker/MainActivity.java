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
import android.content.Intent;
import java.util.Calendar;
import android.graphics.Color;


public class MainActivity extends Activity
{

static FeeManipulator fm;

private TableLayout history;

private static String selectedTuition="";
private static String selectedMonth="";


    /** Called when the activity is first created. */
    //initialize or open database here
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        history=(TableLayout)findViewById(R.id.history);
        
        
        fm=new FeeManipulator(this);
        fm.open();
        
        
        
        populateHistory();
        
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	populateHistory();
    }

    
    void populateHistory(){
        history.removeAllViews();
        String[][] t=fm.getHistory();
        
        
        for(int i=0;i<t.length;i++){
        	TableRow tr=new TableRow(this);
        	//tr.setLayoutParams(lp);
        	TextView tuition=new TextView(this);
        	tuition.setText(t[i][0]);
        	TextView paid=new TextView(this);
        	paid.setText(t[i][1]);
        	TextView month=new TextView(this);
        	month.setText(t[i][2]);
        	tr.addView(tuition);
        	tr.addView(paid);
        	tr.addView(month);
        	history.addView(tr);
        }
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	MenuInflater inflater=getMenuInflater();
    	inflater.inflate(R.menu.main, menu);
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    Intent intent=null;
    	switch(item.getItemId()){
    		case R.id.itemAddTuition:
    			intent=new Intent(this, AddTuitionActivity.class);
    			startActivity(intent);
    			return true;
    		case R.id.itemPayTuition:
    			intent=new Intent(this, AddPaymentActivity.class);
    			startActivity(intent);
    			return true;
    		case R.id.itemQuit:
    			finish();
    			return true;
    		default:
    			return false;
    	}
    	
    }
    
    public void refreshHistory(View view){
    	populateHistory();
    }

}
