package model;

public class JoinVO {

	// 관리자 등록 조건 변수 선언

	String id;// 아이디
	String password;// 비밀번호
	String name;// 이름
	String phone;// 핸드폰번호
	String bank;// 은행명
	String account;// 계좌번호

	// 디폴트 생성자
	public JoinVO() {
		super();
	}

	// 아이디 중복을 위한 생성자
	public JoinVO(String id) {
		super();
		this.id = id;
	}
	// 로그인 일치를 위한 아이디 패스워드 확인 생성자

	public JoinVO(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}
	// 전체 확인을 위한 생성자

	public JoinVO(String id, String password, String name, String phone, String bank, String account) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.bank = bank;
		this.account = account;
	}
	// 접근자 설정자

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

}
