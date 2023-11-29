package com.bogdyan.fullstackbackend.controller;

import com.bogdyan.fullstackbackend.model.Discipline;
import com.bogdyan.fullstackbackend.model.QuestionBank;
import com.bogdyan.fullstackbackend.repository.DisciplineRepository;
import com.bogdyan.fullstackbackend.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DisciplineController {
    final private DisciplineService disciplineService;

    @Autowired
    public DisciplineController(DisciplineService discServ){
        this.disciplineService = discServ;
    }

    @GetMapping("/disciplines")
    public String ListDisciplines(Model model){
        Iterable<Discipline> disciplines = disciplineService.getAll();
        model.addAttribute("disciplines", disciplines);
        return "disciplines";
    }

    @PostMapping("/disciplines/add")
    public String addDisciplines(String text){
        disciplineService.createDiscipline(text);
        return "redirect:/disciplines";
    }

    @GetMapping("/disciplines/{did}")
    public String listDisciplineInfo(@PathVariable("did") int did, Model model){
        model.addAttribute(disciplineService.findById(did));
        return "disciplineInfo";
    }

    @PostMapping("/discipline/{did}/changeName")
    public ResponseEntity<String> changeName(@PathVariable("did") int did, String newName, Model model){
        disciplineService.changeDisciplineName(did, newName);
        model.addAttribute(disciplineService.findById(did));
        return ResponseEntity.ok().body("{\"message\": \"Success\"}");
    }

    @PostMapping("/discipline/{did}/delete-questionbank/{qbid}")
    public ResponseEntity<String> deleteQuestionBank(@PathVariable("did") int did, @PathVariable("qbid") int qbid, Model model){
        disciplineService.deleteQuestionBankFromDiscipline(qbid, did);
        model.addAttribute(disciplineService.findById(did));
        return ResponseEntity.ok().body("{\"message\": \"Success\"}");
    }
    @PostMapping("/discipline/{did}/add-questionbank")
    public ResponseEntity<QuestionBank> deleteQuestionBank(@PathVariable("did") int did, String qbName, Model model){
        QuestionBank newQb = disciplineService.createAndAddNewQBtoDisciplineWithReturn(did, qbName);
        model.addAttribute(disciplineService.findById(did));
        return ResponseEntity.ok().body(newQb);
    }
}
