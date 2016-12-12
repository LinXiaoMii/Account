package com.account.app.activity;

import java.util.Calendar;

import com.account.app.db.InAccoutDB;
import com.account.app.db.OutAccountDB;
import com.account.app.model.InAccount;
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
import android.widget.TextView;
import android.widget.Toast;

//删除修改收入信息
public class InfoManage extends Activity{

	protected static final int DATE_DIALOG_ID = 0 ;
	TextView  textTitle , textHandler ;
	EditText editMoney , editMark ,ediitHandler ;
	static EditText editTime;
	Spinner spinnerType ;
	Button buttonEdit , buttonDelete ;
	String []strInfos ;
	String strId;
	String strType ; //定义两个人字符串变量记录信息编号及管理类型；
	public static int mYear ;
	public static int mMonth;
	public static int mDay;
	InAccoutDB inAccountDB = new InAccoutDB(InfoManage.this);
	OutAccountDB outAccountDB = new OutAccountDB(InfoManage.this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.infomanage);
		
		//初始化控件
		textTitle = (TextView)findViewById(R.id.inoutTtile);
		textHandler = (TextView)findViewById(R.id.textInoutHnadler);
		editMark = (EditText)findViewById(R.id.editInoutMark);
		editMoney = (EditText)findViewById(R.id.editInoutMoney);
		ediitHandler = (EditText)findViewById(R.id.editInoutHandler);
		editTime = (EditText)findViewById(R.id.editInoutTime);
		spinnerType = (Spinner)findViewById(R.id.spinInoutType);
		buttonEdit = (Button)findViewById(R.id.buttonInoutEdit);
		buttonDelete = (Button)findViewById(R.id.buttonInoutCancel);
		
		//输入时间的功能实现
		editTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDialog();  //显示日期选择对话框
				}
			});
		//设置默认显示日期为当前日期
		final Calendar c = Calendar.getInstance() ;  //获取当前系统日期
		mYear = c.get(Calendar.YEAR);  //获取年份
		mMonth = c.get(Calendar.MONTH); //获取月份
		mDay = c.get(Calendar.DAY_OF_MONTH); //获取天数
		updateDisplay();  //显示当前系统日期
		//根据strId，strType判断是收入信息还是支出信息
		Intent intent = getIntent();//获取Intent对象!!!
		Bundle bundle = intent.getExtras() ; //获取传入的信息，并使用bundle记录
		strInfos = bundle.getStringArray(ShowInfo.FLAG);
		strId = strInfos[0] ; //记录id
		strType = strInfos[1] ;  //记录类型
		if(strType.equals("ininfo")) {
			textTitle.setText("收入管理");
			textHandler.setText("付款方：");
			
			//根据编号查找收入信息，存储到Inaccount对象中
			InAccount inAccount = inAccountDB.findInAccoutDB(Integer.parseInt(strId));
			editMoney.setText(String.valueOf(inAccount.getMoney()));
			editMark.setText(String.valueOf(inAccount.getMark()));
			ediitHandler.setText(String.valueOf(inAccount.getHandler()));
			editTime.setText(String.valueOf(inAccount.getTime()));
			spinnerType.setPrompt(inAccount.getType());
			
		}else if(strType.equals("outinfo")) {
			textTitle.setText("支出管理");
			textHandler.setText("付款地点：");
			
			//根据编号查找收入信息，存储到Outaccount对象中
			OutAccount outAccount = outAccountDB.findOutAccoutDB(Integer.parseInt(strId));
			editMoney.setText(String.valueOf(outAccount.getMoney()));
			editMark.setText(String.valueOf(outAccount.getMark()));
			ediitHandler.setText(String.valueOf(outAccount.getAddress()));
			editTime.setText(String.valueOf(outAccount.getTime()));
			spinnerType.setPrompt(outAccount.getType());//新用法
		}
		
		buttonEdit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(strType.equals("ininfo")){
					InAccount inAccount = new InAccount() ;
					inAccount.setId(Integer.parseInt(strId));
					inAccount.setHandler(ediitHandler.getText().toString());
					inAccount.setMark(editMark.getText().toString());
					inAccount.setMoney(Double.parseDouble(editMoney.getText().toString()));
					inAccount.setTime(editTime.getText().toString());
					inAccount.setType(spinnerType.getSelectedItem().toString());
					inAccountDB.updateInAccount(inAccount);
				}else if(strType.equals("outinfo")){
					OutAccount outAccount = new OutAccount() ;
					outAccount.setId(Integer.parseInt(strId));
					outAccount.setAddress(ediitHandler.getText().toString());
					outAccount.setMark(editMark.getText().toString());
					outAccount.setMoney(Double.parseDouble(editMoney.getText().toString()));
					outAccount.setTime(editTime.getText().toString());
					outAccount.setType(spinnerType.getSelectedItem().toString());
					outAccountDB.updateOutAccount(outAccount);
				}
				Toast.makeText(InfoManage.this, "数据修改成功", Toast.LENGTH_SHORT).show() ;
				
			}
		});
		
		buttonDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(strType.equals("ininfo")){
					inAccountDB.deleteInAccoutDB(Integer.parseInt(strId));
				}else if(strType.equals("outinfo")){
					outAccountDB.deleteOutAccoutDB(Integer.parseInt(strId));
				}
				Toast.makeText(InfoManage.this, "数据删除成功", Toast.LENGTH_SHORT).show() ;
			}
		});
		
	}
	
	public static void updateDisplay() {
		// TODO Auto-generated method stub
		editTime.setText(new StringBuilder().append(mYear).append("-").append(mMonth+1).append("-").append(mDay));
		//显示设置的日期
	}

	//显示对话框的方法
	protected void showDialog() {
		// TODO Auto-generated method stub
		MyDialogFragment newDialog = MyDialogFragment.newInstance(MyDialogFragment.ALERT_TIME_DIALOG);
		newDialog.show(getFragmentManager(), "time alter msg");
	}
}
