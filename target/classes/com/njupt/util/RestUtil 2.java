package com.njupt.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 */
public final class RestUtil {

    private static final Logger LOG = LoggerFactory.getLogger(RestUtil.class);


    private static Boolean supporteForJsCrossDomain = Boolean.FALSE;

    public static void printData(HttpServletResponse response, Object data) {
        setRespHeader(response);
        try {
            PrintWriter writer = getWriter(response);
            writer.print(JSONObject.toJSONString(data, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteDateUseDateFormat));
            flush(writer);
        } catch (IOException e) {
            LOG.error("Write response data failed.", e);
        }
    }


    private static PrintWriter getWriter(HttpServletResponse response) throws IOException {
        return response.getWriter();
    }

    private static void flush(PrintWriter writer) {
        writer.flush();
    }

    private static void setRespHeader(HttpServletResponse response) {
        if (supporteForJsCrossDomain.booleanValue()) {
            response.setHeader("Access-Control-Allow-Origin", "*");
        }

        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET");
    }

    private static void setRespHeaderForAjaxFileUpload(HttpServletResponse response) {
        if (supporteForJsCrossDomain.booleanValue()) {
            response.setHeader("Access-Control-Allow-Origin", "*");
        }

        response.setHeader("Content-Type", "text/plain;charset=UTF-8");
    }
}
