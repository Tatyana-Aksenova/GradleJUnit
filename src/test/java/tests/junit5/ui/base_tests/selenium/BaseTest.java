package tests.junit5.ui.base_tests.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.RetryListenerJunit5;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import tests.junit5.ui.selenoid.AllureLogsExtension;

import java.time.Duration;

@ExtendWith({AllureLogsExtension.class, RetryListenerJunit5.class})
public class BaseTest {

    protected WebDriver driver;

    @BeforeAll
    public static void downloadDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterAll
    public static void saveFailed() {
        RetryListenerJunit5.saveFailedTests();
    }
}
