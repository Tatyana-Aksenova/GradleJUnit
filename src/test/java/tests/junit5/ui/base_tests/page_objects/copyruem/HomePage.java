package tests.junit5.ui.base_tests.page_objects.copyruem;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import tests.junit5.ui.base_tests.selenium.BasePage;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public CalculatorPage clickCalculatorPage() {
        driver.findElement(By.xpath(String.format("//a[@href='%s']", "/online-calculator"))).click();

        return new CalculatorPage(driver);
    }


}
