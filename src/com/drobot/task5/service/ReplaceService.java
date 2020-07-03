package com.drobot.task5.service;

import com.drobot.task5.exception.InputException;

public interface ReplaceService {
    String replaceCharAt(String string, int index, char newChar) throws InputException;
    String fixMistake(String string, String before, String after) throws InputException;
    String replaceWordsByLength(String string, int length, String newWord) throws InputException;
}
