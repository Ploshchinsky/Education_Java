package org;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static final String URL = "https://www.deepl.com/";

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        List<String> allLinks = new ArrayList<>(forkJoinPool.invoke(new LinkCollector(URL)));
        duplicateRemover(allLinks);
    }

    private static List<String> duplicateRemover(List<String> stringList) {
        stringList.sort(String::compareTo);
        String tempString = stringList.get(0);
        for (int i = 1; i < stringList.size(); i++) {
            if (tempString.equals(stringList.get(i))) {
                stringList.remove(i);
                i--;
            } else {
                tempString = stringList.get(i);
            }
        }
        return stringList;
    }
}
