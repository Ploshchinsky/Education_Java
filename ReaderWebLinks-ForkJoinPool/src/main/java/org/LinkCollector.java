package org;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class LinkCollector extends RecursiveTask<List<String>> {
    private static final Logger LOGGER = LogManager.getLogger(LinkCollector.class);
    private final String MAIN_URL;
    private List<String> childLinks;
    private Document htmlDoc;
    private Elements htmlElements;

    public LinkCollector(String url) {
        this.MAIN_URL = url;
        LOGGER.trace("LinkCollector object created in thread - " + Thread.currentThread().getName());
    }

    @Override
    protected List<String> compute() {
        LOGGER.debug("\tCompute - " + Thread.currentThread().getName());
        htmlDoc = getDocumentFromUrl(MAIN_URL);
        htmlElements = (htmlDoc == null) ? null : htmlDoc.select("a");
        childLinks = (htmlElements == null) ? null : elementsToList(htmlElements);

        if (childLinks == null) {
            childLinks = new ArrayList<>();
            childLinks.add("Link - " + MAIN_URL + " return NULL");
            return childLinks;
        } else if (childLinks.isEmpty()) {
            childLinks.add(MAIN_URL + "\n");
            return childLinks;
        }

        List<LinkCollector> childLinksList = new ArrayList<>();

        childLinks.stream().map(LinkCollector::new).forEach(childLinksList::add);
        childLinksList.forEach(ForkJoinTask::fork);
        childLinksList.stream().map(ForkJoinTask::join).forEach(childLinks::addAll);

        LOGGER.debug("Child Links size - " + childLinks.size());
        childLinks.add(MAIN_URL + "\n");
        return childLinks;
    }

    private Document getDocumentFromUrl(String url) {
        try {
            Thread.sleep(160);
            return Jsoup.connect(url)
                    .maxBodySize(0)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                            "(KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36")
                    .timeout(10_000)
                    .referrer("https://www.google.com")
                    .followRedirects(true)
                    .get();
        } catch (IOException ex) {
            LOGGER.error("File not found. Check URL -> "
                    + url + " - "
                    + Thread.currentThread().getName());
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
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
