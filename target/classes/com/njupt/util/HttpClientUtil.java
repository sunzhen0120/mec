package com.njupt.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.conn.util.PublicSuffixMatcherLoader;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

public class HttpClientUtil {
    private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(120000).setConnectTimeout(120000)
            .setConnectionRequestTimeout(120000).build();
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);
    private static HttpClientUtil instance = null;

    private HttpClientUtil() {
    }

    public static HttpClientUtil getInstance() {
        if (instance == null) {
            instance = new HttpClientUtil();
        }
        return instance;
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl 地址
     */
    public static String sendHttpPost(String httpUrl) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        return sendHttpPost(httpPost, true);
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl 地址
     * @param params  参数(格式:key1=value1&key2=value2)
     */
    public static String sendHttpPost(String httpUrl, String params, boolean trust) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        try {
            // 设置参数
            StringEntity stringEntity = new StringEntity(params, "UTF-8");
            stringEntity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(stringEntity);
        } catch (Exception e) {
            LOGGER.error("sendHttpPost异常:", e);
        }
        return sendHttpPost(httpPost, trust);
    }

    public static String sendHttpPost(String httpUrl, String params) {
        return sendHttpPost(httpUrl, params, true);
    }


    public static String sendHttpPostWithToken(String httpUrl, String params, String token) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        try {
            if (StringUtils.isEmpty(token)) {
                return "token不能为空";
            }
            httpPost.setHeader("Authorization", "Bearer " + token);
            // 设置参数
            StringEntity stringEntity = new StringEntity(params, "UTF-8");
            stringEntity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(stringEntity);
        } catch (Exception e) {
            LOGGER.error("sendHttpPost异常:", e);
        }
        return sendHttpPost(httpPost, true);
    }

    public static String sendHttpPostWithToken(String httpUrl, String token) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        try {
            if (StringUtils.isEmpty(token)) {
                return "token不能为空";
            }
            httpPost.setHeader("Authorization", "Bearer " + token);
            // 设置参数
            StringEntity stringEntity = new StringEntity("UTF-8");
            stringEntity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(stringEntity);
        } catch (Exception e) {
            LOGGER.error("sendHttpPost异常:", e);
        }
        return sendHttpPost(httpPost, true);
    }

    public static String sendHttpPostWithJson(String httpUrl, String params) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        try {
            // 设置参数
            StringEntity stringEntity = new StringEntity(params, ContentType.APPLICATION_JSON);
            stringEntity.setContentType("application/json; charset=UTF-8");
            httpPost.setEntity(stringEntity);
        } catch (Exception e) {
            LOGGER.error("sendHttpPostWithJson异常:", e);
        }
        String result = (String) sendHttpPost(httpPost, true, false);
        return result;
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl 地址
     * @param maps    参数
     */
    public static String sendHttpPost(String httpUrl, Map<String, String> maps) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        // 创建参数队列
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        for (Map.Entry<String, String> stringStringEntry : maps.entrySet()) {
            nameValuePairs.add(new BasicNameValuePair(stringStringEntry.getKey(), stringStringEntry.getValue()));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        } catch (Exception e) {
            LOGGER.error("sendHttpPost异常:", e);
        }
        return sendHttpPost(httpPost, true);
    }

    public static byte[] sendHttpPost(String httpUrl, Map<String, String> maps, boolean isByteArray) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        // 创建参数队列
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        for (Map.Entry<String, String> stringStringEntry : maps.entrySet()) {
            nameValuePairs.add(new BasicNameValuePair(stringStringEntry.getKey(), stringStringEntry.getValue()));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        } catch (Exception e) {
            LOGGER.error("sendHttpPost异常:", e);
        }

        byte[] bytes = (byte[]) sendHttpPost(httpPost, true, true);
        return bytes;
    }

    /**
     * 发送Post请求
     * trust:是否信任证书
     *
     * @param httpPost
     * @return
     */
    private static Object sendHttpPost(HttpPost httpPost, boolean trust, boolean isByteArray) {
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
                httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            }
            httpPost.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpPost);
            entity = response.getEntity();

            if (isByteArray) {
                result = EntityUtils.toByteArray(entity);
            } else {
                result = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (Exception e) {
            LOGGER.error("sendHttpPost异常:", e);
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

    private static String sendHttpPost(HttpPost httpPost, boolean trust) {
        return (String) sendHttpPost(httpPost, trust, false);
    }

    /**
     * 发送 get请求
     *
     * @param httpUrl
     */
    public String sendHttpGet(String httpUrl) {
        HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求
        return sendHttpGet(httpGet);
    }

    /**
     * 发送 get请求Https
     *
     * @param httpUrl
     */
    public static String sendHttpsGet(String httpUrl) {
        HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求
        return sendHttpsGet(httpGet);
    }

    public static String sendHttpsJsperGetWithToken(String httpUrl, String token) {
        HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求
        httpGet.addHeader("Accept", "application/json");
        httpGet.addHeader("Authorization", token);
        return sendHttpsGet(httpGet);
    }

    public static String sendHttpsDeleteWithToken(String httpUrl, String token) {
        HttpDelete httpDelete = new HttpDelete(httpUrl);// 创建get请求
        httpDelete.setHeader("Authorization", "Bearer " + token);
        return sendHttpDelete(httpDelete);
    }

    public static String sendHttpsDelete(String httpUrl) {
        HttpDelete httpDelete = new HttpDelete(httpUrl);// 创建get请求
        return sendHttpDelete(httpDelete);
    }

//    public static String sendHttpsPutWithToken(String httpUrl, String token) {
//        HttpDelete httpDelete = new HttpDelete(httpUrl);// 创建get请求
//        httpDelete.setHeader("Authorization", "Bearer " + token);
//        return sendHttpDelete(httpDelete);
//    }


    /**
     * 发送Get请求
     *
     * @param httpGet
     * @return
     */
    private String sendHttpGet(HttpGet httpGet) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClientBuilder.create().setRetryHandler(new DefaultHttpRequestRetryHandler(0, false)).build();
            httpGet.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            LOGGER.error("sendHttpGet异常:", e);
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
        return responseContent;
    }

    /**
     * 发送Get请求Https
     *
     * @param httpGet
     * @return
     */
    private static String sendHttpsGet(HttpGet httpGet) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            PublicSuffixMatcher publicSuffixMatcher = PublicSuffixMatcherLoader.load(new URL(httpGet.getURI().toString()));
            DefaultHostnameVerifier hostnameVerifier = new DefaultHostnameVerifier(publicSuffixMatcher);
            httpClient = HttpClients.custom().setSSLHostnameVerifier(hostnameVerifier).build();
            httpGet.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            LOGGER.error("sendHttpsGet异常:", e);
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
        return responseContent;
    }

    /**
     * 发送delete请求Https
     *
     * @param httpDelete
     * @return
     */
    private static String sendHttpDelete(HttpDelete httpDelete) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        try {
            httpDelete.setConfig(requestConfig);
            httpResponse = httpClient.execute(httpDelete);
            HttpEntity entity = httpResponse.getEntity();
            String result = EntityUtils.toString(entity);
            return result;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 设置签名
     *
     * @param paramValues
     * @param secret
     * @return
     * @throws Exception
     */
    public static String sign(Map<String, String> paramValues, String secret) throws Exception {
        StringBuilder sb = new StringBuilder();
        List<String> paramNames = new ArrayList<String>(paramValues.size());
        paramNames.addAll(paramValues.keySet());
        Collections.sort(paramNames);
        sb.append(secret);
        for (String paramName : paramNames) {
            sb.append(paramName).append(paramValues.get(paramName));
        }
        sb.append(secret);
        String signMd5 = getmd5Digest(sb.toString());
        return signMd5;
    }

    private static String getmd5Digest(String data) {
        try {
            byte[] bytes = null;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes("UTF-8"));
            bytes = md.digest();

            StringBuilder stringBuilder = new StringBuilder("");
            for (int i = 0; i < bytes.length; i++) {
                int v = bytes[i] & 0xFF;
                String hv = Integer.toHexString(v);
                if (hv.length() < 2) {
                    stringBuilder.append(0);
                }
                stringBuilder.append(hv);
            }
            return stringBuilder.toString();
        } catch (GeneralSecurityException gse) {
            return "";
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static String postWithParamsForString(String url, List<BasicNameValuePair> params) {
        HttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        String s = "";
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            HttpResponse response = client.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                s = EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }


}
