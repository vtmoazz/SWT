package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    protected WebElement waitVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitPresent(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected WebElement waitClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void click(By locator) {
        waitClickable(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement element = waitVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected void pressEnter(By locator) {
        waitVisible(locator).sendKeys(Keys.ENTER);
    }

    protected void jsClick(By locator) {
        WebElement element = waitClickable(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    protected void scrollIntoView(By locator) {
        WebElement element = waitVisible(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void setValueWithJs(By locator, String value) {
        WebElement element = waitVisible(locator);
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value=arguments[1]; arguments[0].dispatchEvent(new Event('input'));", element, value);
    }

    protected void upload(By locator, String absolutePath) {
        WebElement input = waitVisible(locator);
        input.sendKeys(absolutePath);
    }

    protected void waitForPageReady() {
        wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
    }

    protected void dismissObstructions() {
        try {
            ((JavascriptExecutor) driver).executeScript(
                    "document.querySelectorAll('#fixedban, #adplus-anchor, .Advertisement, iframe[id*=\\'ad\\'], iframe[src*=\\'ads\\']').forEach(e=>e.remove());");
        } catch (Exception ignored) {
        }
        try {
            WebElement close = driver.findElement(By.id("close-fixedban"));
            if (close.isDisplayed()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", close);
            }
        } catch (Exception ignored) {
        }
    }
}
