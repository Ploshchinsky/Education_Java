package main;

import main.response.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.Integer;

public class Storage {
    private static ConcurrentHashMap<Integer, Note> noteMap = new ConcurrentHashMap<>();

    public static int put(Note note) {
        Integer id = noteMap.size() + 1;
        note.setId(id);
        noteMap.put(id, note);
        return id;
    }

    public static Note get(Integer id) {
        return noteMap.get(id);
    }

    public static List<Note> list() {
        return new ArrayList<>(noteMap.values());
    }
}
