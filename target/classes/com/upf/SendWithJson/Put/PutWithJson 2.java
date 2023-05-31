package com.upf.SendWithJson.Put;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
//import org.example.util.HttpClientUtil;


public class PutWithJson {
    private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(120000).setConnectTimeout(120000)
            .setConnectionRequestTimeout(120000).build();
  //  private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);
    //private static HttpClientUtil instance = null;
    public static String PutDataWithJson(String httpUrl, String params) {//1
        HttpPut httpPut = new HttpPut(httpUrl);// 创建httpPost
        try {
            // 设置参数
            StringEntity stringEntity = new StringEntity(params, ContentType.APPLICATION_JSON);
            stringEntity.setContentType("application/json; charset=UTF-8");///序列化JSON数据

            httpPut.setEntity(stringEntity);
        } catch (Exception e) {
         //   LOGGER.error("sendHttpPostWithJson异常:", e);
        }
        String result = (String) sendHttpPut( httpPut, true, false);
        return result;
    }

    private static Object sendHttpPut(HttpPut httpPut, boolean trust, boolean isByteArray) {//2
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        Object result = null;
        try {
            if (trust) {
                // 创建默认的httpClient实例.
                httpClient = HttpClients.createDefault();

            } else {
                SSLContextBuilder builder = new SSLContextBuilder();
                builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
                httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();///
            }
            httpPut.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpPut);///
            entity = response.getEntity();

            if (isByteArray) {
                result = EntityUtils.toByteArray(entity);
            } else {
                result = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (Exception e) {
           // LOGGER.error("sendHttpPost异常:", e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (Exception e) {
                }
            }

            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (Exception e) {
                }
            }
        }
        return result;
    }
}
