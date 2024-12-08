package so.team.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InstructionController {

    @GetMapping("/instruction")
    public String mainPage(Model model) {
        return "instruction"; // Thymeleaf шаблон "index.html"
    }
}