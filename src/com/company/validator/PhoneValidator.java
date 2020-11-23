package com.company.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^(\\+375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$";

    public PhoneValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    public boolean validate(final String hex) {
        matcher = pattern.matcher(hex);

        return matcher.matches();
    }

}