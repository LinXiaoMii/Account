package com.account.app.model;

//便签 信息表
public class Flag {

	private int id ;
	private String flag ;
	
	//定义构造函数（两种）
	public Flag(){
		super();
	}
	public Flag(int id , String flag) {
		// TODO Auto-generated constructor stub
		this.id = id ;
		this.flag = flag ;
	}
	
	public void setId(int id){
		this.id = id ;
	}
	
	public void setFlag(String flag){
		this.flag = flag ;
	}
	
	public int getId(){
		return id ;
	}
	
	public String getFlag(){
		return flag ;
	}
}
;