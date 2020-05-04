package com.cts.fms.feedbackquestions.service;

import com.cts.fms.feedbackquestions.entities.Questions;

public interface FeedbackService {
	
	public Questions createQuestion(Questions questions);
	
	public Questions fetchQuestion(Questions questions);
	
	
}
