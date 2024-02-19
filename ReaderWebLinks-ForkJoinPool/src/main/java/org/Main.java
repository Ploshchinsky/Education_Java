package org;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {
    private static final String URL = "https://www.deepl.com/";

    public static void main(String[] args) {
        Document siteDoc = getDocumentFromUrl(URL);
        String links = elementsToString(siteDoc.select("a"), URL);
        System.out.println(links);
    }

    private static String elementsToString(Elements elements, String url) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Element element : elements) {
            String temp = element.toString();
            if (temp.contains(url)) {
                stringBuffer.append(element.attr("abs:href")).append("\n");
            }
        }
        return stringBuffer.toString();
    }

    private static Document getDocumentFromUrl(String url) {
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
}
