package com.example.demo;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class PayUtil {
    private static final List<String> STATE_ARR = Arrays.asList("created", "failed", "approved");

    public static String getTime() {
        return DateTimeFormatter.ISO_INSTANT.format(Instant.now());
    }

    public static String generateId() {
        return new BigInteger(130, new SecureRandom()).toString(32);
    }

    public static String generateState() {
        return STATE_ARR.get((int) (Math.random() * 3));
    }
}
