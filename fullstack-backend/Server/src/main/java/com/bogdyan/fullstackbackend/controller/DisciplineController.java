package com.bogdyan.fullstackbackend.controller;

import com.bogdyan.fullstackbackend.model.Discipline;
import com.bogdyan.fullstackbackend.repository.DisciplineRepository;
import com.bogdyan.fullstackbackend.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/disciplines/{name}")
    public String listDisciplineInfo(@PathVariable("name") String nameOfDiscipline, Model model){
        model.addAttribute(disciplineService.findByName(nameOfDiscipline));
        return "disciplineInfo";
    }

    @PostMapping("/discipline/{did}/changeName")
    public String changeName(@PathVariable("did") int did, String newName, Model model){
        disciplineService.changeDisciplineName(did, newName);
        model.addAttribute(disciplineService.findById(did));
        return "disciplineInfo";
    }

    @PostMapping("/discipline/{did}/delete-questionbank/{qbid}")
    public String deleteAnswer(@PathVariable("did") int did, @PathVariable("qbid") int qbid, Model model){
        disciplineService.deleteQuestionBankFromDiscipline(qbid, did);
        model.addAttribute(disciplineService.findById(did));
        return "disciplineInfo";
    }
    @PostMapping("/discipline/{did}/add-questionbank")
    public String deleteAnswer(@PathVariable("did") int did, String qbName, Model model){
        disciplineService.createAndAddNewQBtoDiscipline(did, qbName);
        model.addAttribute(disciplineService.findById(did));
        return "disciplineInfo";
    }
}
