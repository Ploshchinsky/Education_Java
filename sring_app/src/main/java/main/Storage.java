package main;

import main.entity.Note;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class Storage {
    private static ConcurrentHashMap<Integer, Note> noteMap = new ConcurrentHashMap<>();

    public static int create(Note note) {
        int id = noteMap.size() + 1;
        note.setId(id);
        noteMap.put(id, note);
        return id;
    }

    public static int update(Note note) {
        noteMap.replace(note.getId(), note);
        return note.getId();
    }

    public static Note get(int id) {
        return noteMap.getOrDefault(id, null);
    }

    public static ArrayList<Note> list() {
        return noteMap.isEmpty() ? null : new ArrayList<>(noteMap.values());
    }

    public static int delete(int id) {
        noteMap.remove(id);
        return id;
    }

    public static boolean exist(int id) {
        return noteMap.containsKey(id);
    }

    public static boolean exist(Note note) {
        return noteMap.containsValue(note);
    }
}
