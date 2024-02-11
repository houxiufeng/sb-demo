package com.example.demo;

import com.example.demo.service.GuavaCacheService;
import com.google.common.cache.LoadingCache;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * ref: https://www.cnblogs.com/rickiyang/p/11074159.html
 */

public class CacheTest {

    @Test
    public void testCache() throws InterruptedException {
        GuavaCacheService guavaCacheService = new GuavaCacheService();
        LoadingCache<Integer, String> cache = guavaCacheService.createCache();
        //模拟线程并发
        new Thread(() -> {
            //非线程安全的时间格式化工具
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            try {
                for (int i = 0; i < 10; i++) {
                    String value = cache.get(1);
                    System.out.println(Thread.currentThread().getName() + " " + simpleDateFormat.format(new Date()) + " " + value);
                    TimeUnit.SECONDS.sleep(3);
                }
            } catch (Exception ignored) {
            }
        }).start();

        new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            try {
                for (int i = 0; i < 10; i++) {
                    String value = cache.get(1);
                    System.out.println(Thread.currentThread().getName() + " " + simpleDateFormat.format(new Date()) + " " + value);
                    TimeUnit.SECONDS.sleep(5);
                }
            } catch (Exception ignored) {
            }
        }).start();

        TimeUnit.SECONDS.sleep(80);

        //缓存状态查看
        System.out.println(cache.stats().toString());

    }

    /**
     * 解读：Thread-1凑巧第一次加载，此时thead-0 在等待，因为缓存中没有值，它需要等有值后直接窃取果实。
     *      Thread-1 加载数据结束后，有值了，大家都来直接用。
     *      等到refreshAfterWrite时间到了，此时刚好又到thread-1获取值，它不得不再次去加载数据。但是此时Thread-0 还是继续获取旧的值。
     *      Thread-1 加载数据结束后，值更新了，此时Thread-0 直接获取新的值，它又不劳而获的直接获取了新的值。
     *      如此往复，直到结束。
     * Thread-1 加载数据开始
     * Thread-1 加载数据结束
     * Thread-0 13:13:01 value:9759
     * Thread-1 13:13:01 value:9759
     * Thread-0 13:13:04 value:9759
     * Thread-1 13:13:06 value:9759
     * Thread-0 13:13:07 value:9759
     * Thread-0 13:13:10 value:9759
     * Thread-1 13:13:11 value:9759
     * Thread-0 13:13:13 value:9759
     * Thread-1 加载数据开始
     * Thread-0 13:13:16 value:9759
     * Thread-0 13:13:19 value:9759
     * Thread-0 13:13:22 value:9759
     * Thread-1 加载数据结束
     * 1 value:9759 被移除,原因:REPLACED
     * Thread-1 13:13:24 value:520
     * Thread-0 13:13:25 value:520
     * Thread-0 13:13:28 value:520
     * Thread-1 13:13:29 value:520
     * Thread-1 13:13:34 value:520
     * Thread-1 加载数据开始
     * Thread-1 加载数据结束
     * 1 value:520 被移除,原因:REPLACED
     * Thread-1 13:13:47 value:2300
     * Thread-1 13:13:52 value:2300
     * Thread-1 13:13:57 value:2300
     * Thread-1 加载数据开始
     * Thread-1 加载数据结束
     * 1 value:2300 被移除,原因:REPLACED
     * Thread-1 13:14:10 value:6411
     * CacheStats{hitCount=18, missCount=2, loadSuccessCount=4, loadExceptionCount=0, totalLoadTime=32038461561, evictionCount=0}
     */
}
