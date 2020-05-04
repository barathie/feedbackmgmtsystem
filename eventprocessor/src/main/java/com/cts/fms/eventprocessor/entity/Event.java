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
@Entity
@Table(name="event")
public class Event {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="EVENTID")
	private String eventId;
	
	@Column(name="EVENTNAME")
	private String eventName;
	
	@Column(name="EVENTDESCRIPTION")
	private String eventDescription;
	
	@Column(name="EVENTDATE")
	private String eventDate;
	
	@Column(name="MONTH")
	private String month;
	
	@Column(name="BASELOCATION")
	private String baseLocation;
	
	@Column(name="BENEFICIARYNAME")
	private String beneficiaryName;	
	
	@Column(name="VENUEADDRESS")
	private String venueAddress;	
	
	@Column(name="COUNCILNAME")
	private String councilName;	
	
	@Column(name="PROJECT")
	private String project;	
	
	@Column(name="CATEGORY")
	private String category;
	
	@Column(name="LIVESIMPACTED")
	private Integer livesImpacted;
	
	@Column(name="ACTIVITYTYPE")
	private Integer activityType;
		
	@Column(name="STATUS")
	private String status;
}
