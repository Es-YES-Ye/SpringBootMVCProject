package kr.co.sesac.vo;

public class LoginVO {

	private String id;
	private String password;
	private String type; // "S" : 愿�由ъ옄, "U" : �씪諛섏궗�슜�옄
	public LoginVO() {

	}
	
	public LoginVO(String id, String password) {
		super();
		this.id = id;
		this.password = password;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "LoginVO [id=" + id + ", password=" + password + ", type=" + type + "]";
	}
	
	
}
