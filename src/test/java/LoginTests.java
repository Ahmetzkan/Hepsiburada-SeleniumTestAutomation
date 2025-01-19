import Base.BaseTest;
import Pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    LoginPage loginPage = new LoginPage();


    @Test(description = "Successful login check")
    public void LoginSuccesful() throws InterruptedException {
        sleep(3000);
                loginPage.fillEmail(email)
                .fillPassword(validPassword)
                .clickLogin();
        sleep(3000);
    }

    @Test(description = "Unsuccessful login check")
    public void LoginUnSuccesful() throws InterruptedException {
        sleep(3000);
        loginPage.fillEmail(email)
                .fillPassword(invalidPassword)
                .clickLogin();
        Thread.sleep(3000);
       loginPage.errorMessageController(errorLoginMessage);
    }

    @Test(description = "Search and add to cart control")
    public void SearchAndAddToCart() throws InterruptedException {
        loginPage.fillEmail(email)
                .fillPassword(validPassword)
                .clickLogin();
        loginPage.searchAndFill(searchData);
        driver.findElement(By.id("id1")).click();
        driver.findElement(By.className("addToCart")).click();
        sleep(3000);
        driver.findElement(By.xpath("//button[@type='button' and text()='Sepete git']")).click();
        sleep(3000);
        driver.findElement(By.id("continue_step_btn")).click();
    }
}
