package com.upf.SendWithJson.Delete;



import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class DeleteWithJson {

    public static  String DeleteDataWithJson(String url, String jsonText) {
        String var4 = null;
        CloseableHttpClient var5 = HttpClients.createDefault();
        try {
            HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(url);
            HttpEntity httpEntity = new StringEntity(jsonText, ContentType.APPLICATION_JSON);
            httpDelete.setEntity(httpEntity);
            CloseableHttpResponse response = var5.execute(httpDelete);
            HttpEntity entity = response.getEntity();
            var4 = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            //log.error("调用get请求Error:" + e.getMessage());
            e.printStackTrace();
        }
        return var4;
    }

}
