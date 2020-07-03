package com.drobot.task5.service;

import com.drobot.task5.exception.InputException;

public interface DeleteService {
    String deleteNonLettersAndPutSpace(String string) throws InputException;
    String deleteWordsByLength(String string, int length, boolean isVowel) throws InputException;
}
