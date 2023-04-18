package com.example.activite_3.web;

import com.example.activite_3.entities.Patient;
import com.example.activite_3.respositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor

public class PatientController {

    // acceder a la base de donnee
    private PatientRepository patientRepository;
    @GetMapping(path = "/index")
    // une metode pour retourner une vue
    public String patients(Model model,
                           @RequestParam(name = "page",defaultValue = "0") int page,
                           @RequestParam(name = "size",defaultValue = "5") int size,
                           @RequestParam(name = "keyword",defaultValue = "") String keyword){
        //recuperer la liste des patients
        Page<Patient> pagePatients=patientRepository.findByNomContains(keyword,PageRequest.of(page, size));
        //stocker dans le module
        model.addAttribute("listPatients",pagePatients.getContent());
        // au lieu de faire http://localhost:8083/index?page=2&size=1
        model.addAttribute("pages",new int [pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "patients";
    }
    @GetMapping("/delete")
    public String delete(Long id, String keyword ,int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword"+keyword;
    }
    @GetMapping("/")
    public String home(){
        return "redirect:/index";
    }
    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> listPatients(){
        return patientRepository.findAll();
    }
}
