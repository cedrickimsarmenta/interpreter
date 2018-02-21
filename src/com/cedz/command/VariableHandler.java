package com.cedz.command;

import sun.jvm.hotspot.interpreter.Interpreter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

/**
 * Created by cedric on 2/21/18.
 */
public class VariableHandler implements CommandHandler {

    private Map<String, String> variableMap = new HashMap<>();

    private static VariableHandler INSTANCE = new VariableHandler();


    public String getVariable(String name) {
        return variableMap.get(name);
    }


    public boolean hasVariable(String name) {
        return variableMap.containsKey(name);
    }
    public static VariableHandler getINSTANCE() {
        if(INSTANCE == null) {
            INSTANCE = new VariableHandler();
        }
        return INSTANCE;
    }

    private VariableHandler () {

    }

    @Override
    public void handle(String currentCommand, Iterator<String> commandIterator, Stack<CommandHandler> stack, StringBuilder output) {
        if(!commandIterator.hasNext()) {
            throw new RuntimeException("No variable name declared after:  " + getKeyWord());
        }
        String nextCommand = commandIterator.next();

        if(com.cedz.command.Interpreter.commandHandlerMap.containsKey(nextCommand)) {
            throw new RuntimeException("Cannot declare variable using reserved keyword: " + nextCommand);
        } else if(variableMap.containsKey(nextCommand)) {
            throw new RuntimeException("Variable already declared: " + nextCommand);
        } else {
            variableMap.put(nextCommand, null);
        }

    }

    @Override
    public String getKeyWord() {
        return "GUYSMERONAKONG";
    }
}
