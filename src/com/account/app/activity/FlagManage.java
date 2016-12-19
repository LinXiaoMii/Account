package com.account.app.activity;

import com.account.app.db.ExitApplication;
import com.account.app.db.FlagDB;
import com.account.app.model.Flag;
import com.example.account.R;

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
	String []strInfos ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flagmanage);
		//便于最终关闭程序
		ExitApplication.getInstance().addActivity(this);
		
		editFlag = (EditText)findViewById(R.id.editFlagManage);
		buttonEdit = (Button)findViewById(R.id.buttonFlagEditManage);
		buttonCancel = (Button)findViewById(R.id.buttonFlagCancelManage);
		
		Intent intent = getIntent() ;//创建Intent对象，getIntent方法可以之间返回一个Intent实例
		Bundle bundle = intent.getExtras() ; //获取便签id（从DataManage传递过来的数据
		strInfos = bundle.getStringArray(ShowInfo.FLAG);
		strId = strInfos[0] ; //记录id
		final FlagDB flagDB = new FlagDB(FlagManage.this) ;
		editFlag.setText(flagDB.findFlag(Integer.parseInt(strId)).getFlag());//根据id产赵便签信息并显示在文本中????????stiId有问题
		
		buttonEdit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Flag flag = new Flag() ;
				flag.setId(Integer.parseInt(strId));
				flag.setFlag(editFlag.getText().toString());
				flagDB.updateFlag(flag);
				Toast.makeText(getApplicationContext(), "便签数据修改成功", Toast.LENGTH_SHORT).show();
				
				//返回上一级菜单，达到刷新数据的效果（否则停在该界面，利用返回键返回ListView时，数据并没有刷新）
				Intent intent = new Intent(FlagManage.this,DataManage.class);
				startActivity(intent);
			}
		});
		
		buttonCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				flagDB.deleteFlag(Integer.parseInt(strId));
				Toast.makeText(getApplicationContext(), "便签数据删除成功", Toast.LENGTH_SHORT).show();
				
				//返回上一级菜单，达到刷新数据的效果（否则停在该界面，利用返回键返回ListView时，数据并没有刷新）
				Intent intent = new Intent(FlagManage.this,DataManage.class);
				startActivity(intent);
			}
		});
	}

}
