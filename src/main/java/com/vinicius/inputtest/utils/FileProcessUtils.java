package com.vinicius.inputtest.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.vinicius.inputtest.exception.ProcessFileException;

/**
 * Process the files, read and write
 * 
 * @author Vinicius Antonio Gai
 *
 */
public class FileProcessUtils {

    /**
     * Read file using file name, but if file name is empty or blank, the system
     * will using default file: resources application input.txt
     * 
     * @param fileName
     * @return {@link StringBuilder} with file content
     * @throws ProcessFileException
     *             Throwing when it has problem to read files
     */
    public static String readFile(final String fileName) throws ProcessFileException {

        try {

            final String fileRead;

            if (StringUtils.isBlank(fileName)) {
                fileRead = readFileDefault();
            } else {
                fileRead = readFileCustom(fileName);
            }

            return fileRead;

        } catch (NullPointerException e) {
            throw new ProcessFileException("Any file found.", e);
        }
    }

    /**
     * Write file on disk.
     * 
     * @param fileContent
     *            Content of file
     * @param fileName
     *            File name
     * @throws ProcessFileException
     *             Throwing when it has problem to write files
     */
    public static void writeFile(final String fileContent, final String fileName) throws ProcessFileException {

        try {
            final File file = new File(fileName);
            FileUtils.writeStringToFile(file, fileContent, "UTF-8");
        } catch (IOException e) {
            throw new ProcessFileException("Problems to write file", e);
        }

    }

    /**
     * Read file from file name
     * 
     * @param fileName
     * @return File content
     * @throws ProcessFileException
     *             Throwing when it has problem to read files
     */
    private static String readFileCustom(final String fileName) throws ProcessFileException {
        try {
            final File file = FileUtils.getFile(fileName);
            return FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            throw new ProcessFileException(e);
        }
    }

    /**
     * Read defaul file input.txt
     * 
     * @return File content
     * @throws ProcessFileException
     *             Throwing when it has problem to read files
     */
    private static String readFileDefault() throws ProcessFileException {
        try {
            final File file = FileUtils
                    .getFile(FileProcessUtils.class.getClassLoader().getResource("input.txt").getFile());
            return FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            throw new ProcessFileException(e);
        }
    }

}
