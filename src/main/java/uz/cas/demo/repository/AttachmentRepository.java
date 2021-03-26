package uz.cas.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.cas.demo.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
