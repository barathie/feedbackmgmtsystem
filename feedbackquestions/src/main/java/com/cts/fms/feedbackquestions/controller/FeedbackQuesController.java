package com.cts.fms.feedbackquestions.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.cts.fms.feedbackquestions.entities.Answers;
import com.cts.fms.feedbackquestions.entities.Questions;
import com.cts.fms.feedbackquestions.exception.ResourceNotFoundException;
import com.cts.fms.feedbackquestions.repositories.AnswersRepository;
import com.cts.fms.feedbackquestions.repositories.QuestionsRepository;
import com.cts.fms.feedbackquestions.service.FeedbackService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/feedback")
public class FeedbackQuesController {

	@Autowired
	private QuestionsRepository questionsRepository;
	@Autowired
	private AnswersRepository answersRepository;

	@Autowired
	private FeedbackService feedbackService;

	@GetMapping(value = "/question/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Questions> getQuestion(@PathVariable Long id) {
		Optional<Questions> question = questionsRepository.findById(id);
		return new ResponseEntity<Questions>(question.get(), HttpStatus.OK);
	}

	@GetMapping(value = "/question/test")
	public ResponseEntity<String> getTest() {
		return new ResponseEntity("Test", HttpStatus.OK);
	}

	@PostMapping("/question")
	public Questions createQuestion(@RequestBody Questions question) {
		question = feedbackService.createQuestion(question);
		return question;
	}

	@PutMapping("/questions/{questionId}")
	public Questions updateQuestion(@PathVariable Long questionId, @RequestBody Questions question) {
		if (!questionsRepository.existsById(questionId)) {
			throw new ResourceNotFoundException("question id" + questionId + " not found");
		}
		return question;
	}

	@PutMapping("/questions")
	public Optional<Questions> updateQuestionAndAnswer(@RequestBody Questions question) {
		Long qId = question.getQuestionId();	
		
		return questionsRepository.findById(qId).map(modifyQuestion -> {
				modifyQuestion.setFeedbackType(question.getFeedbackType());
				modifyQuestion.setQuestionCategory(question.getQuestionCategory());
				modifyQuestion.setStatus(question.getStatus());
				modifyQuestion.getAnswers().clear();
				modifyQuestion.getAnswers().addAll(question.getAnswers());	
				return questionsRepository.save(modifyQuestion);
			});
	}

	@DeleteMapping("/questions/{questionId}")
	public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
		return questionsRepository.findById(questionId).map(question -> {
			questionsRepository.delete(question);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("question id" + questionId + "not found"));
	}

}
