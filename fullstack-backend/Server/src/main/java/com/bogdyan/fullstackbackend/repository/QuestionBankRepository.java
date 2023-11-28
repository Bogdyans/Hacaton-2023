package com.bogdyan.fullstackbackend.repository;

import com.bogdyan.fullstackbackend.model.Answer;
import com.bogdyan.fullstackbackend.model.QuestionBank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionBankRepository extends JpaRepository<QuestionBank, Integer> {
    List<QuestionBank> findAllByOrderByQuestionBankIdAsc();
}
