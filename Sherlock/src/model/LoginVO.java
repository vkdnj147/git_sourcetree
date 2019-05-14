package model;

public class LoginVO {

	private String id; // 아이디

	// 디폴드 생성자
	public LoginVO() {
		super();
	}

	// 생성자
	public LoginVO(String id) {
		super();
		this.id = id;
	}

	// 접근자와 설정자
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
