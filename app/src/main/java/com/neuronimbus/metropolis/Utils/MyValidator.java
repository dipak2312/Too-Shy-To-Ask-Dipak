package com.neuronimbus.metropolis.Utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyValidator {
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    public static boolean isValidName( String target) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^[a-zA-Z ]+$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(target);

        return matcher.matches();

    }
    public static boolean isValidAlphaNumber( String target) {

        String pattern = "^[a-zA-Z0-9 ]+$";
        return target.matches(pattern);

    }
}
