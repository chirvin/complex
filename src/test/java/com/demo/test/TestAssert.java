package com.demo.test;

import org.junit.Ignore;
import org.junit.Test;
import java.util.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author scc
 * @date 2020/5/28 9:33
 */

public class TestAssert {

    @Test(timeout = 1000)
    @Ignore("暂时不用")
    public void testIg() {
        int i = 10 / 2;
        assertNotNull(i);
    }

    @Test
    public void testNumber() {
        int actual = 6 / 2;
        // 判断实际值大于2且小于9
        assertThat(actual, allOf(greaterThan(2), lessThan(9)));
        // 判断实际值小于2或者大于9
//        assertThat(actual, anyOf(lessThan(2), greaterThan(9)));
        assertThat(actual, is(3));
        assertThat(actual, not(2));
    }

    @Test
    public void testDouble() {
        double d = 10.0 / 3;
        // closeTo：浮点型变量的值在3.0的0.5上下误差内
        assertThat(d, closeTo(3.0, 0.5));
        assertThat(d, greaterThan(3.3));
        assertThat(d, greaterThanOrEqualTo(3.3));
    }

    @Test
    public void testString() {
        String actual = "Magic";
        assertThat(actual, containsString("M"));
        assertThat(actual, startsWith("M"));
        assertThat(actual, endsWith("c"));
        assertThat(actual, equalTo("Magic"));
    }

    @Test
    public void testList() {
        List<String> result = new ArrayList<>();
        result.add("Magic");
        assertThat(result, notNullValue());
        assertThat(result, hasItem("Magic"));
    }

    @Test
    public void testMap() {
        Map<String, String> result = new HashMap<>();
        result.put("1", "小明");
        result.put("2", "小白");
        assertThat(result, notNullValue());
        assertThat(result, hasKey("2"));
        assertThat(result, hasEntry("1","小明"));
        assertThat(result, hasValue(anyOf(equalTo("小白"), equalTo("小黑"))));
    }
}
