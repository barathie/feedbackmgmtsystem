package com.cts.fms.eventmgmt.controller;

import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.BodyInserters;

import com.cts.fms.eventmgmt.entity.EventSummary;
import com.cts.fms.eventmgmt.repository.EventSummaryRepository;
import com.cts.fms.eventmgmt.service.EventSummaryService;
import com.cts.fms.eventmgmt.service.EventSummaryServiceImpl;
import com.cts.fms.eventmgmt.service.UserService;
import com.cts.fms.eventmgmt.service.UserServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = EventSummaryController.class)
@Import({EventSummaryServiceImpl.class, UserServiceImpl.class})
public class EventSummaryControllerTest {

	@MockBean
	EventSummaryRepository repository;

	@Autowired
	private WebTestClient webClient;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@SuppressWarnings("deprecation")
	@Test
    void testCreateEventSummary() {
		EventSummary eventSummary = new EventSummary();
		eventSummary.setId(1L);
		eventSummary.setEventId("EVNT00046611");
		eventSummary.setEventName("Testing");
 
        Mockito.when(repository.save(eventSummary)).thenReturn(Mono.just(eventSummary));
 
        webClient.post()
            .uri("/events/eventsummary")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromObject(eventSummary))
            .exchange()
            .expectStatus().isCreated();
 
        Mockito.verify(repository, times(1)).save(eventSummary);
    }
	
	@SuppressWarnings("deprecation")
	@Test
    void testUpdateEventSummary() {
		EventSummary eventSummary = new EventSummary();
		eventSummary.setId(1L);
		eventSummary.setEventId("EVNT00046611");
		eventSummary.setEventName("Testing");
 
        Mockito.when(repository.save(eventSummary)).thenReturn(Mono.just(eventSummary));
 
        webClient.post()
            .uri("/events/eventsummary")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromObject(eventSummary))
            .exchange()
            .expectStatus().isCreated();
 
        Mockito.verify(repository, times(1)).save(eventSummary);
    }
	
	@Test
    void testGetEventSummaryByEventId() 
    {
		EventSummary eventSummary = new EventSummary();
		eventSummary.setId(1L);
		eventSummary.setEventId("EVNT00046611");
		eventSummary.setEventName("Testing");
         
        Mono<EventSummary> eventSummaryFlux = Mono.just(eventSummary);
         
        Mockito
            .when(repository.findByEventId("EVNT00046611"))
            .thenReturn(eventSummaryFlux);
 
        webClient.get().uri("/events/eventsummary/{eventId}","EVNT00046611")
            .header(HttpHeaders.ACCEPT, "application/json")
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(EventSummary.class);
         
        Mockito.verify(repository, times(1)).findByEventId("EVNT00046611");
    }
	
	@Test
    void testDeleteEventSummary() 
    {
		EventSummary eventSummary = new EventSummary();
		eventSummary.setId(1L);
		eventSummary.setEventId("EVNT00046611");
		eventSummary.setEventName("Testing");
		
        Mono<Void> voidReturn  = Mono.empty();
        Mockito
            .when(repository.delete(eventSummary))
            .thenReturn(voidReturn);
 
        webClient.delete().uri("/events/eventsummary/{eventId}", "EVNT00046611")
            .exchange()
            .expectStatus().isOk();
    }

}
