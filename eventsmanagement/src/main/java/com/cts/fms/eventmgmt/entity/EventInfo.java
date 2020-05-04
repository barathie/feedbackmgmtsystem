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
@Table("event_info")
public class EventInfo {
	@Id
	private Long id;
	
	@Column("EVENTID")
	private String eventId;	
	
	@Column("BASELOCATION")
	private String baseLocation;
	
	@Column("BENEFICIARYNAME")
	private String beneficiaryName;	
	
	@Column("COUNCILNAME")
	private String councilName;
	
	@Column("EVENTNAME")
	private String eventName;
	
	@Column("EVENTDESCRIPTION")
	private String eventDescription;
	
	@Column("EVENTDATE")
	private String eventDate;
	
	@Column("EMPLOYEEID")
	private String empId;
	
	@Column("EMPLOYEENAME")
	private String empName;
	
	@Column("VOLUNTEERHOURS")
	private Double volunteerHours;	
	
	@Column("TRAVELHOURS")
	private Double travelHours;
	
	@Column("LIVESIMPACTED")
	private Integer livesImpacted;
	
	@Column("BUSINESSUNIT")
	private String businessunit;
	
	@Column("STATUS")
	private String status;	
	
	@Column("IIEPCATEGORY")
	private String iiepCategory;	
}
