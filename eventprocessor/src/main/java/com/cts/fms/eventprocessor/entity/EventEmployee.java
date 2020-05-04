/**
 * 
 */
package com.cts.fms.eventprocessor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author barat
 *
 */
@Data
@Entity
@Table(name = "event_employee")
public class EventEmployee {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="EVENTID")
	private String eventId;
	  
	@Column(name="EMPID")
	private String empId;
	
	@Column(name="VOLUNTEERHOURS")
	private Double volunteerHours;
	
	@Column(name="TRAVELHOURS")
	private Double travelHours;
}
