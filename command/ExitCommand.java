package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class ExitCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Do you really want to exit? <y|n>");
        String result = ConsoleHelper.readString();

        if (result != null && result.toLowerCase().equals("y")){
            ConsoleHelper.writeMessage("Thank you for visiting our ATM. Good luck!");
        }
        else {

        }
    }
}
