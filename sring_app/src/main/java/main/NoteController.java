package main;

import main.entity.Note;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class NoteController {
    @PostMapping("/notes/")
    public int create(Note note) {
        return Storage.create(note);
    }

    @GetMapping("/notes/")
    public ArrayList<Note> list() {
        return Storage.list();
    }
}
