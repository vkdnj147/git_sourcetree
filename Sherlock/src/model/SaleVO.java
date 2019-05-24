
package model;

public class SaleVO {

	// 테마이름으로 매출 정렬
	// 테마 이름, 이용날짜 (예약일), 이용자 이름, 핸드폰번호, 이용인원

	String c_name; // 이용자이름
	String c_phone; // 핸드폰 번호
	String c_team; // 이용인원
	String t_name; // 테마이름
	String t_price;// 이용 금액
	String r_reserveddate; // 예약날짜
	String r_reservedtime; // 예약시간

	public SaleVO() {
		super();
	}

	public SaleVO(String c_name, String c_phone, String c_team, String t_name, String t_price, String r_reserveddate,
			String r_reservedtime) {
		super();
		this.c_name = c_name;
		this.c_phone = c_phone;
		this.c_team = c_team;
		this.t_name = t_name;
		this.t_price = t_price;
		this.r_reserveddate = r_reserveddate;
		this.r_reservedtime = r_reservedtime;
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

	public String getC_team() {
		return c_team;
	}

	public void setC_team(String c_team) {
		this.c_team = c_team;
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

	public String getR_reserveddate() {
		return r_reserveddate;
	}

	public void setR_reserveddate(String r_reserveddate) {
		this.r_reserveddate = r_reserveddate;
	}

	public String getR_reservedtime() {
		return r_reservedtime;
	}

	public void setR_reservedtime(String r_reservedtime) {
		this.r_reservedtime = r_reservedtime;
	}

	

}