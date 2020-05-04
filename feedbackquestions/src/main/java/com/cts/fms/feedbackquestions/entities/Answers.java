/**
 * 
 */
package com.cts.fms.feedbackquestions.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author barat
 *
 */
@Data
@Entity
@NoArgsConstructor
@Table(name="answers")
@EqualsAndHashCode
public class Answers {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="answerid" )
	private Long answerId;
	
   //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "questionid", referencedColumnName = "questionid")
	@Column(name="questionid")
	private Long questionId;
	
	//@Column(name="answer")
	private String answer;
	
	//@Column(name="status")
	private String status;

	public Long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	
}
