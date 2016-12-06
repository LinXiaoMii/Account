package com.account.app.db;

import java.util.ArrayList;
import java.util.List;

import com.account.app.model.OutAccount;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

//֧����Ϣ����
public class OutAccountDB {

	

	private SQLiteDatabase db ;
	private AccountOpenHelper helper ;
	
	public OutAccountDB() {
		// TODO Auto-generated constructor stub
		super();
	}
	public OutAccountDB(Context context) {
		// TODO Auto-generated constructor stub
		helper = new AccountOpenHelper(context);
	}
	
	//���������Ϣ
	public void addOutAccount(OutAccount OutAccount){
		db = helper.getWritableDatabase() ; 
		db.execSQL("insert into OUTACCOUNT(id,money,time,type,address,mark)values(?,?,?,?,?,?)",new Object[]{OutAccount.getId(),OutAccount.getMoney(),
				                          OutAccount.getTime(),OutAccount.getType(),OutAccount.getAddress(),OutAccount.getMark()});
	}
	
	//����������Ϣ
	public void updateOutAccount(OutAccount OutAccount){
		db = helper.getWritableDatabase() ;
		db.execSQL("update OUTACCOUNT set money = ?,time = ?,type = ?,address = ?,mark = ? where id =?",new Object[]{OutAccount.getMoney(),OutAccount.getTime(),
				OutAccount.getType(),OutAccount.getAddress(),OutAccount.getMark(),OutAccount.getId()});
	}
	
	//����������Ϣ
	public OutAccount findOutAccoutDB(int id){
		db = helper.getWritableDatabase() ;
		Cursor cursor = db.rawQuery("select * from OUTACCOUNT where id =?", new String[]{String.valueOf(id)});
		if(cursor.moveToNext()){
			Double money = cursor.getDouble(cursor.getColumnIndex("money"));
			String time = cursor.getString(cursor.getColumnIndex("time"));
			String type = cursor.getString(cursor.getColumnIndex("type"));
			String address = cursor.getString(cursor.getColumnIndex("address"));
			String mark = cursor.getString(cursor.getColumnIndex("mark"));
			int ID = cursor.getInt(cursor.getColumnIndex("id"));
			OutAccount outAccount = new OutAccount(id ,money , time , type , address , mark);
			return outAccount ;
		}
		return null ;
		
	}
	//ɾ��������Ϣ
	public void deleteOutAccoutDB(Integer...ids){
		if(ids.length>0){
			StringBuffer sb = new StringBuffer();
			for(int i =0;i<ids.length;i++){
				sb.append('?').append('?');
			}
			db.execSQL("delete from OUTACCOUNT where id in("+ sb +")",(Object[])ids);
		}
	}
	
	//���������Ϣ
	public List<OutAccount> loadOutAccount(int start,int count){
		Cursor cursor = db.rawQuery("select * from OUTACCOUNT limit ?,?",new String[]{String.valueOf(start),String.valueOf(count)});
		List OutAccounts = new ArrayList<OutAccount>();
		while(cursor.moveToNext()){
			OutAccount OutAccount = new OutAccount();
			OutAccount.setId(cursor.getInt(cursor.getColumnIndex("id")));
			OutAccount.setTime(cursor.getString(cursor.getColumnIndex("time")));
			OutAccount.setType(cursor.getString(cursor.getColumnIndex("typr")));
			OutAccount.setMark(cursor.getString(cursor.getColumnIndex("mark")));
			OutAccount.setMoney(cursor.getDouble(cursor.getColumnIndex("money")));
			OutAccount.setAddress(cursor.getString(cursor.getColumnIndex("address")));
			OutAccounts.add(OutAccount);
		}
		return OutAccounts ;
	}
	
	//��ȡ�ܼ�¼��
	public long getCount(){
		db = helper.getWritableDatabase() ;
		Cursor cursor = db.rawQuery("select count(id) from OUTACCOUNT", null);
		if(cursor.moveToNext()){
			return cursor.getLong(0);
		}
		return 0 ;
	}
	
	//��ȡ������ֵ
	public int getMax(){
		db = helper.getWritableDatabase() ;
		Cursor cursor = db.rawQuery("select max(id) from OUTACCOUNT", null);
		while(cursor.moveToNext()){
			return cursor.getInt(0);
		}
		return 0 ;
	}

}
