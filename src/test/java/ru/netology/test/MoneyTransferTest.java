package ru.netology.test;

import com.codeborne.selenide.Configuration;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelp;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999/");
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void TransferMoneyBetweenOwnCardsFrom2To_1() {

        var loginPage = new LoginPage();
        var authInfo = DataHelp.getAuthInf();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelp.getVerificationCodeFor(authInfo);
        var dashboardCardPage = verificationPage.validVerify(verificationCode);
        String sumRep = "1000";
        val balance_1_CardBefoRep = dashboardCardPage.infoBalansCard(DataHelp.getCard_1().getNumber());
        val balance_2_CardBefoRep = dashboardCardPage.infoBalansCard(DataHelp.getCard_2().getNumber());
        val dashboardCardReplPage = dashboardCardPage.replenishCard(DataHelp.getCard_1().getNumber());
        dashboardCardReplPage.replenishCard(sumRep, DataHelp.getCard_2().getNumber());
        val balance_1_CardAfteRep = dashboardCardPage.infoBalansCard(DataHelp.getCard_1().getNumber());
        val balance_2_CardAfteRep = dashboardCardPage.infoBalansCard(DataHelp.getCard_2().getNumber());
        int sum_Rep = Integer.parseInt(sumRep);
        assertEquals(balance_1_CardBefoRep + sum_Rep, balance_1_CardAfteRep);
        assertEquals(balance_2_CardBefoRep - sum_Rep, balance_2_CardAfteRep);
    }


    @Test
    void TransferMoneyBetweenOwnCardsFrom1To_2() {

        var loginPage = new LoginPage();
        var authInfo = DataHelp.getAuthInf();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelp.getVerificationCodeFor(authInfo);
        var dashboardCardPage = verificationPage.validVerify(verificationCode);
        String sumRep = "1000";
        val balance_1_CardBefoRep = dashboardCardPage.infoBalansCard(DataHelp.getCard_1().getNumber());
        val balance_2_CardBefoRep = dashboardCardPage.infoBalansCard(DataHelp.getCard_2().getNumber());
        val dashboardCardReplPage = dashboardCardPage.replenishCard(DataHelp.getCard_2().getNumber());
        dashboardCardReplPage.replenishCard(sumRep, DataHelp.getCard_1().getNumber());
        val balance_1_CardAfteRep = dashboardCardPage.infoBalansCard(DataHelp.getCard_1().getNumber());
        val balance_2_CardAfteRep = dashboardCardPage.infoBalansCard(DataHelp.getCard_2().getNumber());
        int sum_Rep = Integer.parseInt(sumRep);
        assertEquals(balance_1_CardBefoRep - sum_Rep, balance_1_CardAfteRep);
        assertEquals(balance_2_CardBefoRep + sum_Rep, balance_2_CardAfteRep);
    }


    @Test
    void DepositAmountExceedsActualAmount() {

        var loginPage = new LoginPage();
        var authInfo = DataHelp.getAuthInf();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelp.getVerificationCodeFor(authInfo);
        var dashboardCardPage = verificationPage.validVerify(verificationCode);
        val balance_1_CardBefoRep = dashboardCardPage.infoBalansCard(DataHelp.getCard_1().getNumber());
        val balance_2_CardBefoRep = dashboardCardPage.infoBalansCard(DataHelp.getCard_2().getNumber());
        int sumRepPlus = balance_1_CardBefoRep + 1000;
        val dashboardCardReplPage = dashboardCardPage.replenishCard(DataHelp.getCard_2().getNumber());
        dashboardCardReplPage.replenishCard(String.valueOf(sumRepPlus), DataHelp.getCard_1().getNumber());
        val balance_1_CardAfteRep = dashboardCardPage.infoBalansCard(DataHelp.getCard_1().getNumber());
        val balance_2_CardAfteRep = dashboardCardPage.infoBalansCard(DataHelp.getCard_2().getNumber());
        assertEquals(balance_1_CardBefoRep, balance_1_CardAfteRep);
        assertEquals(balance_2_CardBefoRep, balance_2_CardAfteRep);
    }

}

