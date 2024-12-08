package so.team.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping("/index")
    public String mainPage(Model model) {
        return "index"; // Thymeleaf шаблон "index.html"
    }
}