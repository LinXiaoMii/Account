package com.account.app.activity;



import com.example.account.R;

import android.app.Activity;
import android.os.Bundle;
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
			
		});
	}

}
