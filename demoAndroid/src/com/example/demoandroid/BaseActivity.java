package com.example.demoandroid;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity {
	public static final String TITLE = "BaseActivity";
	ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(true);
	}

}
