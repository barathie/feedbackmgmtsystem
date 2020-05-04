package com.cts.fms.eventmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.fms.eventmgmt.entity.EventSummary;
import com.cts.fms.eventmgmt.service.EventSummaryService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController()
@RequestMapping(value = "/events/eventsummary")
public class EventSummaryController {
	@Autowired
	private EventSummaryService eventService;

	@GetMapping(path="/user/{userId}")
	public Flux<EventSummary> all(@PathVariable String userId) {
		return this.eventService.getAllEventSummary(userId);
	}
	
	@GetMapping(path="/{eventId}")
	public Mono<ResponseEntity<EventSummary>> getEventByEventId(@PathVariable String eventId) {
		return this.eventService.getByEventId(eventId)
				.map(event->ResponseEntity.ok(event))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Mono<ResponseEntity<EventSummary>> save(@RequestBody EventSummary event) {
		return this.eventService.save(event)
				.map(savedEvent -> new ResponseEntity<>(savedEvent, HttpStatus.CREATED));
	}

	@DeleteMapping(path="/{eventId}")
	public Mono<EventSummary> delete(@PathVariable String eventId) {
		return this.eventService.delete(eventId);
	}
	
	@PutMapping
	public Mono<EventSummary> update(@RequestBody EventSummary event) {
		Mono<EventSummary> e = this.eventService.update(event);
		return e;
	}
}
