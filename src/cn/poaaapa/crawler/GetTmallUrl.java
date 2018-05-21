package cn.poaaapa.crawler;

import cn.poaaapa.TaskEdit.TaskAction;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTmallUrl {
    public GetTmallUrl(String url,int id) throws Exception{
        //获取工具类返回的html,并用Jsoup解析
        String result = WyzCrawler.getResult(url);
        Document document = Jsoup.parse(result);
        document.setBaseUri(url);
        //获取所有的img元素
        Elements prices = document.select("p[class='productPrice'] > em");
        Elements titles = document.select("p[class='productTitle'] > a");
        Elements shops = document.select("div[class='productShop'] > a");
        Elements comments = document.select("p[class='productStatus'] > span > a");
        for(int i=0;i<prices.size();i++){
            System.out.println(prices.get(i).text());
            System.out.println(titles.get(i).text());
            System.out.println(shops.get(i).text());
            System.out.println(comments.get(i).text());
        }

        int colNum =4;
        String headers[]={"价格","商品名","店铺名","评论数"};
        HSSFWorkbook wb= new HSSFWorkbook();
        HSSFSheet sheet= wb.createSheet("天猫数据");
        for (int i=0;i<prices.size();i++){
            HSSFRow row= sheet.createRow(i);
            HSSFCell cell0= row.createCell(0);
            HSSFCell cell1= row.createCell(1);
            HSSFCell cell2= row.createCell(2);
            HSSFCell cell3= row.createCell(3);

            if(i==0){
                cell0.setCellValue(headers[0]);
                cell1.setCellValue(headers[1]);
                cell2.setCellValue(headers[2]);
                cell3.setCellValue(headers[3]);
            }else {
                cell0.setCellValue(prices.get(i).text());
                cell1.setCellValue(titles.get(i).text());
                cell2.setCellValue(shops.get(i).text());
                cell3.setCellValue(comments.get(i).text());

            }
        }
        Date date = new Date();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd_HHmmss");
        String strDate =sdf.format(date);
        String path = this.getClass().getClassLoader().getResource("").getPath();
        String docUrl =path+"doc/"+id+"_"+strDate+".xls";
        OutputStream out = new FileOutputStream(docUrl);
        wb.write(out);
        out.close();
        TaskAction tsa = new TaskAction();
        tsa.updateDocUrl(id,docUrl);

    }
}
