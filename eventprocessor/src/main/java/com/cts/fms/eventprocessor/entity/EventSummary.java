/**
 * 
 */
package com.cts.fms.eventprocessor.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

import lombok.Data;

/**
 * @author barat
 *
 */
@Data
//@Entity
//@Table(name="event")
public class EventSummary {
	//@Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
	//private Long id;	
	private String eventId;	
	private String month;	
	private String baseLocation;	
	private String beneficiaryName;	
	private String venueAddress;
	private String councilName;
	private String project;
	private String category;
	private String eventName;	
	private String eventDescription;
	private String eventDate;	
	private Integer totalNoOfVolunteers;	
	private Double totalVolunteerHours;	
	private Double totalTravelHours;
	private Double overallVolunteeringHours;		
	private Integer livesImpacted;	
	private Integer activityType;	
	private String status;	
	private String pocIds;	
	private String pocNames;
	private String pocContactNumbers;
}
