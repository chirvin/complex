package com.demo.test.guaua;

import com.alibaba.fastjson.JSONArray;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertThat;

/**
 * @author scc
 * @date 2020/5/29 17:22
 */
public class TestGuaua {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private List<Integer> integerList;

    @Before
    public void testInit() {
        integerList = Lists.newArrayList();
        integerList.add(2);
        integerList.add(null);
        integerList.add(1);

        List<String> list = Lists.newArrayList();
        // 按指定大小拆分集合
        List<List<String>> result = Lists.partition(list, 10);

        Joiner joiner = Joiner.on(",").skipNulls();
        String resultString = joiner.join("a",null,"b");
    }

    @Test
    public void testSort() {
        integerList.sort(Ordering.natural().nullsFirst());
        assertThat(integerList.size(), greaterThanOrEqualTo(1));
        logger.info("result = {}", JSONArray.toJSONString(integerList));
    }


}
