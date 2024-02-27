package main;

import main.entity.Note;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class Storage {
    private static ConcurrentHashMap<Integer, Note> noteMap = new ConcurrentHashMap<>();

    public static int put(Note note) {
        int id = noteMap.size() + 1;
        note.setId(id);
        noteMap.put(id, note);
        return id;
    }

    public static ArrayList<Note> list() {
        return noteMap.isEmpty() ? null : new ArrayList<>(noteMap.values());
    }

    public static Note get(int id) {
        return noteMap.getOrDefault(id, null);
    }

    public static boolean exist(int id) {
        return noteMap.containsKey(id);
    }

    public static boolean exist(Note note) {
        return noteMap.containsValue(note);
    }
}
