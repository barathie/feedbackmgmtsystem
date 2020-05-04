package com.cts.fms.eventmgmt.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cts.fms.eventmgmt.entity.EventInfo;
import com.cts.fms.eventmgmt.entity.EventSummary;
import com.cts.fms.eventmgmt.entity.JwtResponse;
import com.cts.fms.eventmgmt.service.EventInfoService;
import com.cts.fms.eventmgmt.service.EventSummaryService;
import com.cts.fms.eventmgmt.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController()
@RequestMapping(value = "/events/eventinfo")
public class EventInfoController {
	@Autowired
	private EventInfoService eventService;
	
	@GetMapping("")
	public Flux<EventInfo> all() {
		return this.eventService.getAllEventInfo();
	}

	@GetMapping(path = "/{eventId}")
	public Flux<EventInfo> getEventByEventId(@PathVariable String eventId) {
		return this.eventService.getByEventId(eventId);
	}

	@PostMapping
	public Mono<ResponseEntity<EventInfo>> save(@RequestBody EventInfo event) {
		return this.eventService.save(event).map(savedEvent -> new ResponseEntity<>(savedEvent, HttpStatus.CREATED));
	}

	@DeleteMapping(path = "/{id}/{eventId}")
	public Mono<EventInfo> delete(@PathVariable Long id, @PathVariable String eventId) {
		return this.eventService.delete(id, eventId);
	}

	@DeleteMapping(path = "/{eventId}")
	public Mono<Void> delete(@PathVariable String eventId) {
		return this.eventService.deleteByEventId(eventId);
	}

	@PutMapping
	public Mono<EventInfo> update(@RequestBody EventInfo event) {
		Mono<EventInfo> e = this.eventService.update(event);
		return e;
	}
}
