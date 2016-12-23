package com.vinicius.inputtest.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.vinicius.inputtest.exception.ProcessFileException;
import com.vinicius.inputtest.utils.FileProcessUtils;
import com.vinicius.inputtest.vos.ProcessWordVO;

public class ProcessFileService {

    public String processFile(final String fileName) throws ProcessFileException {

        final StringBuilder result = new StringBuilder();

        final List<ProcessWordVO> processWordVOs = processWords(fileName);

        if (CollectionUtils.isNotEmpty(processWordVOs)) {
            processWordVOs.forEach(processWordVO -> {
                result.append(processWordVO.toString());
                result.append("\n");
            });
        }

        FileProcessUtils.writeFile(result.toString(), "output.txt");

        return result.toString();
    }

    public List<ProcessWordVO> processWords(final String fileName) throws ProcessFileException {
        List<ProcessWordVO> processWordVOs = null;

        final String fileContent = FileProcessUtils.readFile(fileName);

        if (StringUtils.isNoneBlank(fileContent)) {
            processWordVOs = new ArrayList<>();

            // get and process words
            final String[] words = StringUtils.split(fileContent);
            for (String word : words) {
                processWord(word, processWordVOs);
            }
        }

        return processWordVOs;
    }

    private void processWord(final String word, final List<ProcessWordVO> processWordVOs) {

        // get quantities
        final Integer wordLength = StringUtils.removePattern(word, "[^A-Za-z]").length();
        final String vowelsString = StringUtils.removePattern(word, "[^aeiouAEIOU]");
        final Integer quantityOfVowel = vowelsString.length();

        // mount vowel list
        final List<String> vowels = createVowelsList(vowelsString);

        // mount ProcessWordVO
        ProcessWordVO processWordVO = new ProcessWordVO(wordLength, vowels);
        final Integer processWordVOIndex = processWordVOs.indexOf(processWordVO);

        if (processWordVOIndex > -1) {
            processWordVO = processWordVOs.get(processWordVOIndex);
            processWordVO.addMoreQuantityVowels(quantityOfVowel);
            processWordVO.addMoreOneWord();
        } else {
            processWordVO.addMoreOneWord();
            processWordVO.addMoreQuantityVowels(quantityOfVowel);
            processWordVOs.add(processWordVO);

        }

    }

    private List<String> createVowelsList(final String vowelsString) {

        final List<String> vowels = new ArrayList<>();

        for (char character : vowelsString.toCharArray()) {
            final String charActual = String.valueOf(character);
            if (!vowels.contains(charActual)) {
                vowels.add(charActual);
            }
        }

        return vowels;

    }

}
