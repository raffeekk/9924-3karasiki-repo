package so.team.web.services;

import so.team.web.controllers.FormData;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CsvService {
    public void writeToCsv(FormData formData) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("submission.csv", true))) {
            writer.println(formData.getSubject() + "," + formData.getPlace() + "," + formData.getTime() + "," + formData.getQuery());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
