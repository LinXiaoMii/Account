package com.account.app.activity;

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

public class InAccountInfo extends Activity{
	public static final String FLAG = "id" ; //����һ����������Ϊ������
	ListView listInaccountInfo ;
	String strInType = "ininfo" ; //��¼��������
	ArrayAdapter<String>arrayAdapter = null ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inaccountinfo);
		listInaccountInfo = (ListView)findViewById(R.id.listInaccountInfo);
		ShowInfo show = ShowInfo.newInstance() ;
		show.showInfo(strInType,InAccountInfo.this) ;
		
		arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,show.strInfo);//simple_list_item_1 ����һ��TextView
		listInaccountInfo.setAdapter(arrayAdapter);
		listInaccountInfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Log.d("YINQI", "ok") ;
				String strInfo = String.valueOf(((TextView)view).getText()); //��¼������Ϣ
				String strid = strInfo.substring(0, strInfo.indexOf('|'));  //��������Ϣ�н�ȡ������
				Intent intent = new Intent(InAccountInfo.this,InfoManage.class);
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
	
	/*private void showInfo(){  //���ݹ���������ʾ��Ӧ��Ϣ
		String strInfo[] = null ; //�����ַ����洢����
		ArrayAdapter<String>arrayAdapter = null ;
		strInType = "btninifo" ;
		InAccoutDB inaccountInfo = new InAccoutDB(InAccountInfo.this);
		//��ȡ����������Ϣ,���洢��list���ͼ�����
		List<InAccount> listInfos = inaccountInfo.loadInAccount(0, (int)inaccountInfo.getCount());
		strInfo = new String[listInfos.size()];
		int m = 0 ; //����һ����ʼ��־
		for(InAccount inAccount:listInfos){  //����list���ͼ���
			strInfo[m] = inAccount.getId()+"|"+inAccount.getType()+" "+String.valueOf(inAccount.getMoney())+"Ԫ      "+inAccount.getType() ;
			m++ ;
		}
		arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strInfo);//simple_list_item_1 ����һ��TextView
		listInaccountInfo.setAdapter(arrayAdapter);
		listInaccountInfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				String strInfo = String.valueOf(((TextView)view).getText()); //��¼������Ϣ
				String strid = strInfo.substring(0, strInfo.indexOf('|'));  //��������Ϣ�н�ȡ������
				Intent intent = new Intent(InAccountInfo.this,InfoManage.class);
				intent.putExtra(FLAG, new String[]{strid,strInType}); //���ô�������
				startActivity(intent);
			}
		});
	}*/
