package com.njupt.util;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class OpenStackRequestUtil {

    private static final Logger log = LoggerFactory.getLogger(OpenStackRequestUtil.class);

    private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000)
            .setConnectionRequestTimeout(5000).build();

    private static RequestConfig putConfig = RequestConfig.custom().setSocketTimeout(1000000).setConnectTimeout(1000000)
            .setConnectionRequestTimeout(1000000).build();


    public static String sendGetTokenPost(String httpUrl, String params) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        // 设置参数
        StringEntity stringEntity = new StringEntity(params, "UTF-8");
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            if (true) {
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
            Header[] headers = response.getHeaders("X-Subject-Token");
            Header header = headers[0];
            return header.getValue();
        } catch (Exception e) {
            log.error("sendGetTokenPost异常:", e);
            HttpEntity entity = response.getEntity();
            String response_1 = null;
            try {
                response_1 = EntityUtils.toString(entity, "UTF-8");
            } catch (IOException ioException) {
            }
            log.error("error,response:{}:", response_1);
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
        return null;
    }

    public static Map sendHttpPostWithToken(String httpUrl, String param, String token) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("code", "400");
        try {
            // 设置参数
            StringEntity stringEntity = new StringEntity(param, "UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
        } catch (Exception e) {
            log.error("sendHttpPost异常:", e);
        }
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        Object result = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpPost.setConfig(requestConfig);
            httpPost.setHeader("X-Auth-Token", token);
            // 执行请求
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            String s = EntityUtils.toString(response.getEntity(), "UTF-8");
            dataMap.put("code", String.valueOf(statusCode));
            dataMap.put("message", s);
        } catch (Exception e) {
            log.error("sendHttpPostWithToken异常:", e);
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
        return dataMap;
    }

    public static void sendHttpPutWithFile(String httpUrl, InputStream inputStream, String token) {
        HttpPut httpPut = new HttpPut(httpUrl);// 创建httpPost
        try {
            // 设置参数
            byte[] bytes = new byte[inputStream.available()];
            ByteArrayEntity byteArrayEntity = new ByteArrayEntity(bytes);
            byteArrayEntity.setContentType("application/octet-stream");
            httpPut.setEntity(byteArrayEntity);
        } catch (Exception e) {
            log.error("sendHttpPut异常:", e);
        }
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
//        Object result = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpPut.setConfig(putConfig);
            httpPut.setHeader("X-Auth-Token", token);

            // 执行请求
            response = httpClient.execute(httpPut);
//            int statusCode = response.getStatusLine().getStatusCode();
//            String s = EntityUtils.toString(response.getEntity(), "UTF-8");

        } catch (Exception e) {
            log.error("sendHttpPutWithFile异常:", e);
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
    }

    public static String sendHttpGetWithToken(String httpUrl, String token) {
        HttpGet httpGet = new HttpGet(httpUrl);// 创建httpPost
        Object result = null;
        try {
            if (StringUtils.isEmpty(token)) {
                return "token不能为空";
            }
            httpGet.setHeader("X-Auth-Token", token);
            // 设置参数
            CloseableHttpClient httpClient = null;
            CloseableHttpResponse response = null;
            HttpEntity entity;
            try {
                SSLContext sslContext = SSLContextBuilder.create().loadTrustMaterial((chain, authType) -> true).build();
                // 创建默认的httpClient实例.
                HttpClientBuilder builder = HttpClients.custom().setSSLContext(sslContext).setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE);

                httpClient = builder.build();
                httpGet.setConfig(requestConfig);
                // 执行请求
                response = httpClient.execute(httpGet);
                entity = response.getEntity();
                result = EntityUtils.toString(entity, "UTF-8");
            } catch (Exception e) {
                log.error("sendHttpGetWithToken异常:", e);
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
        } catch (Exception e) {
            log.error("sendOcHttpGet异常:", e);
        }
        return String.valueOf(result);
    }

    public static Map<String, String> sendHttpDeleteWithToken(String httpUrl, String token) {
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("code", "400");
        HttpDelete httpDelete = new HttpDelete(httpUrl);// 创建httpPost
        Object result = null;
        try {
            if (StringUtils.isEmpty(token)) {
                return dataMap;
            }
            httpDelete.setHeader("X-Auth-Token", token);
            // 设置参数
            CloseableHttpClient httpClient = null;
            CloseableHttpResponse response = null;
            HttpEntity entity;
            try {
                SSLContext sslContext = SSLContextBuilder.create().loadTrustMaterial((chain, authType) -> true).build();
                // 创建默认的httpClient实例.
                HttpClientBuilder builder = HttpClients.custom().setSSLContext(sslContext).setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE);

                httpClient = builder.build();
                httpDelete.setConfig(requestConfig);
                // 执行请求
                response = httpClient.execute(httpDelete);
                entity = response.getEntity();
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    dataMap.put("code", "200");
                    dataMap.put("message", "删除成功");
                    return dataMap;
                }
                result = EntityUtils.toString(entity, "UTF-8");
                dataMap.put("code", String.valueOf(statusCode));
                dataMap.put("message", String.valueOf(result));
            } catch (Exception e) {
                log.error("sendHttpDeleteWithToken异常:", e);
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
        } catch (Exception e) {
            log.error("sendHttpDeleteWithToken异常:", e);
        }
        return dataMap;
    }

    public static Map sendHttpPutWithTokenNetwork(String httpUrl, String param, String token) {
        HttpPut httpPut = new HttpPut(httpUrl);// 创建httpPost
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("code", "400");
        try {
            // 设置参数
            StringEntity stringEntity = new StringEntity(param, "UTF-8");
            stringEntity.setContentType("application/json");
            httpPut.setEntity(stringEntity);
        } catch (Exception e) {
            log.error("sendHttpPut异常:", e);
        }
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        Object result = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpPut.setConfig(requestConfig);
            httpPut.setHeader("X-Auth-Token", token);
            // 执行请求
            response = httpClient.execute(httpPut);
            int statusCode = response.getStatusLine().getStatusCode();
            String s = EntityUtils.toString(response.getEntity(), "UTF-8");
            dataMap.put("code", String.valueOf(statusCode));
            dataMap.put("message", s);
        } catch (Exception e) {
            log.error("sendHttpPutWithToken异常:", e);
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
        return dataMap;
    }

    public static Map<String, String> sendHttpDeleteWithToken204(String httpUrl, String token) {
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("code", "400");
        HttpDelete httpDelete = new HttpDelete(httpUrl);// 创建httpPost
        Object result = null;
        try {
            if (StringUtils.isEmpty(token)) {
                return dataMap;
            }
            httpDelete.setHeader("X-Auth-Token", token);
            // 设置参数
            CloseableHttpClient httpClient = null;
            CloseableHttpResponse response = null;
            HttpEntity entity;
            try {
                SSLContext sslContext = SSLContextBuilder.create().loadTrustMaterial((chain, authType) -> true).build();
                // 创建默认的httpClient实例.
                HttpClientBuilder builder = HttpClients.custom().setSSLContext(sslContext).setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE);

                httpClient = builder.build();
                httpDelete.setConfig(requestConfig);
                // 执行请求
                response = httpClient.execute(httpDelete);
                entity = response.getEntity();
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 204) {
                    /*状态码为204算出成功*/
                    dataMap.put("code", "200");
                    dataMap.put("message", "删除成功");
                    return dataMap;
                }
                result = EntityUtils.toString(entity, "UTF-8");
                dataMap.put("code", String.valueOf(statusCode));
                dataMap.put("message", String.valueOf(result));
            } catch (Exception e) {
                log.error("sendHttpDeleteWithToken异常:", e);
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
        } catch (Exception e) {
            log.error("sendHttpDeleteWithToken异常:", e);
        }
        return dataMap;
    }


    public static Map<String, String> sendHttpDeleteWithTokenForImage(String httpUrl, String token) {
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("code", "400");
        HttpDelete httpDelete = new HttpDelete(httpUrl);// 创建httpPost
        Object result = null;
        try {
            if (StringUtils.isEmpty(token)) {
                return dataMap;
            }
            httpDelete.setHeader("X-Auth-Token", token);
            // 设置参数
            CloseableHttpClient httpClient = null;
            CloseableHttpResponse response = null;
            HttpEntity entity;
            try {
                SSLContext sslContext = SSLContextBuilder.create().loadTrustMaterial((chain, authType) -> true).build();
                // 创建默认的httpClient实例.
                HttpClientBuilder builder = HttpClients.custom().setSSLContext(sslContext).setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE);

                httpClient = builder.build();
                httpDelete.setConfig(requestConfig);
                // 执行请求
                response = httpClient.execute(httpDelete);
                entity = response.getEntity();
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 204) {
                    dataMap.put("code", "204");
                    dataMap.put("message", "删除成功");
                    return dataMap;
                }
                result = EntityUtils.toString(entity, "UTF-8");
                dataMap.put("code", String.valueOf(statusCode));
                dataMap.put("message", String.valueOf(result));
            } catch (Exception e) {
                log.error("sendHttpDeleteWithToken异常:", e);
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
        } catch (Exception e) {
            log.error("sendHttpDeleteWithToken异常:", e);
        }
        return dataMap;
    }

    public static Map<String, String> sendHttpPutWithToken(String httpUrl, String token) {
        HttpPut httpPut = new HttpPut(httpUrl);// 创建httpPost
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("code", "400");
        Object result = null;
        try {
            if (StringUtils.isEmpty(token)) {
                return dataMap;
            }
            httpPut.setHeader("X-Auth-Token", token);

            CloseableHttpClient httpClient = null;
            CloseableHttpResponse response = null;
            HttpEntity entity;
            try {
                SSLContext sslContext = SSLContextBuilder.create().loadTrustMaterial((chain, authType) -> true).build();
                // 创建默认的httpClient实例.
                HttpClientBuilder builder = HttpClients.custom().setSSLContext(sslContext).setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE);

                httpClient = builder.build();
                httpPut.setConfig(requestConfig);
                // 执行请求
                response = httpClient.execute(httpPut);
                entity = response.getEntity();
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 204) {
                    dataMap.put("code", "204");
                    dataMap.put("message", "上传成功");
                    return dataMap;
                }
                result = EntityUtils.toString(entity, "UTF-8");
                dataMap.put("code", String.valueOf(statusCode));
                dataMap.put("message", String.valueOf(result));
            } catch (Exception e) {
                log.error("sendHttpPutWithToken异常:", e);
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
        } catch (Exception e) {
            log.error("sendHttpPutWithToken异常:", e);
        }
        return dataMap;
    }

    public static Map<String, String> sendHttpPutWithTokenForUploadImageData(String httpUrl, String token) {
        HttpPut httpPut = new HttpPut(httpUrl);// 创建httpPost
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("code", "400");
        Object result = null;
        try {
            if (StringUtils.isEmpty(token)) {
                return dataMap;
            }
            httpPut.setHeader("X-Auth-Token", token);
            httpPut.setHeader("Content-type", "application/octet-stream");

            CloseableHttpClient httpClient = null;
            CloseableHttpResponse response = null;
            HttpEntity entity;
            try {
                SSLContext sslContext = SSLContextBuilder.create().loadTrustMaterial((chain, authType) -> true).build();
                // 创建默认的httpClient实例.
                HttpClientBuilder builder = HttpClients.custom().setSSLContext(sslContext).setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE);

                httpClient = builder.build();
                httpPut.setConfig(requestConfig);
                // 执行请求
                response = httpClient.execute(httpPut);
                entity = response.getEntity();
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 204) {
                    dataMap.put("code", "204");
                    dataMap.put("message", "上传成功");
                    return dataMap;
                }
                result = EntityUtils.toString(entity, "UTF-8");
                dataMap.put("code", String.valueOf(statusCode));
                dataMap.put("message", String.valueOf(result));
            } catch (Exception e) {
                log.error("sendHttpPutWithToken异常:", e);
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
        } catch (Exception e) {
            log.error("sendHttpPutWithToken异常:", e);
        }
        return dataMap;
    }

    public static Map sendHttpPostWithTokenWithoutParam(String httpUrl, String token) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("code", "400");
        Object result = null;
        try {
            if (StringUtils.isEmpty(token)) {
                return dataMap;
            }
            httpPost.setHeader("X-Auth-Token", token);

            CloseableHttpClient httpClient = null;
            CloseableHttpResponse response = null;
            HttpEntity entity;
            try {
                SSLContext sslContext = SSLContextBuilder.create().loadTrustMaterial((chain, authType) -> true).build();
                // 创建默认的httpClient实例.
                HttpClientBuilder builder = HttpClients.custom().setSSLContext(sslContext).setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE);

                httpClient = builder.build();
                httpPost.setConfig(requestConfig);
                // 执行请求
                response = httpClient.execute(httpPost);
                entity = response.getEntity();
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 204) {
                    dataMap.put("code", "204");
                    dataMap.put("message", "操作成功");
                    return dataMap;
                }
                result = EntityUtils.toString(entity, "UTF-8");
                dataMap.put("code", String.valueOf(statusCode));
                dataMap.put("message", String.valueOf(result));
            } catch (Exception e) {
                log.error("sendHttpPostWithToken异常:", e);
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
        } catch (Exception e) {
            log.error("sendHttpPostWithToken异常:", e);
        }
        return dataMap;
    }
}
