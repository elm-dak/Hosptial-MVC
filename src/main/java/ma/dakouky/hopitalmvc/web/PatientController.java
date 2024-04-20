package ma.dakouky.hopitalmvc.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.dakouky.hopitalmvc.entities.Patient;
import ma.dakouky.hopitalmvc.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;

    @GetMapping("/index")
    public  String index(Model model, @RequestParam(name = "page",defaultValue = "0") int page ,
                         @RequestParam(name = "size",defaultValue = "5") int size,
                         @RequestParam(name = "keyword",defaultValue = "") String kw){
        Page<Patient> patientPage = patientRepository.findByNomContains(kw,PageRequest.of(page,size));
        model.addAttribute("listPatients",patientPage.getContent());
        //Stocker le nomber de pages sur une tableaux
        model.addAttribute("pages",new int[patientPage.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",kw);
        return "patients";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id") Long id,
                         @RequestParam(name = "keyword",defaultValue = "") String keyword,
                         @RequestParam(name = "page",defaultValue = "0") int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/index";
    }

    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> patientList(){
        return patientRepository.findAll();
    }

    @GetMapping("/formPatients")
    public String formPatients(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatients";

    }

    @PostMapping("/save")
    public String save(Model model, @Valid Patient patient,
                       BindingResult bindingResult,
                       @RequestParam(defaultValue = "") String keyword,
                       @RequestParam(defaultValue = "0") int page

        ){
        if(bindingResult.hasErrors()){
            return "formPatients";
        }
        patientRepository.save(patient);
        return "redirect:/index?page="+page+"&keyword="+keyword;

    }

    @GetMapping("/editPatients")
    public String editPatients(Model model, Long id, String keyword ,int page){
        Patient patient = patientRepository.findById(id).orElse(null);

            if (patient == null) throw new RuntimeException("Patient introuvable");

        model.addAttribute("patient",patient);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);

        return "editPatients";

    }

}
