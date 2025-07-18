package tests.junit5.ui.base_tests.selenium;

import listeners.RetryListenerJunit5;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import tests.junit5.ui.base_tests.page_objects.unitickets.UtSearchPage;
import tests.junit5.ui.base_tests.page_objects.unitickets.UtMainPage;

import java.util.List;

@Tag("UI")
@ExtendWith(RetryListenerJunit5.class)
public class UtFilterTest extends BaseTest {
    @BeforeEach
    public void openSite() {
        driver.get("https://uniticket.ru/");
    }

    @Test
    public void filterTest() {
        int expectedDayForward = 20;
        int expectedDayBack = 27;

        UtSearchPage searchPage = new UtMainPage(driver)
                .setCityFrom("Казань")
                .setCityTo("Дубай")
                .setDayForward(expectedDayForward)
                .setDayBack(expectedDayBack)
                .search();

        int actualDayForward = searchPage.getMainDayForward();
        int actualDayBack = searchPage.getMainDayBack();
        Assertions.assertEquals(expectedDayForward, actualDayForward);
        Assertions.assertEquals(expectedDayBack, actualDayBack);

        List<Integer> daysForward = searchPage.getDaysForward();
        List<Integer> daysBack = searchPage.getDaysBack();

        boolean isAllDaysForwardOk = daysForward.stream().allMatch(x->x.equals(expectedDayForward));
        boolean isAllDaysBackOk = daysBack.stream().allMatch(x->x.equals(expectedDayBack));
        
        Assertions.assertTrue(isAllDaysForwardOk);
        Assertions.assertTrue(isAllDaysBackOk);
    }
}
