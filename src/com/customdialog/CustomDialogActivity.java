package com.customdialog;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import android.widget.TextView;


public class CustomDialogActivity extends ListActivity {

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		switch (id) {
		case DIALOG_CONCAT_DATA:
			TextView contactNameTextView=(TextView) findViewById(R.id.contactNameTextView);
			contactNameTextView.setText(names[selected_item_position]);
			break;
		}
		
	}

	@Override
	protected Dialog onCreateDialog(int id, Bundle args) {
		Dialog dialog;
		switch(id){
			case DIALOG_CONCAT_DATA:
				dialog=getContactInfoDialog();
			break;
			default:
				dialog=null;
				break;
		}
		
		
		return dialog;
	}
	
	private Dialog getContactInfoDialog() {
		LayoutInflater inflater=LayoutInflater.from(this);
		View layout=inflater.inflate(R.layout.dialog,null);
		
		Button guncelleButton=(Button) findViewById(R.id.guncelleButton);
		Button iptalButton=(Button) findViewById(R.id.iptalButton);
		final EditText guncelleEditText=(EditText) findViewById(R.id.guncelleEditText);
		
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setView(layout);
		final AlertDialog dialog=builder.create();
		
		guncelleButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				names[selected_item_position]=guncelleEditText.getText().toString();
				adapter.notifyDataSetChanged();
				dialog.dismiss();
			}
		});
		
		iptalButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.cancel();
				
			}
		});
		
		return dialog;
	}
	
	private static final int DIALOG_CONCAT_DATA=0;
	private static int selected_item_position=-1;
	private String[] names;
	private ArrayAdapter<String> adapter;
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		names=getResources().getStringArray(R.array.isimler);
		adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
		setListAdapter(adapter);
		
		ListView list=getListView();
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				selected_item_position=position;
				showDialog(DIALOG_CONCAT_DATA);
			}
		});
	}
	 
	 

}
