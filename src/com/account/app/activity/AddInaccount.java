package com.account.app.activity;

import java.util.Calendar;

import com.account.app.db.InAccoutDB;
import com.account.app.model.InAccount;
import com.account.app.util.MyDialogFragment;
import com.example.account.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddInaccount extends Activity{
	
	Spinner spinInType ;
	Button inSaveButton ;
	Button inCancelButton ;
	EditText editInMoney , editInHnadler , editInMark;
	static EditText editInTime;
	public static int inYear ;
	public static int inMonth ;
	public static int inDay ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addincount);
		editInMoney = (EditText)findViewById(R.id.editInMoney);
		spinInType = (Spinner)findViewById(R.id.spinInType);
		editInHnadler = (EditText)findViewById(R.id.editInHandler);
		editInMark = (EditText)findViewById(R.id.editInMark);
		editInTime = (EditText)findViewById(R.id.editInTime);
		inSaveButton = (Button)findViewById(R.id.buttonInSave);
		inCancelButton = (Button)findViewById(R.id.buttonInCancel);
		
		//输入时间的功能实现
		editInTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog();  //显示日期选择对话框
			}
		});
		//设置默认显示日期为当前日期
		final Calendar c = Calendar.getInstance() ;  //获取当前系统日期
		inYear = c.get(Calendar.YEAR);  //获取年份
		inMonth = c.get(Calendar.MONTH); //获取月份
		inDay = c.get(Calendar.DAY_OF_MONTH); //获取天数
		updateDisplay();  //显示当前系统日期
		
		//确定按钮添加收入信息功能实现
		inSaveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String inMoney = editInMoney.getText().toString(); //获取金额文本框的值
				if(!inMoney.isEmpty()){  //判断金额文本框不为空
					InAccoutDB inAccountDB = new InAccoutDB(AddInaccount.this);
					InAccount inAccount = new InAccount(inAccountDB.getMax()+1,Double.parseDouble(inMoney),editInTime.getText().toString(),spinInType.getSelectedItem().toString(),editInHnadler.getText().toString(),editInMark.getText().toString());
					inAccountDB.addInAccount(inAccount);  //添加收入信息
					Toast.makeText(AddInaccount.this, "[新增收入]数据添加成功", Toast.LENGTH_SHORT).show();
					
					editInMoney.setText("");
					final Calendar c = Calendar.getInstance() ;  //获取当前系统日期
					inYear = c.get(Calendar.YEAR);  //获取年份
					inMonth = c.get(Calendar.MONTH); //获取月份
					inDay = c.get(Calendar.DAY_OF_MONTH); //获取天数
					updateDisplay();  //显示当前系统日期
				}else{
					Toast.makeText(AddInaccount.this, "请输入收入金额", Toast.LENGTH_SHORT).show();
				}
			}
		});
		inCancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				editInMoney.setText("");
				editInMoney.setHint("0.00");
				editInHnadler.setText("");
				editInMark.setText("");
				editInTime.setText("");
				spinInType.setSelection(0);//设置类别下拉列表默认选择第一项
				
				final Calendar c = Calendar.getInstance() ;  //获取当前系统日期
				inYear = c.get(Calendar.YEAR);  //获取年份
				inMonth = c.get(Calendar.MONTH); //获取月份
				inDay = c.get(Calendar.DAY_OF_MONTH); //获取天数
				updateDisplay();  //显示当前系统日期
			}
		});
		}

	public static void updateDisplay() {
		// TODO Auto-generated method stub
		editInTime.setText(new StringBuilder().append(inYear).append("-").append(inMonth+1).append("-").append(inDay));
		//显示设置的日期
	}

	//显示对话框的方法
	protected void showDialog() {
		// TODO Auto-generated method stub
		MyDialogFragment newDialog = MyDialogFragment.newInstance(MyDialogFragment.DATE_PICKER_DIALOG);
		newDialog.show(getFragmentManager(), "time msg");
	}
	
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
	}
	
}


