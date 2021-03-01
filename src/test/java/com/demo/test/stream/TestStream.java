package com.demo.test.stream;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;
import java.util.stream.Collectors;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author scc
 * @date 2020/5/28 15:49
 */
public class TestStream {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestStream.class);

    private List<String> list;

    private List<User> userList, scoreList;

    private Map<String, Integer> map;

    @Before
    public void testInit() {
        list = new ArrayList<>();
        list.add("1");
        list.add("1");
        userList = new ArrayList<>();
        userList.add(new User("小白", 17));
        userList.add(new User("小白", 17));
        scoreList = new ArrayList<>();
        scoreList.add(new User("小小", 18, 10));
        scoreList.add(new User("大大", 18, 5));
        map = new HashMap<>();
        map.put("ni", 19);
        map.put("wo", 17);
    }

    @Test
    public void testFindFirst() {
        String result = list.stream()
                .findFirst().orElse(null);
        assertThat(result, notNullValue());
        LOGGER.info("result = {}", result);
    }

    @Test
    public void testMap() {
//        List<String> nameList = userList.stream()
//                .map(User::getName).collect(Collectors.toList());
        List<String> nameList = userList.stream()
                .map(o -> o.getName()).collect(Collectors.toList());
        assertThat(nameList, notNullValue());
        LOGGER.info("result = {}", JSON.toJSONString(nameList));
    }

    @Test
    public void testFilter() {
        List<User> filterUser = userList.stream()
                .filter(o -> o.getAge() > 17).collect(Collectors.toList());
        assertThat(filterUser.size(), lessThanOrEqualTo(userList.size()));
        LOGGER.info("result = {}", JSON.toJSONString(filterUser));
    }

    @Test
    public void testGroupByName() {
        Map<String, List<User>> result = userList.stream()
                .collect(Collectors.groupingBy(User::getName));
        assertThat(result, notNullValue());
        LOGGER.info("result = {}", JSON.toJSONString(result));
    }

    /**
     * 倒序
     */
    @Test
    public void testOneSort() {
        List<User> result = userList.stream()
                .sorted(Comparator.comparing(User::getAge).reversed())
                .collect(Collectors.toList());
        assertThat(result, notNullValue());
        LOGGER.info("result = {}", JSON.toJSONString(result));
    }

    /**
     * 多个字段排序
     */
    @Test
    public void testMoreSort() {
        List<User> result = scoreList.stream()
                .sorted(Comparator.comparing(User::getAge).thenComparing(User::getScore))
                .collect(Collectors.toList());
        assertThat(result, notNullValue());
        LOGGER.info("result = {}", JSON.toJSONString(result));
    }

    @Test
    public void testDistinct() {
        // 注意重写User的hashcode、equal
        List<User> result = userList.stream()
                .distinct().collect(Collectors.toList());
        assertThat(result, notNullValue());
        LOGGER.info("result = {}", result);

    }

    @Test
    public void testList2List() {
        List<User> result = userList.stream()
                .map(o -> new User(o.getName(), o.getAge(), o.getScore()))
                .collect(Collectors.toList());
    }

    @Test
    public void testMap2List() {
        List<User> result = map.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .map(o -> new User(o.getKey(), o.getValue()))
                .collect(Collectors.toList());
        assertThat(result, notNullValue());
        LOGGER.info("result = {}", result);
    }

    @Test
    public void testList2Map() {
        Map<String, List<User>> result = userList.stream()
                .collect(Collectors.groupingBy(User::getName));
        assertThat(result, notNullValue());
        LOGGER.info("result = {}", result);
    }

    @Test
    public void testList2Map2() {
        Map<String, User> result = userList.stream()
                .collect(Collectors.toMap(User::getName, a -> a, (c, d) -> c));
        assertThat(result, notNullValue());
        LOGGER.info("result = {}", result);
    }
}
