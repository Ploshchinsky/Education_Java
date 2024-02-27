package main;

import main.entity.Note;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
//        for (int i = 0; i < 4; i++) {
//            Note note = new Note();
//            note.setId(i);
//            note.setHeader("Header " + i);
//            note.setBody("Body " + i);
//            Storage.put(note);
//        }

    }
}
