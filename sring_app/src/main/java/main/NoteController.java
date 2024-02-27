package main;

import main.entity.Note;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @DeleteMapping("/notes/{id}")
    public void delete(@PathVariable int id) {
        Storage.delete(id);
    }

}
