package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
/**
 * 加载properties文件
 * **/
public class  LoadProp {
    public Map loadProp() throws Exception{
        Properties prop = new Properties();
        InputStream resourceAsStream;
        Map map = new HashMap();
        try {
            //通过相对路径找到properties文件
            resourceAsStream = LoadProp.class.getResourceAsStream("/mail.properties");
            prop.load(new InputStreamReader(resourceAsStream,"utf-8"));
            Enumeration en = prop.propertyNames();

            //把properties文件内容保存到Map
            while (en.hasMoreElements()){
                String key = en.nextElement().toString();
                String value = prop.getProperty(key);
                map.put(key,value);
            }
        } catch (IOException e) {
            throw new Exception("配置文件加载错误!");
        }
        return map;
    }
}
