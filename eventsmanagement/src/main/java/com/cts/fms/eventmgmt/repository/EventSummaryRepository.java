package com.cts.fms.eventmgmt.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.cts.fms.eventmgmt.entity.EventSummary;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface EventSummaryRepository extends ReactiveCrudRepository<EventSummary, Long> {
	@Query("SELECT * FROM event_summary WHERE eventId = :eventId")
	public Mono<EventSummary> findByEventId(String eventId);
	
	@Query("SELECT * FROM event_summary WHERE POCID = :userId")
	public Flux<EventSummary> findByUserId(String userId);

}
