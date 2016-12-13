package com.account.app.activity;

import com.account.app.db.PasswardDB;
import com.account.app.model.Passward;
import com.example.account.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Setting extends Activity{

	EditText editPassword ;
	Button buttonSet , buttonCancel ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		
		editPassword = (EditText)findViewById(R.id.editPassword) ;
		buttonSet = (Button)findViewById(R.id.buttonSetSet);
		buttonCancel =  (Button)findViewById(R.id.buttonSetCancel);
		
		buttonSet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PasswardDB passwordDB = new PasswardDB(Setting.this) ;
				String passward = editPassword.getText().toString() ;
				Passward password = new Passward(passward);
				if(passwordDB.getCount() == 0){
					passwordDB.addPassward(password);
				}else {
					passwordDB.updatePassward(password);
				}
				Toast.makeText(getApplicationContext(), "√‹¬Î…Ë÷√≥…π¶", Toast.LENGTH_SHORT).show() ;
			}
		});
		
		buttonCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				editPassword.setText("");
				editPassword.setHint("«Î ‰»Î√‹¬Î£∫");
			}
		});
	}
	
	//÷ÿ–¥∑µªÿº¸
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
	}
}
