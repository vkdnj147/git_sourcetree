package model;

public class ReservationVO {
	
	String r_no;//예약번호
	String r_payment;//결제방법
	String r_price;//금액
	String r_reserveddate;//예약일
	String r_checkin;//입실 시간
	String r_record;//기록
	String r_escape;//탈출 여부
	String c_no;//고객번호
	String em_no; // 사원번호
	String t_no;//테마번호
	String c_name;//고객이름
	String c_phone;//고객전화번호
	String c_age;//고객나이
	String c_team;//고객 팀원수
	String t_name;//테마
	
	//디폴트 생성자
	public ReservationVO() {
		super();
	}
	
	//예약할때 추가 수정할때 필요한 생성자
	public ReservationVO(String r_payment, String r_price, String r_reserveddate, String r_checkin, String r_record,
			String r_escape) {
		super();
		this.r_payment = r_payment;
		this.r_price = r_price;
		this.r_reserveddate = r_reserveddate;
		this.r_checkin = r_checkin;
		this.r_record = r_record;
		this.r_escape = r_escape;
	}

	//전체다
	public ReservationVO(String r_no, String r_payment, String r_price, String r_reserveddate, String r_checkin,
			String r_record, String r_escape, String c_no, String em_no, String t_no, String c_name, String c_phone,
			String c_age, String c_team, String t_name) {
		super();
		this.r_no = r_no;
		this.r_payment = r_payment;
		this.r_price = r_price;
		this.r_reserveddate = r_reserveddate;
		this.r_checkin = r_checkin;
		this.r_record = r_record;
		this.r_escape = r_escape;
		this.c_no = c_no;
		this.em_no = em_no;
		this.t_no = t_no;
		this.c_name = c_name;
		this.c_phone = c_phone;
		this.c_age = c_age;
		this.c_team = c_team;
		this.t_name = t_name;
	}

	//접근자 설정자
	public String getR_no() {
		return r_no;
	}

	public void setR_no(String r_no) {
		this.r_no = r_no;
	}

	public String getR_payment() {
		return r_payment;
	}

	public void setR_payment(String r_payment) {
		this.r_payment = r_payment;
	}

	public String getR_price() {
		return r_price;
	}

	public void setR_price(String r_price) {
		this.r_price = r_price;
	}

	public String getR_reserveddate() {
		return r_reserveddate;
	}

	public void setR_reserveddate(String r_reserveddate) {
		this.r_reserveddate = r_reserveddate;
	}

	public String getR_checkin() {
		return r_checkin;
	}

	public void setR_checkin(String r_checkin) {
		this.r_checkin = r_checkin;
	}

	public String getR_record() {
		return r_record;
	}

	public void setR_record(String r_record) {
		this.r_record = r_record;
	}

	public String getR_escape() {
		return r_escape;
	}

	public void setR_escape(String r_escape) {
		this.r_escape = r_escape;
	}

	public String getC_no() {
		return c_no;
	}

	public void setC_no(String c_no) {
		this.c_no = c_no;
	}

	public String getEm_no() {
		return em_no;
	}

	public void setEm_no(String em_no) {
		this.em_no = em_no;
	}

	public String getT_no() {
		return t_no;
	}

	public void setT_no(String t_no) {
		this.t_no = t_no;
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

	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
	}

	
	

	
	

}
