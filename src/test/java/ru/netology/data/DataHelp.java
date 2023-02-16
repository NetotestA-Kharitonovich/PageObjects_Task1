package ru.netology.data;

import lombok.Value;

public class DataHelp {
    private DataHelp(){}

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInf() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class Card {
        private String number;
    }

    public static Card getCard_1() {
        return new Card("5559 0000 0000 0001");
    }

    public static Card getCard_2() {
        return new Card("5559 0000 0000 0002");
    }

}