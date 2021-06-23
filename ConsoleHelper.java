package com.javarush.task.task26.task2613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        try {
            String string = bis.readLine();
            return string;
        } catch (IOException e) {
            // IGNORE
        }
        return null;
    }

    public static String askCurrencyCode() {
        String currencyCode;
        while (true) {
            ConsoleHelper.writeMessage("Please chose a currency code, for example USD");
            currencyCode = ConsoleHelper.readString().trim();

            if (currencyCode.length() == 3) {
                break;
            }
            ConsoleHelper.writeMessage("Please specify valid data");
        }
        return currencyCode.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) {
        String[] nums;

        while (true) {
            ConsoleHelper.writeMessage(String.format("Please specify integer denomination and integer count. For example '10 3' means 30 %s", currencyCode));
            String string = ConsoleHelper.readString();

            nums = string.split(" ");

            if (nums.length == 2) {
                    try {
                        if (Integer.parseInt(nums[0]) <= 0 || Integer.parseInt(nums[1]) <= 0)
                            throw new NumberFormatException();

                    } catch (NumberFormatException e) {
                        ConsoleHelper.writeMessage("Please specify valid data");
                        continue;
                    }
                return nums;
            }
            ConsoleHelper.writeMessage("Please specify valid data");
        }
    }
}
