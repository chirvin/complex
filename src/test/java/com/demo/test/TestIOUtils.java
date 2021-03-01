package com.demo.test;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author scc
 * @date 2020/5/28 15:36
 */
public class TestIOUtils {

    private File filePath = null;
    private File file = null;

    @Before
    public void testInit() {
//        filePath = new File("D:\\Downloads\\主题和字体");
//        file = new File("D:\\Downloads\\主题和字体\\hello.txt");
        filePath = FileUtils.getFile("D:\\Downloads\\主题和字体");
        file = FileUtils.getFile("D:\\Downloads\\主题和字体\\hello.txt");

    }

    @Test
    public void testBase() throws Exception {
        InputStream is = FileUtils.openInputStream(file);
        BufferedInputStream bi = IOUtils.buffer(is);
//        BufferedInputStream bi = IOUtils.buffer(is, 8196);

    }

    @Test
    public void testRead() throws Exception {
        InputStream is = FileUtils.openInputStream(file);
        InputStream isBu = IOUtils.toBufferedInputStream(is);
        assertThat(is, notNullValue());

        Reader reader = new FileReader(file);
        BufferedReader brTmp = IOUtils.toBufferedReader(reader);
    }
}
