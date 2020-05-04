package com.cts.fms.eventprocessor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.fms.eventprocessor.entity.Event;
import com.cts.fms.eventprocessor.entity.EventPoc;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
