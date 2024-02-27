package main;

import main.response.Note;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.lang.Integer;

@RestController
@RequestMapping("/notes")
public class NoteController {
    @PostMapping("/")
    public int add(Note note) {
        return Storage.put(note);
    }

    @GetMapping("/")
    public List<Note> list() {
        return Storage.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Note note = Storage.get(id);
        System.out.println(note.getId() + " " + note.getText());
        if (note == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(note, HttpStatus.OK);
    }
}
