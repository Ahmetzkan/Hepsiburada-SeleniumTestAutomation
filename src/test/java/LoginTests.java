import Base.BaseTest;
import Pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    LoginPage loginPage = new LoginPage();

    @Test(description = "Successful login check")
    public void LoginSuccesful() throws InterruptedException {
                loginPage.fillEmail(email)
                         .fillPassword(validPassword)
                         .clickLogin();
    }

    @Test(description = "Unsuccessful login check")
    public void LoginUnSuccesful() throws InterruptedException {
        loginPage.fillEmail(email)
                 .fillPassword(invalidPassword)
                 .clickLogin();
        loginPage.errorMessageController(errorLoginMessage);
    }

    @Test(description = "Registration control with existing e-mail")
    public void ExistingRegistration() throws InterruptedException {
        loginPage.fillRegisterMail(email);
        loginPage.warningMessageController(errorExistingRegistration);
    }

    @Test(description = "Search and add to cart control")
    public void SearchAndAddToCart() throws InterruptedException {
        loginPage.fillEmail(email)
                 .fillPassword(validPassword)
                 .clickLogin();
        loginPage.searchAndFill(searchData);
    }
}
