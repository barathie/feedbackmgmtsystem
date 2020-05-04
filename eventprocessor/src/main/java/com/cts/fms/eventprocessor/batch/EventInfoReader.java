package com.cts.fms.eventprocessor.batch;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;

import com.cts.fms.eventprocessor.entity.EventInfo;

public class EventInfoReader extends FlatFileItemReader<EventInfo> {
	
	public EventInfoReader(Resource resource) {
		
		super();
		
		setResource(resource);
		
		DefaultLineMapper<EventInfo> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[] { "eventId", "baseLocation", "beneficiaryName", "councilName", "eventName",
				"eventDescription", "eventDate", "empId", "empName", "volunteerHours", "travelHours",
				"livesImpacted", "businessunit", "status", "iiepCategory" });
		lineTokenizer.setDelimiter("|");
		lineTokenizer.setIncludedFields(
				new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 });
		BeanWrapperFieldSetMapper<EventInfo> fieldSetMapper = new BeanWrapperFieldSetMapper<EventInfo>();
		fieldSetMapper.setTargetType(EventInfo.class);
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		setLineMapper(lineMapper);
		setLinesToSkip(1);
	}
}
