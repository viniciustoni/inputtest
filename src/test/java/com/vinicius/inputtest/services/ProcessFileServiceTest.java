package com.vinicius.inputtest.services;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import com.vinicius.inputtest.exception.ProcessFileException;
import com.vinicius.inputtest.utils.BaseTest;
import com.vinicius.inputtest.utils.FileProcessUtils;
import com.vinicius.inputtest.vos.ProcessWordVO;

@PrepareForTest({ FileProcessUtils.class })
public class ProcessFileServiceTest extends BaseTest {

    private static final String INPUT_TXT = "input.txt";

    private static final String FILE_CONTENT = "Platon made bamboo boats.";

    private ProcessFileService processFileService = new ProcessFileService();

    @Captor
    private ArgumentCaptor<String> stringCaptor;

    @Before
    public void init() {
        PowerMockito.mockStatic(FileProcessUtils.class);
    }

    @Test
    public void testProcessFile() throws ProcessFileException {

        // events
        when(FileProcessUtils.readFile(INPUT_TXT)).thenReturn(FILE_CONTENT);

        // execution
        final String resultContent = processFileService.processFile(INPUT_TXT);

        // verifies
        PowerMockito.verifyStatic();
        FileProcessUtils.writeFile(anyString(), eq("output.txt"));

        Assert.assertTrue(StringUtils.isNotBlank(resultContent));
    }

    @Test
    public void testProcessFileNullContent() throws ProcessFileException {

        // events
        when(FileProcessUtils.readFile(INPUT_TXT)).thenReturn(null);

        // execution
        final String resultContent = processFileService.processFile(INPUT_TXT);

        // verifies
        PowerMockito.verifyStatic();
        FileProcessUtils.writeFile(stringCaptor.capture(), eq("output.txt"));

        // asserts
        Assert.assertEquals("", stringCaptor.getValue());
        Assert.assertEquals("", resultContent);
    }

    @Test
    public void testProcessWords() throws ProcessFileException {

        // events
        when(FileProcessUtils.readFile(INPUT_TXT)).thenReturn(FILE_CONTENT);

        // execution
        final List<ProcessWordVO> processWordVOs = processFileService.processWords(INPUT_TXT);

        // verifies
        ProcessWordVO processWordVO = processWordVOs.get(0);
        Assert.assertEquals(Integer.valueOf(2), processWordVO.getNumbersWord());
        Assert.assertEquals(Integer.valueOf(6), processWordVO.getWordLength());
        Assert.assertEquals(Arrays.asList("a", "o"), processWordVO.getVowels());
    }

    @Test
    public void testProcessWordsNullContent() throws ProcessFileException {

        // events
        when(FileProcessUtils.readFile(INPUT_TXT)).thenReturn(null);

        // execution
        final List<ProcessWordVO> processWordVOs = processFileService.processWords(INPUT_TXT);

        // asserts
        Assert.assertNull(processWordVOs);
    }

}
