package test.com.drobot.task5.service.impl;

import com.drobot.task5.exception.InputException;
import com.drobot.task5.service.ReplaceService;
import com.drobot.task5.service.impl.StringReplaceServiceImpl;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StringReplaceServiceImplTest {

    @Test
    public void replaceCharAt_True() {
        ReplaceService stringService = new StringReplaceServiceImpl();
        String string = "Hello";
        int index = 4;
        char newChar = ' ';

        try {
            String actual = stringService.replaceCharAt(string, index, newChar);
            String expected = "Hell ";

            assertEquals(actual, expected);
        } catch (InputException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void replaceCharAt_False() {
        ReplaceService stringService = new StringReplaceServiceImpl();
        String string = "Hello";
        int index = 4;
        char newChar = 'y';

        try {
            String actual = stringService.replaceCharAt(string, index, newChar);
            String expected = "Hell ";

            assertNotEquals(actual, expected);
        } catch (InputException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = InputException.class)
    public void replaceCharAt_Exception() throws InputException {
        ReplaceService stringService = new StringReplaceServiceImpl();
        int index = 4;
        char newChar = 'y';

        stringService.replaceCharAt(null, index, newChar);
    }

    @Test
    public void fixMistake_True() {
        ReplaceService stringService = new StringReplaceServiceImpl();
        String string = "THE PAST";
        String before = "PA";
        String after = "PO";

        try {
            String actual = stringService.fixMistake(string, before, after);
            String expected = "THE POST";

            assertEquals(actual, expected);
        } catch (InputException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void fixMistake_False() {
        ReplaceService stringService = new StringReplaceServiceImpl();
        String string = "THE PAST";
        String before = "PA";
        String after = "PO";

        try {
            String actual = stringService.fixMistake(string, before, after);
            String expected = "THE PAST";

            assertNotEquals(actual, expected);
        } catch (InputException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = InputException.class)
    public void fixMistake_Exception() throws InputException {
        ReplaceService stringService = new StringReplaceServiceImpl();
        String before = "PA";
        String after = "PO";

        stringService.fixMistake(null, before, after);
    }

    @Test
    public void replaceWordByLength_True() {
        ReplaceService stringService = new StringReplaceServiceImpl();
        String string = "Some text, which I can't think up, so I'm just printing... Whatever";
        int length = 8;
        String newWord = "oops....";

        try {
            String actual = stringService.replaceWordsByLength(string, length, newWord);
            String expected = "Some text, which I can't think up, so I'm just oops....... oops....";

            assertEquals(actual, expected);
        } catch (InputException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void replaceWordByLength_False() {
        ReplaceService stringService = new StringReplaceServiceImpl();
        String string = "Some text, which I can't think up, so I'm just printing... Whatever";
        int length = 6;
        String newWord = "oops....";

        try {
            String actual = stringService.replaceWordsByLength(string, length, newWord);
            String expected = "Some text, oops.... I oops.... oops.... up, so I'm just printing... Whatever";

            assertNotEquals(actual, expected);
        } catch (InputException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = InputException.class)
    public void replaceWordByLength_Exception() throws InputException {
        ReplaceService stringService = new StringReplaceServiceImpl();
        int length = 6;
        String newWord = "oops....";

        stringService.replaceWordsByLength(null, length, newWord);
    }
}
