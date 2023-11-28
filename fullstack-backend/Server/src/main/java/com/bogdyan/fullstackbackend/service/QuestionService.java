package com.bogdyan.fullstackbackend.service;

import com.bogdyan.fullstackbackend.model.Answer;
import com.bogdyan.fullstackbackend.model.Discipline;
import com.bogdyan.fullstackbackend.model.Question;
import com.bogdyan.fullstackbackend.repository.AnswerRepository;
import com.bogdyan.fullstackbackend.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository,
                           AnswerRepository answerRepository){
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    public Question findById(int id){
        return questionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Question not found"));
    }

    public void save(Question q){
        questionRepository.save(q);
    }

    public List<Question> getAll(){
        return questionRepository.findAllByOrderByQuestionIdAsc();
    }

    public void changeQuestionContent(int qid, String text){
        Question question = findById(qid);
        question.setQuestionText(text);
        save(question);
    }

    public void  addAnswerToQuestion(String content, int score, int qid){
        answerRepository.save(new Answer(score, content, findById(qid)));
    }
}
