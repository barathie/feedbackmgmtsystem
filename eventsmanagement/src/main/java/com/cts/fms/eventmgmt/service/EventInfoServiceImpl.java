package com.cts.fms.eventmgmt.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fms.eventmgmt.entity.EventInfo;
import com.cts.fms.eventmgmt.entity.EventSummary;
import com.cts.fms.eventmgmt.entity.JwtResponse;
import com.cts.fms.eventmgmt.repository.EventInfoRepository;
import com.cts.fms.eventmgmt.repository.EventSummaryRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class EventInfoServiceImpl implements EventInfoService {

	@Autowired
	EventInfoRepository eventInfoRepository;

	public Flux<EventInfo> getAllEventInfo() {		
		return eventInfoRepository.findAll().switchIfEmpty(Flux.empty());
	}

	public Flux<EventInfo> getByEventId(String eventId) {
		return eventInfoRepository.findByEventId(eventId);
	}
	
	public Mono<EventInfo> getByEventId(Long id,String eventId) {
		return eventInfoRepository.findByEventId(id,eventId);
	}

	public Mono<EventInfo> save(EventInfo event) {
		return eventInfoRepository.save(event);
	}

	public Mono<EventInfo> update(EventInfo event) {
		return this.eventInfoRepository.findByEventId(event.getId(),event.getEventId()).flatMap(p -> {
			p.setBaseLocation(event.getBaseLocation());
			p.setBeneficiaryName(event.getBeneficiaryName());
			p.setCouncilName(event.getCouncilName());
			p.setEmpId(event.getEmpId());
			p.setEmpName(event.getEmpName());
			p.setEventDate(event.getEventDate());
			p.setEventDescription(event.getEventDescription());
			p.setEventId(event.getEventId());
			p.setEventName(event.getEventName());
			p.setIiepCategory(event.getIiepCategory());
			p.setLivesImpacted(event.getLivesImpacted());
			p.setStatus(event.getStatus());
			p.setTravelHours(event.getTravelHours());
			p.setVolunteerHours(event.getVolunteerHours());
			return eventInfoRepository.save(p);
		});
	}

	public Mono<EventInfo> delete(Long id, String eventId) {
		final Mono<EventInfo> dbEventInfo = getByEventId(id, eventId);
		if (Objects.isNull(dbEventInfo)) {
			return Mono.empty();
		}
		return dbEventInfo.switchIfEmpty(Mono.empty()).filter(Objects::nonNull).flatMap(
				eventToBeDeleted -> eventInfoRepository.delete(eventToBeDeleted).then(Mono.just(eventToBeDeleted)));

	}
	
	public Mono<Void> deleteByEventId(String eventId) {
		return eventInfoRepository.deleteAll(eventId);
	}
}
