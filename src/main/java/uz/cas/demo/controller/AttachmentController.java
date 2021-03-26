package uz.cas.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.cas.demo.service.AttachmentService;

import java.io.IOException;

@Controller
@RequestMapping("/api/img")
@CrossOrigin("*")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    //    @Secured("ROLE_ADMIN")
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(MultipartHttpServletRequest request) throws IOException {
        return ResponseEntity.ok(attachmentService.uploadFile(request));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable Long id) {
        return attachmentService.download(id);
    }

}
