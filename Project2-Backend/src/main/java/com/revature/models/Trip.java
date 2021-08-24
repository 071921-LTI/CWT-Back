package com.revature.models;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class Trip {
	private int t_id;
	private Timestamp TimeSubmited;
	private String CurrLocation;
	private String Destination;
	private int TimeElapsed;
	
	public Trip() {
		super();
	}
	
	
	public Trip(int t_id, Timestamp timeSubmited, String currLocation, String destination, int timeElapsed) {
		super();
		this.t_id = t_id;
		TimeSubmited = timeSubmited;
		CurrLocation = currLocation;
		Destination = destination;
		TimeElapsed = timeElapsed;
	}

	public Trip(Timestamp timeSubmited, String currLocation, String destination, int timeElapsed) {
		super();
		TimeSubmited = timeSubmited;
		CurrLocation = currLocation;
		Destination = destination;
		TimeElapsed = timeElapsed;
	}


	public int getT_id() {
		return t_id;
	}


	public void setT_id(int t_id) {
		this.t_id = t_id;
	}


	public Timestamp getTimeSubmited() {
		return TimeSubmited;
	}


	public void setTimeSubmited(Timestamp timeSubmited) {
		TimeSubmited = timeSubmited;
	}


	public String getCurrLocation() {
		return CurrLocation;
	}


	public void setCurrLocation(String currLocation) {
		CurrLocation = currLocation;
	}


	public String getDestination() {
		return Destination;
	}


	public void setDestination(String destination) {
		Destination = destination;
	}


	public int getTimeElapsed() {
		return TimeElapsed;
	}


	public void setTimeElapsed(int timeElapsed) {
		TimeElapsed = timeElapsed;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CurrLocation == null) ? 0 : CurrLocation.hashCode());
		result = prime * result + ((Destination == null) ? 0 : Destination.hashCode());
		result = prime * result + TimeElapsed;
		result = prime * result + ((TimeSubmited == null) ? 0 : TimeSubmited.hashCode());
		result = prime * result + t_id;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trip other = (Trip) obj;
		if (CurrLocation == null) {
			if (other.CurrLocation != null)
				return false;
		} else if (!CurrLocation.equals(other.CurrLocation))
			return false;
		if (Destination == null) {
			if (other.Destination != null)
				return false;
		} else if (!Destination.equals(other.Destination))
			return false;
		if (TimeElapsed != other.TimeElapsed)
			return false;
		if (TimeSubmited == null) {
			if (other.TimeSubmited != null)
				return false;
		} else if (!TimeSubmited.equals(other.TimeSubmited))
			return false;
		if (t_id != other.t_id)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Trip [t_id=" + t_id + ", TimeSubmited=" + TimeSubmited + ", CurrLocation=" + CurrLocation
				+ ", Destination=" + Destination + ", TimeElapsed=" + TimeElapsed + "]";
	}

	
}
