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
import org.springframework.web.reactive.function.BodyInserters;

import com.cts.fms.eventmgmt.entity.EventInfo;
import com.cts.fms.eventmgmt.repository.EventInfoRepository;
import com.cts.fms.eventmgmt.service.EventInfoServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = EventInfoController.class)
@Import(EventInfoServiceImpl.class)
public class EventInfoControllerTest {

	@MockBean
	EventInfoRepository repository;

	@Autowired
	private WebTestClient webClient;
	
	@SuppressWarnings("deprecation")
	@Test
    void testCreateEventInfo() {
		EventInfo eventInfo = new EventInfo();
		eventInfo.setId(1L);
		eventInfo.setEventId("EVNT00046611");
		eventInfo.setEventName("Testing");
 
        Mockito.when(repository.save(eventInfo)).thenReturn(Mono.just(eventInfo));
 
        webClient.post()
            .uri("/events/eventinfo")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromObject(eventInfo))
            .exchange()
            .expectStatus().isCreated();
 
        Mockito.verify(repository, times(1)).save(eventInfo);
    }
	
	@SuppressWarnings("deprecation")
	@Test
    void testUpdateEventInfo() {
		EventInfo eventInfo = new EventInfo();
		eventInfo.setId(1L);
		eventInfo.setEventId("EVNT00046611");
		eventInfo.setEventName("Testing");
 
        Mockito.when(repository.save(eventInfo)).thenReturn(Mono.just(eventInfo));
 
        webClient.post()
            .uri("/events/eventinfo")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromObject(eventInfo))
            .exchange()
            .expectStatus().isCreated();
 
        Mockito.verify(repository, times(1)).save(eventInfo);
    }
	
	@Test
    void testGetEventInfoByEventId() 
    {
		EventInfo eventInfo = new EventInfo();
		eventInfo.setId(1L);
		eventInfo.setEventId("EVNT00046611");
		eventInfo.setEventName("Testing");
		
		List<EventInfo> list = new ArrayList<EventInfo>();
        list.add(eventInfo);
         
        Flux<EventInfo> eventInfoFlux = Flux.fromIterable(list);
         
        Mockito
            .when(repository.findByEventId("EVNT00046611"))
            .thenReturn(eventInfoFlux);
 
        webClient.get().uri("/events/eventinfo/{eventId}","EVNT00046611")
            .header(HttpHeaders.ACCEPT, "application/json")
            .exchange()
            .expectStatus().isOk()
            .expectBodyList(EventInfo.class);
         
        Mockito.verify(repository, times(1)).findByEventId("EVNT00046611");
    }
	
	@Test
    void testDeleteEventInfo() 
    {
		EventInfo eventInfo = new EventInfo();
		eventInfo.setId(1L);
		eventInfo.setEventId("EVNT00046611");
		eventInfo.setEventName("Testing");
		
        Mono<Void> voidReturn  = Mono.empty();
        Mockito
            .when(repository.delete(eventInfo))
            .thenReturn(voidReturn);
 
        webClient.delete().uri("/events/eventinfo/{eventId}", "EVNT00046611")
            .exchange()
            .expectStatus().isOk();
    }
}
