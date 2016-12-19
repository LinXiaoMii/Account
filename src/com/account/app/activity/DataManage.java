package com.account.app.activity;

import com.account.app.db.ExitApplication;
import com.example.account.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class DataManage extends Activity{

	Button buttonInInfo ;
	Button buttonOutInfo ;
	Button buttonFlagInfo ;
	
	public static final String FLAG = "id" ; //定义一个常量来作为请求码
	ListView listInfo ;
	String strInType = "" ; //记录管理类型
	ArrayAdapter<String>arrayAdapter = null ;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datamanage);
		//便于最终关闭程序
		ExitApplication.getInstance().addActivity(this);
		
		buttonInInfo = (Button)findViewById(R.id.buttonInInfo);
		buttonOutInfo = (Button)findViewById(R.id.buttonOutInfo);
		buttonFlagInfo = (Button)findViewById(R.id.buttonFlagInfo);
		listInfo = (ListView)findViewById(R.id.listInfo);
		
		final ShowInfo show = ShowInfo.newInstance() ;
		
		buttonInInfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				strInType = "ininfo" ;
				show.showInfo(strInType,DataManage.this) ;
				arrayAdapter = new ArrayAdapter<String>(DataManage.this,android.R.layout.simple_list_item_1,show.strInfo);//simple_list_item_1 中有一个TextView
				listInfo.setAdapter(arrayAdapter);
				listInfo.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						// TODO Auto-generated method stub
						String strInfo = String.valueOf(((TextView)view).getText()); //记录收入信息
						String strid = strInfo.substring(0, strInfo.indexOf('|'));  //从收入信息中截取收入编号
						Intent intent = new Intent(DataManage.this,InfoManage.class);
						intent.putExtra(FLAG, new String[]{strid,strInType}); //设置传递数据,便于后面获取key为id的数据（用ShowInfo.FLAG表示）
						startActivity(intent);
					}
				});
			}
		});
		
		buttonOutInfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				strInType = "outinfo" ;
				show.showInfo(strInType,DataManage.this) ;
				arrayAdapter = new ArrayAdapter<String>(DataManage.this,android.R.layout.simple_list_item_1,show.strInfo);//simple_list_item_1 中有一个TextView
				listInfo.setAdapter(arrayAdapter);
				listInfo.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						// TODO Auto-generated method stub
						String strInfo = String.valueOf(((TextView)view).getText()); //记录收入信息
						String strid = strInfo.substring(0, strInfo.indexOf('|'));  //从信息中截取编号
						Intent intent = new Intent(DataManage.this,InfoManage.class);
						intent.putExtra(FLAG, new String[]{strid,strInType}); //设置传递数据
						startActivity(intent);
					}
				});
			}
		});
		
		buttonFlagInfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				strInType = "flaginfo" ;
				show.showInfo(strInType,DataManage.this) ;
				arrayAdapter = new ArrayAdapter<String>(DataManage.this,android.R.layout.simple_list_item_1,show.strInfo);//simple_list_item_1 中有一个TextView
				listInfo.setAdapter(arrayAdapter);
				listInfo.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						// TODO Auto-generated method stub
						String strInfo = String.valueOf(((TextView)view).getText()); //记录信息
						String strid = strInfo.substring(0, strInfo.indexOf('|'));  //从信息中截取编号
						Intent intent = new Intent(DataManage.this,FlagManage.class);
						intent.putExtra(FLAG, new String[]{strid,strInType}); //设置传递数据
						startActivity(intent);
					}
				});
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
