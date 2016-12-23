package com.vinicius.inputtest.vos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains all information about Words
 * 
 * @author Vinicius A Gai
 *
 */
public class ProcessWordVO implements Serializable {

    private static final long serialVersionUID = 3372431709341400035L;

    private final List<String> vowels;
    private final Integer wordLength;
    private Integer numbersWord = 0;
    private Integer quantityVowels = 0;

    public ProcessWordVO(final Integer wordLength, final List<String> vowels) {
        super();
        this.vowels = new ArrayList<>(vowels);
        this.wordLength = Integer.valueOf(wordLength);
    }

    public Integer getNumbersWord() {
        return numbersWord;
    }

    public Integer getWordLength() {
        return wordLength;
    }

    public List<String> getVowels() {
        return vowels;
    }

    /**
     * Add more one word to count.
     */
    public ProcessWordVO addMoreOneWord() {
        numbersWord++;
        return this;
    }

    /**
     * Add quantities of vowels count.
     */
    public ProcessWordVO addMoreQuantityVowels(final Integer quantityVowels) {
        this.quantityVowels = this.quantityVowels + quantityVowels;
        return this;
    }

    /**
     * Calculate vowel avg
     * 
     * @return AVG
     */
    public Double calculateVowelAvg() {

        Double vowelAvg = 0.0;
        if (quantityVowels > 0) {
            final BigDecimal quantityVowels = BigDecimal.valueOf(this.quantityVowels);
            vowelAvg = quantityVowels.divide(BigDecimal.valueOf(this.numbersWord), new MathContext(2)).doubleValue();
        }

        return vowelAvg;
    }

    @Override
    public String toString() {

        final StringBuilder result = new StringBuilder();

        result.append("(");
        result.append(vowels.toString());
        result.append(", ");
        result.append(wordLength);
        result.append(") -> ");
        result.append(calculateVowelAvg());

        return result.toString();

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((vowels == null) ? 0 : vowels.hashCode());
        result = prime * result + ((wordLength == null) ? 0 : wordLength.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProcessWordVO other = (ProcessWordVO) obj;
        if (vowels == null) {
            if (other.vowels != null)
                return false;
        } else if (!vowels.equals(other.vowels))
            return false;
        if (wordLength == null) {
            if (other.wordLength != null)
                return false;
        } else if (!wordLength.equals(other.wordLength))
            return false;
        return true;
    }

}
