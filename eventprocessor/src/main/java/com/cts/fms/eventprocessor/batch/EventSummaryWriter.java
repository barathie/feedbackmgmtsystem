package com.cts.fms.eventprocessor.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cts.fms.eventprocessor.entity.Event;
import com.cts.fms.eventprocessor.entity.EventPoc;
import com.cts.fms.eventprocessor.entity.EventSummary;
import com.cts.fms.eventprocessor.repository.EventPocRepository;
import com.cts.fms.eventprocessor.repository.EventRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EventSummaryWriter implements ItemWriter<EventSummary>{
	
	@Autowired
	private EventPocRepository eventPocRepository;
	
	@Autowired
	private EventRepository eventRepository;

	@Override
	public void write(List<? extends EventSummary> items) throws Exception {
		EventPoc eventPoc = null;
		Event event = null;
		for (EventSummary item : items) {
			System.out.println("........ITEMS.........." + item.getPocNames());
			eventPoc = new EventPoc();
			eventPoc.setEventId(item.getEventId());
			eventPoc.setPocId(item.getPocIds());
			eventPocRepository.save(eventPoc);
			
			event = new Event();
			event.setEventId(item.getEventId());
			event.setEventName(item.getEventName());
			event.setEventDescription(item.getEventDescription());
			event.setEventDate(item.getEventDate());
			event.setMonth(item.getMonth());
			event.setBaseLocation(item.getBaseLocation());
			event.setBeneficiaryName(item.getBeneficiaryName());
			event.setVenueAddress(item.getVenueAddress());
			event.setCouncilName(item.getCouncilName());
			event.setProject(item.getProject());
			event.setCategory(item.getCategory());
			event.setLivesImpacted(item.getLivesImpacted());
			event.setActivityType(item.getActivityType());
			event.setStatus(item.getStatus());
			
			eventRepository.save(event);					
		}
	}
	
}
