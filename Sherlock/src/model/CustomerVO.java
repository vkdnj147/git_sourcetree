package model;

public class CustomerVO {
	
	String c_No;//고객번호
	String c_Name;//고객이름
	String c_Phone;//고객전화번호
	String c_Age;//고객나이
	String c_Team;//고객 팀원수
	
	
	//디폴트 생성자
	public CustomerVO() {
		super();
	}

	// 전체다
	public CustomerVO(String c_No, String c_Name, String c_Phone, String c_Age, String c_Team) {
		super();
		this.c_No = c_No;
		this.c_Name = c_Name;
		this.c_Phone = c_Phone;
		this.c_Age = c_Age;
		this.c_Team = c_Team;
	}

	//접근자 설정자
	public String getC_No() {
		return c_No;
	}

	public void setC_No(String c_No) {
		this.c_No = c_No;
	}

	public String getC_Name() {
		return c_Name;
	}

	public void setC_Name(String c_Name) {
		this.c_Name = c_Name;
	}

	public String getC_Phone() {
		return c_Phone;
	}

	public void setC_Phone(String c_Phone) {
		this.c_Phone = c_Phone;
	}

	public String getC_Age() {
		return c_Age;
	}

	public void setC_Age(String c_Age) {
		this.c_Age = c_Age;
	}

	public String getC_Team() {
		return c_Team;
	}

	public void setC_Team(String c_Team) {
		this.c_Team = c_Team;
	}
	
	
	

}
