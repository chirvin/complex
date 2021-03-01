package com.demo.test;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author scc
 * @date 2020/5/28 15:48
 */
public class TestStringUtils {

    @Test
    public void testReplaceString() {
        String arrString = "12";
        String replaceStr = StringUtils.replaceAll(arrString, "1", "2");
        assertThat(replaceStr, equalTo("22"));
        System.out.println(replaceStr);
    }

    @Test
    public void testArray2List() {
        // 数组转换为ArrayList
        String[] arr = {"1", "2"};
//        List<String> resultList = new ArrayList<>(Arrays.asList(arr));
        List<String> resultList = Stream.of(arr).collect(Collectors.toList());
        assertThat(resultList, notNullValue());
        resultList.stream().forEach(System.out::println);
    }

    @Test
    public void testList2Array() {
        // ArrayList转换为数组
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
//        String[] strings = list.toArray(new String[list.size()]);
        String[] result = list.stream().toArray(String[]::new);
        assertThat(result, notNullValue());
        for (String s : result) {
            System.out.println(s);
        }
    }

    @Test
    public void testList2String() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        String arrString = StringUtils.join(list, ",");
        assertThat(arrString, notNullValue());
        System.out.println(arrString);
    }

    @Test
    public void testString2List() {
        String arrString = "1,2";
        String[] arr = StringUtils.split(arrString, ",");
        assertThat(arr, notNullValue());
        for (String s : arr) {
            System.out.println(s);
        }
    }

    @Test
    public void testPartition() {
        List<String> list = Lists.newArrayList();
        // 按指定大小拆分集合
        List<List<String>> result = Lists.partition(list, 10);
    }
}
