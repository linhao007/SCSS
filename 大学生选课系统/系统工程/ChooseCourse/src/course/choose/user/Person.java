package course.choose.user;

public class Person {	
	private String No;
	private String name;
	private String sex;
	private String password;
	public  String getNo() {
		return No;
	}
	public void setNo(String no) {
		No = no;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
