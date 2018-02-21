package com.cedz.command;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by cedric on 2/21/18.
 */
public class PrintHandler implements CommandHandler {
    @Override
    public void handle(String currentCommand, Iterator<String> commandIterator, Stack<CommandHandler> stack, StringBuilder output) {
        String nextCommand = commandIterator.next();

        if(com.cedz.command.Interpreter.commandHandlerMap.containsKey(nextCommand)) {
            throw new RuntimeException("Cannot declare variable using reserved keyword: " + nextCommand);
        }else if(VariableHandler.getINSTANCE().hasVariable(nextCommand)) {
            output.append(VariableHandler.getINSTANCE().getVariable(nextCommand) + "\n");
        } else if (false) {
            //TODO: Handle printing of literal Strings and expressions
        } else {
            throw new RuntimeException("Uncrecognized variable: " + nextCommand);
        }
    }

    @Override
    public String getKeyWord() {
        return "JOLOGS";
    }
}
