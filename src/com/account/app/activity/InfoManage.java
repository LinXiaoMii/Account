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

//ɾ���޸�������Ϣ
public class InfoManage extends Activity{

	protected static final int DATE_DIALOG_ID = 0 ;
	TextView  textTitle , textHandler ;
	EditText editMoney , editMark ,ediitHandler ;
	static EditText editTime;
	Spinner spinnerType ;
	Button buttonEdit , buttonDelete ;
	String []strInfos ;
	String strId;
	String strType ; //�����������ַ���������¼��Ϣ��ż��������ͣ�
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
		
		//��ʼ���ؼ�
		textTitle = (TextView)findViewById(R.id.inoutTtile);
		textHandler = (TextView)findViewById(R.id.textInoutHnadler);
		editMark = (EditText)findViewById(R.id.editInoutMark);
		editMoney = (EditText)findViewById(R.id.editInoutMoney);
		ediitHandler = (EditText)findViewById(R.id.editInoutHandler);
		editTime = (EditText)findViewById(R.id.editInoutTime);
		spinnerType = (Spinner)findViewById(R.id.spinInoutType);
		buttonEdit = (Button)findViewById(R.id.buttonInoutEdit);
		buttonDelete = (Button)findViewById(R.id.buttonInoutCancel);
		
		//����ʱ��Ĺ���ʵ��
		editTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDialog();  //��ʾ����ѡ��Ի���
				}
			});
		//����Ĭ����ʾ����Ϊ��ǰ����
		final Calendar c = Calendar.getInstance() ;  //��ȡ��ǰϵͳ����
		mYear = c.get(Calendar.YEAR);  //��ȡ���
		mMonth = c.get(Calendar.MONTH); //��ȡ�·�
		mDay = c.get(Calendar.DAY_OF_MONTH); //��ȡ����
		updateDisplay();  //��ʾ��ǰϵͳ����
		//����strId��strType�ж���������Ϣ����֧����Ϣ
		Intent intent = getIntent();//��ȡIntent����!!!
		Bundle bundle = intent.getExtras() ; //��ȡ�������Ϣ����ʹ��bundle��¼
		strInfos = bundle.getStringArray(ShowInfo.FLAG);
		strId = strInfos[0] ; //��¼id
		strType = strInfos[1] ;  //��¼����
		if(strType.equals("ininfo")) {
			textTitle.setText("�������");
			textHandler.setText("�����");
			
			//���ݱ�Ų���������Ϣ���洢��Inaccount������
			InAccount inAccount = inAccountDB.findInAccoutDB(Integer.parseInt(strId));
			editMoney.setText(String.valueOf(inAccount.getMoney()));
			editMark.setText(String.valueOf(inAccount.getMark()));
			ediitHandler.setText(String.valueOf(inAccount.getHandler()));
			editTime.setText(String.valueOf(inAccount.getTime()));
			spinnerType.setPrompt(inAccount.getType());
			
		}else if(strType.equals("outinfo")) {
			textTitle.setText("֧������");
			textHandler.setText("����ص㣺");
			
			//���ݱ�Ų���������Ϣ���洢��Outaccount������
			OutAccount outAccount = outAccountDB.findOutAccoutDB(Integer.parseInt(strId));
			editMoney.setText(String.valueOf(outAccount.getMoney()));
			editMark.setText(String.valueOf(outAccount.getMark()));
			ediitHandler.setText(String.valueOf(outAccount.getAddress()));
			editTime.setText(String.valueOf(outAccount.getTime()));
			spinnerType.setPrompt(outAccount.getType());//���÷�
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
				Toast.makeText(InfoManage.this, "�����޸ĳɹ�", Toast.LENGTH_SHORT).show() ;
				
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
				Toast.makeText(InfoManage.this, "����ɾ���ɹ�", Toast.LENGTH_SHORT).show() ;
			}
		});
		
	}
	
	public static void updateDisplay() {
		// TODO Auto-generated method stub
		editTime.setText(new StringBuilder().append(mYear).append("-").append(mMonth+1).append("-").append(mDay));
		//��ʾ���õ�����
	}

	//��ʾ�Ի���ķ���
	protected void showDialog() {
		// TODO Auto-generated method stub
		MyDialogFragment newDialog = MyDialogFragment.newInstance(MyDialogFragment.ALERT_TIME_DIALOG);
		newDialog.show(getFragmentManager(), "time alter msg");
	}
}
