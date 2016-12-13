package com.account.app.activity;

import com.account.app.db.FlagDB;
import com.account.app.model.Flag;
import com.example.account.R;

import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FlagManage extends Activity{
	
	EditText editFlag ;
	Button buttonEdit , buttonCancel ;
	String strId ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flagmanage);
		
		editFlag = (EditText)findViewById(R.id.editFlagManage);
		buttonEdit = (Button)findViewById(R.id.buttonFlagEditManage);
		buttonCancel = (Button)findViewById(R.id.buttonFlagCancelManage);
		
		Intent intent = getIntent() ;//����Intent����getIntent��������֮�䷵��һ��Intentʵ��
		Bundle bundle = intent.getExtras() ; //��ȡ��ǩid����DataManage���ݹ���������
		strId = bundle.getString(ShowInfo.FLAG);
		final FlagDB flagDB = new FlagDB(FlagManage.this) ;
		editFlag.setText(flagDB.findFlag(Integer.parseInt(strId)).getFlag());//����id���Ա�ǩ��Ϣ����ʾ���ı���
		
		buttonEdit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Flag flag = new Flag() ;
				flag.setId(Integer.parseInt(strId));
				flag.setFlag(editFlag.getText().toString());
				flagDB.updateFlag(flag);
				Toast.makeText(getApplicationContext(), "��ǩ�����޸ĳɹ�", Toast.LENGTH_SHORT);
			}
		});
		
		buttonCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				flagDB.deleteFlag(Integer.parseInt(strId));
				Toast.makeText(getApplicationContext(), "��ǩ����ɾ���ɹ�", Toast.LENGTH_SHORT);
			}
		});
	}

}
