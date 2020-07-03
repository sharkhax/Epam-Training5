package com.drobot.task5.service.impl;

import com.drobot.task5.exception.InputException;
import com.drobot.task5.service.ReplaceService;
import com.drobot.task5.utility.CharacterUtil;

import java.util.List;

import static java.lang.Character.isLetter;

public class CharReplaceServiceImpl implements ReplaceService {

    @Override
    public String replaceCharAt(String string, int index, char newChar) throws InputException {
        if (string == null) {
            throw new InputException("null");
        }

        if (index < 0 || index >= string.length()) {
            return string;
        }

        char[] charArray = string.toCharArray();
        charArray[index] = newChar;

        return new String(charArray);
    }

    @Override
    public String fixMistake(String string, String before, String after) throws InputException { //
        if (string == null) {
            throw new InputException("null");
        }

        if (before.length() != after.length()) {
            return string;
        }

        char[] charArray = string.toCharArray();
        char[] beforeArray = before.toCharArray();
        char[] afterArray = after.toCharArray();
        int startIndex = 0;
        boolean isSame = false;

        for (int i = 0; i < charArray.length - beforeArray.length; i++) {
            for (int j = 0; j < beforeArray.length; j++) {
                if (charArray[i + j] == beforeArray[j]) {
                    isSame = true;
                } else {
                    startIndex = i + 1;
                    isSame = false;
                    break;
                }
            }
            if (isSame) {
                for (int k = startIndex; k < afterArray.length + startIndex; k++) {
                    charArray[k] = afterArray[k - startIndex];
                }
            }
        }
        return new String(charArray);
    }

    @Override
    public String replaceWordsByLength(String string, int length, String newWord) throws InputException {
        if (string == null) {
            throw new InputException("null");
        }

        if (length <= 0 || length > string.length() || newWord == null) {
            return string;
        }

        char[] charArray = string.toCharArray();
        char[] newWordArray = newWord.toCharArray();
        CharacterUtil characterUtil = new CharacterUtil();
        List<Character> charList = characterUtil.valueOf(charArray);
        List<Character> newWordList = characterUtil.valueOf(newWordArray);
        int currentWordLength = 0;
        int startIndex = 0;

        for (int i = 0; i < charList.size(); i++) {
            if (isLetter(charList.get(i)) || charList.get(i) == '\'') {
                currentWordLength++;

            } else if (currentWordLength == length) {
                int k = 0;

                while (k != currentWordLength) {
                    charList.remove(startIndex);
                    k++;
                }
                charList.addAll(startIndex, newWordList);
                i = startIndex + newWordArray.length - 1;
                currentWordLength = 0;
                startIndex = i + 1;

            } else {
                currentWordLength = 0;
                startIndex = i + 1;
            }
        }

        if (currentWordLength == length) {
            int k = 0;

            while (k != currentWordLength) {
                charList.remove(startIndex);
                k++;
            }
            charList.addAll(startIndex, newWordList);
        }

        charArray = characterUtil.valueOf(charList);

        return new String(charArray);
    }
}
