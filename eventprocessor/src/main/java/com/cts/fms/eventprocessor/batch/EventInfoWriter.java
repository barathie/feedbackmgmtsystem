package com.cts.fms.eventprocessor.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.fms.eventprocessor.entity.Event;
import com.cts.fms.eventprocessor.entity.EventEmployee;
import com.cts.fms.eventprocessor.entity.EventInfo;
import com.cts.fms.eventprocessor.repository.EventEmpRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EventInfoWriter implements ItemWriter<EventInfo>{
	
	@Autowired
	private EventEmpRepository eventEmpRepository;

	@Override
	public void write(List<? extends EventInfo> items) throws Exception {
		EventEmployee eventEmployee = null;
		Event event = null;
		for (EventInfo item : items) {
			System.out.println("........ITEMS.........." + item.getEmpId());
			eventEmployee = new EventEmployee();
			eventEmployee.setEventId(item.getEventId());
			eventEmployee.setEmpId(item.getEmpId());
			//volunteerHours,	travelHours
			eventEmployee.setVolunteerHours(item.getVolunteerHours());
			eventEmployee.setTravelHours(item.getTravelHours());
			eventEmpRepository.save(eventEmployee);		
		}
	}
	
}
