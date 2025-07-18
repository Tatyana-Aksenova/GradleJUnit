package tests.junit5.ui.base_tests.selenium;


import listeners.RetryListenerJunit5;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import tests.junit5.ui.base_tests.page_objects.copyruem.CalculatorPage;
import tests.junit5.ui.base_tests.page_objects.copyruem.HomePage;

@Tag("UI")
public class CopyruemCalculatorTests extends BaseTest {

    @BeforeEach
    public void openSite() {
        driver.get("https://copyruem.ru/");
    }

    @ExtendWith(RetryListenerJunit5.class)
    @ParameterizedTest
    @CsvFileSource(resources = "/orders.csv", delimiter = ';')
    public void checkTotalSumTest(String format, String color, String falc ,String printRun, String expectedResult) {

        CalculatorPage calculatorPage = new HomePage(driver)
                .clickCalculatorPage()
                .setFormat(format)
                .setFalc(falc)
                .setColor(color)
                .setPrintRun(printRun);

        final String actualPrice = calculatorPage.getTotalSum();

        Assertions.assertEquals(expectedResult, actualPrice);
    }
}
