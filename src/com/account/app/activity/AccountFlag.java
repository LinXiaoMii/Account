package com.account.app.activity;

import com.account.app.db.ExitApplication;
import com.account.app.db.FlagDB;
import com.account.app.model.Flag;
import com.example.account.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AccountFlag extends Activity{

	EditText editFlag ;
	Button buttonFlagCancle , buttonFlagSave ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accountflag);
		//便于最终关闭程序
		ExitApplication.getInstance().addActivity(this);
		
		editFlag = (EditText)findViewById(R.id.editFlag);
		buttonFlagSave = (Button)findViewById(R.id.buttonFlagSave) ;
		buttonFlagCancle = (Button)findViewById(R.id.buttonFlagCancel) ;
		
		buttonFlagSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				String strFlag = editFlag.getText().toString() ;
				if(! strFlag.isEmpty()){
					FlagDB flagDB = new FlagDB(AccountFlag.this);
					Flag flag = new Flag(flagDB.getMax()+1,strFlag) ;
					flagDB.addFlag(flag);
					Toast.makeText(AccountFlag.this, "便签添加成功", Toast.LENGTH_SHORT).show() ;
					
				}else{
					Toast.makeText(AccountFlag.this, "请输入便签", Toast.LENGTH_SHORT).show() ;
				}
				
			}
		});
		 
		buttonFlagCancle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				editFlag.setText("");
			}
		});
	}
	
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
	}
}
