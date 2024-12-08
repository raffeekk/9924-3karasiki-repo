package so.team.web.services;

import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class FormSubmissionService {

    private static final String CSV_FILE_PATH = "submissions.csv"; // Путь к файлу CSV

    // Метод для записи данных в CSV
    public void saveFormSubmission(String subject, String place, String time, String query) {
        // Данные для записи
        String[] data = {subject, place, time, query};

        try {
            // Создание или открытие CSV файла для записи
            FileWriter fileWriter = new FileWriter(CSV_FILE_PATH, true);
            CSVWriter csvWriter = new CSVWriter(fileWriter);

            // Записываем данные в CSV файл
            csvWriter.writeNext(data);

            // Закрытие writer
            csvWriter.close();
            System.out.println("Данные успешно записаны в файл CSV");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Ошибка при записи данных в CSV файл");
        }
    }
}
