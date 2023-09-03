package com.cy.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 */
public class ValidatorUtils {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!?<>,.:;{}]).*$";

    public static boolean validate(String str, int flag) {
        String regex;
        if(flag == 1){
            regex = EMAIL_REGEX;
        }
        else{
            regex = PASSWORD_REGEX;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }


    public static void main(String[] args) {
        String[] emails = {"1310516892@q16.com", "invalid_email", "another@example"};
        String[] passwords = {"!20030903Ab", "123567890", "anotherKexampl", ".1bbbbbbb",".KKKKKKKK"};

        for (String email : emails) {
            boolean isValid = validate(email, 1);
            System.out.println(email + " is " + (isValid ? "valid" : "invalid"));
        }

        for (String password : passwords) {
            boolean isValid = validate(password, 2);
            System.out.println(password + " is " + (isValid ? "valid" : "invalid"));
        }
    }
}