package com.account.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Setting extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}
	
	//ÖØÐ´·µ»Ø¼ü
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
	}
}
