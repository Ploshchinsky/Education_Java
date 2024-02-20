package org;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final String URL = "https://www.deepl.com/";

    public static void main(String[] args) {
        LOGGER.info("App start.... " + Thread.currentThread().getName());
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        List<String> allLinks = new ArrayList<>(forkJoinPool.invoke(new LinkCollector(URL)));
        listToFileWriter(duplicateRemover(allLinks), "DeepL.txt");
        LOGGER.info("App end ....");
    }

    private static List<String> duplicateRemover(List<String> stringList) {
        stringList.sort(String::compareTo);
        String duplicateStr = stringList.get(0).replaceAll("\n", "");
        for (int i = 1; i < stringList.size(); i++) {
            String temp = stringList.get(i).replaceAll("\n", "");
            if (temp.equals(duplicateStr)) {
                stringList.remove(i);
                i--;
            } else {
                duplicateStr = stringList.get(i).replaceAll("\n", "");
            }
        }
        return stringList;
    }

    private static void listToFileWriter(List<String> stringList, String fileName) {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (String s : stringList) {
                bufferedWriter.write(s);
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException ex) {
            LOGGER.error("File can't created. Check file name -> " + fileName);
        }

    }
}
