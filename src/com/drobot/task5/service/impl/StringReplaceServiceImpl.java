package com.drobot.task5.service.impl;

import com.drobot.task5.exception.InputException;
import com.drobot.task5.service.ReplaceService;

import static java.lang.Character.isLetter;

public class StringReplaceServiceImpl implements ReplaceService {

    @Override
    public String replaceCharAt(String string, int index, char newChar) throws InputException {
        if (string == null) {
            throw new InputException("null");
        }

        if (index < 0 || index >= string.length()) {
            return string;
        }

        StringBuilder sb = new StringBuilder(string);
        sb.setCharAt(index, newChar);

        return new String(sb);
    }

    @Override
    public String fixMistake(String string, String before, String after) throws InputException {
        if (string == null) {
            throw new InputException("null");
        }

        if (before.length() != after.length()) {
            return string;
        }

        for (int i = 0; i <= string.length() - before.length(); i++) {
            if (string.startsWith(before, i)) {
                StringBuilder sb = new StringBuilder(string);
                sb.replace(i, i + after.length(), after);
                string = new String(sb);
                break;
            }
        }
        return string;
    }

    @Override
    public String replaceWordsByLength(String string, int length, String newWord) throws InputException {
        if (string == null) {
            throw new InputException("null");
        }

        if (length <= 0 || length > string.length() || newWord == null) {
            return string;
        }

        int currentWordLength = 0;
        int startIndex = 0;
        StringBuilder sb = new StringBuilder(string);

        for (int i = 0; i < sb.length(); i++) {
            if (isLetter(sb.charAt(i)) || sb.charAt(i) == '\'') {
                currentWordLength++;

            } else if (currentWordLength == length) {
                sb.delete(startIndex, i);
                sb.insert(startIndex, newWord);
                i = startIndex + newWord.length() - 1;
                currentWordLength = 0;
                startIndex = i + 1;

            } else {
                currentWordLength = 0;
                startIndex = i + 1;
            }
        }

        if (currentWordLength == length) {
            sb.delete(startIndex, sb.length());
            sb.insert(startIndex, newWord);
        }

        return new String(sb);
    }
}
