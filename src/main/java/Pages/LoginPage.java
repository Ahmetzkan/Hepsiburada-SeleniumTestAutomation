package Pages;

import Base.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage extends BaseTest {

    @Step("Email field is populated")
    public LoginPage fillEmail(String text){
        WebElement element = driver.findElement(By.id("txtUserName"));
        element.clear();
        element.sendKeys(text);
        return this;
    }

    @Step("Password field is filled")
    public LoginPage fillPassword(String text){
        driver.findElement(By.id("txtPassword")).sendKeys(text);
        return this;
    }

    @Step("Click on the login button")
    public LoginPage clickLogin(){
        driver.findElement(By.id("btnLogin")).click();
        return this;
    }

    @Step("Error message check")
    public LoginPage errorMessageController(String text){
        String message = driver.findElement(By.xpath("//div[@type='ERROR']")).getText();
        Allure.addAttachment(message, "");
        screenshot();
        Assert.assertEquals(message, text);
        return this;
    }

    @Step("The search field is filled")
    public LoginPage searchAndFill(String text){
        driver.findElement(By.id("search-bar-input")).sendKeys(text, Keys.ENTER);
        return this;
    }
}
