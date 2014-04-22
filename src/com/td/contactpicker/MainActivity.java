package com.td.contactpicker;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

/**
 * Author Vikalp Patel
 * vikalppatelce@yahoo.com
 */
public class MainActivity extends Activity{
	
	final int REQUEST_CODE = 100;
	
	Button btnPickContact = null;
	TextView tvData = null;
	CheckBox cbCheckAll = null;
	FlowLayout chipsBoxLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main_activity);
		
		initUi();
		setUi();
		
	}
	
	public void initUi() {
		tvData = (TextView) findViewById(R.id.tvData);
		btnPickContact = (Button) findViewById(R.id.btnPick);
		chipsBoxLayout = (FlowLayout)findViewById(R.id.chips_box_layout);
		
	}
	
	public void setUi() {
		
		btnPickContact.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Intent contactPicker = new Intent(MainActivity.this, ContactManager.class);
				startActivityForResult(contactPicker, REQUEST_CODE);
				
			}
		});

	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if(requestCode == REQUEST_CODE) {
			if(resultCode == Activity.RESULT_OK) {
				if(data.hasExtra(Contact.CONTACTS_DATA)) {
					
					FlowLayout.LayoutParams params = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, FlowLayout.LayoutParams.WRAP_CONTENT);
					params.setMargins(5, 5, 5, 5);
					
					ArrayList<Contact> contacts = data.getParcelableArrayListExtra(Contact.CONTACTS_DATA);

					if(contacts != null) {

						String dataTxt = "";
						
						Iterator<Contact> iterContacts = contacts.iterator();
						while(iterContacts.hasNext()) {
							
							Contact contact = iterContacts.next();
							
//							dataTxt += contact.getContactName() + contact.getContactNumber() + "\n";
							
							TextView t = new TextView(MainActivity.this);
							t.setLayoutParams(params);
							t.setTextSize(18f);
							t.setPadding(5, 5, 5, 5);
							t.setText(contact.getContactName());
							t.setTextColor(Color.WHITE);
//							t.setBackgroundColor(Color.BLUE);
							t.setBackgroundResource(R.drawable.chips_bg);
							chipsBoxLayout.addView(t);
							
						}
						tvData.setText(dataTxt);
						
					}
				}
			}
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
}
