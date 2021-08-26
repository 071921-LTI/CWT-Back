package com.revature.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;

@Entity
@Data
@Table(name="trips")
public class Trip {
	
	@Id @Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int t_id;
	
	@Column(name="submitted_datetime", updatable=false, columnDefinition="timestamp default CURRENT_TIMESTAMP")
	private LocalDateTime TimeSubmited;
	
	@Column(nullable=false)
	private String TripName;
	
	@Column(nullable=false)
	private String CurrLocation;
	
	@Column(nullable=false)
	private String Destination;
	
	@Column()
	private int TimeElapsed;
	
	@Column(nullable=false)
	private int userId;

}