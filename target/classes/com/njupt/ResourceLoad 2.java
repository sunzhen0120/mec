package com.njupt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ResourceLoad {

    private static final Logger log = LoggerFactory.getLogger(ResourceLoad.class);

    static Map<String, String> resourceMap = new ConcurrentHashMap<>();

    public static final String GET_TOKEN = "gettoken";
    private static final String SUFFIX = ".json";

    private static final List<String> RESOURCE_LIST = Arrays.asList(GET_TOKEN);

    @PostConstruct
    public void init() {
        for (String path : RESOURCE_LIST) {
            try {
                InputStream inputStream = this.getClass().getResourceAsStream("/json/" + path + SUFFIX);
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String s;
                StringBuffer sb = new StringBuffer();
                while ((s = br.readLine()) != null) {
                    sb.append(s);
                }
                resourceMap.put(path, sb.toString());
            } catch (IOException e) {
                log.error("load error", e);
            }
        }
    }

    public static String getTemplate(String key) {
        return resourceMap.get(key);
    }
}
