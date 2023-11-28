package com.bogdyan.fullstackbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="questions")
public class Question{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionId;

    private String questionText;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @OrderBy("answerId ASC")
    private Set<Answer> answers = new HashSet<>();

    @JsonBackReference
    @ManyToMany(mappedBy = "questions")
    @OrderBy("questionGroupId ASC")
    private Set<QuestionGroup> questionGroups = new HashSet<>();

    @JsonBackReference
    @ManyToMany(mappedBy = "questions")
    @OrderBy("testId ASC")
    private Set<Test> tests = new HashSet<>();

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="question_bank_id")
    @OrderBy("questionBankId ASC")
    private QuestionBank questionBank;

    public Question() {
    }
    public Question(String questionText, QuestionBank questionBank) {
        this.questionText = questionText;
        this.questionBank = questionBank;
    }

}
