package com.account.app.db;

import com.account.app.model.Passward;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

//ÕËºÅÃÜÂë¹ÜÀí
public class PasswardDB {

	private SQLiteDatabase db ;
	private AccountOpenHelper helper ;
	
	public PasswardDB() {
		// TODO Auto-generated constructor stub
		super() ;
	}
	public PasswardDB(Context context){
		helper = new AccountOpenHelper(context);
	}
	
	//Ìí¼ÓÃÜÂë£¨Ê×´ÎµÇÂ½£©
	public void addPassward(Passward Passward){
		db = helper.getWritableDatabase() ; 
		db.execSQL("insert into Passward(passward)values(?)",new Object[]{Passward.getPassward()});
	}
		
	//ĞŞ¸ÄÃÜÂë
	public void updatePassward(Passward Passward){
		db = helper.getWritableDatabase() ;
		db.execSQL("update PASSWARD set passward = ? ",new Object[]{Passward.getPassward()});
	}
	
	//É¾³ıÃÜÂë
	public void deletePasswardtDB(int id){
		db.execSQL("delete from PASSWARD ",null);
	}

}
