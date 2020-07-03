package test.com.drobot.task5.service.impl;

import com.drobot.task5.exception.InputException;
import com.drobot.task5.service.DeleteService;
import com.drobot.task5.service.impl.RegexDeleteServiceImpl;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RegexDeleteServiceImplTest {

    @Test
    public void deleteNonLettersAndPutSpace_True() {
        DeleteService regexService = new RegexDeleteServiceImpl();
        String string = "Say \"hello\" and then yell, like you got a million!";

        try {
            String actual = regexService.deleteNonLettersAndPutSpace(string);
            String expected = "Say hel lo and then yel l like you got a mil lion";

            assertEquals(actual, expected);
        } catch (InputException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void deleteNonLettersAndPutSpace_False() {
        DeleteService regexService = new RegexDeleteServiceImpl();
        String string = "Say \"hello\" and then yell, like you got a million!";

        try {
            String actual = regexService.deleteNonLettersAndPutSpace(string);
            String expected = "Say hel lo and then yel l, like you got a mil lion";

            assertNotEquals(actual, expected);
        } catch (InputException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = InputException.class)
    public void deleteNonLettersAndPutSpace_Exception() throws InputException {
        DeleteService regexService = new RegexDeleteServiceImpl();

        regexService.deleteNonLettersAndPutSpace(null);
    }

    @Test
    public void deleteWordsByLength_True() {
        DeleteService regexService = new RegexDeleteServiceImpl();
        String string = "The surest way to find your dream job is to create it. " +
                "Good evening, Vladislav. What is your main focus for today?";

        try {
            String actual = regexService.deleteWordsByLength(string, 3, false);
            String expected = " surest  to find your dream  is to create it. " +
                    "Good evening, Vladislav. What is your main focus  today?";

            assertEquals(actual, expected);
        } catch (InputException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void deleteWordsByLength_False() {
        DeleteService regexService = new RegexDeleteServiceImpl();
        String string = "The surest way to find your dream job is to create it. " +
                "Good evening, Vladislav. What is your main focus for today?";

        try {
            String actual = regexService.deleteWordsByLength(string, 3, false);
            String expected = "The surest way to find your dream job is to create it. " +
                    "Good evening, Vladislav. What is  main focus for today?";

            assertNotEquals(actual, expected);
        } catch (InputException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = InputException.class)
    public void deleteWordsByLength_Exception() throws InputException {
        DeleteService regexService = new RegexDeleteServiceImpl();

        regexService.deleteWordsByLength(null, 3, false);
    }
}
