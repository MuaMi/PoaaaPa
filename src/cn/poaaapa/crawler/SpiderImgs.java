package cn.poaaapa.crawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 2017-5-17 爬取指定URL的图片
 *
 * @author tom
 *
 */
public class SpiderImgs {
    public SpiderImgs(String url) throws Exception {
        //获取工具类返回的html,并用Jsoup解析
        String result = WyzCrawler.getResult(url);
        Document document = Jsoup.parse(result);
        document.setBaseUri(url);

        File dirFile;
        boolean bFile;
        bFile = false;
        try {
            dirFile = new File("E://img");
            bFile = dirFile.exists();
            if (bFile != true) {
                bFile = dirFile.mkdir();
                if (bFile == true) {
                    System.out.println("Create successfully!");
                } else {
                    System.out.println("Disable to make the folder,please check the disk is full or not.");
                    System.exit(1);
                }
            }
        } catch (Exception e){
            System.out.println(e);
        }
        //获取所有的img元素
        Elements elements = document.select("img");
        for (Element e : elements) {
            //获取每个src的绝对路径
            String src = e.absUrl("src");
            URL urlSource = new URL(src);
            URLConnection urlConnection = urlSource.openConnection();
            String imageName = src.substring(src.lastIndexOf("/") + 1, src.length());
            System.out.println(e.absUrl("src"));
            //通过URLConnection得到一个流，将图片写到流中，并且新建文件保存
            InputStream in = urlConnection.getInputStream();
            if (imageName.matches("[^\\\\s\\\\\\\\/:\\\\*\\\\?\\\\\\\"<>\\\\|](\\\\x20|[^\\\\s\\\\\\\\/:\\\\*\\\\?\\\\\\\"<>\\\\|])*[^\\\\s\\\\\\\\/:\\\\*\\\\?\\\\\\\"<>\\\\|\\\\.]$")){
                OutputStream out = new FileOutputStream(new File("E:\\img\\", imageName));
                byte[] buf = new byte[1024];
                int l = 0;
                while ((l = in.read(buf)) != -1) {
                    out.write(buf, 0, l);
                }
            } else {
                System.out.println("不合法");
            }



        }
    }
}
