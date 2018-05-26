package cn.poaaapa.crawler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import cn.poaaapa.TaskEdit.TaskAction;
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
    public SpiderImgs(String url,int id) throws Exception {
        //获取工具类返回的html,并用Jsoup解析
            String result = WyzCrawler.getResult(url);
            Document document = Jsoup.parse(result);
            document.setBaseUri(url);

            document=Jsoup.connect(url).data("query", "Java")//请求参数
                    .userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)")//设置urer-agent  get();
                    .timeout(5000)
                    .get();
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
            } catch (Exception e) {
                System.out.println(e);
            }
            //获取所有的img元素
            Elements elements = document.select("li[class='imgitem']").select("div[class='imgbox']").select("img");
            System.out.println(elements);
            for (Element e : elements) {
                //获取每个src的绝对路径
                String src = e.absUrl("src");
                System.out.println(e.absUrl("src"));
                if (src == null || "".equals(src) || !src.startsWith("http")) {
                    continue;
                }
                URL urlSource = new URL(src);
                URLConnection urlConnection = urlSource.openConnection();
                String imageName = src.substring(src.lastIndexOf("/") + 1, src.length());

                //通过URLConnection得到一个流，将图片写到流中，并且新建文件保存
                InputStream in = urlConnection.getInputStream();
                if (imageName.matches("[^\\\\s\\\\\\\\/:\\\\*\\\\?\\\\\\\"<>\\\\|](\\\\x20|[^\\\\s\\\\\\\\/:\\\\*\\\\?\\\\\\\"<>\\\\|])*[^\\\\s\\\\\\\\/:\\\\*\\\\?\\\\\\\"<>\\\\|\\\\.]$")) {
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

        TaskAction tsa = new TaskAction();
        tsa.updateDocUrl(id,"");
    }
}
