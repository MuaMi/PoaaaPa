package cn.poaaapa.crawler;

import cn.poaaapa.TaskEdit.TaskAction;
import com.google.gson.JsonObject;
import netscape.javascript.JSObject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.codehaus.jackson.map.util.JSONPObject;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.text.html.HTMLDocument;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class GetCustomUrl {
    public GetCustomUrl(String url,String urlRule,int id) throws Exception {
        JSONObject json = new JSONObject(urlRule);
        Iterator keys = json.keys();
        List<String> names = new ArrayList<String>();
        List<String> rules = new ArrayList<>() ;
        while(keys.hasNext()){
            String name =keys.next().toString();
            String rule =json.getString(name);
            names.add(name);
            rules.add(rule);
        }
        System.out.println(names);
        System.out.println(rules);
        int colNum = names.size();
        int rowNum =0;
        List<Elements> list =new ArrayList<>();
        //获取工具类返回的html,并用Jsoup解析
        String result = WyzCrawler.getResult(url);
        Document document = Jsoup.parse(result);
        document.setBaseUri(url);
        for(String rule:rules){
            Elements e =document.select(rule);
            list.add(e);
            System.out.println(e);
            rowNum = e.size();
        }
        HSSFWorkbook wb= new HSSFWorkbook();
        HSSFSheet sheet= wb.createSheet("自定义数据");
        for(int i=0; i<rowNum;i++){
            HSSFRow row= sheet.createRow(i);
            for(int j=0;j<colNum;j++){
                HSSFCell cellj= row.createCell(j);
                if(i==0){
                    cellj.setCellValue(names.get(j));
                }else {
                    cellj.setCellValue(list.get(j).get(i).text());
                }
            }
        }
        Date date = new Date();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd_HHmmss");
        String strDate =sdf.format(date);
//        String path = this.getClass().getClassLoader().getResource("/").getPath();

        File dic =new File(".");
        String path = dic.getCanonicalPath();
        String docUrl =path+"/doc/"+id+"_"+strDate+".xls";
        System.out.println(docUrl);
        OutputStream out = new FileOutputStream(docUrl);
        wb.write(out);
        out.close();
        TaskAction tsa = new TaskAction();
        tsa.updateDocUrl(id,id+"_"+strDate+".xls");



    }
}
