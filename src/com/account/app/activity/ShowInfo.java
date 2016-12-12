package com.account.app.activity;

import java.util.List;

import com.account.app.db.InAccoutDB;
import com.account.app.db.OutAccountDB;
import com.account.app.model.InAccount;
import com.account.app.model.OutAccount;

import android.content.Context;

public class ShowInfo {
	
	public static ShowInfo newInstance(){
		ShowInfo showInfo = new ShowInfo();
		return showInfo ;
	}

	public static final String FLAG = "id" ; //定义一个常量来作为请求码
	String strInfo[] = null ; //定义字符串存储数组
	
	public void showInfo(final String strInType,final Context context){  //根据管理类型显示相应信息
		
		if(strInType == "ininfo" ){
			InAccoutDB inaccountInfo = new InAccoutDB(context);
			//获取所有收入信息,并存储到list泛型集合中
			List<InAccount> listInfos = inaccountInfo.loadInAccount(0, (int)inaccountInfo.getCount());
			strInfo = new String[listInfos.size()];
			int m = 0 ; //定义一个开始标志
			for(InAccount inAccount:listInfos){  //遍历list泛型集合
				strInfo[m] = inAccount.getId()+"|"+inAccount.getType()+" "+String.valueOf(inAccount.getMoney())+"元      "+inAccount.getType() ;
				m++ ;
			}
		}else if(strInType == "outinfo"){
			OutAccountDB outaccountInfo = new OutAccountDB(context);
			//获取所有收入信息,并存储到list泛型集合中
			List<OutAccount> listInfos = outaccountInfo.loadOutAccount(0, (int)outaccountInfo.getCount());
			strInfo = new String[listInfos.size()];
			int m = 0 ; //定义一个开始标志
			for(OutAccount outAccount:listInfos){  //遍历list泛型集合
				strInfo[m] = outAccount.getId()+"|"+outAccount.getType()+" "+String.valueOf(outAccount.getMoney())+"元      "+outAccount.getType() ;
				m++ ;
			}
		}
		
	}
}
