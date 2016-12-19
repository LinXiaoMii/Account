package com.account.app.activity;

import java.util.Calendar;

import com.account.app.db.ExitApplication;
import com.account.app.db.OutAccountDB;
import com.account.app.model.OutAccount;
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

public class AddOutaccount extends Activity{
	
	Spinner spoutOutType ;
	Button outSaveButton ;
	Button outCancelButton ;
	EditText editOutMoney , editOutAddress , editOutMark;
	static EditText editOutTime;
	public static int outYear ;
	public static int outMonth ;
	public static int outDay ;

	@Override
	protected void onCreate(Bundle savedOutstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedOutstanceState);
		setContentView(R.layout.addoutaccount);
		//便于最终关闭程序
		ExitApplication.getInstance().addActivity(this);
		
		editOutMoney = (EditText)findViewById(R.id.editOutMoney);
		spoutOutType = (Spinner)findViewById(R.id.spinOutType);
		editOutAddress = (EditText)findViewById(R.id.editOutAddress);
		editOutMark = (EditText)findViewById(R.id.editOutMark);
		editOutTime = (EditText)findViewById(R.id.editOutTime);
		outSaveButton = (Button)findViewById(R.id.buttonOutSave);
		outCancelButton = (Button)findViewById(R.id.buttonOutCancel);
		
		//输入时间的功能实现
		editOutTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog();  //显示日期选择对话框
			}
		});
		//设置默认显示日期为当前日期
		final Calendar calendar = Calendar.getInstance() ;  //获取当前系统日期
		outYear = calendar.get(Calendar.YEAR);  //获取年份
		outMonth = calendar.get(Calendar.MONTH); //获取月份
		outDay = calendar.get(Calendar.DAY_OF_MONTH); //获取天数
		updateDisplay();  //显示当前系统日期
		
		//确定按钮添加收入信息功能实现
		outSaveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String outMoney = editOutMoney.getText().toString(); //获取金额文本框的值
				if(!outMoney.isEmpty()){  //判断金额文本框不为空
					OutAccountDB outAccountDB = new OutAccountDB(AddOutaccount.this);
					OutAccount outAccount = new OutAccount(outAccountDB.getMax()+1,Double.parseDouble(outMoney),editOutTime.getText().toString(),spoutOutType.getSelectedItem().toString(),editOutAddress.getText().toString(),editOutMark.getText().toString());
					outAccountDB.addOutAccount(outAccount);  //添加收入信息
					Toast.makeText(AddOutaccount.this, "[新增收入]数据添加成功", Toast.LENGTH_SHORT).show();
					
					editOutMoney.setText("");
					final Calendar c = Calendar.getInstance() ;  //获取当前系统日期
					outYear = c.get(Calendar.YEAR);  //获取年份
					outMonth = c.get(Calendar.MONTH); //获取月份
					outDay = c.get(Calendar.DAY_OF_MONTH); //获取天数
					updateDisplay();  //显示当前系统日期
				}else{
					Toast.makeText(AddOutaccount.this, "请输入收入金额", Toast.LENGTH_SHORT).show();
				}
			}
		});
		outCancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				editOutMoney.setText("");
				editOutMoney.setHint("0.00");
				editOutAddress.setText("");
				editOutMark.setText("");
				editOutTime.setText("");
				spoutOutType.setSelection(0);//设置类别下拉列表默认选择第一项
				
				final Calendar c = Calendar.getInstance() ;  //获取当前系统日期
				outYear = c.get(Calendar.YEAR);  //获取年份
				outMonth = c.get(Calendar.MONTH); //获取月份
				outDay = c.get(Calendar.DAY_OF_MONTH); //获取天数
				updateDisplay();  //显示当前系统日期
			}
		});
		}

	public static void updateDisplay() {
		// TODO Auto-generated method stub
		editOutTime.setText(new StringBuilder().append(outYear).append("-").append(outMonth+1).append("-").append(outDay));
		//显示设置的日期
	}

	//显示对话框的方法
	protected void showDialog() {
		// TODO Auto-generated method stub
		MyDialogFragment newDialog = MyDialogFragment.newInstance(MyDialogFragment.OUTDATE_PICKER_DIALOG);
		newDialog.show(getFragmentManager(), "time msg");
	}

	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
	}
}


