package com.bogdyan.fullstackbackend.service;

import com.bogdyan.fullstackbackend.model.Discipline;
import com.bogdyan.fullstackbackend.model.Question;
import com.bogdyan.fullstackbackend.model.QuestionBank;
import com.bogdyan.fullstackbackend.model.QuestionGroup;
import com.bogdyan.fullstackbackend.repository.QuestionBankRepository;
import com.bogdyan.fullstackbackend.repository.QuestionGroupRepository;
import com.bogdyan.fullstackbackend.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class QuestionBankService {
    private final QuestionBankRepository questionBankRepository;
    private final QuestionGroupRepository questionGroupRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionBankService(QuestionBankRepository questionBankRepository,
                               QuestionGroupRepository questionGroupRepository,
                               QuestionRepository questionRepository){
        this.questionBankRepository = questionBankRepository;
        this.questionGroupRepository = questionGroupRepository;
        this.questionRepository = questionRepository;
    }

    public List<QuestionBank> getAll(){
        return questionBankRepository.findAll();
    }

    public QuestionBank findById(int id){
        return questionBankRepository.findById(id).orElseThrow(() -> new NoSuchElementException("QB not found"));
    }
    public QuestionGroup findQuestionGroupById(int id){
        return questionGroupRepository.findById(id).orElseThrow(() -> new NoSuchElementException("QG not found"));
    }
    public Question findQuestionById(int id){
        return questionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Question not found"));
    }

    private void save(QuestionBank questionBank){
        questionBankRepository.save(questionBank);
    }

    public void changeQuestionBankName(int qbid, String newName){
        QuestionBank questionBank = findById(qbid);
        questionBank.setQuestionBankName(newName);
        save(questionBank);
    }

    public void deleteQuestionGroupFromQuestionBank(int qgid){
        questionGroupRepository.delete(findQuestionGroupById(qgid));
    }

    public void addQuestionGroupToQuestionBank(String name, int qbid){
        QuestionGroup questionGroup = new QuestionGroup(name, findById(qbid));
        questionGroupRepository.save(questionGroup);
    }

    public void deleteQuestionFromQuestionBank(int qid){
        questionRepository.delete(findQuestionById(qid));
    }

    public void addQuestionToQuestionBank(String content, int qbid){
        Question question = new Question(content, findById(qbid));
        questionRepository.save(question);
    }
}
