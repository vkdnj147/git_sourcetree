package model;

public class JoinVO {
	//직원 등록 변수선언
    String em_no;//직원 번호
    String em_id;//아이디
    String em_passwd;//비밀번호
    String em_name;//이름
    String em_phone;//핸드폰 번호
    String em_bank;//은행명
    String em_account;//계좌번호
    String em_rank;//직급
    String em_entry;//입사일
    String em_leaveday;//퇴사일
    String em_address;//주소
    String em_whether;//재직여부
    
    //디폴트 생성자
	public JoinVO() {
		super();
	}

	//아이디 중복확인을 위한 생성자
	public JoinVO(String em_id) {
		super();
		this.em_id = em_id;
	}

	//로그인 일치를 위한 생성자
	public JoinVO(String em_id, String em_passwd) {
		super();
		this.em_id = em_id;
		this.em_passwd = em_passwd;
	}

	//전체 생성자
	public JoinVO(String em_no, String em_id, String em_passwd, String em_name, String em_phone, String em_bank,
			String em_account, String em_rank, String em_entry, String em_leaveday, String em_address,
			String em_whether) {
		super();
		this.em_no = em_no;
		this.em_id = em_id;
		this.em_passwd = em_passwd;
		this.em_name = em_name;
		this.em_phone = em_phone;
		this.em_bank = em_bank;
		this.em_account = em_account;
		this.em_rank = em_rank;
		this.em_entry = em_entry;
		this.em_leaveday = em_leaveday;
		this.em_address = em_address;
		this.em_whether = em_whether;
	}

	//접근자 설정자
	public String getEm_no() {
		return em_no;
	}

	public void setEm_no(String em_no) {
		this.em_no = em_no;
	}

	public String getEm_id() {
		return em_id;
	}

	public void setEm_id(String em_id) {
		this.em_id = em_id;
	}

	public String getEm_passwd() {
		return em_passwd;
	}

	public void setEm_passwd(String em_passwd) {
		this.em_passwd = em_passwd;
	}

	public String getEm_name() {
		return em_name;
	}

	public void setEm_name(String em_name) {
		this.em_name = em_name;
	}

	public String getEm_phone() {
		return em_phone;
	}

	public void setEm_phone(String em_phone) {
		this.em_phone = em_phone;
	}

	public String getEm_bank() {
		return em_bank;
	}

	public void setEm_bank(String em_bank) {
		this.em_bank = em_bank;
	}

	public String getEm_account() {
		return em_account;
	}

	public void setEm_account(String em_account) {
		this.em_account = em_account;
	}

	public String getEm_rank() {
		return em_rank;
	}

	public void setEm_rank(String em_rank) {
		this.em_rank = em_rank;
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