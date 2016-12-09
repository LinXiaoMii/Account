package com.account.app.model;

public class Passward {

	private String passward ;
	
	//定义构造函数
	public Passward() {
		// TODO Auto-generated constructor stub
		super();
	}
	public Passward(String passward){
		this.passward = passward ;
	}
	
	public void setPassward(String passward){
		this.passward = passward ;
	}

	public String getPassward(){
		return passward ;
	}
}
