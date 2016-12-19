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
		//�������չرճ���
		ExitApplication.getInstance().addActivity(this);
		
		editOutMoney = (EditText)findViewById(R.id.editOutMoney);
		spoutOutType = (Spinner)findViewById(R.id.spinOutType);
		editOutAddress = (EditText)findViewById(R.id.editOutAddress);
		editOutMark = (EditText)findViewById(R.id.editOutMark);
		editOutTime = (EditText)findViewById(R.id.editOutTime);
		outSaveButton = (Button)findViewById(R.id.buttonOutSave);
		outCancelButton = (Button)findViewById(R.id.buttonOutCancel);
		
		//����ʱ��Ĺ���ʵ��
		editOutTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog();  //��ʾ����ѡ��Ի���
			}
		});
		//����Ĭ����ʾ����Ϊ��ǰ����
		final Calendar calendar = Calendar.getInstance() ;  //��ȡ��ǰϵͳ����
		outYear = calendar.get(Calendar.YEAR);  //��ȡ���
		outMonth = calendar.get(Calendar.MONTH); //��ȡ�·�
		outDay = calendar.get(Calendar.DAY_OF_MONTH); //��ȡ����
		updateDisplay();  //��ʾ��ǰϵͳ����
		
		//ȷ����ť���������Ϣ����ʵ��
		outSaveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String outMoney = editOutMoney.getText().toString(); //��ȡ����ı����ֵ
				if(!outMoney.isEmpty()){  //�жϽ���ı���Ϊ��
					OutAccountDB outAccountDB = new OutAccountDB(AddOutaccount.this);
					OutAccount outAccount = new OutAccount(outAccountDB.getMax()+1,Double.parseDouble(outMoney),editOutTime.getText().toString(),spoutOutType.getSelectedItem().toString(),editOutAddress.getText().toString(),editOutMark.getText().toString());
					outAccountDB.addOutAccount(outAccount);  //���������Ϣ
					Toast.makeText(AddOutaccount.this, "[��������]������ӳɹ�", Toast.LENGTH_SHORT).show();
					
					editOutMoney.setText("");
					final Calendar c = Calendar.getInstance() ;  //��ȡ��ǰϵͳ����
					outYear = c.get(Calendar.YEAR);  //��ȡ���
					outMonth = c.get(Calendar.MONTH); //��ȡ�·�
					outDay = c.get(Calendar.DAY_OF_MONTH); //��ȡ����
					updateDisplay();  //��ʾ��ǰϵͳ����
				}else{
					Toast.makeText(AddOutaccount.this, "������������", Toast.LENGTH_SHORT).show();
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
				spoutOutType.setSelection(0);//������������б�Ĭ��ѡ���һ��
				
				final Calendar c = Calendar.getInstance() ;  //��ȡ��ǰϵͳ����
				outYear = c.get(Calendar.YEAR);  //��ȡ���
				outMonth = c.get(Calendar.MONTH); //��ȡ�·�
				outDay = c.get(Calendar.DAY_OF_MONTH); //��ȡ����
				updateDisplay();  //��ʾ��ǰϵͳ����
			}
		});
		}

	public static void updateDisplay() {
		// TODO Auto-generated method stub
		editOutTime.setText(new StringBuilder().append(outYear).append("-").append(outMonth+1).append("-").append(outDay));
		//��ʾ���õ�����
	}

	//��ʾ�Ի���ķ���
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


