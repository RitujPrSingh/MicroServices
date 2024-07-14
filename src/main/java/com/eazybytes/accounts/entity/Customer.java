package com.eazybytes.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Customer extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerID;
	
	private String name;
	
	private String email;
	
	@Column(name="mobile_number")
	private String mobileNumber;


	public Customer() {
		super();
	}
	

	public Customer(Long customerID, String name, String email, String mobileNumber) {
		super();
		this.customerID = customerID;
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
	}




	public Long getCustomerID() {
		return customerID;
	}
	

	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", name=" + name + ", email=" + email + ", mobileNumber="
				+ mobileNumber + "]";
	}

	
}
