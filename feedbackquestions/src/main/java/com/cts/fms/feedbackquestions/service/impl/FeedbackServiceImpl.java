package com.cts.fms.feedbackquestions.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fms.feedbackquestions.entities.Questions;
import com.cts.fms.feedbackquestions.repositories.QuestionsRepository;
import com.cts.fms.feedbackquestions.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService{
	
	@Autowired
	private QuestionsRepository questionsRepository;
	
	@Override
	public Questions createQuestion(Questions questions) {
		return questionsRepository.saveAndFlush(questions);
	}
	
	@Override
	public Questions fetchQuestion(Questions questions) {
		
		return null;
		
	}
	
	
}
