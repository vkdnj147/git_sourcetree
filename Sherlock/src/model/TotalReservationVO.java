package model;

public class TotalReservationVO {

	//전체 이용자 관리 리스트를 불러오기 위한 VO
	//이용자 번호 , 이름, 핸드폰번호, 테마, 성공여부 

	String c_no;//고객번호
	String c_name;//고객이름
	String c_phone;//고객전화번호
	String t_name;//테마이름
	String r_escape;//탈출 여부
	
	//기본 생성자
	public TotalReservationVO() {
		super();
	}

	public TotalReservationVO(String c_no, String c_name, String c_phone, String t_name, String r_escape) {
		super();
		this.c_no = c_no;
		this.c_name = c_name;
		this.c_phone = c_phone;
		this.t_name = t_name;
		this.r_escape = r_escape;
	}

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

	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
	}

	public String getR_escape() {
		return r_escape;
	}

	public void setR_escape(String r_escape) {
		this.r_escape = r_escape;
	}
	
	
	
	
	
}
