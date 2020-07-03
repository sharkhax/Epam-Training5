package com.drobot.task5.service.impl;

import com.drobot.task5.exception.InputException;
import com.drobot.task5.service.DeleteService;
import com.drobot.task5.utility.CharacterUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDeleteServiceImpl implements DeleteService {

    private static final String REGEX_NON_LETTERS_AND_NON_SPACES = "[^a-zA-Z ]";
    private static final String REGEX_FOR_PUTTING_SPACES = "(a){2}|(b){2}|(c){2}|(d){2}|(e){2}|(f){2}|" +
            "(g){2}|(h){2}|(i){2}|(j){2}|(k){2}|(l){2}|(m){2}|(n){2}|(o){2}|(p){2}|(q){2}|(r){2}|" +
            "(s){2}|(t){2}|(u){2}|(v){2}|(w){2}|(x){2}|(y){2}|(z){2}" +
            "(A){2}|(B){2}|(C){2}|(D){2}|(E){2}|(F){2}|" +
            "(G){2}|(H){2}|(I){2}|(J){2}|(K){2}|(L){2}|(M){2}|(N){2}|(O){2}|(P){2}|(Q){2}|(R){2}|" +
            "(S){2}|(T){2}|(U){2}|(V){2}|(W){2}|(X){2}|(Y){2}|(Z){2}";
    private static final String REGEX_FOR_REMOVING_BY_LENGTH_PART1 = "\\b[a-zA-Z']{";
    private static final String REGEX_FOR_REMOVING_BY_LENGTH_PART2 = "}\\b";

    @Override
    public String deleteNonLettersAndPutSpace(String string) throws InputException {
        if (string == null) {
            throw new InputException("null");
        }

        String result = removeNonLettersAndNonSpaces(string);
        result = putSpacesBetweenSameLetters(result);

        return result;
    }

    @Override
    public String deleteWordsByLength(String string, int length, boolean isVowel) throws InputException {
        if (string == null) {
            throw new InputException("null");
        }

        if (length <= 0 || length > string.length()) {
            return string;
        }

        StringBuilder sb = new StringBuilder(string);
        String regex = REGEX_FOR_REMOVING_BY_LENGTH_PART1 + length
                + REGEX_FOR_REMOVING_BY_LENGTH_PART2;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sb);
        CharacterUtil charUtil = new CharacterUtil();
        int index = 0;

        while (matcher.find(index)) {
            char fistLetter = sb.charAt(matcher.start());

            if (charUtil.isVowel(fistLetter) == isVowel) {
                sb.delete(matcher.start(), matcher.end());
            }
            index = matcher.start() + 1;
        }

        return new String(sb);
    }

    private String removeNonLettersAndNonSpaces(String string) {
        Pattern pattern = Pattern.compile(REGEX_NON_LETTERS_AND_NON_SPACES);
        Matcher matcher = pattern.matcher(string);
        String result = matcher.replaceAll("");

        return result;
    }

    private String putSpacesBetweenSameLetters(String string) {
        StringBuilder sb = new StringBuilder(string);
        Pattern pattern = Pattern.compile(REGEX_FOR_PUTTING_SPACES);
        Matcher matcher = pattern.matcher(sb);
        int index = 0;

        while (matcher.find(index)) {
            sb.insert(matcher.end() - 1, " ");
            index = matcher.start();
        }

        return new String(sb);
    }
}
