package com.demo.test;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author scc
 * @date 2020/5/28 14:54
 */
public class TestFileUtils {

    private File filePath = null;
    private File file = null;

    @Before
    public void testInit() {
        filePath = new File("D:\\Downloads\\主题和字体");
        file = new File("D:\\Downloads\\主题和字体\\hello.txt");
    }

    @Test
    public void testBase() {
        String result = FileUtils.byteCountToDisplaySize(25L);
        assertThat(result, equalTo("25 bytes"));
    }

    @Test
    public void testList() {
        // 筛选出指定后缀名的文件
        String[] extensions = new String[]{"xlsx"};
        // null表示不作筛选，FileUtils.listFiles会直接返回所有的文件
        // String[] extensions = null;
        Collection<File> files = FileUtils
                .listFiles(filePath, extensions, true);
        assertThat(files, notNullValue());
    }

    @Test
    public void testEquals() throws IOException {
        boolean result = FileUtils.contentEquals(file, file);
        assertThat(result, equalTo(true));
    }

    @Test
    public void testRead() throws Exception {
        // 根据文件获取输入流
//        InputStream is = FileUtils.openInputStream(file);
//        assertThat(is, notNullValue());
//        // 根据文件获取输出流
//        OutputStream os = FileUtils.openOutputStream(file);
//        assertThat(os, notNullValue());
        // 根据文件获取字节数组
        byte[] bytes = FileUtils.readFileToByteArray(file);
        assertThat(bytes, notNullValue());
        // 按行读取文件
        List<String> lineStringList = FileUtils.readLines(file, "GBK");
        assertThat(lineStringList, notNullValue());
    }

    @Test
    public void testWrite() throws IOException{
        // 追加写
        FileUtils.writeStringToFile(file, IOUtils.LINE_SEPARATOR +
                "i am data 咿呀咔咔!", Charset.forName("GBK"), true);
    }
}
