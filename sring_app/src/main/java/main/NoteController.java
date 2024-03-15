package main;

import main.model.Note;
import main.model.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {
    @Autowired
    private NoteRepository noteRepository;

    @PostMapping("/")
    public ResponseEntity add(Note note) {
        return new ResponseEntity(noteRepository.save(note).getId(), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<Note> list() {
        return (List<Note>) noteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Note note = noteRepository.findById(id).get();
        return note == null ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) :
                new ResponseEntity(note, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        Note note = noteRepository.findById(id).get();
        if (note == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        noteRepository.delete(note);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PutMapping("/{id}")
    public Note update(@PathVariable int id, Note note) {
        note.setId(id);
        noteRepository.save(note);
        return noteRepository.findById(note.getId()).get();
    }
}
