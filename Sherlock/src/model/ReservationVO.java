package model;

public class ReservationVO {

	// 예약날짜 , 예약 시간 , 테마, 이름, 핸드폰, 나이, 금액,결제 방법 선택, 결제 완료 여부, 탈출 여부, 힌트

	String r_no;// 예약자 번호
	String r_reserveddate; // 예약날짜
	String r_reservedtime; // 예약시간
	String t_name; // 테마 이름
	String c_name; // 고객 이름
	String c_team; // 인원수
	String c_phone; // 핸드폰
	String c_age; // 나이
	String t_price; // 금액
	String r_payment; // 결제 방법 선택
	String r_pay; // 결제 완료 여부
	String r_escape; // 탈출 여부
	String r_hint; // 탈출

	// 디폴트 생성자
	public ReservationVO() {
		super();
	}

	public ReservationVO(String r_no, String r_reserveddate, String r_reservedtime, String t_name, String c_name,
			String c_team, String c_phone, String c_age, String t_price, String r_payment, String r_pay,
			String r_escape, String r_hint) {
		super();
		this.r_no = r_no;
		this.r_reserveddate = r_reserveddate;
		this.r_reservedtime = r_reservedtime;
		this.t_name = t_name;
		this.c_name = c_name;
		this.c_team = c_team;
		this.c_phone = c_phone;
		this.c_age = c_age;
		this.t_price = t_price;
		this.r_payment = r_payment;
		this.r_pay = r_pay;
		this.r_escape = r_escape;
		this.r_hint = r_hint;
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

	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
	}

	public String getR_no() {
		return r_no;
	}

	public void setR_no(String r_no) {
		this.r_no = r_no;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getC_team() {
		return c_team;
	}

	public void setC_team(String c_team) {
		this.c_team = c_team;
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

	public String getT_price() {
		return t_price;
	}

	public void setT_price(String t_price) {
		this.t_price = t_price;
	}

	public String getR_payment() {
		return r_payment;
	}

	public void setR_payment(String r_payment) {
		this.r_payment = r_payment;
	}

	public String getR_pay() {
		return r_pay;
	}

	public void setR_pay(String r_pay) {
		this.r_pay = r_pay;
	}

	public String getR_escape() {
		return r_escape;
	}

	public void setR_escape(String r_escape) {
		this.r_escape = r_escape;
	}

	public String getR_hint() {
		return r_hint;
	}

	public void setR_hint(String r_hint) {
		this.r_hint = r_hint;
	}

}