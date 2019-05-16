package model;

public class CustomerVO {
	
	String c_no;//고객번호
	String c_name;//고객이름
	String c_phone;//고객전화번호
	String c_age;//고객나이
	String c_team;//고객 팀원수
	
	
	//디폴트 생성자
	public CustomerVO() {
		super();
	}

	//전체
	public CustomerVO(String c_no, String c_name, String c_phone, String c_age, String c_team) {
		super();
		this.c_no = c_no;
		this.c_name = c_name;
		this.c_phone = c_phone;
		this.c_age = c_age;
		this.c_team = c_team;
	}

	//접근자 설정자
	public String getC_no() {
		return c_no;
	}

	public void setC_no(String c_no) {
		this.c_no = c_no;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getC_phone() {
		return c_phone;
	}

	public void setC_phone(String c_phone) {
		this.c_phone = c_phone;
	}

	public String getC_age() {
		return c_age;
	}

	public void setC_age(String c_age) {
		this.c_age = c_age;
	}

	public String getC_team() {
		return c_team;
	}

	public void setC_team(String c_team) {
		this.c_team = c_team;
	}
	
	
	
	
	
	

}
