package com.cts.fms.eventmgmt.service;


import com.cts.fms.eventmgmt.entity.EventSummary;
import com.cts.fms.eventmgmt.entity.JwtResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EventSummaryService {
	
	public Flux<EventSummary> getAllEventSummary(String userId);
	
	public Mono<EventSummary> getByEventId(String eventId);
	
	public Mono<EventSummary> save(EventSummary event);
	
	public Mono<EventSummary> update(EventSummary event);	
	
	public Mono<EventSummary> delete(String eventId);
	
}
