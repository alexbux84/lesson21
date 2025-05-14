package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VerifyBySmsPage extends BasePage{
    @FindBy(id = "otp-code")
    private WebElement otpCodeField;
    @FindBy(id = "login-otp-button")
    private WebElement confirmButton;
    @FindBy(id = "otp-code-text")
    private WebElement optCodeText;

    private VerifyBySmsPage(WebDriver webDriver) {
        super(webDriver);
    }

    public static VerifyBySmsPage open(WebDriver driver) {
        return new VerifyBySmsPage(driver);
    }

    public MainPage confirmByCode(String code) {
        otpCodeField.sendKeys(code);
        confirmButton.submit();
        return MainPage.open(webDriver);
    }

    public WebElement getOptCodeText() {
        return optCodeText;
    }
}
