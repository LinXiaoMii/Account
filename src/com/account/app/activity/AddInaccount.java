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
		
		//����ʱ��Ĺ���ʵ��
		editInTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog();  //��ʾ����ѡ��Ի���
			}
		});
		//����Ĭ����ʾ����Ϊ��ǰ����
		final Calendar c = Calendar.getInstance() ;  //��ȡ��ǰϵͳ����
		inYear = c.get(Calendar.YEAR);  //��ȡ���
		inMonth = c.get(Calendar.MONTH); //��ȡ�·�
		inDay = c.get(Calendar.DAY_OF_MONTH); //��ȡ����
		updateDisplay();  //��ʾ��ǰϵͳ����
		
		//ȷ����ť���������Ϣ����ʵ��
		inSaveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String inMoney = editInMoney.getText().toString(); //��ȡ����ı����ֵ
				if(!inMoney.isEmpty()){  //�жϽ���ı���Ϊ��
					InAccoutDB inAccountDB = new InAccoutDB(AddInaccount.this);
					InAccount inAccount = new InAccount(inAccountDB.getMax()+1,Double.parseDouble(inMoney),editInTime.getText().toString(),spinInType.getSelectedItem().toString(),editInHnadler.getText().toString(),editInMark.getText().toString());
					inAccountDB.addInAccount(inAccount);  //���������Ϣ
					Toast.makeText(AddInaccount.this, "[��������]������ӳɹ�", Toast.LENGTH_SHORT).show();
					
					editInMoney.setText("");
					final Calendar c = Calendar.getInstance() ;  //��ȡ��ǰϵͳ����
					inYear = c.get(Calendar.YEAR);  //��ȡ���
					inMonth = c.get(Calendar.MONTH); //��ȡ�·�
					inDay = c.get(Calendar.DAY_OF_MONTH); //��ȡ����
					updateDisplay();  //��ʾ��ǰϵͳ����
				}else{
					Toast.makeText(AddInaccount.this, "������������", Toast.LENGTH_SHORT).show();
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
				spinInType.setSelection(0);//������������б�Ĭ��ѡ���һ��
				
				final Calendar c = Calendar.getInstance() ;  //��ȡ��ǰϵͳ����
				inYear = c.get(Calendar.YEAR);  //��ȡ���
				inMonth = c.get(Calendar.MONTH); //��ȡ�·�
				inDay = c.get(Calendar.DAY_OF_MONTH); //��ȡ����
				updateDisplay();  //��ʾ��ǰϵͳ����
			}
		});
		}

	public static void updateDisplay() {
		// TODO Auto-generated method stub
		editInTime.setText(new StringBuilder().append(inYear).append("-").append(inMonth+1).append("-").append(inDay));
		//��ʾ���õ�����
	}

	//��ʾ�Ի���ķ���
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


