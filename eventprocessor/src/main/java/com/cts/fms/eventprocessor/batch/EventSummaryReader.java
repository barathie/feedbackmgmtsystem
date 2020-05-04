package com.cts.fms.eventprocessor.batch;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;

import com.cts.fms.eventprocessor.entity.EventSummary;

public class EventSummaryReader extends FlatFileItemReader<EventSummary>{
	
	public EventSummaryReader(Resource resource) {
		
		super();		
		setResource(resource);
		
		DefaultLineMapper<EventSummary> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[] { "eventId", "month", "baseLocation", "beneficiaryName", "venueAddress",
				"councilName", "project", "category", "eventName", "eventDescription", "eventDate",
				"totalNoOfVolunteers", "totalVolunteerHours", "totalTravelHours", "overallVolunteeringHours",
				"livesImpacted", "activityType", "status", "pocIds", "pocNames", "pocContactNumbers" });
		lineTokenizer.setDelimiter("|");
		lineTokenizer.setIncludedFields(
				new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21 });
		BeanWrapperFieldSetMapper<EventSummary> fieldSetMapper = new BeanWrapperFieldSetMapper<EventSummary>();
		fieldSetMapper.setTargetType(EventSummary.class);
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		setLineMapper(lineMapper);
		setLinesToSkip(1);
	}
}
