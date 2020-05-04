package com.cts.fms.eventmgmt.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fms.eventmgmt.entity.EventSummary;
import com.cts.fms.eventmgmt.entity.JwtResponse;
import com.cts.fms.eventmgmt.repository.EventSummaryRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static com.cts.fms.eventmgmt.AppConstants.*;

@Slf4j
@Service
public class EventSummaryServiceImpl implements EventSummaryService {

	@Autowired
	EventSummaryRepository eventSummaryRepository;
	
	@Autowired
	private UserService userService;

	public Flux<EventSummary> getAllEventSummary(String userId) {
		Flux<EventSummary> eventSummary = null;	
		
		if(!isEventPoc(userId)) {
			eventSummary = this.eventSummaryRepository.findAll();
		} else {
			eventSummary = this.eventSummaryRepository.findByUserId(userId);
		}	
		return eventSummary.switchIfEmpty(Flux.empty());
	}

	public Mono<EventSummary> getByEventId(String eventId) {
		return eventSummaryRepository.findByEventId(eventId);
	}

	public Mono<EventSummary> save(EventSummary event) {
		return eventSummaryRepository.save(event);
	}

	public Mono<EventSummary> update(EventSummary event) {
		return this.eventSummaryRepository.findByEventId(event.getEventId()).flatMap(p -> {
			p.setActivityType(event.getActivityType());
			p.setBaseLocation(event.getBaseLocation());
			p.setBeneficiaryName(event.getBeneficiaryName());
			p.setCategory(event.getCategory());
			p.setCouncilName(event.getCouncilName());
			p.setEventDate(event.getEventDate());
			p.setEventDescription(event.getEventDescription());
			p.setEventId(event.getEventId());
			p.setLivesImpacted(event.getLivesImpacted());
			p.setMonth(event.getMonth());
			p.setOverallVolunteeringHours(event.getOverallVolunteeringHours());
			p.setPocContactNumbers(event.getPocContactNumbers());
			p.setPocIds(event.getPocIds());
			p.setPocNames(event.getPocNames());
			p.setProject(event.getProject());
			p.setStatus(event.getStatus());
			p.setTotalNoOfVolunteers(event.getTotalNoOfVolunteers());
			p.setTotalTravelHours(event.getTotalTravelHours());
			p.setTotalVolunteerHours(event.getTotalVolunteerHours());
			p.setVenueAddress(event.getVenueAddress());
			return eventSummaryRepository.save(p);
		});
	}

	public Mono<EventSummary> delete(String eventId) {
		final Mono<EventSummary> dbEventInfo = getByEventId(eventId);
		if (Objects.isNull(dbEventInfo)) {
			return Mono.empty();
		}
		return dbEventInfo.switchIfEmpty(Mono.empty()).filter(Objects::nonNull).flatMap(
				eventToBeDeleted -> eventSummaryRepository.delete(eventToBeDeleted).then(Mono.just(eventToBeDeleted)));
	}	
	
	private boolean isEventPoc(String userId) {
		boolean result = false;
		JwtResponse jwtResponse = userService.getUserDetails(userId);
		
		List<String> roles = jwtResponse.getRoles();
		if(null!=roles && roles.contains(ROLE_POC)) {
			result = true;		
		}
		return result;
	}
}
