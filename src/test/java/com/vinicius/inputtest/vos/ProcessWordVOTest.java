package com.vinicius.inputtest.vos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.vinicius.inputtest.utils.BaseTest;

public class ProcessWordVOTest extends BaseTest {

    private static final List<String> VOWELS = Arrays.asList("a", "o");
    private static final Integer WORD_LENGTH = 6;

    @Test
    public void testAddMoreOneWord() {

        final ProcessWordVO processWordVO = new ProcessWordVO(WORD_LENGTH, VOWELS);

        // executions
        processWordVO.addMoreOneWord();
        processWordVO.addMoreOneWord();
        processWordVO.addMoreOneWord();

        // asserts
        Assert.assertEquals(Integer.valueOf(3), processWordVO.getNumbersWord());
        Assert.assertEquals(WORD_LENGTH, processWordVO.getWordLength());
        Assert.assertEquals(VOWELS, processWordVO.getVowels());
    }

    @Test
    public void testCalculateVowelAvg() {

        final ProcessWordVO processWordVO = new ProcessWordVO(WORD_LENGTH, VOWELS);

        // executions
        processWordVO.addMoreOneWord();
        processWordVO.addMoreOneWord();

        // executions
        processWordVO.addMoreQuantityVowels(5);
        final Double avg = processWordVO.calculateVowelAvg();

        // asserts
        Assert.assertEquals(WORD_LENGTH, processWordVO.getWordLength());
        Assert.assertEquals(VOWELS, processWordVO.getVowels());
        Assert.assertEquals(Double.valueOf(2.5), avg);

    }

    @Test
    public void testCalculateVowelAvgAnyVowel() {

        final ProcessWordVO processWordVO = new ProcessWordVO(WORD_LENGTH, new ArrayList<>());

        // executions
        processWordVO.addMoreOneWord();
        processWordVO.addMoreOneWord();

        // executions
        final Double avg = processWordVO.calculateVowelAvg();

        // asserts
        Assert.assertEquals(WORD_LENGTH, processWordVO.getWordLength());
        Assert.assertEquals(Double.valueOf(0), avg);

    }
}
