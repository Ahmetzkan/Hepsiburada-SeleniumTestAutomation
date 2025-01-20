package Pages;

import Base.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class LoginPage extends BaseTest {

    @Step("Email field is populated")
    public LoginPage fillEmail(String text) throws InterruptedException {
        sleep(3000);
        WebElement element = driver.findElement(By.id("txtUserName"));
        element.clear();
        element.sendKeys(text);

        return this;
    }

    @Step("The box is filled with the existing e-mail registration.")
    public LoginPage fillRegisterMail(String text) throws InterruptedException {
        sleep(3000);
        WebElement registerInput = driver.findElement(By.cssSelector("div._1c4F0n5z1dsgA8QsVtjExd.false > div._38FlHJkdsLsYZwaQFjwexe"));
        registerInput.click();

        sleep(2000);
        WebElement element = driver.findElement(By.id("txtUserName"));
        element.clear();
        element.sendKeys(text);
        WebElement registerButton = driver.findElement(By.id("btnSignUpSubmit"));
        registerButton.click();

        return this;
    }

    @Step("Password field is filled")
    public LoginPage fillPassword(String text) {
        driver.findElement(By.id("txtPassword")).sendKeys(text);

        return this;
    }

    @Step("Click on the login button")
    public LoginPage clickLogin() throws InterruptedException {
        driver.findElement(By.id("btnLogin")).click();
        sleep(3000);

        return this;
    }

    @Step("Error message check")
    public LoginPage errorMessageController(String text) {
        SoftAssert softAssert = new SoftAssert();
        String message = driver.findElement(By.xpath("//div[@type='ERROR']")).getText();

        Allure.addAttachment("Error message", message);
        screenshot();

        Assert.assertEquals(message, text);
        softAssert.assertAll();

        return this;
    }

    @Step("Warning message check")
    public LoginPage warningMessageController(String text) {
        SoftAssert softAssert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@type='WARNING']")));

        Allure.addAttachment("WARNING message", message.getText());
        screenshot();

        Assert.assertEquals(message.getText(), text);
        softAssert.assertAll();

        return this;
    }

    @Step("The search field is filled")
    public LoginPage searchAndFill(String text) throws InterruptedException {
        sleep(3000);
        WebElement searchInput = driver.findElement(By.cssSelector("[data-test-id='search-bar-input']"));

        Actions actions = new Actions(driver);
        actions.moveToElement(searchInput)
                .click()
                .pause(Duration.ofSeconds(1))
                .keyDown(Keys.CONTROL)
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.DELETE)
                .pause(Duration.ofMillis(500))
                .sendKeys(text)
                .pause(Duration.ofMillis(500))
                .sendKeys(Keys.ENTER)
                .build()
                .perform();

        sleep(3000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("i1"))).click();

        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.equals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test-id='addToCart']"))).click();
        sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button' and text()='Sepete git']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("continue_step_btn"))).click();

        return this;
    }
}
