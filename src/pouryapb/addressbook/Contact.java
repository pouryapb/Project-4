package pouryapb.addressbook;

import java.io.Serializable;

public class Contact implements Serializable{

	/**
	 * not so complicated!
	 */
	private static final long serialVersionUID = -8303231299345628715L;
	private String name = new String();
	private long phoneNumber;
	private String birthDate;
	private String address;
	private String gender;
	private String group;
	private String Email;
	
	// Constructors //
	public Contact(String name, long phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	public Contact(String name, long phoneNumber, String birthDate) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
	}

	public Contact(String name, long phoneNumber, String birthDate, String adress) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
		this.address = adress;
	}

	public Contact(String name, long phoneNumber, String birthDate, String adress, String gender) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
		this.address = adress;
		this.gender = gender;
	}

	public Contact(String name, long phoneNumber, String birthDate, String adress, String gender,
			String group) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
		this.address = adress;
		this.gender = gender;
		this.group = group;
	}

	public Contact(String name, long phoneNumber, String birthDate, String adress, String gender,
			String group, String email) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
		this.address = adress;
		this.gender = gender;
		this.group = group;
		Email = email;
	}
	// Constructors //
	
	// setters and getters //
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getAdress() {
		return address;
	}

	public void setAdress(String adress) {
		this.address = adress;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
	// setters and getters //
}
