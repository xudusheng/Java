package com.xudu.orderfood;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrawlerTest {

    // 读取的地址
    private final static String URL_ADDRESS = "http://blog.csdn.net/je_ge?viewmode=contents";

    @Test
    public void getLinks() throws Exception {
        Document document = Jsoup.connect(URL_ADDRESS).userAgent("iOS/12.0.1").get();
        Elements elements = document.select("h1 a");
        Assert.assertNotEquals(0, elements.size());
    }

}
