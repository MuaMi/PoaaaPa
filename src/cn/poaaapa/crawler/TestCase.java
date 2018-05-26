package cn.poaaapa.crawler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;


public class TestCase {
    @Test
    public void testGetResult() throws Exception{
        String url = "http://image.baidu.com/search/avatarjson?tn=resultjsonavatarnew&ie=utf-8&word=%BE%B0%C9%AB&cg=star&pn=90&rn=30&itg=0&z=0&fr=&width=&height=&lm=-1&ic=0&s=0&st=-1&gsm="+Integer.toHexString(90);
            GetBaiImgUrl getBaiImgUrl =new GetBaiImgUrl(url,123456   );
//        SpiderImgs spider=new SpiderImgs("https://image.baidu.com/search/index?tn=baiduimage&ct=201326592&lm=-1&cl=2&ie=gbk&word=%BE%B0%C9%AB&fr=ala&ala=1&alatpl=adress&pos=0&hs=2&xthttps=111111",123456);
//        GetBaiduUrl getBaidu = new GetBaiduUrl("http://tieba.baidu.com/f?fr=wwwt&kw=美团",123);
//        GetMtUrl getmt = new GetMtUrl("http://bj.meituan.com/shop/6151046");
//        GetMtUrl getmt = new GetMtUrl("http://chs.meituan.com/meishi/");
//        GetTmallUrl tmall = new GetTmallUrl("https://list.tmall.com/search_product.htm?spm=875.7931836/B.subpannel2016049.25.66144265FL3Rbc&pos=2&cat=56042001&acm=201604135.1003.2.768869&scm=1003.2.201604135.OTHER_1522553335778_768869",123);
//        GetZhihuUrl getzhihu = new GetZhihuUrl("https://www.zhihu.com/question/35888396");
//        GetCustomUrl getCustomUrl = new GetCustomUrl("http://tieba.baidu.com/f?fr=wwwt&kw=美团","{\"名称\":\"a[class='123']\",'值':'a[class=123]'}",123);
//        String url ="https://image.baidu.com/search/index?ct=201326592&cl=2&st=-1&lm=-1&nc=1&ie=utf-8&tn=baiduimage&ipn=r&rps=1&pv=&fm=rs1&word=%E4%BA%BA%E9%97%B4%E4%BB%99%E5%A2%83&oriquery=%E6%99%AF%E8%89%B2&ofr=%E6%99%AF%E8%89%B2&hs=2&sensitive=0";
//        String result = WyzCrawler.getResult(url);
//        Document document = Jsoup.parse(result);
//        document.setBaseUri(url);
////        Elements elements = document.select("li[class='imgitem']").select("div[class='imgbox']").select("img");
//        Elements elements = document.select("div[id='wrapper']").select("div[id='imgid']");
//        System.out.println(elements);

    }

}
