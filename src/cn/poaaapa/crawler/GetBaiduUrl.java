package cn.poaaapa.crawler;

import cn.edu.hfut.dmic.webcollector.model.Page;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetBaiduUrl {

    public  GetBaiduUrl(String url) throws Exception{
        //获取工具类返回的html,并用Jsoup解析
        String result = AbstractSpider.getResult(url);
        Document document = Jsoup.parse(result);
        document.setBaseUri(url);
        //获取所有的img元素
//        Elements elements = document.select("a[class='j_th_tit']");
        Elements title = document.select("a[class='j_th_tit']");
        Elements num = document.select("span[class='threadlist_rep_num center_text']");
        Elements userName = document.select("span[class='frs-author-name-wrap']");

        System.out.println(title.text());
        System.out.println(num.text());
        System.out.println(userName.text());

//        System.out.println(elements.first().text());
    }

}
