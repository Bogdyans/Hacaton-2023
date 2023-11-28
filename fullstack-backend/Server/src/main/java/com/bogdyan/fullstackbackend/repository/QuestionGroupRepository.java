package com.bogdyan.fullstackbackend.repository;

import com.bogdyan.fullstackbackend.model.Answer;
import com.bogdyan.fullstackbackend.model.QuestionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionGroupRepository extends JpaRepository<QuestionGroup, Integer> {
    List<QuestionGroup> findAllByOrderByQuestionGroupIdAsc();
}
