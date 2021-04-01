package com.capg.onlineflatrental.model;

import org.springframework.stereotype.Component;

import com.capg.onlineflatrental.entities.FlatAddress;

@Component
public class FlatDTO {

	private int flatId;
	private float flatCost;
	private FlatAddress flatAddress;
	private String flatAvailabilty;
	
	public FlatDTO() {
		super();
	}

	public FlatDTO(int flatId, float flatCost, FlatAddress flatAddress, String flatAvailabilty) {
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
