import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class DeliveryTest {

    @BeforeEach
    void setup() {
        com.codeborne.selenide.Selenide.open("http://localhost:9999");
    }

    @Test
    void shouldSuccessfulPlanAndReplanMeeting() {
        DataGenerator.UserInfo user = DataGenerator.generateUser();
        String firstDate = DataGenerator.generateDate(4);
        String secondDate = DataGenerator.generateDate(7);

        // Заполняем форму
        com.codeborne.selenide.Selenide.$("[data-test-id=city] input").setValue(user.getCity());
        com.codeborne.selenide.Selenide.$("[data-test-id=date] input")
                .sendKeys(org.openqa.selenium.Keys.chord(org.openqa.selenium.Keys.SHIFT,
                                org.openqa.selenium.Keys.HOME),
                        org.openqa.selenium.Keys.BACK_SPACE);
        com.codeborne.selenide.Selenide.$("[data-test-id=date] input").setValue(firstDate);
        com.codeborne.selenide.Selenide.$("[data-test-id=name] input").setValue(user.getName());
        com.codeborne.selenide.Selenide.$("[data-test-id=phone] input").setValue(user.getPhone());
        com.codeborne.selenide.Selenide.$("[data-test-id=agreement]").click();
        com.codeborne.selenide.Selenide.$(".button").click();

        // Проверяем
        com.codeborne.selenide.Selenide.$("[data-test-id=success-notification] .notification__content")
                .shouldHave(com.codeborne.selenide.Condition.text("Встреча успешно запланирована на " + firstDate),
                        java.time.Duration.ofSeconds(15));
    }
}