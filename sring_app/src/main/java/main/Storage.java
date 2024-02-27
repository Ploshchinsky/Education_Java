package main;

import main.entity.Note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Storage {
    private static ConcurrentHashMap<Integer, Note> noteMap = new ConcurrentHashMap<>();

    public static int createOrUpdate(Note note) {
        int id = note.getId();
        if (noteMap.containsKey(id)) {
            noteMap.replace(id, note);
            return id;
        }
        id = noteMap.size() + 1;
        noteMap.put(id, note);
        return id;
    }

    public static Note get(int id) {
        return noteMap.getOrDefault(id, null);
    }

    public static List<Note> list() {
        return noteMap.isEmpty() ? null : new ArrayList<>(noteMap.values());
    }

    public static int delete(int id) {
        if (noteMap.containsKey(id)) {
            noteMap.remove(id);
            return id;
        }
        return -1;
    }
}
