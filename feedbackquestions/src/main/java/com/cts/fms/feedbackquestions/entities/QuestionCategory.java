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
@Table(name="questioncategory")
public class QuestionCategory {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="quescategoryid")
	private long quesCategoryId;
	
	@Column(name="quescategory")
	private String quesCategory;
}
