package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MainPage extends BasePage {
    @FindBy(xpath = "//span[text()='Финансовая свобода']/parent::span[@id='accounts-can-spend']")
    private WebElement financeFreedom;
    @FindBy(xpath = "//span[text()='Финансовая свобода']/parent::span[@id='accounts-can-spend']//span[@class='can-spend-amount']")
    private WebElement financeFreedomSum;
    @FindBy(xpath = "//*[contains(@class,'account-cards')]//*[@data-content]")
    private List<WebElement> cards;
    private MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    public static MainPage open(WebDriver webDriver) {
        return new MainPage(webDriver);
    }

    public WebElement getFinanceFreedom() {
        return financeFreedom;
    }

    public String getFinanceFreedomSum() {
        return financeFreedomSum.getText();
    }

    public List<WebElement> getCards() {
        return cards;
    }

    public String getTitle() {
        return webDriver.getTitle();
    }
}
