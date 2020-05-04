/**
 * 
 */
package com.cts.fms.feedbackquestions.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author barat
 *
 */
@Data
@Entity
@Table(name="feedbacktype")
public class FeedbackType {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="feedbackid")
	private long feedbackId;
	
	@Column(name="feedback")
	private String feedback;
}
