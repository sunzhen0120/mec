package com.upf.SendWithJson.Get;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.apache.http.util.EntityUtils;

public class GetWithJson {
//    void get(String url, ArrayList<Param> params) throws Exception {
//        // Build url
//        url = url + "?" + buildParams(params);
//
//        System.out.println(url);
//        // Connection
//        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//
//        // Headers
//        //User-Agent 使服务器识别客户使用的操作系统及版本、浏览器及版本等信息
//        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
//
//        // Print response status
//        System.out.println("GET status " + connection.getResponseCode());
//
//        // Print response text
//        String response;
//        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//
//        while ((response = reader.readLine()) != null) {
//            System.out.println(response);
//        }
//
//        reader.close();
//    }
//
//    // Build params string
//    private String buildParams(ArrayList<Param> params){
//        StringBuilder stringBuilder = new StringBuilder();
//
//        // Build
//        for (Param param : params) {
//            stringBuilder.append(param.getKeyParameter());
//            stringBuilder.append("=");
//            stringBuilder.append(param.getValueParameter());
//            stringBuilder.append("&");
//        }
//        // remove last '&'
//        stringBuilder.deleteCharAt(stringBuilder.length()-1);
//        System.out.println(stringBuilder.toString());
//        return stringBuilder.toString();
//    }


   public static  String GetDataWithJson(String var1, String jsonText) {
        String var4 = null;
        CloseableHttpClient var5 = HttpClients.createDefault();
        try {
            HttpGetWithEntity httpGetWithEntity = new HttpGetWithEntity(var1);
            HttpEntity httpEntity = new StringEntity(jsonText, ContentType.APPLICATION_JSON);
            httpGetWithEntity.setEntity(httpEntity);
            CloseableHttpResponse response = var5.execute(httpGetWithEntity);

            HttpEntity entity = response.getEntity();
            var4 = EntityUtils.toString(entity, "UTF-8");

        } catch (Exception e) {
            //log.error("调用get请求Error:" + e.getMessage());
            e.printStackTrace();
        }
        return var4;
    }
}
