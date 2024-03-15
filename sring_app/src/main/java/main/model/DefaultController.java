package main.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DefaultController {
    @Autowired
    private NoteRepository noteRepository;
    @Value("${string.from.cfg}")
    private String message;

    @RequestMapping("/")
    public String index(Model model) {
        List<Note> noteList = (List<Note>) noteRepository.findAll();
        model.addAttribute("notes", noteList);
        model.addAttribute("message", message);
        return "index";
    }
}
