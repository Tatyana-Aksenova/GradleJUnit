package tests.junit5.ui.base_tests.page_objects.copyruem;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import tests.junit5.ui.base_tests.selenium.BasePage;

public class CalculatorPage extends BasePage {

    public CalculatorPage(WebDriver driver) {
        super(driver);
    }

    public CalculatorPage setFormat(String format) {
        Select Selectedformat = new Select(driver.findElement(By.xpath("//label[text()='Формат']/following-sibling::div//following-sibling::select")));
        Selectedformat.selectByValue(format);

        return this;
    }

    public CalculatorPage setColor(String color) {
        Select selectedColor = new Select(driver.findElement(By.xpath("//label[text()='Цветность']/following-sibling::div//following-sibling::select")));
        selectedColor.selectByValue(color);

        return this;
    }
    public CalculatorPage setFalc(String falc) {
        WebElement selectElement = driver.findElement(By.xpath("//label[text()='Фальцовка']/following-sibling::div//following-sibling::select"));
        if (selectElement.isEnabled()) {
            Select selectedFalc = new Select(selectElement);
            selectedFalc.selectByValue(falc);
        }
        return this;
    }

    public CalculatorPage setPrintRun(String count) {
        driver.findElement(By.xpath("//label[text()='Количество']/following-sibling::div//following-sibling::input")).clear();
        driver.findElement(By.xpath("//label[text()='Количество']/following-sibling::div//following-sibling::input")).sendKeys(count);

        return this;
    }

    public String getTotalSum() {
        return driver.findElement(By.xpath("//span[@data-calc-expr='format*count']")).getText();
    }
}
