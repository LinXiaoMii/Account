package com.account.app.model;

//收入 信息表
public class InAccount {

	private int id ;
	private double money ;
	private String time ;
	private String type ;
	private String handler ;//收款方
	private String mark ; //备注
	
	//定义构造函数（两种）
	public InAccount(){
		super();
	}
	public InAccount(int id ,double money , String time , String type , String handler , String mark) {
		// TODO Auto-generated constructor stub
		this.id = id ;
		this.money = money ;
		this.time = time ;
		this.type = type ;
		this.handler = handler ;
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
	
	public void setHandler(String handler){
		this.handler = handler ;
	}
	
	public String getHandler(){
		return handler ;
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
