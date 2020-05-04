package com.cts.fms.feedbackquestions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.fms.feedbackquestions.entities.FeedbackType;

@Repository
public interface FeedbackTypeRepository extends JpaRepository<FeedbackType, Long>{

}
