package com.account.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

//�������ݿ����ݱ�
public class AccountOpenHelper extends SQLiteOpenHelper{
	
	//���ݿ�������Ϣ
	static final int DB_VERSION = 1 ;
	static final String DB_NAME = "account.db" ;
	private SQLiteDatabase db ;
	
	
	//������ǩ��Ϣ��
	public String CREATE_FLAG = "create table FLAG(" + " id integer primary key autoincrement,"
			                                         + " flag varchar(200) )";
	//����������Ϣ��
	public String CREATE_PWD = "create table PASSWARD(" + " passward varchar(20) )";
	//����֧����Ϣ��
	public String CREATE_OUTACCOUNT = "create table OUTACCOUNT(" + " id integer primary key autoincrement,"
	                                                             + " money decimal,"
			                                                     + " time varchar(10),"
	                                                             + " type varchar(10),"
			                                                     + " address varchar(100),"
	                                                             + " mark varchar(200) )";
	//����������Ϣ��
	public String CREATE_INACCOUNT = "create table INACCOUNT(" + " id integer primary key autoincrement,"
															   + " money deciaml,"
															   + " time varchar(10),"
															   + " type varchar(10),"
															   + " handler varchar(100),"
															   + " mark varchar(200) )";
			
	//���캯��
	public AccountOpenHelper(Context context) {
		super(context, DB_NAME , null , DB_VERSION);//��ʼ��
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_FLAG);
		db.execSQL(CREATE_INACCOUNT);
		db.execSQL(CREATE_OUTACCOUNT);
		db.execSQL(CREATE_PWD);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
