package model;

//전체리스트 직원정보
public class EmployeeVO {

	String em_no; // 사원번호
	String em_entry; // 입사일, 변형이 없기 때문에 String으로 받는다
	String em_leaveday; // 퇴사일, 변함이 없기 때문에 String으로 받는다
	String em_address; // 주소
	String em_whether; // 재직여부

	// 디폴트 생성자
	public EmployeeVO() {
		super();
	}

	// 사원번호 제거하고
	public EmployeeVO(String em_entry, String em_leaveday, String em_address, String em_whether) {
		super();
		this.em_entry = em_entry;
		this.em_leaveday = em_leaveday;
		this.em_address = em_address;
		this.em_whether = em_whether;
	}

	public EmployeeVO(String em_no, String em_entry, String em_leaveday, String em_address, String em_whether) {
		super();
		this.em_no = em_no;
		this.em_entry = em_entry;
		this.em_leaveday = em_leaveday;
		this.em_address = em_address;
		this.em_whether = em_whether;
	}

	
	//접근자와 설정자
	public String getEm_no() {
		return em_no;
	}

	public void setEm_no(String em_no) {
		this.em_no = em_no;
	}

	public String getEm_entry() {
		return em_entry;
	}

	public void setEm_entry(String em_entry) {
		this.em_entry = em_entry;
	}

	public String getEm_leaveday() {
		return em_leaveday;
	}

	public void setEm_leaveday(String em_leaveday) {
		this.em_leaveday = em_leaveday;
	}

	public String getEm_address() {
		return em_address;
	}

	public void setEm_address(String em_address) {
		this.em_address = em_address;
	}

	public String getEm_whether() {
		return em_whether;
	}

	public void setEm_whether(String em_whether) {
		this.em_whether = em_whether;
	}

}
