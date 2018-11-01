package com.xudu.orderfood;


import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CrawlerTest {

    // 读取的地址
    private final static String URL_ADDRESS = "http://www.74zu.com/type/2/3.html";
    @Test
    public void getLinks() throws Exception {
        Document document = Jsoup.connect(URL_ADDRESS).userAgent("iOS/12.0.1").get();
        Elements elements = document.select("div.movie-item");

        for (Element element : elements) {
            Element moviename_element = element.select("a.movie-name").first();
            Element img_element = element.select("img").first();
            Element otherinfo_element = element.select("div.otherinfo").first();
            Element hdtag_element = element.select("button.hdtag").first();

            String title = moviename_element.text();
            String detailHref = moviename_element.attr("href");
            String img = img_element.attr("src");
            String update = otherinfo_element.text();
            String hdtag = hdtag_element.text();

            log.info("title={}, detail={}, img={}, update={}, hdtag={}", title, detailHref, img, update, hdtag);
        }
        Assert.assertNotEquals(0, elements.size());
    }
}
