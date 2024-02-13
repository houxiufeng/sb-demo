package com.example.demo.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 动态加载属性值第二种方式.
 */
public class PropertiesUtil2 {

    private static Properties prop = new Properties();

    static {
        init("home.properties");
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(
                () -> init("home.properties"), 5, 5, TimeUnit.SECONDS);
    }

    public static String get(String key) {
        return Optional.ofNullable(prop.get(key)).map(Object::toString).orElse(null);
    }

    /**
     * 加载文件
     */
    private static void init(String configFile) {
        System.out.println("-------> init at:" + OffsetDateTime.now());
        prop.clear();
//        prop = new Properties();
        try {
            File file = new File(PropertiesUtil.class.getClassLoader().getResource(configFile).getPath());
            //重新加载文件
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                prop.load(fileInputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (NullPointerException e) {
            throw new RuntimeException("file is not found", e);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            String s = PropertiesUtil2.get("home.name");
            System.out.println(s);
            TimeUnit.SECONDS.sleep(10);
        }


    }
}
