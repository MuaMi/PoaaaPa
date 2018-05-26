package cn.poaaapa.crawler;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * 2017-5-17 基础类，通过URL返回一个响应的html
 *
 * @author tom
 */
public class WyzCrawler {

    public static String getResult(String url) throws Exception {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
             CloseableHttpResponse response = httpClient.execute(new HttpGetConfig(url))) {
            String result = EntityUtils.toString(response.getEntity());
            return result;
        } catch (Exception e) {
            return "error";
        }
    }
}

/**
 * 设置头部
 */
class HttpGetConfig extends HttpGet {
    public HttpGetConfig(String url) {
        super(url);
        set_Config();
    }

    private void set_Config() {
        this.setConfig(RequestConfig.custom()
                .setConnectionRequestTimeout(10000)
                .setConnectTimeout(10000)
                .setSocketTimeout(10000).build());
        this.setHeader("User-Agent", "cs");
        //部分网站需要设置Cookie才可访问扒取
        //否则失败
        //this.setHeader("Cookie","uuid=123;");
    }
}