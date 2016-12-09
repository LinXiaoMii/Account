package com.account.app.db;

import java.util.ArrayList;
import java.util.List;

import com.account.app.model.InAccount;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

//收入信息管理
public class InAccoutDB {

	private SQLiteDatabase db ;
	private AccountOpenHelper helper ;
	
	public InAccoutDB(Context context) {
		// TODO Auto-generated constructor stub
		helper = new AccountOpenHelper(context);
	}
	
	//添加收入信息
	public void addInAccount(InAccount inAccount){
		db = helper.getWritableDatabase() ; 
		db.execSQL("insert into INACCOUNT(id,money,time,type,handler,mark)values(?,?,?,?,?,?)",new Object[]{inAccount.getId(),inAccount.getMoney(),
				                          inAccount.getTime(),inAccount.getType(),inAccount.getHandler(),inAccount.getMark()});
	}
	
	//更新收入信息
	public void updateInAccount(InAccount inAccount){
		db = helper.getWritableDatabase() ;
		db.execSQL("update INACCOUNT set money = ?,time = ?,type = ?,hanlder = ?,mark = ? where id =?",new Object[]{inAccount.getMoney(),inAccount.getTime(),
				inAccount.getType(),inAccount.getHandler(),inAccount.getMark(),inAccount.getId()});
	}
	
	//查找收入信息
	public InAccount findInAccoutDB(int id){
		db = helper.getWritableDatabase() ;
		Cursor cursor = db.rawQuery("select * from INACCOUNT where id =?", new String[]{String.valueOf(id)});
		if(cursor.moveToNext()){
			Double money = cursor.getDouble(cursor.getColumnIndex("money"));
			String time = cursor.getString(cursor.getColumnIndex("time"));
			String type = cursor.getString(cursor.getColumnIndex("type"));
			String handler = cursor.getString(cursor.getColumnIndex("handler"));
			String mark = cursor.getString(cursor.getColumnIndex("mark"));
			int ID = cursor.getInt(cursor.getColumnIndex("id"));
			InAccount inAccount = new InAccount(id ,money , time , type , handler , mark);
			return inAccount ;
		}
		return null ;
		
	}
	//删除收入信息
	public void deleteInAccoutDB(Integer...ids){
		if(ids.length>0){
			StringBuffer sb = new StringBuffer();
			for(int i =0;i<ids.length;i++){
				sb.append('?').append('?');
			}
			db.execSQL("delete from INACCOUNT where id in("+ sb +")",(Object[])ids);
		}
	}
	
	//浏览收入信息
	public List<InAccount> loadInAccount(int start,int count){
		Cursor cursor = db.rawQuery("select * from INACCOUNT limit ?,?",new String[]{String.valueOf(start),String.valueOf(count)});
		List<InAccount> inAccounts = new ArrayList<InAccount>();
		while(cursor.moveToNext()){
			InAccount inAccount = new InAccount();
			inAccount.setId(cursor.getInt(cursor.getColumnIndex("id")));
			inAccount.setTime(cursor.getString(cursor.getColumnIndex("time")));
			inAccount.setType(cursor.getString(cursor.getColumnIndex("type")));
			inAccount.setMark(cursor.getString(cursor.getColumnIndex("mark")));
			inAccount.setHandler(cursor.getString(cursor.getColumnIndex("handler")));
			inAccount.setMoney(cursor.getDouble(cursor.getColumnIndex("money")));
			inAccounts.add(inAccount);
		}
		return inAccounts ;
	}
	
	//获取总记录数
	public long getCount(){
		db = helper.getWritableDatabase() ;
		Cursor cursor = db.rawQuery("select count(id) from INACCOUNT", null);
		if(cursor.moveToNext()){
			return cursor.getLong(0);
		}
		return 0 ;
	}
	
	//获取编号最大值
	public int getMax(){
		db = helper.getWritableDatabase() ;
		Cursor cursor = db.rawQuery("select max(id) from INACCOUNT", null);
		while(cursor.moveToNext()){
			return cursor.getInt(0);
		}
		return 0 ;
	}

}
