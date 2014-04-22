package com.td.contactpicker;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

public class Util {

	public static void hideSoftKeyboard(Activity activity) {
		InputMethodManager inputMethodManager = (InputMethodManager) activity
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
	}

}
