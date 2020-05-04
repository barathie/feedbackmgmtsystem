/**
 * 
 */
package com.cts.fms.feedbackquestions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.fms.feedbackquestions.entities.QuestionCategory;

/**
 * @author barat
 *
 */
@Repository
public interface QuestionCategoryRepository extends JpaRepository<QuestionCategory, Long>{

}
