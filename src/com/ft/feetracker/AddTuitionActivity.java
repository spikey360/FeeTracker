package com.ft.feetracker;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;
import android.database.sqlite.SQLiteDatabase;


public class AddTuitionActivity extends Activity{

private FeeManipulator fm;
private EditText name;
private EditText fee;

@Override
public void onCreate(Bundle savedInstanceState){
	super.onCreate(savedInstanceState);
	setContentView(R.layout.addTuition);
	
	name=(EditText)	findViewById(R.id.editNewTuitionName);
	fee=(EditText)findViewById(R.id.editNewTuitionFee);
	
	//init db
	fm=new FeeManipulator(this);
	fm.open();
}

public void insertTuition(View view){
	fm.addTuition(name.getText().toString(), Integer.parseInt(fee.getText().toString()),"Tuition");
	//give a toast message
	Toast.makeText(getApplicationContext(),"Added"+name.getText().toString(),Toast.LENGTH_SHORT).show();
	name.setText(new String(),TextView.BufferType.EDITABLE);
	fee.setText(new String(), TextView.BufferType.EDITABLE);
}


}
