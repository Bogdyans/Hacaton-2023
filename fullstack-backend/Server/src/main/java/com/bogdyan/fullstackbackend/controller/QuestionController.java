package com.bogdyan.fullstackbackend.controller;

import com.bogdyan.fullstackbackend.model.Answer;
import com.bogdyan.fullstackbackend.model.Question;
import com.bogdyan.fullstackbackend.service.AnswerService;
import com.bogdyan.fullstackbackend.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class QuestionController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    @Autowired
    public QuestionController(QuestionService questionService, AnswerService answerService){
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @GetMapping("/question/{qid}")
    public String listQuestionInfo(@PathVariable("qid") Integer qid, Model model){
        model.addAttribute("question", questionService.findById(qid));
        return "QuestionInfo";
    }

    @PostMapping("/question/{qid}/change-answer/{aid}")
    public String changeQuestionAnswer(@PathVariable("qid") int qid, @PathVariable("aid") int aid, String text, String score, Model model){
        answerService.changeAnswer(aid, text, score);
        model.addAttribute("question", questionService.findById(qid));
        return "QuestionInfo";
    }

    @PostMapping("/question/{qid}/add-answer")
    public String addAnswer(@PathVariable("qid") int qid, String content, int score, Model model){
        questionService.addAnswerToQuestion(content, score, qid);
        model.addAttribute("question",  questionService.findById(qid));
        return "QuestionInfo";
    }

    @PostMapping("/question/{qid}/delete-answer/{aid}")
    public String deleteAnswer(@PathVariable("qid") int qid, @PathVariable("aid") int aid, Model model) {
        answerService.deleteAnswer(aid);
        model.addAttribute("question", questionService.findById(qid));
        return "QuestionInfo";
    }

    @PostMapping("/question/{qid}/changeText")
    public String changeText(@PathVariable("qid") int qid, String content, Model model){
        questionService.changeQuestionContent(qid, content);
        model.addAttribute("question", questionService.findById(qid));
        return "QuestionInfo";
    }


}
