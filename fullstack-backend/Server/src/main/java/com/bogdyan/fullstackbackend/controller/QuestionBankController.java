package com.bogdyan.fullstackbackend.controller;

import com.bogdyan.fullstackbackend.service.QuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class QuestionBankController {
    private final QuestionBankService questionBankService;

    @Autowired
    public QuestionBankController(QuestionBankService questionBankService){
        this.questionBankService = questionBankService;
    }

    @GetMapping("/questionBank/{qbid}")
    public String listQuestionBankInfo(@PathVariable("qbid") int qbid, Model model){
        model.addAttribute("questionbank", questionBankService.findById(qbid));
        return "questionBankInfo";
    }

    @PostMapping("/questionBank/{qbid}/changeName")
    public String changeName(@PathVariable("qbid") int qbid, String newName, Model model){
        questionBankService.changeQuestionBankName(qbid, newName);
        model.addAttribute("questionbank", questionBankService.findById(qbid));
        return "questionBankInfo";
    }

    @PostMapping("/questionBank/{qbid}/delete-question-group/{qgid}")
    public String deleteQuestionGroup(@PathVariable("qbid") int qbid, @PathVariable("qgid") int qgid, Model model){
        questionBankService.deleteQuestionGroupFromQuestionBank(qgid);
        model.addAttribute("questionbank", questionBankService.findById(qbid));
        return "questionBankInfo";
    }

    @PostMapping("/questionBank/{qbid}/add-question-group")
    public String addQuestionGroup(@PathVariable("qbid") int qbid, String name, Model model){
        questionBankService.addQuestionGroupToQuestionBank(name, qbid);
        model.addAttribute("questionbank", questionBankService.findById(qbid));
        return "questionBankInfo";
    }

    @PostMapping("/questionBank/{qbid}/delete-question/{qid}")
    public String deleteQuestion(@PathVariable("qbid") int qbid, @PathVariable("qid") int qid, Model model){
        questionBankService.deleteQuestionFromQuestionBank(qid);
        model.addAttribute("questionbank", questionBankService.findById(qbid));
        return "questionBankInfo";
    }

    @PostMapping("/questionBank/{qbid}/add-question")
    public String addQuestion(@PathVariable("qbid") int qbid, String content, Model model){
        questionBankService.addQuestionToQuestionBank(content, qbid);
        model.addAttribute("questionbank", questionBankService.findById(qbid));
        return "questionBankInfo";
    }
}
