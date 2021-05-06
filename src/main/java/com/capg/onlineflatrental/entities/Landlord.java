package com.capg.onlineflatrental.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Landlord")
public class Landlord {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LANLORD_ID")
	private int landlordId;
	@Column(name = "LANDLORD_NAME", length = 30)
	@NotBlank(message = "Landlord Name is mandatory")
	private String landlordName;
	@Column(name = "LANDLORD_AGE")
	private int  landlordAge;
	@OneToOne(cascade = CascadeType.PERSIST)
	private Flat flatList;
	
	public Landlord() {
		super();
	}

	public Landlord(int landlordId, String landlordName, int landlordAge, Flat flatList) {
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
	
