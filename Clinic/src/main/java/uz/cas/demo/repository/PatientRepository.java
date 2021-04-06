package uz.cas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.cas.demo.entity.Patient;

import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
