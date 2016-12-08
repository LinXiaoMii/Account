package com.account.app.activity;



import com.example.account.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainActivity extends Activity{

	GridView gridView ;
	String []titles = new String[]{"新增收入","新增支出","我的支出","我的收入","数据管理","系统设置","收支便签","退出"};
	int images[] =new int[]{R.drawable.incount,R.drawable.output,R.drawable.myoutput,R.drawable.myinput,R.drawable.manage,R.drawable.setting,R.drawable.flag,R.drawable.exit};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		gridView = (GridView)findViewById(R.id.gridItem);
		//创建picturAAdapter对象
		PictureAdapter adapter = new PictureAdapter(titles,images,MainActivity.this) ;
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener(){
			
		});
	}

}
