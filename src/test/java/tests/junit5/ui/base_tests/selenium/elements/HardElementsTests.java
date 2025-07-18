package tests.junit5.ui.base_tests.selenium.elements;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class HardElementsTests {
    private WebDriver driver;
    private WebDriverWait wait;

    protected WebDriverWait getWait() {
        if (wait == null) {
            wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        }
        return wait;
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("http://85.192.34.140:8081/");
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void hoverTest() {

        WebElement elementsCard = driver.findElement(By.xpath("//div[@class='card-body']//h5[text()='Widgets']"));
        elementsCard.click();

        WebElement elementsTextBox = driver.findElement(By.xpath("//span[text()='Menu']"));
        elementsTextBox.click();

        WebElement menuItemMiddle = driver.findElement(By.xpath("//a[text()='Main Item 2']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(menuItemMiddle).build().perform();

        WebElement subSubList = driver.findElement(By.xpath("//a[text()='SUB SUB LIST »']"));
        actions.moveToElement(subSubList).build().perform();

        List<WebElement> lastElements = driver.findElements(By.xpath("//a[contains(text(),'Sub Sub Item')]"));
        Assertions.assertEquals(2, lastElements.size());
    }

    @Test
    public void checkBoxTest() {
        driver.findElement(By.xpath("//h5[contains(text(), 'Elements')]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Check Box')]")).click();

        WebElement downloadButton = driver.findElement(By.xpath("//button[@title='Toggle']"));
        downloadButton.click();

        WebElement documentsDir = driver.findElement(By.xpath("//span[contains(text(), 'Documents')]/parent::label//preceding-sibling::button"));
        documentsDir.click();

        WebElement officeDir = driver.findElement(By.xpath("//span[contains(text(), 'Office')]/parent::label//preceding-sibling::button"));
        officeDir.click();

        WebElement publicFile = driver.findElement(By.xpath("//span[contains(text(), 'Public')]"));
        publicFile.click();

        WebElement privateFile = driver.findElement(By.xpath("//span[contains(text(), 'Private')]"));
        privateFile.click();

        List<WebElement> spans = driver.findElements(By.xpath("//div[@id='result']//span[contains(@class, 'text-success')]"));
        String result = driver.findElement(By.id("result")).getText();

        assertTrue(result.contains("public"));
        assertTrue(result.contains("private"));
        Assertions.assertEquals(2, spans.size());
    }

    @Test
    public void alertTest() {
        driver.findElement(By.xpath("//h5[contains(text(), 'Alerts, Frame & Windows')]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Alerts')]")).click();

        driver.findElement(By.id("alertButton")).click();
        driver.switchTo().alert().accept();

        boolean alertIsGone = getWait().until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
        assertTrue(alertIsGone);
    }

    @Test
    public void checkBoxUseExpandAllTest() {
        int expectedCountCheckBoxes = 17;

        driver.findElement(By.xpath("//h5[contains(text(), 'Elements')]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Check Box')]")).click();

        WebElement expandAll = driver.findElement(By.xpath(" //button[@title='Expand all']"));
        expandAll.click();

        List<WebElement> visibleCheckboxes = driver.findElements(By.xpath("//span[@class='rct-title' and not(ancestor::li[contains(@class, 'rct-node-collapsed')])]"));

        Assertions.assertEquals(expectedCountCheckBoxes, visibleCheckboxes.size());
    }

    @Test
    public void alertVisibleAfter5secTest() {
        driver.findElement(By.xpath("//h5[contains(text(), 'Alerts, Frame & Windows')]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Alerts')]")).click();
        driver.findElement(By.id("timerAlertButton")).click();
        getWait().until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        boolean alertIsGone = getWait().until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
        assertTrue(alertIsGone);
    }

    @Test
    public void alertConfirmBoxTest() {
        String expectedTextResult = "You selected Cancel";

        driver.findElement(By.xpath("//h5[contains(text(), 'Alerts, Frame & Windows')]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Alerts')]")).click();
        driver.findElement(By.id("confirmButton")).click();
        driver.switchTo().alert().dismiss();

        String confirmTextResult = driver.findElement(By.id("confirmResult")).getText();

        Assertions.assertEquals(expectedTextResult, confirmTextResult);
    }

    @Test
    public void alertPromptBoxTest() {
        String expectedTextResult = "You entered Bob";

        driver.findElement(By.xpath("//h5[contains(text(), 'Alerts, Frame & Windows')]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Alerts')]")).click();
        driver.findElement(By.id("promtButton")).click();
        driver.switchTo().alert().sendKeys("Bob");
        driver.switchTo().alert().accept();

        String promptResult = driver.findElement(By.id("promptResult")).getText();

        Assertions.assertEquals(expectedTextResult, promptResult);
    }

    @Test
    public void selectReactMenuTest() {

        driver.findElement(By.xpath("//h5[contains(text(), 'Widgets')]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Select Menu')]")).click();
        WebElement selectInput = driver.findElement(By.id("withOptGroup"));
        selectInput.click();
        WebElement option2 = driver.findElement(By.xpath("//div[contains(text(), 'Group 2, option 1')]"));
        option2.click();

        WebElement selectedElement = driver.findElement(By.xpath("//div[@id='withOptGroup']//div[@class=' css-1uccc91-singleValue']"));

        Assertions.assertEquals("Group 2, option 1", selectedElement.getText());
    }
    @Test
    public void multiselectMenuTest() {

        driver.findElement(By.xpath("//h5[contains(text(), 'Widgets')]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Select Menu')]")).click();
        WebElement multiselectElement = driver.findElement(By.id("cars"));

        Select multiselect = new Select(multiselectElement);
        multiselect.selectByValue("opel");
        multiselect.selectByValue("audi");

        List<WebElement> selectedOptions = multiselect.getAllSelectedOptions();

        boolean hasOpel = selectedOptions.stream().anyMatch(o -> o.getText().equals("Opel"));
        boolean hasAudi = selectedOptions.stream().anyMatch(o -> o.getText().equals("Audi"));

        assertTrue(hasOpel && hasAudi);
    }

    @Test
    public void multiselectReactMenuTest() {

        driver.findElement(By.xpath("//h5[contains(text(), 'Widgets')]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Select Menu')]")).click();
        driver.findElement(By.xpath("//b[contains(text(), 'Multiselect drop down')]/parent::p/following-sibling::div")).click();

        selectMultiOption("Green");
        selectMultiOption("Blue");
        selectMultiOption("Black");

        List<WebElement> selectedItems = driver.findElements(By.cssSelector(".css-1rhbuit-multiValue"));

        boolean hasGreen = selectedItems.stream().anyMatch(e -> e.getText().contains("Green"));
        boolean hasBlue = selectedItems.stream().anyMatch(e -> e.getText().contains("Blue"));
        boolean hasBlack = selectedItems.stream().anyMatch(e -> e.getText().contains("Black"));

        assertTrue(hasGreen && hasBlue && hasBlack);
    }

    private void selectMultiOption(String visibleText) {

        WebElement option = driver.findElement(By.xpath("//div[contains(@class,'option') and text()='" + visibleText + "']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(option).click().perform();
    }

    @Test
    public void sliderTest() {
        int expectedSliderValue = 60;

        driver.findElement(By.xpath("//h5[contains(text(), 'Widgets')]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Slider')]")).click();
        WebElement sliderElement = driver.findElement(By.xpath("//div[@id='sliderContainer']//input"));
        int currentStiderValue = Integer.parseInt(sliderElement.getAttribute("value"));
        int valueToMove = expectedSliderValue - currentStiderValue;
        for (int i = 0; i < valueToMove; i++) {
            sliderElement.sendKeys(Keys.ARROW_RIGHT);
        }

        WebElement sliderInput = driver.findElement(By.id("sliderValue"));
        int currentValue = Integer.parseInt(sliderInput.getAttribute("value"));

        Assertions.assertEquals(expectedSliderValue, currentValue);
    }

    @Test
    public  void menuTest() {
        driver.findElement(By.xpath("//h5[contains(text(), 'Widgets')]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Menu')]")).click();

        WebElement menu1level = driver.findElement(By.linkText("Main Item 2"));
        Actions actions = new Actions(driver);
        actions.moveToElement(menu1level).build().perform();

        WebElement submenu1 = driver.findElement(By.linkText("SUB SUB LIST »"));
        actions.moveToElement(submenu1).build().perform();

        List<WebElement> subElements3Level= driver.findElements(By.xpath("//a[contains(text(), 'SUB SUB LIST »')]/parent::li/ul/li/a"));
        boolean hasItem1 = subElements3Level.stream().anyMatch(e -> e.getText().trim().equals("Sub Sub Item 1"));
        boolean hasItem2 = subElements3Level.stream().anyMatch(e -> e.getText().trim().equals("Sub Sub Item 2"));

        assertTrue(hasItem1 && hasItem2);
    }

    @Test
    public  void progressBarTest() {
        driver.findElement(By.xpath("//h5[contains(text(), 'Widgets')]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Progress Bar')]")).click();
        WebElement progressBar = driver.findElement(By.xpath("//div[@id='progressBar']/div[@role='progressbar']"));

        WebElement startStopButtonElement = driver.findElement(By.id("startStopButton"));
        startStopButtonElement.click();

        // Ждём, пока значение >= 70, и быстро кликаем Stop
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(driver1 -> {
            int currentValue = Integer.parseInt(progressBar.getAttribute("aria-valuenow"));
            if (currentValue >= 70 && currentValue < 100) {
                startStopButtonElement.click(); // остановить прогресс сразу
                return true;
            }
            return false;
        });

        // Проверим, что значение действительно >= 70
        int finalValue = Integer.parseInt(progressBar.getAttribute("aria-valuenow"));
        assertTrue(finalValue >= 70 && finalValue <= 100, "Значение прогресс-бара вне диапазона: " + finalValue);
    }

    @SneakyThrows
    @ParameterizedTest
    @ValueSource(ints = {25, 50, 75})
    public void startProgressBar(int targetProgress){
        driver.findElement(By.xpath("//h5[contains(text(), 'Widgets')]")).click();
        driver.findElement(By.xpath("//span[contains(text(), 'Progress Bar')]")).click();
        WebElement startStopButton = driver.findElement(By.id("startStopButton"));
        startStopButton.click();

        Thread.sleep(targetProgress * 100);

        startStopButton.click();

        WebElement progressBar = driver.findElement(By.className("progress-bar"));
        assertTrue(progressBar.getText().contains(targetProgress + "%"));
    }

}
