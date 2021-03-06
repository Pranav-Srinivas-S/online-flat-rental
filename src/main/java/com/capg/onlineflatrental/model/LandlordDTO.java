package com.capg.onlineflatrental.model;

import org.springframework.stereotype.Component;

import com.capg.onlineflatrental.entities.Flat;

@Component
public class LandlordDTO {

	private int landlordId;
	private String landlordName;
	private int  landlordAge;
	private Flat flatList;
	
	public LandlordDTO() {
		super();
	}

	public LandlordDTO(int landlordId, String landlordName, int landlordAge, Flat flatList) {
		super();
		this.landlordId = landlordId;
		this.landlordName = landlordName;
		this.landlordAge = landlordAge;
		this.flatList = flatList;
	}

	public int getLandlordId() {
		return landlordId;
	}

	public void setLandlordId(int landlordId) {
		this.landlordId = landlordId;
	}

	public String getLandlordName() {
		return landlordName;
	}

	public void setLandlordName(String landlordName) {
		this.landlordName = landlordName;
	}

	public int getLandlordAge() {
		return landlordAge;
	}

	public void setLandlordAge(int landlordAge) {
		this.landlordAge = landlordAge;
	}

	public Flat getFlatList() {
		return flatList;
	}

	public void setFlatList(Flat flatList) {
		this.flatList = flatList;
	}

	@Override
	public String toString() {
		return "Landlord [landlordId=" + landlordId + ", landlordName=" + landlordName + ", landlordAge=" + landlordAge
				+ ", flatList=" + flatList + "]";
	}
	
}
