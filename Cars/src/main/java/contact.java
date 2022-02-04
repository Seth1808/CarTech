
public class contact {
	protected String name;
	 protected String position;
	 protected String phonenumber;
	 protected String email;
	public contact(String name, String position, String phonenumber, String email) {
		super();
		this.name = name;
		this.position = position;
		this.phonenumber = phonenumber;
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
