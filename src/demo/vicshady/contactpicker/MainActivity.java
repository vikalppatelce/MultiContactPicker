package demo.vicshady.contactpicker;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
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
	
	public void onFlowLayoutClick(View v)
	{
//		Intent contactPicker = new Intent(MainActivity.this, ContactManager.class);
//		startActivityForResult(contactPicker, REQUEST_CODE);
	}
	public void setUi() {
		btnPickContact.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent contactPicker = new Intent(MainActivity.this, ContactManager.class);
				startActivityForResult(contactPicker, REQUEST_CODE);
				
//				 Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
//				    pickContactIntent.setType(Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
//				    startActivityForResult(pickContactIntent, REQUEST_CODE);
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
								final Contact contact = iterContacts.next();
								if(!repeatCheck)
								{
									contactsShareDetail.add(contact);
									contactsSharePhone.add(contact.getContactNumber());
									TextView t = new TextView(MainActivity.this);
									t.setLayoutParams(params);
									t.setTextSize(16f);
									t.setPadding(2, 2, 2, 2);
									t.setText(contact.getContactName());
									t.setTextColor(Color.WHITE);
									t.setCompoundDrawablesWithIntrinsicBounds(null, null,getResources().getDrawable(R.drawable.ic_delete_light), null);
//									t.setCompoundDrawablePadding(20);
									// t.setBackgroundColor(Color.BLUE);
									t.setBackgroundResource(R.drawable.chips_bg);
//									t.setTag(collectionIndex);
									t.setTag(contact);
									chipsBoxLayout.addView(t);
									t.setOnClickListener(new View.OnClickListener() {
										
										@Override
										public void onClick(View v) {
											// TODO Auto-generated method stub
//											contactsSharePhone.remove(Integer.parseInt(v.getTag().toString())-1);
//											contactsShareDetail.remove(Integer.parseInt(v.getTag().toString())-1);
											try {
												contactsShareDetail.remove(contact);
												contactsSharePhone.remove(contact.getContactNumber());
												chipsBoxLayout.removeView(v);
											} catch (Exception e) {
												Log.e("Remove Chips", e.toString());
											}
//											collectionIndex--;
										}
									});
								}
								else if(repeatCheck && !contactsSharePhone.contains(contact.getContactNumber()))
								{
									    contactsShareDetail.add(contact);
									    contactsSharePhone.add(contact.getContactNumber());
										TextView t = new TextView(MainActivity.this);
										t.setLayoutParams(params);
										t.setTextSize(16f);
										t.setPadding(2, 2, 2, 2);
										t.setText(contact.getContactName());
										t.setTextColor(Color.WHITE);
										t.setCompoundDrawablesWithIntrinsicBounds(null, null,getResources().getDrawable(R.drawable.ic_delete_light), null);
//										t.setCompoundDrawablePadding(20);
										// t.setBackgroundColor(Color.BLUE);
										t.setBackgroundResource(R.drawable.chips_bg);
										chipsBoxLayout.addView(t);
//										t.setTag(collectionIndex);
										t.setTag(contact);
										t.setOnClickListener(new View.OnClickListener() {
											
											@Override
											public void onClick(View v) {
												// TODO Auto-generated method stub
//												contactsSharePhone.remove(Integer.parseInt(v.getTag().toString())-1);
//												contactsShareDetail.remove(Integer.parseInt(v.getTag().toString())-1);
										try {
											contactsShareDetail.remove(contact);
											contactsSharePhone.remove(contact.getContactNumber());
											chipsBoxLayout.removeView(v);
										} catch (Exception e) {
											Log.e("Remove Chips", e.toString());
										}
//												collectionIndex--;
											}
										});
								}
						}
					}
				}
			}
		}
		
		super.onActivityResult(requestCode, resultCode, data);
	}
}
