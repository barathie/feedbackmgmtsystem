/**
 * 
 */
package com.cts.fms.feedbackquestions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.fms.feedbackquestions.entities.Questions;

/**
 * @author barat
 *
 */
@Repository
public interface QuestionsRepository extends JpaRepository<Questions, Long> {

}
