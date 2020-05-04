package com.cts.fms.eventmgmt.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.cts.fms.eventmgmt.entity.EventInfo;
import com.cts.fms.eventmgmt.entity.EventSummary;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface EventInfoRepository extends ReactiveCrudRepository<EventInfo, Long> {
	@Query("SELECT * FROM event_info WHERE id = :id and eventId = :eventId")
	public Mono<EventInfo> findByEventId(Long id, String eventId);
	
	@Query("SELECT * FROM event_info WHERE eventId = :eventId")
	public Flux<EventInfo> findByEventId(String eventId);
	
	@Query("DELETE FROM event_info WHERE eventId = :eventId")
	public Mono<Void> deleteAll(String eventId);
}