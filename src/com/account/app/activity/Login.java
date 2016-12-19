package com.account.app.activity;

import com.account.app.db.ExitApplication;
import com.account.app.db.PasswardDB;
import com.example.account.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity{

	Button loginButton ;
	Button cancelButton ;
	EditText passwardEdit ;
	SharedPreferences sp ;
	SharedPreferences.Editor editor ;
	CheckBox rememberCheck ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		//�������չرճ���
		ExitApplication.getInstance().addActivity(this);
		
		loginButton = (Button)findViewById(R.id.login_button);
		cancelButton = (Button)findViewById(R.id.cancel_button);
		passwardEdit = (EditText)findViewById(R.id.psd_edit);
		rememberCheck = (CheckBox)findViewById(R.id.remeber_checbox);
		editor = getSharedPreferences("login", MODE_PRIVATE).edit();
		sp = getSharedPreferences("login", MODE_PRIVATE);
		Boolean isRemember = sp.getBoolean("isRemember", false);
		
		if(isRemember){
			String passward = sp.getString("passward", "");
			passwardEdit.setText(passward);
			rememberCheck.setChecked(true);
		}
		
		loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Login.this,MainActivity.class);
				PasswardDB passwardDB = new PasswardDB(Login.this);
				//String s = passwardDB.findPassward().getPassward() ;
				//�ж��Ƿ�������/�Ƿ�����������
				if((passwardDB.getCount() == 0 |passwardDB.findPassward().getPassward() == null) && passwardEdit.getText().toString().isEmpty()){
					startActivity(intent);//�״ε�½
				}else{
					if(passwardDB.findPassward().getPassward().equals(passwardEdit.getText().toString())){
						startActivity(intent);
					}else{
						Toast.makeText(Login.this, "�������", Toast.LENGTH_SHORT).show();
					}
				}
				passwardEdit.setText("");
			}
		});
		
		cancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	
	public void onBackPressed() {
		// TODO Auto-generated method stub
		ExitApplication.getInstance().exit();
	}
	

}
