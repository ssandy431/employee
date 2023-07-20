package com.app.emp.dto;

public class AddressDTO {
	
	private Long id;
    private String street;
    private String city;
    private String state;
    private String pinCode;
    private String country;

    public AddressDTO() {super();}
    
    public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}

	public AddressDTO(Long id,String street, String city, String state, String pinCode, String country) {
    	this.id=id;
    	this.street = street;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

