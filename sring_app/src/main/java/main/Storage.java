package main;

import main.response.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.lang.Integer;

public class Storage {
    private static ConcurrentHashMap<Integer, Note> noteMap = new ConcurrentHashMap<>();

    public static int put(Note note) {
        if (noteMap.containsValue(note)) {
            update(note);
        }
        Integer id = noteMap.size() + 1;
        note.setId(id);
        noteMap.put(id, note);
        return id;
    }

    public static Note get(int id) {
        return noteMap.get(id);
    }

    public static List<Note> list() {
        return new ArrayList<>(noteMap.values());
    }

    public static int delete(int id) {
        if (noteMap.containsKey(id)) {
            noteMap.remove(id);
            return id;
        }
        return -1;
    }

    public static int update(Note note) {
        int id = note.getId();
        noteMap.replace(id, note);
        return id;
    }
}
