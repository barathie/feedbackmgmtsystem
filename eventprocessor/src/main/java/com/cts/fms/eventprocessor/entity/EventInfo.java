/**
 * 
 */
package com.cts.fms.eventprocessor.entity;

import lombok.Data;

/**
 * @author barat
 *
 */
@Data
public class EventInfo {
	private String eventId;	
	private String baseLocation;	
	private String beneficiaryName;	
	private String councilName;
	private String eventName;	
	private String eventDescription;
	private String eventDate;	
	private String empId;
	private String empName;
	private Double volunteerHours;	
	private Double travelHours;
	private Integer livesImpacted;
	private String businessunit;
	private String status;	
	private String iiepCategory;	
}
