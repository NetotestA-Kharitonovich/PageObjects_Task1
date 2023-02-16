package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardCardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement battonReload = $("[data-test-id='action-reload']");

    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardCardReplPage replenishCard(String numberCard) {
        cards.find(text(numberCard.substring(16, 19))).$("[data-test-id='action-deposit']").click();
        return new DashboardCardReplPage();
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
    public int infoBalansCard(String numberCard) {
        val text = cards.find(text(numberCard.substring(16, 19))).getText();
        return extractBalance(text);
    }
    public DashboardCardPage() {
        heading.shouldBe(visible);
    }
}