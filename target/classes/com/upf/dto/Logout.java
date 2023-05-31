package com.upf.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Logout {
    public static void main(String[] args) {

        //未自己测试
        try {
            String postURL  = " http://192.168.2.157::8500/v1/coreNetwork/auth/Logout";
            PostMethod postMethod = null;
            postMethod = new PostMethod(postURL) ;
            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8") ;
            //参数设置，需要注意的就是里边不能传NULL，要传空字符串
            NameValuePair[] data = {
                    new NameValuePair("username","admin"),
                    new NameValuePair("password","49dc52e6bf2abe5ef6e2bb5b0f1ee2d765b922ae6cc8b95d39dc06c21c848f8c"),
                    new NameValuePair("access_token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2NDk2OTEwNDQsImlzcyI6ImlwbG9va181Z2Nfb2FtIiwidV9pZCI6IjIwMjAwNjExMTU0NTAwIiwidV9uYW1lIjoiYWRtaW4iLCJjcmVhdGVfdGltZSI6MTY0NzA5OTA0NH0.0fr6fwNRdiIegaeYpnfrUbX7yrBU2ApWXVjB-aRHyu0")

            };
            postMethod.setRequestBody(data);
            org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
            int response =httpClient.executeMethod(postMethod); // 执行POST方法

            InputStream inputStream = postMethod.getResponseBodyAsStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();

            String str= "";
            while((str = br.readLine() )!=null) {
                stringBuffer.append(str);
            }

            JSONObject jsonObject = JSON.parseObject(stringBuffer.toString());
            String result= jsonObject.getString("result");
            String msg= jsonObject.getString("msg");

            //输出获取到的结果
            System.out.println(result);
            System.out.println(msg);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}


//       System.out.println("------------------以下是测试logout-------------------------");
//
//        try {
//            String postURL  = "http://192.168.2.157:8500/v1/coreNetwork/auth/Logout";
//            PostMethod postMethod = null;
//            postMethod = new PostMethod(postURL) ;
//            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8") ;
//            //参数设置，需要注意的就是里边不能传NULL，要传空字符串
//            NameValuePair[] data = {
//                    new NameValuePair("username","admin"),
//                    new NameValuePair("password","49dc52e6bf2abe5ef6e2bb5b0f1ee2d765b922ae6cc8b95d39dc06c21c848f8c"),
//                    new NameValuePair("access_token","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2NjEzMTg5MzUsImlzcyI6ImlwbG9va181Z2Nfb2FtIiwidV9pZCI6IjIwMjAwNjExMTU0NTAwIiwidV9uYW1lIjoiYWRtaW4iLCJjcmVhdGVfdGltZSI6MTY1ODcyNjkzNX0.Jq-la1-pA6KoZqfA6b0bR-XJLzmiqO90tDbyYxVvRNE")
//
//            };
//            postMethod.setRequestBody(data);
//            org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
//            int response =httpClient.executeMethod(postMethod); // 执行POST方法
//
//            InputStream inputStream = postMethod.getResponseBodyAsStream();
//            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
//            StringBuffer stringBuffer = new StringBuffer();
//
//            String str= "";
//            while((str = br.readLine() )!=null) {
//                stringBuffer.append(str);
//            }
//
//            JSONObject jsonObject = JSON.parseObject(stringBuffer.toString());
//
//            String result= jsonObject.getString("result");
//            String msg= jsonObject.getString("msg");
//            //String access_token= jsonObject.getString("access_token");
//
//            //输出获取到的结果
//            System.out.println("resulT=="+result);
//            System.out.println("msg=="+msg);
//            //System.out.println("access_token=="+access_token);
//
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }

