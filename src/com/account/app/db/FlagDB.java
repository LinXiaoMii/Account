package com.account.app.db;

import java.util.ArrayList;
import java.util.List;

import com.account.app.model.Flag;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

//便签管理
public class FlagDB {

	private AccountOpenHelper helper ;
	private SQLiteDatabase db ;
	
	public FlagDB(Context context) {
		// TODO Auto-generated constructor stub
		helper = new AccountOpenHelper(context) ;
	}

	//添加便签
	public void addFlag(Flag flag){
		db = helper.getWritableDatabase() ; //初始化数据库对象
		ContentValues values = new ContentValues() ;
		values.put("id", flag.getId()) ;
		values.put("flag", flag.getFlag()) ;
		db.insert("FLAG", null, values) ;
	}
	
	//更新便签
	public void updateFlag(Flag flag){
		db = helper.getWritableDatabase() ; //初始化数据库对象
		/*ContentValues values = new ContentValues() ;
		values.put("flag", flag.getFlag()) ;
		db.update("FLAG", values, "id=?", new String[]{String.valueOf(flag.getId())}) ;*/
		db.execSQL("update FLAG set flag = ? where id =?",new Object[]{flag.getFlag(),flag.getId()});//推荐 方法
	}
	
	//查找便签
	public Flag findFlag(int id){
		db = helper.getWritableDatabase() ;
		Cursor cursor = db.rawQuery("select flag from FLAG where id =?", new String[]{String.valueOf(id)});
		if(cursor.moveToFirst()){
			do{
				String flag = cursor.getString(cursor.getColumnIndex("flag"));
				int ID = cursor.getInt(cursor.getColumnIndex("id"));
				return new Flag(ID,flag) ;
			}while(cursor.moveToNext());
		}
		return null ;
	}
	
	//删除便签(重点）
	/*public void deleteFlag(int []ids){
		if(ids.length >0 ){
			for(int i = 0;i<ids.length ;i++){
				db.execSQL("delete from FLAG where id = ?",new Integer[]{ids[0]});
			}
		}
	}*/
	//@param ids
	public void deleteFlag(Integer...ids){ //...表示一个或多个Integer类型（类似一种省略写法）
		if(ids.length > 0){  //判断是否存在要删除的id
			StringBuffer sb = new StringBuffer() ;
			for(int i=0;i<ids.length;i++){  //遍历要删除的id集合
				sb.append('?').append(',');  //将删除条件添加到StringBuffer对象中
			}
			sb.deleteCharAt(sb.length()-1);  //去掉最后一个“，”字符
			db = helper.getWritableDatabase() ;
			db.execSQL("delete from FLAG where id in (" + sb + ")",(Object[])ids);
		}
	}
	
	//浏览便签
	public List<Flag> loadFlag(){
		List<Flag> flags = new ArrayList<Flag>();
		Cursor cursor = db.query("FLAG", null, null, null, null, null, null) ;
		if(cursor.moveToFirst()){
			do{
				Flag flag = new Flag() ;
				flag.setId(cursor.getInt(cursor.getColumnIndex("id")));
				flag.setFlag(cursor.getString(cursor.getColumnIndex("flag")));
				flags.add(flag);
			}while(cursor.moveToNext());
		}
		return flags ;
		
	}
	
	//获取总便签数
	public long getCount(){
		db = helper.getWritableDatabase() ; 
		Cursor cursor = db.rawQuery("select count(id) from FLAG", null) ; //获取收入信息的记录数
		if(cursor.moveToNext()){  //判断 cursor中是否有数据
			return cursor.getLong(0); //返回总记录数
		}
		return 0 ;
	}
		
}
