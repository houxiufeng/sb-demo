package com.example.demo.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

/**
 * 动态加载属性值
 */
public class PropertiesUtil {
    private static Properties prop;
    private static Long lastModified = 0L;


    /**
     * 加载文件
     */
    private static void init(String configFile) {
        prop = new Properties();
        try {
            File file = new File(PropertiesUtil.class.getClassLoader().getResource(configFile).getPath());
            //重新加载文件
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                prop.load(fileInputStream);
                lastModified = file.lastModified();
                //执行业务操作
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (NullPointerException e) {
            throw new RuntimeException("file is not found", e);
        }

    }

    private static boolean isPropertiesModified(String configFile) {
        boolean modified = false;
        File file = new File(PropertiesUtil.class.getClassLoader().getResource(configFile).getPath());
        if (file.lastModified() > lastModified) {
            lastModified = file.lastModified();
            modified = true;
        }
        return modified;
    }

    public static String get(String configFile,String key) {

        if (prop == null || isPropertiesModified(configFile)) {
            init(configFile);
        }
        return Optional.ofNullable(prop.get(key)).map(Object::toString).orElse(null);
    }

    public static void main(String[] args) throws InterruptedException {
        //实际路径为 <project>/target/classes/home.properties
        String filePath = "home.properties";
        System.out.println("before:" + get(filePath, "home.name"));
        for (int i = 0; i < 5; i++) {
            Thread.sleep(10_000);
            System.out.println("after:" + get(filePath, "home.name"));
        }

    }
}
