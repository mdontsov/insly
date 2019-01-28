package base;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public abstract class BasePage extends DriverFactory {

    public WebDriverWait webDriverWait(long timeOutInSeconds) {
        return new WebDriverWait(driver(), timeOutInSeconds);
    }

    public FluentWait<WebDriver> fluentWait() {
        return new FluentWait<>(driver())
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotVisibleException.class)
                .ignoring(ElementNotInteractableException.class)
                .ignoring(ElementNotSelectableException.class);
    }

    public void click(Object object) throws NoSuchElementException, InvalidArgumentException {
        if (object instanceof WebElement) {
            ((WebElement) object).click();
        } else {
            List<WebElement> elements = (List<WebElement>) object;
            for (WebElement element : elements) {
                element.click();
            }
        }
    }

    public void clickAny(List<WebElement> elements, int times) {
        fluentWait().until(ExpectedConditions.visibilityOfAllElements(elements));
        Random r = new Random();
        while (times > 0) {
            --times;
            WebElement element = elements.get(r.nextInt(elements.size()));
            scrollToElement(element);
            webDriverWait(10).until(ExpectedConditions.elementToBeClickable(element));
            click(element);
        }
    }

    public boolean isClickable(Object object) throws NoSuchElementException {
        if (object instanceof WebElement) {
            webDriverWait(10).until(ExpectedConditions.elementToBeClickable((WebElement) object));
            return true;
        } else if (object instanceof List<?>) {
            List<WebElement> elements = (List<WebElement>) object;
            elements.forEach(element -> webDriverWait(10).until(ExpectedConditions.elementToBeClickable(element)));
            return true;
        } else {
            return false;
        }
    }

    public void quit() {
        driver().quit();
    }

    public void openUrl(String url) {
        driver().get(url);
    }

    public void displayAlert(String alertMessage) {
        ((JavascriptExecutor) driver()).executeScript("(alert( ' " + alertMessage + " '))");
    }

    public void scrollToElement(WebElement element) {
        Actions actions = new Actions(driver());
        fluentWait().until(ExpectedConditions.visibilityOf(element));
        actions.moveToElement(element);
        actions.perform();
    }

    public void input(WebElement element, Object data) {
        fluentWait().until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(String.valueOf(data));
    }

    public void waitUntilPageLoaded() {
        webDriverWait(60).until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }

    public String getValue(WebElement element) {
        return element.getText();
    }

    public String getCurrentUrl() {
        return driver().getCurrentUrl();
    }

    public void selectValue(WebElement element, String value) {
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(value);
    }

    public String randomWord() {
        return RandomStringUtils.randomAlphabetic(5).toLowerCase();
    }
}
