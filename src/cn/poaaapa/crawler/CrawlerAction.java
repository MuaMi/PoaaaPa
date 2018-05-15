package cn.poaaapa.crawler;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import org.jsoup.nodes.Document;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlerAction extends BreadthCrawler{

//    static String  URL = null;
    public static void StartCrawler(String str){
        System.out.println(str);
    }
    public CrawlerAction(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
        /*start page*/
        //种子页面
        this.addSeed("https://www.baidu.com/s?wd=测试&pn=20");
        //正则规则设置
        this.addRegex("https://www.baidu.com/s?wd=测试&pn=0");
        this.addRegex("-.*#.*");
    }

    @Override
    public void visit(Page page, CrawlDatums next) {
        String url = page.url();
        /*if page is news page*/
        //正则判断
//        if (page.matchUrl("http://news.hfut.edu.cn/show-.*html")) {
        /*we use jsoup to parse page*/
        Document doc = page.doc();
//            System.out.println(doc);

        Elements links = doc.select("h3[class='t']");
        System.out.println(links);
        System.out.println("URL:" + url);
//            System.out.println(links);
//
//            /*extract title and content of news by css selector*/
//            String title = page.select("div[id=Article]>h2").first().text();
//            String content = page.select("div#artibody", 0).text();
//
//            System.out.println("URL:\n" + url);
//            System.out.println("title:\n" + title);
//            System.out.println("content:\n" + content);

        /*If you want to add urls to crawl,add them to nextLink*/
        /*WebCollector automatically filters links that have been fetched before*/
        /*If autoParse is true and the link you add to nextLinks does not
          match the regex rules,the link will also been filtered.*/
        //next.add("http://xxxxxx.com");
//        }
    }

    public void main(String[] args) throws Exception {
        CrawlerAction crawler = new CrawlerAction("crawl", true);
        crawler.setThreads(50);
//        crawler.setTopN(100);
        //crawler.setResumable(true);
        /*start crawl with depth of 4*/
        crawler.start(4);
    }

}