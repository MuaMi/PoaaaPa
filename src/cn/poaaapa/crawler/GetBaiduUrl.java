package cn.poaaapa.crawler;

import cn.edu.hfut.dmic.webcollector.model.Page;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetBaiduUrl {

    public  GetBaiduUrl(String url,int id) throws Exception{
        //获取工具类返回的html,并用Jsoup解析
        String result = WyzCrawler.getResult(url);
        Document document = Jsoup.parse(result);
        document.setBaseUri(url);
        //获取所有的img元素
//        Elements elements = document.select("a[class='j_th_tit']");
        Elements title = document.select("a[class='j_th_tit']");
        Elements num = document.select("span[class='threadlist_rep_num center_text']");
        Elements userName = document.select("span[class='frs-author-name-wrap']");

        for (Element e : title){
            System.out.println(e.text());
        }
        for (Element e : num){
            System.out.println(e.text());
        }
        for (Element e : userName){
            System.out.println(e.text());
        }
        int colNum =3;
        String headers[]={"标题","回复数","发帖人"};
        HSSFWorkbook  wb= new HSSFWorkbook();
        HSSFSheet sheet= wb.createSheet("贴吧数据");
        for (int i=0;i<title.size();i++){
            HSSFRow row= sheet.createRow(i);
            HSSFCell cell0= row.createCell(0);
            HSSFCell cell1= row.createCell(1);
            HSSFCell cell2= row.createCell(2);
            if(i==0){
                cell0.setCellValue(headers[0]);
                cell1.setCellValue(headers[1]);
                cell2.setCellValue(headers[2]);
            }else {
                cell0.setCellValue(title.get(i).text());
                cell1.setCellValue(num.get(i).text());
                cell2.setCellValue(userName.get(i).text());
            }
            System.out.println(title.get(i).text());
            System.out.println(num.get(i).text());
            System.out.println(userName.get(i).text());
        }
        Date date = new Date();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd_HHmmss");
        String strDate =sdf.format(date);
        String path = this.getClass().getResource("").getPath();
        OutputStream out = new FileOutputStream(path+id+"_"+strDate+".xls");
        wb.write(out);
        out.close();


//        System.out.println(elements.first().text());
    }

}
