package so.team.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import so.team.web.services.CsvService;

@RestController
@RequestMapping("/api")
public class FormController {

    private final CsvService csvWriter = new CsvService();

    @PostMapping("/forms")
    public ResponseEntity<?> handleForm(@RequestBody FormData formData) {
        // Сохранить данные в CSV
        csvWriter.writeToCsv(formData);

        // Возвращаем успешный ответ
        return ResponseEntity.ok().build();
    }
}