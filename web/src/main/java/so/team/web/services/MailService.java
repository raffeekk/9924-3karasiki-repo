package so.team.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private MailSender mailSender;

    public void sendMail(String subject, String place, String time, String query) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("likriklikrik@gmail.com");
        message.setTo("l1kr1kl1kr1k@yandex.ru");
        message.setSubject("Новое обращение: " + subject);
        message.setText("Тип происшествия: " + subject + "\n" +
                "Место: " + place + "\n" +
                "Время: " + time + "\n" +
                "Дополнительные сведения: " + query);

        mailSender.send(message);
    }
}