package com.account.app.db;

import com.account.app.model.Passward;

import android.content.Context;
import android.database.Cursor;
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
		db.execSQL("insert into PASSWARD(passward)values(?)",new Object[]{Passward.getPassward()});
	}
		
	//ĞŞ¸ÄÃÜÂë
	public void updatePassward(Passward Passward){
		db = helper.getWritableDatabase() ;
		db.execSQL("update PASSWARD set passward = ? ",new Object[]{Passward.getPassward()});
	}
	
	//É¾³ıÃÜÂë
	public void deletePasswardtDB(){
		db.execSQL("delete from PASSWARD ",null);
	}
	
	public Passward findPassward(){
		db = helper.getReadableDatabase() ;
		Cursor cursor = db.query("PASSWARD", null, null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			String passward = cursor.getString(cursor.getColumnIndex("passward"));
			return new Passward(passward) ;
		}
		return new Passward() ;
	}
	
	public long getCount(){
		db = helper.getWritableDatabase() ;
		Cursor cursor = db.rawQuery("select count(passward) from PASSWARD", null);
		if(cursor.moveToNext()){
			return cursor.getLong(0) ;
		}
		return 0 ;
	}

}
