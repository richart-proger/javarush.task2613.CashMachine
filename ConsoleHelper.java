package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.common");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        try {
            String string = bis.readLine();
            if (string.toLowerCase().equals("exit")){
                throw new InterruptOperationException();
            }
            return string;
        } catch (IOException e) {
            // IGNORE
        }
        return null;
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        String currencyCode;
        while (true) {
            ConsoleHelper.writeMessage(res.getString("choose.currency.code"));
            currencyCode = ConsoleHelper.readString().trim();

            if (currencyCode.length() == 3) {
                break;
            }
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }
        return currencyCode.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String[] nums;

        while (true)    {
            ConsoleHelper.writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
            String string = ConsoleHelper.readString();

            nums = string.split(" ");

            if (nums.length == 2) {
                try {
                    if (Integer.parseInt(nums[0]) <= 0 || Integer.parseInt(nums[1]) <= 0)
                        throw new NumberFormatException();

                } catch (NumberFormatException e) {
                    ConsoleHelper.writeMessage(res.getString("invalid.data"));
                    continue;
                }
                return nums;
            }
            ConsoleHelper.writeMessage(res.getString("invalid.data"));
        }
    }

    public static Operation askOperation() throws InterruptOperationException {
        while (true) {
            ConsoleHelper.writeMessage(res.getString("choose.operation"));
            ConsoleHelper.writeMessage("\t 1 - " + res.getString("operation.INFO"));
            ConsoleHelper.writeMessage("\t 2 - " + res.getString("operation.DEPOSIT"));
            ConsoleHelper.writeMessage("\t 3 - " + res.getString("operation.WITHDRAW"));
            ConsoleHelper.writeMessage("\t 4 - " + res.getString("operation.EXIT"));
            String numberOfOperation = ConsoleHelper.readString().trim();

            if (numberOfOperation.length() == 1) {
                try {
                    int n = Integer.parseInt(numberOfOperation);
                    return Operation.getAllowableOperationByOrdinal(n);
                } catch (IllegalArgumentException e) {
                    ConsoleHelper.writeMessage(res.getString("invalid.data"));
                }
            }
        }
    }
}