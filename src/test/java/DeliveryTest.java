import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
// УБЕРИ ЭТИ ИМПОРТЫ:
// import main.DataGenerator;
// import main.UserInfo;
import java.time.Duration;

public class DeliveryTest {

    @BeforeAll
    static void setupAll() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
        );
    }

    @BeforeEach
    void setup() {
        Selenide.open("http://localhost:9999");
    }

    @Test
    void shouldSuccessfulPlanAndReplanMeeting() {
        UserInfo user = DataGenerator.generateUser();
        String firstDate = DataGenerator.generateDate(4);

        Selenide.$("[data-test-id=city] input").setValue(user.getCity());
        Selenide.$("[data-test-id=date] input")
                .sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        Selenide.$("[data-test-id=date] input").setValue(firstDate);
        Selenide.$("[data-test-id=name] input").setValue(user.getName());
        Selenide.$("[data-test-id=phone] input").setValue(user.getPhone());
        Selenide.$("[data-test-id=agreement]").click();
        Selenide.$(".button").click();

        Selenide.$("[data-test-id=success-notification] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + firstDate),
                        Duration.ofSeconds(15));
    }
}