package com.capg.onlineflatrental.model;

import org.springframework.stereotype.Component;

import com.capg.onlineflatrental.entities.FlatAddress;

@Component
public class FlatDTO {

	private int flatId;
	private float flatCost;
	private FlatAddress flatAddress;
	private String flatAvailability;
	
	public FlatDTO() {
		super();
	}

	public FlatDTO(int flatId, float flatCost, FlatAddress flatAddress, String flatAvailability) {
		super();
		this.flatId = flatId;
		this.flatCost = flatCost;
		this.flatAddress = flatAddress;
		this.flatAvailability = flatAvailability;
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

	public String getFlatAvailability() {
		return flatAvailability;
	}

	public void setFlatAvailability(String flatAvailability) {
		this.flatAvailability = flatAvailability;
	}

	@Override
	public String toString() {
		return "Flat [flatId=" + flatId + ", flatCost=" + flatCost + ", flatAddress=" + flatAddress
				+ ", flatAvailability=" + flatAvailability + "]";
	}
	
}
