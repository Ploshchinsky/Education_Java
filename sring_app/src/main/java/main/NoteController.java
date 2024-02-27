package main;

import main.entity.Note;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {
    @PostMapping("/notes/")
    public int create(Note note) {
        return Storage.createOrUpdate(note);
    }

    @PutMapping("/notes/{id}")
    public int update(@PathVariable int id) {
        Note tempNote = Storage.get(id);
        return Storage.createOrUpdate(tempNote);
    }

    @GetMapping("/notes/{id}")
    public Note get(@PathVariable int id) {
        return Storage.exist(id) ? Storage.get(id) : null;
    }

    @GetMapping("/notes/")
    public List<Note> list() {
        return Storage.list();
    }

    @DeleteMapping("notes/{id}")
    public int delete(@PathVariable int id) {
        return Storage.delete(id);
    }
}
