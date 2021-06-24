package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

public class LoginCommand implements Command{
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.verifiedCards");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Logging in...");

        while (true){
            ConsoleHelper.writeMessage("Please specify your credit card number " +
                    "and pin code or type 'EXIT' for exiting");
            String number = ConsoleHelper.readString();
            String pin = ConsoleHelper.readString();

            if (number == null || (number = number.trim()).length() != 12 ||
            pin == null || (pin = pin.trim()).length() != 4){
                ConsoleHelper.writeMessage("Please specify valid credit card number" +
                        " - 12 digits, pin code - 4 digits.");
            } else {
                try {
                    if (validCreditCards.containsKey(number) && pin.equals(validCreditCards.getString(number))){
                        ConsoleHelper.writeMessage(String.format("Credit card [%s] is verified successfully!", number));
                        break;
                    } else {
                        ConsoleHelper.writeMessage(String.format("Credit card [%s] is not verified.", number));
                        ConsoleHelper.writeMessage("Please try again or type 'EXIT' for urgent exiting");
                    }
                } catch (NumberFormatException e) {
                    ConsoleHelper.writeMessage("Please specify valid credit card number - 12 digits, pin code - 4 digits.");
                }
            }
        }
    }
}
