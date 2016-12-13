package com.account.app.activity;



import com.example.account.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainActivity extends Activity{

	GridView gridView ;
	String []titles = new String[]{"��������","����֧��","�ҵ�֧��","�ҵ�����","���ݹ���","ϵͳ����","��֧��ǩ","�˳�"};
	int images[] =new int[]{R.drawable.incount,R.drawable.output,R.drawable.myoutput,R.drawable.myinput,R.drawable.manage,R.drawable.setting,R.drawable.flag,R.drawable.exit};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		gridView = (GridView)findViewById(R.id.gridItem);
		//����picturAAdapter����
		PictureAdapter adapter = new PictureAdapter(titles,images,MainActivity.this) ;
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = null;
				switch(position){
				case 0:
					intent = new Intent(MainActivity.this,AddInaccount.class);
					startActivity(intent);
					break;
				case 1:
					intent = new Intent(MainActivity.this,AddOutaccount.class);
					startActivity(intent);
					break; 
				case 2:
					intent = new Intent(MainActivity.this,OutAccountInfo.class);
					startActivity(intent);
					break; 
				case 3:
					intent = new Intent(MainActivity.this,InAccountInfo.class);
					startActivity(intent);
					break; 
				case 4:
					intent = new Intent(MainActivity.this,DataManage.class);
					startActivity(intent);
					break; 
				case 5:
					intent = new Intent(MainActivity.this,Setting.class);
					startActivity(intent);
					break; 
				case 6:
					intent = new Intent(MainActivity.this,AccountFlag.class);
					startActivity(intent);
					break; 
				case 7:
					finish();
				}
			}
			
		});
	}

	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		Intent intent = new Intent(this,Login.class);
		startActivity(intent);
	}

}
