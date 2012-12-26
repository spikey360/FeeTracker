package com.ft.feetracker;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;
import android.view.View;


public class AddTuitionActivity extends Activity{

private EditText name;
private EditText fee;

@Override
public void onCreate(Bundle savedInstanceState){
	super.onCreate(savedInstanceState);
	setContentView(R.layout.addtuition);
	
	name=(EditText)	findViewById(R.id.editNewTuitionName);
	fee=(EditText)findViewById(R.id.editNewTuitionFee);
	
	
}

public void insertTuition(View view){
	if(MainActivity.fm!=null){
	//perform validation
	if (name.getText().toString()==null || fee.getText().toString()==null || name.getText().toString().length()<=0 || name.getText().toString().length()<=0 ){
		Toast.makeText(getApplicationContext(),"Form fill error",Toast.LENGTH_SHORT).show();
		return;
	}
	MainActivity.fm.addTuition(name.getText().toString(), Integer.parseInt(fee.getText().toString()),"Tuition");
	//give a toast message
	Toast.makeText(getApplicationContext(),"Added "+name.getText().toString(),Toast.LENGTH_SHORT).show();
	name.setText(new String(),TextView.BufferType.EDITABLE);
	fee.setText(new String(), TextView.BufferType.EDITABLE);
	}else{
		System.err.println("Error while accessing db object");
	}
}


}
