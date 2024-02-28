package main;

import main.model.Note;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    @PostMapping("/")
    public ResponseEntity add(Note note) {
        return new ResponseEntity(Storage.put(note), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<Note> list() {
        return Storage.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Note note = Storage.get(id);
        return note == null ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) :
                new ResponseEntity(note, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        Note note = Storage.get(id);
        return note == null ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) :
                new ResponseEntity(Storage.delete(id), HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public Note update(@PathVariable int id, Note note) {
        note.setId(id);
        Storage.put(note);
        return Storage.get(id);
    }
}
