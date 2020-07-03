package com.drobot.task5.service.impl;

import com.drobot.task5.exception.InputException;
import com.drobot.task5.service.ReplaceService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexReplaceServiceImpl implements ReplaceService {

    private static final String REGEX_FOR_REPLACE_PART1 = "\\b[a-zA-Z']{";
    private static final String REGEX_FOR_REPLACE_PART2 = "}\\b";

    @Override
    public String replaceCharAt(String string, int index, char newChar) throws InputException {
        if (string == null) {
            throw new InputException("null");
        }

        if (index < 0 || index >= string.length()) {
            return string;
        }

        char buffer = string.charAt(index);
        String regex = String.valueOf(buffer);

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        String result = matcher.replaceAll(String.valueOf(newChar));

        return result;
    }

    @Override
    public String fixMistake(String string, String before, String after) throws InputException {
        if (string == null) {
            throw new InputException("null");
        }

        if (before.length() != after.length()) {
            return string;
        }

        Pattern pattern = Pattern.compile(before);
        Matcher matcher = pattern.matcher(string);
        String result = matcher.replaceAll(after);

        return result;
    }

    @Override
    public String replaceWordsByLength(String string, int length, String newWord) throws InputException {
        if (string == null) {
            throw new InputException("null");
        }

        if (length <= 0 || length > string.length() || newWord == null) {
            return string;
        }

        String regex = REGEX_FOR_REPLACE_PART1 + length + REGEX_FOR_REPLACE_PART2;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        String result = matcher.replaceAll(newWord);

        return result;
    }
}
