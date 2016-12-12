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

	public static final String FLAG = "id" ; //����һ����������Ϊ������
	String strInfo[] = null ; //�����ַ����洢����
	
	public void showInfo(final String strInType,final Context context){  //���ݹ���������ʾ��Ӧ��Ϣ
		
		if(strInType == "ininfo" ){
			InAccoutDB inaccountInfo = new InAccoutDB(context);
			//��ȡ����������Ϣ,���洢��list���ͼ�����
			List<InAccount> listInfos = inaccountInfo.loadInAccount(0, (int)inaccountInfo.getCount());
			strInfo = new String[listInfos.size()];
			int m = 0 ; //����һ����ʼ��־
			for(InAccount inAccount:listInfos){  //����list���ͼ���
				strInfo[m] = inAccount.getId()+"|"+inAccount.getType()+" "+String.valueOf(inAccount.getMoney())+"Ԫ      "+inAccount.getType() ;
				m++ ;
			}
		}else if(strInType == "outinfo"){
			OutAccountDB outaccountInfo = new OutAccountDB(context);
			//��ȡ����������Ϣ,���洢��list���ͼ�����
			List<OutAccount> listInfos = outaccountInfo.loadOutAccount(0, (int)outaccountInfo.getCount());
			strInfo = new String[listInfos.size()];
			int m = 0 ; //����һ����ʼ��־
			for(OutAccount outAccount:listInfos){  //����list���ͼ���
				strInfo[m] = outAccount.getId()+"|"+outAccount.getType()+" "+String.valueOf(outAccount.getMoney())+"Ԫ      "+outAccount.getType() ;
				m++ ;
			}
		}
		
	}
}
