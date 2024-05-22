package it.epicode.teoria.controller;

import it.epicode.teoria.DTO.StudenteDTO;
import it.epicode.teoria.exception.StudenteNonTrovatoException;
import it.epicode.teoria.model.Studente;
import it.epicode.teoria.service.StudenteService;
import it.epicode.teoria.service.StudenteServiceList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


//logica applicativa
@RestController // così non abbiamo problemi nei valori che vogliamo ritornare
public class StudenteController {

//    @GetMapping("/api")  // verrà chiamato se l'operazione è di tipo Get
//    public String benvenuto() {
//        return "Benvenuto";
//    }
//
//    @GetMapping("/api/nome_cognome")  // con i parametri
//    public String benvenuto2(@RequestParam String nome, String cognome) {
//        return "Benvenuto " + nome + " " + cognome;
//    }
//
//    @GetMapping("/api/{nome}/{cognome}") // tra parentesi graffe si mette la variabile nel path
//    public String benvenuto3(@PathVariable String nome,@PathVariable String cognome) {
//        return "Benvenuto " + nome + " " + cognome;
//    }
//
//    @GetMapping("/api/body")  // potevo scrivere qualsiasi altra cosa anziche body
//    public String benvenuto4(@RequestBody String nomeCognome) {
//        return "Benvenuto " + nomeCognome;
//    }

    //**************** faccio con il service ****************************

    @Autowired
    private StudenteService studenteService;

    @PostMapping("/api/studenti") //metodo per assegnarli risorse ù
    public String saveStudente(@RequestBody StudenteDTO studenteDTO) {
        return studenteService.saveStudente(studenteDTO);
    }

    @GetMapping("/api/studenti")
    public Page<Studente> getAllStudenti(@RequestParam(defaultValue = "0")int page,
                                         @RequestParam (defaultValue = "15")int size,
                                         @RequestParam(defaultValue = "matricola") String sortBy ) {

        return studenteService.getStudenti(page, size, sortBy);
    }

    @GetMapping("/api/studenti/{matricola}")
    public Studente getStudenteByMatricola(@PathVariable int matricola) throws StudenteNonTrovatoException {
        Optional<Studente> studenteOptional = studenteService.getStudenteByMatricola(matricola);
        if (studenteOptional.isPresent()){
            return studenteOptional.get();
        }else{
            throw new StudenteNonTrovatoException("Studente con matricola " + matricola + " non trovato");
        }
    }

    @PutMapping("/api/studenti/{matricola}")
    public Studente updateStudente(@PathVariable int matricola, @RequestBody StudenteDTO studenteDTO) throws StudenteNonTrovatoException {
        return studenteService.updateStudente(matricola, studenteDTO);
    }

    @DeleteMapping("/api/studenti/{matricola}")
    public String deleteStudente(@PathVariable int matricola) throws StudenteNonTrovatoException {
        return studenteService.deleteStudente(matricola);
    }
}
