package com.capg.onlineflatrental.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class LandlordDTO {

	private int landlordId;
	private String landlordName;
	private int  landlordAge;
	private List<FlatDTO> flatList;
	
	public LandlordDTO() {
		super();
	}

	public LandlordDTO(int landlordId, String landlordName, int landlordAge, List<FlatDTO> flatList) {
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

	public List<FlatDTO> getFlatList() {
		return flatList;
	}

	public void setFlatList(List<FlatDTO> flatList) {
		this.flatList = flatList;
	}

	@Override
	public String toString() {
		return "Landlord [landlordId=" + landlordId + ", landlordName=" + landlordName + ", landlordAge=" + landlordAge
				+ ", flatList=" + flatList + "]";
	}
	
}
