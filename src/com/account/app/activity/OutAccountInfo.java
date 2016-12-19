package com.account.app.activity;

import com.account.app.db.ExitApplication;
import com.example.account.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class OutAccountInfo extends Activity{
	public static final String FLAG = "id" ; //����һ����������Ϊ������
	ListView listOutaccountInfo ;
	String strInType = "outinfo" ; //��¼��������
	ArrayAdapter<String>arrayAdapter = null ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.outaccountinfo);
		//�������չرճ���
		ExitApplication.getInstance().addActivity(this);
		
		listOutaccountInfo = (ListView)findViewById(R.id.listOutaccountInfo);
		ShowInfo show = ShowInfo.newInstance() ;
		show.showInfo(strInType,OutAccountInfo.this) ;
		
		arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,show.strInfo);//simple_list_item_1 ����һ��TextView
		listOutaccountInfo.setAdapter(arrayAdapter);
		listOutaccountInfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				String strInfo = String.valueOf(((TextView)view).getText()); //��¼֧����Ϣ
				String strid = strInfo.substring(0, strInfo.indexOf('|'));  //��֧����Ϣ�н�ȡ֧�����
				Intent intent = new Intent(OutAccountInfo.this,InfoManage.class);
				intent.putExtra(FLAG, new String[]{strid,strInType}); //���ô�������
				startActivity(intent);
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
	
	