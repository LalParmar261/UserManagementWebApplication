package model;

public class User {
	private int id;
	private String name;
	private String email;
	private String country;
	
	
	public User(int id, String name, String email, String country) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.country = country;
	}
	
	public User(String name, String email, String country) {
		this.name = name;
		this.email = email;
		this.country = country;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
