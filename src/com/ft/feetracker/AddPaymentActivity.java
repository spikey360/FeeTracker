package com.ft.feetracker;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import android.view.View;

import java.util.Calendar;

public class AddPaymentActivity extends Activity implements OnItemSelectedListener{

//private EditText date;
private Spinner payToSpinner;
private Spinner monthSpinner;
private Spinner payDateSpinner;
private Spinner payMonthSpinner;
private Spinner payYearSpinner;
//private ListView history;

private static String selectedTuition="";
private static String selectedMonth="";

@Override
public void onCreate(Bundle savedInstance){
	super.onCreate(savedInstance);
	setContentView(R.layout.addpayment);
	
	payToSpinner=(Spinner)findViewById(R.id.tuitionNameSpinner);
	monthSpinner=(Spinner)findViewById(R.id.monthSpinner);
	
	payDateSpinner=(Spinner)findViewById(R.id.payDateSpinner);
	payMonthSpinner=(Spinner)findViewById(R.id.payMonthSpinner);
	payYearSpinner=(Spinner)findViewById(R.id.payYearSpinner);
	
	//populate month for which to be paid
        ArrayAdapter<CharSequence> ma=ArrayAdapter.createFromResource(this, R.array.months_array,android.R.layout.simple_spinner_dropdown_item);
        ma.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(ma);
	
	//populate date
        ArrayAdapter<CharSequence> eda=ArrayAdapter.createFromResource(this, R.array.date_array,android.R.layout.simple_spinner_dropdown_item);
        eda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        payDateSpinner.setAdapter(eda);
        //populate month
        ArrayAdapter<CharSequence> ema=ArrayAdapter.createFromResource(this, R.array.months_array,android.R.layout.simple_spinner_dropdown_item);
        ema.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        payMonthSpinner.setAdapter(ema);
        //populate tuitions
        ArrayAdapter<String> na=(ArrayAdapter<String>)new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item);
        String[] t=MainActivity.fm.getTuitions();
        for(int i=0;i<t.length;i++){
        	na.add(t[i]);
        }
        na.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        payToSpinner.setAdapter(na);
        
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
        payYearSpinner.setAdapter(eya);
        
        payDateSpinner.setSelection(d-1);
        payMonthSpinner.setSelection(m);
        payYearSpinner.setSelection(1);
}

public void payTuition(View view){
    	selectedTuition=payToSpinner.getSelectedItem().toString();
    	selectedMonth=monthSpinner.getSelectedItem().toString();
    	String dateString=payDateSpinner.getSelectedItem().toString()+"-"+payMonthSpinner.getSelectedItem().toString()+"-"+payYearSpinner.getSelectedItem().toString();
    	MainActivity.fm.payTuition(selectedTuition,selectedMonth,dateString);
    	Toast.makeText(getApplicationContext(),"Paid "+selectedTuition+" for the month of "+selectedMonth+" on "+dateString,Toast.LENGTH_SHORT).show();
    	//MainActivity.populateHistory();
}

public void onItemSelected(AdapterView<?> parent, View v, int pos, long id){
    	if (id==R.id.tuitionNameSpinner){
    	selectedTuition=parent.getItemAtPosition(pos).toString();
    	return;
    	}else if(id==R.id.monthSpinner){
    	selectedMonth=parent.getItemAtPosition(pos).toString();
    	}
    }

public void onNothingSelected(AdapterView<?> parent){
}

}

