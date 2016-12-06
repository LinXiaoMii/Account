package com.account.app.model;

//支出信息表
public class OutAccount {


	private int id ;
	private double money ;
	private String time ;
	private String type ;
	private String address ;//支出地点
	private String mark ; //备注
	
	//定义构造函数
	public OutAccount() {
		// TODO Auto-generated constructor stub
		super();
	}
	public OutAccount(int id , double money , String time , String type , String address , String mark) {
		this.id = id ;
		this.money = money ;
		this.time = time ;
		this.type = type ;
		this.address = address ;
		this.mark = mark ;
	}
	
	public void setId(int id){
		this.id = id ;
	}
	
	public int getId(){
		return id ;
	}
	
	public void setMoney(double money){
		this.money = money ;
	}
	
	public Double getMoney(){
		return money ;
	}
	
	public void setType(String type){
		this.type = type ;
	}
	
	public String getType(){
		return type ;
	}
	
	public void setAddress(String handler){
		this.address = address ;
	}
	
	public String getAddress(){
		return address ;
	}
	
	public void setTime(String time){
		this.time = time ;
	}
	
	public String getTime(){
		return time ;
	}
	
	public void setMark(String mark){
		this.time = time ;
	}
	
	public String getMark(){
		return mark ;
	}

}
