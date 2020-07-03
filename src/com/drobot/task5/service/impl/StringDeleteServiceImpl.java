package com.drobot.task5.service.impl;

import com.drobot.task5.enumeration.Alphabet;
import com.drobot.task5.exception.InputException;
import com.drobot.task5.service.DeleteService;

import static java.lang.Character.isLetter;
import static java.lang.Character.isSpaceChar;

public class StringDeleteServiceImpl implements DeleteService {

    @Override
    public String deleteNonLettersAndPutSpace(String string) throws InputException {
        if (string == null) {
            throw new InputException("null");
        }

        StringBuilder sb = new StringBuilder(string);
        sb = removeNonLettersAndNonSpaces(sb);
        sb = putSpaceBetweenSameLetters(sb);

        return new String(sb);
    }

    @Override
    public String deleteWordsByLength(String string, int length, boolean isVowel) throws InputException {
        if (string == null) {
            throw new InputException("null");
        }

        if (length <= 0 || length > string.length()) {
            return string;
        }

        int startIndex = 0;
        StringBuilder sb = new StringBuilder(string);
        StringBuilder currentWord = new StringBuilder();

        for (int i = 0; i < sb.length(); i++) {
            if (isLetter(sb.charAt(i)) || sb.charAt(i) == '\'') {
                currentWord.append(sb.charAt(i));

            } else if (currentWord.length() == length
                    && isVowel == doesStartWithVowel(currentWord)) {
                sb.delete(startIndex, i);
                i = startIndex;
                startIndex = i + 1;
                currentWord = new StringBuilder();

            } else {
                startIndex = i + 1;
                currentWord = new StringBuilder();
            }
        }

        if (currentWord.length() == length && isVowel == doesStartWithVowel(currentWord)) {
            sb.delete(startIndex, currentWord.length() + startIndex);
        }

        return new String(sb);
    }

    private StringBuilder putSpaceBetweenSameLetters(StringBuilder sb) {
        int currentLength = sb.length();

        for (int i = 1; i < currentLength; i++) {
            if (isLetter(sb.charAt(i)) && sb.charAt(i) == sb.charAt(i - 1)) {
                sb.insert(i, ' ');
                currentLength = sb.length();
            }
        }
        return new StringBuilder(sb);
    }

    private StringBuilder removeNonLettersAndNonSpaces(StringBuilder sb) {
        char currentSymbol;

        for (int i = 0; i < sb.length(); i++) {
            currentSymbol = sb.charAt(i);

            if (!(isLetter(currentSymbol) || isSpaceChar(currentSymbol))) {
                sb.deleteCharAt(i);
            }
        }
        return new StringBuilder(sb);
    }

    private boolean doesStartWithVowel(StringBuilder word) {
        String firstLetter = String.valueOf(word.charAt(0));
        Alphabet alphabet = Alphabet.valueOf(firstLetter.toUpperCase());

        return alphabet.isVowel();
    }
}
