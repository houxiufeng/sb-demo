package com.example.demo;

import com.example.demo.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

/**
 * BiPredicate 和 Predicate 函数接口一样，都是返回一个布尔类型，唯一不同的是 Predicate 接受一个参数，而 BiPredicate 可以接受两个不同类型的参数。
 * https://www.wdbyte.com/java8/java8-bipredicate/
 * 异步service: https://www.bilibili.com/video/BV1VT4y1h7Rm/?vd_source=a829e69232988cd1691360547865a27a
 */

public class Java8FunctionBiPredicateParams {

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        list.add(new User(1L,"牧羊犬", 1));
        list.add(new User(2L,"牧羊犬", 2));
        list.add(new User(3L,"哈士奇", 2));
        list.add(new User(4L,"田园犬", 3));
        // 筛选2岁的狗
        BiPredicate<String, Integer> age = (n, a) -> a == 2;
        // 筛选牧羊犬
        BiPredicate<String, Integer> name = (n, a) -> "牧羊犬".equals(n);
        // 筛选2岁的狗或者筛选牧羊犬
        BiPredicate<String, Integer> ageAndName = (n, a) -> "牧羊犬".equals(n) || a == 2;
        System.out.println(filter(list, age));
        System.out.println(filter(list, name));
        System.out.println(filter(list, ageAndName));

    }

    public static <T extends User> List<T> filter(List<T> list, BiPredicate<String, Integer> biPredicate) {
        return list.stream()
                .filter(dog -> biPredicate.test(dog.getName(), dog.getAge()))
                .collect(Collectors.toList());
    }
}
