package test.com.drobot.task5.service.impl;

import com.drobot.task5.exception.InputException;
import com.drobot.task5.service.DeleteService;
import com.drobot.task5.service.impl.StringDeleteServiceImpl;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StringDeleteServiceImplTest {

    @Test
    public void deleteNonLettersAndPutSpace_True() {
        DeleteService stringService = new StringDeleteServiceImpl();
        String string = "Say \"hello\" and then yell, like you got a million!";

        try {
            String actual = stringService.deleteNonLettersAndPutSpace(string);
            String expected = "Say hel lo and then yel l like you got a mil lion";

            assertEquals(actual, expected);
        } catch (InputException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void deleteNonLettersAndPutSpace_False() {
        DeleteService stringService = new StringDeleteServiceImpl();
        String string = "Say \"hello\" and then yell, like you got a million!";

        try {
            String actual = stringService.deleteNonLettersAndPutSpace(string);
            String expected = "Say hel lo and then yel l, like you got a mil lion";

            assertNotEquals(actual, expected);
        } catch (InputException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = InputException.class)
    public void deleteNonLettersAndPutSpace_Exception() throws InputException {
        DeleteService stringService = new StringDeleteServiceImpl();

        stringService.deleteNonLettersAndPutSpace(null);
    }

    @Test
    public void deleteWordsByLength_True() {
        DeleteService stringService = new StringDeleteServiceImpl();
        String string = "The surest way to find your dream job is to create it. " +
                "Good evening, Vladislav. What is your main focus for today?";

        try {
            String actual = stringService.deleteWordsByLength(string, 3, false);
            String expected = " surest  to find your dream  is to create it. " +
                    "Good evening, Vladislav. What is your main focus  today?";

            assertEquals(actual, expected);
        } catch (InputException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void deleteWordsByLength_False() {
        DeleteService stringService = new StringDeleteServiceImpl();
        String string = "The surest way to find your dream job is to create it. " +
                "Good evening, Vladislav. What is your main focus for today?";

        try {
            String actual = stringService.deleteWordsByLength(string, 3, false);
            String expected = "The surest way to find your dream job is to create it. " +
                    "Good evening, Vladislav. What is  main focus for today?";

            assertNotEquals(actual, expected);
        } catch (InputException e) {
            fail(e.getMessage());
        }
    }

    @Test(expectedExceptions = InputException.class)
    public void deleteWordsByLength_Exception() throws InputException {
        DeleteService stringService = new StringDeleteServiceImpl();

        stringService.deleteWordsByLength(null, 3, false);
    }
}
