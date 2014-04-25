package com.td.contactpicker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import demo.vicshady.utils.contactpicker.R;

/**
 * Author Vikalp Patel
 * vikalppatelce@yahoo.com
 */
public class MainActivity extends Activity{
	
	final int REQUEST_CODE = 100;
	
	Button btnPickContact = null;
	CheckBox cbCheckAll = null;
	FlowLayout chipsBoxLayout;
	ArrayList<Contact> contactsShareDetail;
	ArrayList<String> contactsSharePhone;
	boolean repeatCheck = false;
	int i = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		initUi();
		setUi();
		contactsShareDetail = new ArrayList<Contact>();
		contactsSharePhone = new ArrayList<String>();
	}
	
	public void initUi() {
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
					params.setMargins(2, 2, 2, 2);
					
					ArrayList<Contact> contacts = data.getParcelableArrayListExtra(Contact.CONTACTS_DATA);
					if(contacts != null) {
						i += 1;
						if (i == 2) {
							repeatCheck = true;
						}
						
						Iterator<Contact> iterContacts = contacts.iterator();
						while (iterContacts.hasNext()) {
								Contact contact = iterContacts.next();
								if(!repeatCheck)
								{
									contactsShareDetail.add(contact);
									contactsSharePhone.add(contact.getContactNumber());
									TextView t = new TextView(MainActivity.this);
									t.setLayoutParams(params);
									t.setTextSize(16f);
									t.setPadding(3, 3, 3, 3);
									t.setText(contact.getContactName());
									t.setTextColor(Color.WHITE);
									// t.setBackgroundColor(Color.BLUE);
									t.setBackgroundResource(R.drawable.chips_bg);
									chipsBoxLayout.addView(t);
								}
								else if(repeatCheck && !contactsSharePhone.contains(contact.getContactNumber()))
								{
									    contactsShareDetail.add(contact);
									    contactsSharePhone.add(contact.getContactNumber());
										TextView t = new TextView(MainActivity.this);
										t.setLayoutParams(params);
										t.setTextSize(16f);
										t.setPadding(3, 3, 3, 3);
										t.setText(contact.getContactName());
										t.setTextColor(Color.WHITE);
										// t.setBackgroundColor(Color.BLUE);
										t.setBackgroundResource(R.drawable.chips_bg);
										chipsBoxLayout.addView(t);
								}
								
						}
					}
				}
			}
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
	
}
