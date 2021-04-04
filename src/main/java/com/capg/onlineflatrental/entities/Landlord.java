package com.capg.onlineflatrental.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
//@Table(name = "Landlord")
public class Landlord {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LANLORD_ID")
	private int landlordId;
	@Column(name = "LANDLORD_NAME", length = 30)
	@NotBlank(message = "Landlord Name is mandatory")
	private String landlordName;
	@Column(name = "LANDLORD_AGE")
	@NotBlank(message = "Landlord Age is mandatory")
	private int  landlordAge;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "landlord")
	private List<Flat> flatList;
	
	public Landlord() {
		super();
	}

	public Landlord(int landlordId, String landlordName, int landlordAge, List<Flat> flatList) {
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

	public List<Flat> getFlatList() {
		return flatList;
	}

	public void setFlatList(List<Flat> flatList) {
		this.flatList = flatList;
	}

	@Override
	public String toString() {
		return "Landlord [landlordId=" + landlordId + ", landlordName=" + landlordName + ", landlordAge=" + landlordAge
				+ ", flatList=" + flatList + "]";
	}

	
	}
	
