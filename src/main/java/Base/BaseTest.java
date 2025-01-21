package Base;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest extends BaseLibrary {

    @BeforeMethod(description = "")
    public void openBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        driver = new ChromeDriver(options);

        driver.get(loginUrl);
        driver.manage().window().maximize();
    }

    @AfterMethod(description = "The browser is closed.")
    public void closeBrowser() {
       driver.quit();
    }
}
