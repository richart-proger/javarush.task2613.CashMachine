package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class DepositCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Depositing...");
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);

        while (true) {
            String[] digits = ConsoleHelper.getValidTwoDigits(currencyCode);

            try {
                int denomination = Integer.parseInt(digits[0]);
                int count = Integer.parseInt(digits[1]);

                manipulator.addAmount(denomination, count);
                ConsoleHelper.writeMessage(String.format("%d %s was deposited successfully", (denomination * count), currencyCode));
                break;
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage("Please specify valid data.");
            }
        }
    }
}
