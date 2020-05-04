package com.cts.fms.eventmgmt.service;


import com.cts.fms.eventmgmt.entity.EventInfo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EventInfoService {
	
	public Flux<EventInfo> getAllEventInfo();
	
	public Flux<EventInfo> getByEventId(String eventId);
	
	public Mono<EventInfo> save(EventInfo event);
	
	public Mono<EventInfo> update(EventInfo event);	
	
	public Mono<EventInfo> delete(Long id, String eventId);
	
	public Mono<Void> deleteByEventId(String eventId);
	
}
