package com.account.app.db;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;

//��д��ȫ�˳�����ķ���
public class ExitApplication extends Activity{
	private List<Activity> activityList = new LinkedList<Activity>() ;
	private static ExitApplication instance ;
	
	private ExitApplication(){
		
	}
	
	//����ģʽ��ȡΨһ��ExitApplicationʵ��
	public static ExitApplication getInstance(){
		if(null == instance){
			instance = new ExitApplication() ;
		}
		return instance ;
	}
	
	public void addActivity(Activity activity){
		activityList.add(activity) ;
	}
	
	public void exit(){
		for(Activity activity:activityList){
			activity.finish();
		}
		System.exit(0);
	}
}
