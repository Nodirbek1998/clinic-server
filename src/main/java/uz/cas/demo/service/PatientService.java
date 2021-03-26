package uz.cas.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.cas.demo.entity.Patient;
import uz.cas.demo.entity.Queue;
import uz.cas.demo.entity.Users;
import uz.cas.demo.payload.ReqPatient;
import uz.cas.demo.repository.PatientRepository;
import uz.cas.demo.repository.QueueRepository;
import uz.cas.demo.repository.UsersRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private QueueRepository queueRepository;
    @Autowired
    private UsersRepository usersRepository;

    public ResponseEntity<?> addPatient(ReqPatient reqPatient) {
        Patient patient = new Patient();
        patient.setFirstName(reqPatient.getFirstName());
        patient.setLastName(reqPatient.getLastName());
        patient.setPhone(reqPatient.getPhone());
        patientRepository.save(patient);

        Users byId = usersRepository.findById(reqPatient.getDoctorId()).get();
        List<Queue> allByUser = queueRepository.findAllByUser(byId);
        int count = allByUser.size();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String timeNow = dtf.format(now);

        if (count != 0) {
            //dd/MM/yyyy
            String time = allByUser.get(count - 1).getCreatedAt().toString();
            if (!time.split("-")[2].split(" ")[0].equals(timeNow.split("/")[0])) {
                queueRepository.deleteAll();
                count = 0;
            }
        }

        Queue queue = new Queue();
        queue.setAccepted(false);
        queue.setName(byId.getCategory().name() + (count + 1));
        queue.setPatient(patient);
        queue.setUser(byId);
        queueRepository.save(queue);
        Map<String, Object> map = new HashMap<>();
        map.put("firstName", reqPatient.getFirstName());
        map.put("lastName", reqPatient.getLastName());
        map.put("phone", reqPatient.getPhone());
        map.put("navbat", byId.getCategory().name() + (count + 1));
        map.put("price", byId.getPrice());
        return ResponseEntity.ok(map);
    }
}
