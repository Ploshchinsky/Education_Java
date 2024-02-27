package main;

import main.entity.Note;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class NoteController {
    @PostMapping("/notes/")
    public int create(Note note) {
        return Storage.put(note);
    }

    @GetMapping("/notes/")
    public ArrayList<Note> list() {
        return Storage.list();
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<Note> get(@PathVariable Integer id) {
        Note note = Storage.get(id);
        if (note == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity<>(note, HttpStatus.OK);
    }
}
