package com.bogdyan.fullstackbackend.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name="question_groups")
public class QuestionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionGroupId;

    @Column(length = 45)
    private String questionGroupName;

    @ManyToOne
    @JoinColumn(name="question_bank_id")
    private QuestionBank questionBank;

    @OneToMany(mappedBy = "questionGroup", cascade = CascadeType.ALL)
    @OrderBy("testId ASC")
    private Set<Test> tests = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="question_group_questions",
            joinColumns = @JoinColumn(name = "question_group_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    @OrderBy("questionId ASC")
    private Set<Question> questions = new HashSet<>();

    public QuestionGroup() {
    }

    public QuestionGroup(String questionGroupName, QuestionBank questionBank) {
        this.questionGroupName = questionGroupName;
        this.questionBank = questionBank;
    }
}
