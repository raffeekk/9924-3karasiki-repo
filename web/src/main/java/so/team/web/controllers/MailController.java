package so.team.web.controllers;

import so.team.web.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sendForm")
@CrossOrigin
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping
    public ResponseEntity<String> sendForm(@RequestBody FormData formData) {
        try {
            // Передаем данные из FormData в сервис для отправки почты
            mailService.sendMail(formData.getSubject(), formData.getPlace(), formData.getTime(), formData.getQuery());
            return ResponseEntity.ok("Сообщение успешно отправлено");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ошибка при отправке сообщения");
        }
    }
}