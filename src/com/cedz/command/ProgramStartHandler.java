package com.cedz.command;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by cedric on 2/21/18.
 */
public class ProgramStartHandler implements CommandHandler {

    public static ProgramStartHandler INSTANCE;
    private boolean started;
    private boolean ended;

    public static ProgramStartHandler getINSTANCE() {
        if(INSTANCE == null) {
            INSTANCE = new ProgramStartHandler();
        }

        return INSTANCE;
    }

    private ProgramStartHandler () {

    }


    @Override
    public void handle(String currentCommand, Iterator<String> commandIterator, Stack<CommandHandler> commandHandlerStack, StringBuilder output) {


        if(currentCommand.equals(getKeyWord())) {
            if(!started) {
                started = true;
                commandHandlerStack.add(this);
            } else if(ended) {
                throw new RuntimeException("PROGRAM ALREADY ENDED");
            } else if (commandHandlerStack.size() > 1 || commandIterator.hasNext()) {
                throw new RuntimeException("PROGRAM TERMINATED PREMATURELY");
            } else if (commandHandlerStack.size() == 1 && commandHandlerStack.pop() == INSTANCE) {
                ended = true;
            }
        } else {
            if(!started) {
                throw new RuntimeException("PROGRAM NOT STARTED");
            }

        }
    }

    @Override
    public String getKeyWord() {
        return "GURL";
    }
}
