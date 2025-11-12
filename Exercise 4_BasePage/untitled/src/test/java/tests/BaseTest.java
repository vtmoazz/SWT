package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utills.DriverFactory;

import java.time.Duration;

public abstract class BaseTest {
    protected static WebDriver driver;
    protected static WebDriverWait wait;

    @BeforeAll
    static void globalSetUp() {
        driver = DriverFactory.createDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterAll
    static void globalTearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


