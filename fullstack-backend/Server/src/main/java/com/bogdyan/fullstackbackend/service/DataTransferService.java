package com.bogdyan.fullstackbackend.service;

import com.bogdyan.fullstackbackend.model.Question;
import com.bogdyan.fullstackbackend.model.QuestionBank;
import com.bogdyan.fullstackbackend.model.QuestionGroup;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.HashSet;
import java.util.Set;


//WIP
public class DataTransferService {
    @Getter
    @Setter
    public class QuestionBankDTO {
        private Integer questionBankId;
        private String questionBankName;
        private Set<QuestionDTO> questions;
        private Set<QuestionGroupDTO> questionGroups;

        public QuestionBankDTO(){}
    }

    @Getter
    @Setter
    public class QuestionGroupDTO {
        private Integer questionGroupId;
        private String questionGroupName;
        private Set<QuestionDTO> questions;

        public QuestionGroupDTO(){}
    }

    @Getter
    @Setter
    private class QuestionDTO {
        private Integer questionId;
        private String questionText;

        public QuestionDTO(){}
    }



    public void SerializeDataBase(QuestionBank questionBank){

        ObjectMapper objectMapper = new ObjectMapper();


        QuestionBankDTO questionBankDTO = new QuestionBankDTO();
        questionBankDTO.setQuestionBankId(questionBank.getQuestionBankId());
        questionBankDTO.setQuestionBankName(questionBank.getQuestionBankName());


        Set<QuestionDTO> questionDTOs = new HashSet<>();
        for (Question question : questionBank.getQuestions()) {
            QuestionDTO questionDTO = new QuestionDTO();
            questionDTO.setQuestionId(question.getQuestionId());
            questionDTO.setQuestionText(question.getQuestionText());

            questionDTOs.add(questionDTO);
        }
        questionBankDTO.setQuestions(questionDTOs);


        Set<QuestionGroupDTO> questionGroupDTOs = new HashSet<>();
        for (QuestionGroup questionGroup : questionBank.getQuestionGroups()) {
            QuestionGroupDTO questionGroupDTO = new QuestionGroupDTO();
            questionGroupDTO.setQuestionGroupId(questionGroup.getQuestionGroupId());
            questionGroupDTO.setQuestionGroupName(questionGroup.getQuestionGroupName());


            Set<QuestionDTO> groupQuestions = new HashSet<>();
            for (Question question : questionGroup.getQuestions()) {
                QuestionDTO questionDTO = new QuestionDTO();
                questionDTO.setQuestionId(question.getQuestionId());
                questionDTO.setQuestionText(question.getQuestionText());

                groupQuestions.add(questionDTO);
            }
            questionGroupDTO.setQuestions(groupQuestions);

            questionGroupDTOs.add(questionGroupDTO);
        }
        questionBankDTO.setQuestionGroups(questionGroupDTOs);

        String json = "";

        try {
            json = objectMapper.writeValueAsString(questionBankDTO);
            //System.out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String filePath = "D:\\PRog\\fullstack-backend\\src\\main\\java\\com\\bogdyan\\fullstackbackend\\model\\questionBank.txt";


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath)))) {
            writer.write(json); //success to export

        } catch (IOException e) {
            e.printStackTrace(); //failed
        }
    }


    public QuestionBankDTO deserializeQuestionBank(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();


        try {
            File file = new File(filePath);
            return objectMapper.readValue(file, QuestionBankDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
