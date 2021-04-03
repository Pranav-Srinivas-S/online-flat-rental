package com.capg.onlineflatrental.entities;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Flat")
public class Flat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FLAT_ID")
	private int flatId;
	@Column(name = "FLAT_COST")
	@NotBlank(message = "Cost is mandatory")
	@Min(value = 1, message = "Cost cannot be 0 or negative")
	private float flatCost;
	@Embedded
	private FlatAddress flatAddress;
	@Column(name = "FLAT_AVAILABILITY")
	@NotBlank(message = "Flat Availability is mandatory")
	private String flatAvailabilty;
	
	public Flat() {
		super();
	}

	public Flat(int flatId, float flatCost, FlatAddress flatAddress, String flatAvailabilty) {
		super();
		this.flatId = flatId;
		this.flatCost = flatCost;
		this.flatAddress = flatAddress;
		this.flatAvailabilty = flatAvailabilty;
	}

	public int getFlatId() {
		return flatId;
	}

	public void setFlatId(int flatId) {
		this.flatId = flatId;
	}

	public float getFlatCost() {
		return flatCost;
	}

	public void setFlatCost(float flatCost) {
		this.flatCost = flatCost;
	}

	public FlatAddress getFlatAddress() {
		return flatAddress;
	}

	public void setFlatAddress(FlatAddress flatAddress) {
		this.flatAddress = flatAddress;
	}

	public String getFlatAvailabilty() {
		return flatAvailabilty;
	}

	public void setFlatAvailabilty(String flatAvailabilty) {
		this.flatAvailabilty = flatAvailabilty;
	}

	@Override
	public String toString() {
		return "Flat [flatId=" + flatId + ", flatCost=" + flatCost + ", flatAddress=" + flatAddress
				+ ", flatAvailabilty=" + flatAvailabilty + "]";
	}
	
}
