package com.account.app.db;

import java.util.ArrayList;
import java.util.List;

import com.account.app.model.Flag;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

//��ǩ����
public class FlagDB {

	private AccountOpenHelper helper ;
	private SQLiteDatabase db ;
	
	public FlagDB(Context context) {
		// TODO Auto-generated constructor stub
		helper = new AccountOpenHelper(context) ;
	}

	//��ӱ�ǩ
	public void addFlag(Flag flag){
		db = helper.getWritableDatabase() ; //��ʼ�����ݿ����
		ContentValues values = new ContentValues() ;
		values.put("id", flag.getId()) ;
		values.put("flag", flag.getFlag()) ;
		db.insert("FLAG", null, values) ;
	}
	
	//���±�ǩ
	public void updateFlag(Flag flag){
		db = helper.getWritableDatabase() ; //��ʼ�����ݿ����
		/*ContentValues values = new ContentValues() ;
		values.put("flag", flag.getFlag()) ;
		db.update("FLAG", values, "id=?", new String[]{String.valueOf(flag.getId())}) ;*/
		db.execSQL("update FLAG set flag = ? where id =?",new Object[]{flag.getFlag(),flag.getId()});//�Ƽ� ����
	}
	
	//���ұ�ǩ
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
	
	//ɾ����ǩ(�ص㣩
	/*public void deleteFlag(int []ids){
		if(ids.length >0 ){
			for(int i = 0;i<ids.length ;i++){
				db.execSQL("delete from FLAG where id = ?",new Integer[]{ids[0]});
			}
		}
	}*/
	//@param ids
	public void deleteFlag(Integer...ids){ //...��ʾһ������Integer���ͣ�����һ��ʡ��д����
		if(ids.length > 0){  //�ж��Ƿ����Ҫɾ����id
			StringBuffer sb = new StringBuffer() ;
			for(int i=0;i<ids.length;i++){  //����Ҫɾ����id����
				sb.append('?').append(',');  //��ɾ��������ӵ�StringBuffer������
			}
			sb.deleteCharAt(sb.length()-1);  //ȥ�����һ���������ַ�
			db = helper.getWritableDatabase() ;
			db.execSQL("delete from FLAG where id in (" + sb + ")",(Object[])ids);
		}
	}
	
	//�����ǩ
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
	
	//��ȡ�ܱ�ǩ��
	public long getCount(){
		db = helper.getWritableDatabase() ; 
		Cursor cursor = db.rawQuery("select count(id) from FLAG", null) ; //��ȡ������Ϣ�ļ�¼��
		if(cursor.moveToNext()){  //�ж� cursor���Ƿ�������
			return cursor.getLong(0); //�����ܼ�¼��
		}
		return 0 ;
	}
		
}
