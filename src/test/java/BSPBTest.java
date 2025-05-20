import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AuthPage;
import pages.MainPage;
import pages.VerifyBySmsPage;

import java.util.List;

public class BSPBTest extends BaseTest {

    private final List<String> expectedContents = List.of(
            "Travel *6192",
            "Золотая *2224",
            "Яркая *9074",
            "Standard КХЛ *1111",
            "Детская *4123",
            "Виртуальная *0556",
            "Срок действия карты Единая карта петербуржца *1123 заканчивается 19.06.2025",
            "Платиновая *5123");

    @Test
    public void testBSPB_Successfully() {
        driver.get("https://idemo.bspb.ru");
        //Войти в систему под учетной записью demo / demo
        VerifyBySmsPage verifyPage = AuthPage.open(driver).authenticate("demo", "demo");
        //Отображается форма двухфакторной аутентификации (определелим по одному видимому элементу)
        Assert.assertTrue(verifyPage.getOptCodeText().isDisplayed());
        //Ввести в поле ввода кода подтверждения 0000. Нажать на кнопку войти
        MainPage mainPage = verifyPage.confirmByCode("0000");
        //Осуществлён вход в систему
        Assert.assertEquals(mainPage.getTitle(), "Старт - Интернет банк - Банк Санкт-Петербург", "Вход в систему не выполнен");
        //Найти на странице «Финансовая свобода»
        WebElement financeFreedom = mainPage.getFinanceFreedom();
        //На странице отображается блок «Финансовая свобода»
        Assert.assertTrue(financeFreedom.isDisplayed(), "Блок «Финансовая свобода» не отображается");
        String sum = mainPage.getFinanceFreedomSum();
        //с указанием суммы в формате “123 456 789.00 ₽”
        Assert.assertTrue(sum.matches("^\\d{1,3}(?: \\d{3})*\\.\\d{2} ₽$"), "Сумма не соответствует формату");

        List<WebElement> cards = mainPage.getCards();
        Actions actions = new Actions(driver);
        cards.forEach(card -> {
            //Навести курсор на символы карт
            actions.moveToElement(card).perform();
            //Появляется надпись:«Travel *6192»,«Золотая *2224»,...
            //надпись в доме появляется динамически следующим элементом после ссылки на карту
            String text = card.findElement(By.xpath("./following-sibling::*[contains(@class,'popover')]//*[@class='popover-content']")).getText();
            Assert.assertTrue(expectedContents.contains(text), "Карта с надписью '%s' не ожидалась".formatted(text));
        });
    }
}
