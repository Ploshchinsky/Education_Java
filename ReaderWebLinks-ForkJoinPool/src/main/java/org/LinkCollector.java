package org;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class LinkCollector extends RecursiveTask<List<String>> {
    private final String MAIN_URL;
    private List<String> childLinks;
    private Document htmlDoc;
    private Elements htmlElements;

    public LinkCollector(String url) {
        this.MAIN_URL = url;
    }

    @Override
    protected List<String> compute() {
        htmlDoc = getDocumentFromUrl(MAIN_URL);
        htmlElements = (htmlDoc == null) ? null : htmlDoc.select("a");
        childLinks = elementsToList(htmlElements);
        if (childLinks.size() <= 1) {
            return childLinks;
        }
        return null;
    }

    private Document getDocumentFromUrl(String url) {
        try {
            return Jsoup.connect(url)
                    .maxBodySize(0)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                            "(KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36")
                    .timeout(10_000)
                    .referrer("https://www.google.com")
                    .followRedirects(true)
                    .get();
        } catch (IOException ex) {
            System.out.println("File not found. Check URL");
        }
        return null;
    }

    private List<String> elementsToList(Elements elements) {
        List<String> stringList = new ArrayList<>();
        for (Element element : elements) {
            String temp = element.toString();
            if (temp.contains(MAIN_URL)) {
                stringList.add(element.attr("abs:href") + "\n");
            }
        }
        return stringList;
    }
}
