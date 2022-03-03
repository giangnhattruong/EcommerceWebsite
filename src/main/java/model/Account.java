package model;

public class Account {
	private String username;
	private String password;
	private String name;
	private String address;
	private String phone;
	private int role;
	private int check;

	public Account() {
	}

	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public Account(String username, String password, String name, String address, String phone, int role, int check) {
		this(username, password);
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.role = role;
		this.check = check;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}

}
