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
	public static final String FLAG = "id" ; //定义一个常量来作为请求码
	ListView listInaccountInfo ;
	String strInType = "ininfo" ; //记录管理类型
	ArrayAdapter<String>arrayAdapter = null ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inaccountinfo);
		listInaccountInfo = (ListView)findViewById(R.id.listInaccountInfo);
		ShowInfo show = ShowInfo.newInstance() ;
		show.showInfo(strInType,InAccountInfo.this) ;
		
		arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,show.strInfo);//simple_list_item_1 中有一个TextView
		listInaccountInfo.setAdapter(arrayAdapter);
		listInaccountInfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Log.d("YINQI", "ok") ;
				String strInfo = String.valueOf(((TextView)view).getText()); //记录收入信息
				String strid = strInfo.substring(0, strInfo.indexOf('|'));  //从收入信息中截取收入编号
				Intent intent = new Intent(InAccountInfo.this,InfoManage.class);
				intent.putExtra(FLAG, new String[]{strid,strInType}); //设置传递数据
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
	
	/*private void showInfo(){  //根据管理类型显示相应信息
		String strInfo[] = null ; //定义字符串存储数组
		ArrayAdapter<String>arrayAdapter = null ;
		strInType = "btninifo" ;
		InAccoutDB inaccountInfo = new InAccoutDB(InAccountInfo.this);
		//获取所有收入信息,并存储到list泛型集合中
		List<InAccount> listInfos = inaccountInfo.loadInAccount(0, (int)inaccountInfo.getCount());
		strInfo = new String[listInfos.size()];
		int m = 0 ; //定义一个开始标志
		for(InAccount inAccount:listInfos){  //遍历list泛型集合
			strInfo[m] = inAccount.getId()+"|"+inAccount.getType()+" "+String.valueOf(inAccount.getMoney())+"元      "+inAccount.getType() ;
			m++ ;
		}
		arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strInfo);//simple_list_item_1 中有一个TextView
		listInaccountInfo.setAdapter(arrayAdapter);
		listInaccountInfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				String strInfo = String.valueOf(((TextView)view).getText()); //记录收入信息
				String strid = strInfo.substring(0, strInfo.indexOf('|'));  //从收入信息中截取收入编号
				Intent intent = new Intent(InAccountInfo.this,InfoManage.class);
				intent.putExtra(FLAG, new String[]{strid,strInType}); //设置传递数据
				startActivity(intent);
			}
		});
	}*/
