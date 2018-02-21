package com.cedz.command;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by cedric on 2/21/18.
 */
public interface CommandHandler {

    public void handle(String currentCommand, Iterator<String> commandIterator, Stack<CommandHandler> stack, StringBuilder output);
    public String getKeyWord();
}
