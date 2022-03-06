package model;

import java.time.LocalDate;

public class Order {
	private String email;
	private String address;
	private int status;
	private LocalDate date;
	private String discountCode;
	
	public Order() {
	}
	
	public Order(String email, String address) {
		this(email, address, 1, null);
	}
	
	public Order(String email, String address, int status, String discountCode) {
		this.email = email;
		this.address = address;
		this.status = status;
		this.date = LocalDate.now();
		this.discountCode = discountCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}
	
	public String validate(Order order) {
		if (order.address == null || order.address.equals(""))
			return "Address cannot be empty.";
		
		return "Success";
	}

}
