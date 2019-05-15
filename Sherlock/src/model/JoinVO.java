package model;

public class JoinVO {
	//직원 등록 변수선언
    String em_No;//직원 번호
    String em_Id;//아이디
    String em_Passwd;//비밀번호
    String em_Name;//이름
    String em_Phone;//핸드폰 번호
    String em_Bank;//은행명
    String em_Account;//계좌번호
    String em_Rank;//직급
    String em_Entry;//입사일
    String em_Leaveday;//퇴사일
    String em_Address;//주소
    String em_Whether;//재직여부
	
    //디폴트 생성자
	public JoinVO() {
		super();
	}
	//아이디 중복을 위한 아이디만 있는 생성자
	public JoinVO(String em_Id) {
		super();
		this.em_Id = em_Id;
	}
	//로그인 시에 일치하게 만들기 위해서 
	public JoinVO(String em_Id, String em_Passwd, String em_Name, String em_Phone, String em_Bank, String em_Account) {
		super();
		this.em_Id = em_Id;
		this.em_Passwd = em_Passwd;
		this.em_Name = em_Name;
		this.em_Phone = em_Phone;
		this.em_Bank = em_Bank;
		this.em_Account = em_Account;
		
	}
		//전체다
	public JoinVO(String em_No, String em_Id, String em_Passwd, String em_Name, String em_Phone, String em_Bank,
			String em_Account, String em_Rank, String em_Entry, String em_Leaveday, String em_Address,
			String em_Whether) {
		super();
		this.em_No = em_No;
		this.em_Id = em_Id;
		this.em_Passwd = em_Passwd;
		this.em_Name = em_Name;
		this.em_Phone = em_Phone;
		this.em_Bank = em_Bank;
		this.em_Account = em_Account;
		this.em_Rank = em_Rank;
		this.em_Entry = em_Entry;
		this.em_Leaveday = em_Leaveday;
		this.em_Address = em_Address;
		this.em_Whether = em_Whether;
	}
	
	//접근자 설정자
	public String getEm_No() {
		return em_No;
	}
	public void setEm_No(String em_No) {
		this.em_No = em_No;
	}
	public String getEm_Id() {
		return em_Id;
	}
	public void setEm_Id(String em_Id) {
		this.em_Id = em_Id;
	}
	public String getEm_Passwd() {
		return em_Passwd;
	}
	public void setEm_Passwd(String em_Passwd) {
		this.em_Passwd = em_Passwd;
	}
	public String getEm_Name() {
		return em_Name;
	}
	public void setEm_Name(String em_Name) {
		this.em_Name = em_Name;
	}
	public String getEm_Phone() {
		return em_Phone;
	}
	public void setEm_Phone(String em_Phone) {
		this.em_Phone = em_Phone;
	}
	public String getEm_Bank() {
		return em_Bank;
	}
	public void setEm_Bank(String em_Bank) {
		this.em_Bank = em_Bank;
	}
	public String getEm_Account() {
		return em_Account;
	}
	public void setEm_Account(String em_Account) {
		this.em_Account = em_Account;
	}
	public String getEm_Rank() {
		return em_Rank;
	}
	public void setEm_Rank(String em_Rank) {
		this.em_Rank = em_Rank;
	}
	public String getEm_Entry() {
		return em_Entry;
	}
	public void setEm_Entry(String em_Entry) {
		this.em_Entry = em_Entry;
	}
	public String getEm_Leaveday() {
		return em_Leaveday;
	}
	public void setEm_Leaveday(String em_Leaveday) {
		this.em_Leaveday = em_Leaveday;
	}
	public String getEm_Address() {
		return em_Address;
	}
	public void setEm_Address(String em_Address) {
		this.em_Address = em_Address;
	}
	public String getEm_Whether() {
		return em_Whether;
	}
	public void setEm_Whether(String em_Whether) {
		this.em_Whether = em_Whether;
	}
	
	
}