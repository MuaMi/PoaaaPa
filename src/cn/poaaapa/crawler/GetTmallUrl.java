package cn.poaaapa.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetTmallUrl {
    public GetTmallUrl(String url) throws Exception{
        //获取工具类返回的html,并用Jsoup解析
        String result = WyzCrawler.getResult(url);
        Document document = Jsoup.parse(result);
        document.setBaseUri(url);
        //获取所有的img元素
        Elements elements = document.select("p[class='productPrice']");

        System.out.println(elements);


    }
}
