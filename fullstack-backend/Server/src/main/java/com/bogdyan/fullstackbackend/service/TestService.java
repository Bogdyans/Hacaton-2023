package com.bogdyan.fullstackbackend.service;

import com.bogdyan.fullstackbackend.model.Answer;
import com.bogdyan.fullstackbackend.model.Question;
import com.bogdyan.fullstackbackend.model.Test;
import com.bogdyan.fullstackbackend.repository.QuestionRepository;
import com.bogdyan.fullstackbackend.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TestService {
    private final TestRepository testRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public TestService(TestRepository testRepository, QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
        this.testRepository = testRepository;
    }

    public Test findById(int id){
        return testRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Test not found"));
    }

    public void save(Test test){
        testRepository.save(test);
    }

    public void deleteQuestionFromTest(int qid, int tid){
        Test test = findById(tid);
        Question question = findQuestionById(qid);
        test.getQuestions().remove(question);
        save(test);
    }

    private Question findQuestionById(int qid){
        return questionRepository.findById(qid).
                orElseThrow(() -> new NoSuchElementException("Question not found"));
    }

    public void addQuestionToTest(int qid, int tid){
        Test test = findById(tid);
        test.getQuestions().add(findQuestionById(qid));
        save(test);
    }
}
