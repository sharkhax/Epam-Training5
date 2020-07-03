package com.drobot.task5.utility;

import com.drobot.task5.enumeration.Alphabet;

import java.util.ArrayList;
import java.util.List;

public class CharacterUtil {

    public char[] valueOf(List<Character> charList) {
        char[] charArray = new char[charList.size()];

        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = charList.get(i);
        }

        return charArray;
    }

    public List<Character> valueOf(char[] charArray) {
        List<Character> charList = new ArrayList<>();

        for (char c : charArray) {
            charList.add(c);
        }

        return charList;
    }

    public boolean isVowel(char c) {
        String s = String.valueOf(c).toUpperCase();
        Alphabet alphabet = Alphabet.valueOf(s);

        return alphabet.isVowel();
    }
}
