package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthPage extends BasePage {

    @FindBy(id = "username")
    private WebElement loginField;
    @FindBy(name = "password")
    private WebElement passwordField;
    @FindBy(id = "login-button")
    private WebElement loginButton;
    private AuthPage(WebDriver webDriver) {
        super(webDriver);
    }
    public static AuthPage open(WebDriver driver) {
        return new AuthPage(driver);
    }

    public VerifyBySmsPage authenticate(String username, String password) {
        loginField.clear();
        loginField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.submit();
        return VerifyBySmsPage.open(webDriver);
    }
}
