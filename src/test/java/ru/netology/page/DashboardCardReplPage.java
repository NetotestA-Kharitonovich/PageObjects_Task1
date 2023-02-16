package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelp;

import static com.codeborne.selenide.Selenide.$;

public class DashboardCardReplPage {
    private SelenideElement sum = $("[data-test-id=amount]").$("[class=input__control]");
    private SelenideElement fromWhich = $("[data-test-id=from]").$("[class=input__control]");
    private SelenideElement battonReplenish = $("[data-test-id=action-transfer");

    public DashboardCardPage replenishCard(String sumRep, String fromRep) {
        sum.setValue(sumRep);
        fromWhich.setValue(fromRep);
        battonReplenish.click();
        return new DashboardCardPage();
    }

}
