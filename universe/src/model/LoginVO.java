package model;

public class LoginVO {
	private String id;//아이디

	public LoginVO() {
		super();
	}

	public LoginVO(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}