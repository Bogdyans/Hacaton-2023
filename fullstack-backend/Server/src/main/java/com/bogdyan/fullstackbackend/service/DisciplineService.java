package com.bogdyan.fullstackbackend.service;

import com.bogdyan.fullstackbackend.model.Discipline;
import com.bogdyan.fullstackbackend.model.QuestionBank;
import com.bogdyan.fullstackbackend.repository.DisciplineRepository;
import com.bogdyan.fullstackbackend.repository.QuestionBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DisciplineService {
    private final DisciplineRepository disciplineRepository;
    private final QuestionBankRepository questionBankRepository;

    @Autowired
    public DisciplineService(DisciplineRepository disciplineRepository, QuestionBankRepository questionBankRepository){
        this.disciplineRepository = disciplineRepository;
        this.questionBankRepository = questionBankRepository;
    }
    public List<Discipline> getAll(){
        return disciplineRepository.findAllByOrderByDisciplineIdAsc();
    }


    public Discipline createDiscipline(String name){
        Discipline discipline = new Discipline(name);
        save(discipline);
        return discipline;
    }
    public Discipline findById(int disciplineId) {
        return disciplineRepository.findById(disciplineId).orElseThrow(() -> new NoSuchElementException("Role not found"));
    }
    public Discipline findByName(String name) {
        return disciplineRepository.findByDisciplineName(name);
    }

    private void save(Discipline discipline){
        disciplineRepository.save(discipline);
    }

    public void changeDisciplineName(int did, String newName){
        Discipline discipline = findById(did);
        discipline.setDisciplineName(newName);
        save(discipline);
    }



    public void deleteQuestionBankFromDiscipline(int qbid, int did){
        questionBankRepository.delete(questionBankRepository.findById(qbid).orElseThrow(() -> new NoSuchElementException("QB not found")));

    }

    public void createAndAddNewQBtoDiscipline(int did, String qbName){
        Discipline discipline = findById(did);
        QuestionBank newQuestionBank = new QuestionBank(qbName, discipline);
        questionBankRepository.save(newQuestionBank);
    }
    public QuestionBank createAndAddNewQBtoDisciplineWithReturn(int did, String qbName){
        Discipline discipline = findById(did);
        QuestionBank newQuestionBank = new QuestionBank(qbName, discipline);
        questionBankRepository.save(newQuestionBank);
        return newQuestionBank;
    }
}
