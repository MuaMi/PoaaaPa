package cn.poaaapa.crawler;
import org.junit.Test;


public class TestCase {
    @Test
    public void testGetResult() throws Exception{
//        SpiderImgs spider=new SpiderImgs("https://image.baidu.com/search/index?tn=baiduimage&ct=201326592&lm=-1&cl=2&ie=gbk&word=%BE%B0%C9%AB&fr=ala&ala=1&alatpl=adress&pos=0&hs=2&xthttps=111111");
        GetBaiduUrl getBaidu = new GetBaiduUrl("http://tieba.baidu.com/f?fr=wwwt&kw=美团",123);
//        GetMtUrl getmt = new GetMtUrl("http://bj.meituan.com/shop/6151046");
//        GetTmallUrl tmall = new GetTmallUrl("https://list.tmall.com/search_product.htm?spm=875.7931836/B.subpannel2016049.25.66144265FL3Rbc&pos=2&cat=56042001&acm=201604135.1003.2.768869&scm=1003.2.201604135.OTHER_1522553335778_768869");
//        GetZhihuUrl getzhihu = new GetZhihuUrl("https://www.zhihu.com/question/35888396");
    }

}
