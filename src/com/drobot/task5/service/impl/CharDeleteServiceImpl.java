package com.drobot.task5.service.impl;

import com.drobot.task5.exception.InputException;
import com.drobot.task5.service.DeleteService;
import com.drobot.task5.utility.CharacterUtil;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Character.isLetter;
import static java.lang.Character.isSpaceChar;

public class CharDeleteServiceImpl implements DeleteService {

    @Override
    public String deleteNonLettersAndPutSpace(String string) throws InputException {
        if (string == null) {
            throw new InputException("null");
        }

        char[] charArray = string.toCharArray();
        CharacterUtil characterUtil = new CharacterUtil();
        List<Character> charList = characterUtil.valueOf(charArray);

        charList = removeNonLettersAndNonSpaces(charList);
        charList = putSpaceBetweenSameLetters(charList);
        charArray = characterUtil.valueOf(charList);

        return new String(charArray);
    }

    @Override
    public String deleteWordsByLength(String string, int length, boolean isVowel) throws InputException {
        if (string == null) {
            throw new InputException("null");
        }

        if (length <= 0 || length > string.length()) {
            return string;
        }

        char[] charArray = string.toCharArray();
        CharacterUtil characterUtil = new CharacterUtil();
        List<Character> charList = characterUtil.valueOf(charArray);
        int startIndex = 0;
        int currentLength = 0;
        char firstLetter = charArray[0];

        for (int i = 0; i < charList.size(); i++) {
            firstLetter = charList.get(startIndex);

            if (!isLetter(firstLetter)) {
                startIndex++;
                continue;
            }

            if (isLetter(charList.get(i)) || charList.get(i) == '\'') {
                currentLength++;

            } else if (currentLength == length && isVowel == characterUtil.isVowel(firstLetter)) {
                int k = 0;

                while (k != currentLength) {
                    charList.remove(startIndex);
                    k++;
                }
                currentLength = 0;
                i = startIndex;

            } else {
                currentLength = 0;
                startIndex = i + 1;
            }
        }

        if (currentLength == length && isVowel == characterUtil.isVowel(firstLetter)) {
            int k = 0;

            while (k != currentLength) {
                charList.remove(startIndex);
                k++;
            }
        }

        charArray = characterUtil.valueOf(charList);

        return new String(charArray);
    }

    private List<Character> putSpaceBetweenSameLetters(List<Character> charList) {
        for (int i = 0; i < charList.size() - 1; i++) {
            if (isLetter(charList.get(i)) && charList.get(i) == charList.get(i + 1)) {
                charList.add(i + 1, ' ');
            }
        }
        return new ArrayList<>(charList);
    }

    private List<Character> removeNonLettersAndNonSpaces(List<Character> charList) {
        for (int i = 0; i < charList.size(); i++) {
            if (!(isLetter(charList.get(i)) || isSpaceChar(charList.get(i)))) {
                charList.remove(i);
                i--;
            }
        }
        return new ArrayList<>(charList);
    }
}