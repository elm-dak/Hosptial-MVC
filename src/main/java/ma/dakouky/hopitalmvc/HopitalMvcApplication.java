package ma.dakouky.hopitalmvc;

import ma.dakouky.hopitalmvc.entities.Patient;
import ma.dakouky.hopitalmvc.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class HopitalMvcApplication implements CommandLineRunner {
    @Autowired

    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(HopitalMvcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

/*

        //1method  :Constructor par default et utilsation les setters

        Patient  patient = new Patient();
        patient.setId(null);
        patient.setNom("Mustapha");
        patient.setDateNaissance(new Date());
        patient.setScore(23);

        //2method  :Constructor avec parameter

        Patient patient2 = new Patient(null,"youness",new Date(),false,25);

        //3method  :Utilsation builder

        Patient patient3 = Patient.builder()
                .nom("zakaria")
                .dateNaissance(new Date())
                .score(55)
                .malade(true)
                .build();
*/

        patientRepository.save(new Patient(null,"mustapha",new Date(),false,225));
        patientRepository.save(new Patient(null,"youness",new Date(),false,253));
        patientRepository.save(new Patient(null,"zakaria",new Date(),true,256));





    }
}
