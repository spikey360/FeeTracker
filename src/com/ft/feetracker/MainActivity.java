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
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import java.util.Calendar;

public class MainActivity extends Activity implements OnItemSelectedListener
{

private FeeManipulator fm;
//private EditText name;
//private EditText fee;
private EditText date;
private Spinner payToSpinner;
private Spinner monthSpinner;
private ListView history;

private static String selectedTuition="";
private static String selectedMonth="";


    /** Called when the activity is first created. */
    //initialize or open database here
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //name=(EditText)findViewById(R.id.edit_msg_text);
        //fee=(EditText)findViewById(R.id.edit_fee_text);
        date=(EditText)findViewById(R.id.edit_date);
        payToSpinner=(Spinner)findViewById(R.id.tuition_spinner);
        monthSpinner=(Spinner)findViewById(R.id.month_spinner);
        //edDateSpinner=(Spinner)findViewById(R.id.edit_date_spinner);
        history=(ListView)findViewById(R.id.history);
        //populate pay month
        ArrayAdapter<CharSequence> ma=ArrayAdapter.createFromResource(this, R.array.months_array,android.R.layout.simple_spinner_dropdown_item);
        ma.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(ma);
        /*
        //populate date
        ArrayAdapter<CharSequence> eda=ArrayAdapter.createFromResource(this, R.array.date_array,android.R.layout.simple_spinner_dropdown_item);
        eda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edDateSpinner.setAdapter(eda);
        //populate month
        ArrayAdapter<CharSequence> ema=ArrayAdapter.createFromResource(this, R.array.months_array,android.R.layout.simple_spinner_dropdown_item);
        ema.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edMonthSpinner.setAdapter(ema);
        
        Calendar c=Calendar.getInstance();
        int d=c.get(Calendar.DAY_OF_MONTH);
        int m=c.get(Calendar.MONTH);
        int y=c.get(Calendar.YEAR);
        
        //populate year
        ArrayAdapter<String> eya=(ArrayAdapter<String>)new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item);
        for(int i=y-1;i<y+3;i++){
        	eya.add(""+i);
        }
        eya.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        edYearSpinner.setAdapter(eya);
        */
        
        Calendar c=Calendar.getInstance();
        int d=c.get(Calendar.DAY_OF_MONTH);
        int m=c.get(Calendar.MONTH);
        int y=c.get(Calendar.YEAR);
        date.setText(new String(d+ "."+(m+1)+"."+y),TextView.BufferType.EDITABLE);
        
        fm=new FeeManipulator(this);
        fm.open();
        
        
        populateTuitions();
        //populate history
        populateHistory();
        //monthSpinner.setOnItemSelectedListener(this);
        //payToSpinner.setOnItemSelectedListener(this);
    }
    
    
    public void payTuition(View view){
    	selectedTuition=payToSpinner.getSelectedItem().toString();
    	selectedMonth=monthSpinner.getSelectedItem().toString();
    	fm.payTuition(selectedTuition,selectedMonth,date.getText().toString());
    	Toast.makeText(getApplicationContext(),"Paid "+selectedTuition+" for the month of "+selectedMonth+" on "+date.getText().toString(),Toast.LENGTH_SHORT).show();
    	//update history
    	populateHistory();
    }
    
    public void onItemSelected(AdapterView<?> parent, View v, int pos, long id){
    	if (id==R.id.tuition_spinner){
    	selectedTuition=parent.getItemAtPosition(pos).toString();
    	return;
    	}else if(id==R.id.month_spinner){
    	selectedMonth=parent.getItemAtPosition(pos).toString();
    	}
    }
    public void onNothingSelected(AdapterView<?> parent){
    }
    
    private void populateTuitions(){
    	ArrayAdapter<String> na=(ArrayAdapter<String>)new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item);
        String[] t=fm.getTuitions();
        for(int i=0;i<t.length;i++){
        	na.add(t[i]);
        }
        na.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        payToSpinner.setAdapter(na);
    }
    
    private void populateHistory(){
    	ArrayAdapter<String> ha=(ArrayAdapter<String>)new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item);
        String[] t=fm.getHistory();
        for(int i=0;i<t.length;i++){
        	ha.add(t[i]);
        }
        ha.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        history.setAdapter(ha);
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
    			return true;
    		case R.id.itemQuit:
    		default:
    			return true;
    	}
    	return false;
    }

}
