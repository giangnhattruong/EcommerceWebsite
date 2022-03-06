package model;

import java.util.List;

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
		this(username, password, null, null, null);
	}
	
	public Account(String username, String password, String name, String address, String phone) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phone = phone;
		role = 1;
	}

	public Account(String username, String password, String name, String address, String phone, int role) {
		this(username, password, name, address, phone);
		this.role = role;
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
	
	public static String validate(String password) {
		String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,10}$";
		
		if (!password.matches(passwordRegex))
			return "Password must contain 6-10 characters, at least 1 special character, 1 uppercase character, 1 lowercase character and 1 digit.";
	
		return "Success";
	}

	
	public static String validate(Account account) {
		String emailRegex = "\\w+@\\w+\\.\\w+";
		String phoneRegex = "\\d{8,10}";
		int nameMaxLength = 30;
		int addressMaxLength = 200;
		
		if (!account.username.matches(emailRegex))
			return "Invalid email.";
		
		if (account.name.length() >= nameMaxLength)
			return "Name can't have more than " + nameMaxLength + " characters.";
		
		if (account.address.length() >= addressMaxLength)
			return "Address can't have more than " + addressMaxLength + " characters.";
		
		if (!account.phone.matches(phoneRegex))
			return "Phone must be a 8-10 digits number.";
		
		return validate(account.password);
	}

}
