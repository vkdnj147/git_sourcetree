package model;

public class ThemeVO {
	
	String t_no;//테마번호
	String t_name;//테마이름
	String t_price;//테마 가격
	String t_time;//테마시간
	
	//디폴트 생성자
	public ThemeVO() {
		super();
	}

	//전체 다 
	public ThemeVO(String t_no, String t_name, String t_price, String t_time) {
		super();
		this.t_no = t_no;
		this.t_name = t_name;
		this.t_price = t_price;
		this.t_time = t_time;
	}

	//접근자 설정자
	public String getT_no() {
		return t_no;
	}

	public void setT_no(String t_no) {
		this.t_no = t_no;
	}

	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
	}

	public String getT_price() {
		return t_price;
	}

	public void setT_price(String t_price) {
		this.t_price = t_price;
	}

	public String getT_time() {
		return t_time;
	}

	public void setT_time(String t_time) {
		this.t_time = t_time;
	}
	
	
	

}
