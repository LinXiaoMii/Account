package com.account.app.model;

//��ǩ ��Ϣ��
public class Flag {

	private int id ;
	private String flag ;
	
	//���幹�캯�������֣�
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