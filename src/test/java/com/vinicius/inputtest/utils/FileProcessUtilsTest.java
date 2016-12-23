package com.vinicius.inputtest.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import com.vinicius.inputtest.exception.ProcessFileException;

public class FileProcessUtilsTest extends BaseTest {

    @Test
    public void testReadFileDefault() throws ProcessFileException {
        final String fileRead = FileProcessUtils.readFile(null);
        Assert.assertNotNull(fileRead);
    }

    @Test
    public void testReadFileCustomFile() throws ProcessFileException {
        final String fileRead = FileProcessUtils
                .readFile(getClass().getClassLoader().getResource("inputTest.txt").getPath());
        Assert.assertNotNull(fileRead);
    }

    @Test(expected = ProcessFileException.class)
    public void testReadFileNotFound() throws ProcessFileException {
        FileProcessUtils.readFile("inputTest1234.txt");
    }

    @Test
    public void testWriteFile() throws ProcessFileException, IOException {

        // mock
        final String fileContent = "Junit test";
        final String fileName = FileUtils.getTempDirectoryPath().concat("junitTest.txt");

        // execution
        FileProcessUtils.writeFile(fileContent, fileName);

        // verify
        final String fileContentVerify = FileProcessUtils.readFile(fileName);
        Assert.assertEquals(fileContent, fileContentVerify);

        // delete test file
        FileUtils.forceDelete(new File(fileName));
    }
}
