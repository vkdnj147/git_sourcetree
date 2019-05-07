package model;

public class JoinVO {

	//변수선언
	String id;// 아이디
	String password;//비밀번호
	String name;//이름
	
	//디폴트 생성자
	public JoinVO() {
		super();
	}

	//아이디 중복확인을 하기 위해서
	public JoinVO(String id) {
		super();
		this.id = id;
	}

	//로그인 시에 일치하게 만들기 위해서 
	public JoinVO(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}

	//전체 다
	public JoinVO(String id, String password, String name) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
	}

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
	
	
	
	
}
