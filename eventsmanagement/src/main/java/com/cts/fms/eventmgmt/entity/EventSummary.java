/**
 * 
 */
package com.cts.fms.eventmgmt.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

/**
 * @author barat
 *
 */
@Data
@Table("event_summary")
public class EventSummary {
	@Id
	private Long id;
	
	@Column("EVENTID")
	private String eventId;	
	
	@Column("MONTH")
	private String month;
	
	@Column("BASELOCATION")
	private String baseLocation;
	
	@Column("BENEFICIARYNAME")
	private String beneficiaryName;
	
	@Column("VENUEADDRESS")
	private String venueAddress;
	
	@Column("COUNCILNAME")
	private String councilName;
	
	@Column("PROJECT")
	private String project;
	
	@Column("CATEGORY")
	private String category;
	
	@Column("EVENTNAME")
	private String eventName;
	
	@Column("EVENTDESCRIPTION")
	private String eventDescription;
	
	@Column("EVENTDATE")
	private String eventDate;
	
	@Column("TOTALNOOFVOLUNTEERS")
	private Integer totalNoOfVolunteers;
	
	@Column("TOTALVOLUNTEERHOURS")
	private Double totalVolunteerHours;	
	
	@Column("TOTALTRAVELHOURS")
	private Double totalTravelHours;
	
	@Column("OVERALLVOLUNTEERINGHOURS")
	private Double overallVolunteeringHours;
	
	@Column("LIVESIMPACTED")
	private Integer livesImpacted;	
	
	@Column("ACTIVITYTYPE")
	private Integer activityType;
	
	@Column("STATUS")
	private String status;
	
	@Column("POCID")
	private String pocIds;	
	
	@Column("POCNAME")
	private String pocNames;
	
	@Column("POCCONTACTNUMBER")
	private String pocContactNumbers;
}
