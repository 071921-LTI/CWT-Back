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
	private LocalDateTime submitted_datetime;
	
	@Column(name ="curr_location", nullable=false )
	private String curr_location;
	
	@Column(name="destination",nullable=false)
	private String destination;
	
	@Column(name="time_elapsed")
	private int time_elapsed;
	
	@Column(name="user_id",nullable=false)
	private int user_id;

}