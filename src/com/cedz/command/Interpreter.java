package com.cedz.command;

import java.util.*;
import java.io.*;
public class Interpreter {
    Stack<CommandHandler>  handlers = new Stack<>();
    public static Map<String, CommandHandler> commandHandlerMap = new HashMap<>();

    static {

        Set<CommandHandler> handlers = new HashSet<>();
        handlers.add(ProgramStartHandler.getINSTANCE());
        handlers.add(VariableHandler.getINSTANCE());
        handlers.add(new PrintHandler());
        for(CommandHandler handler : handlers) {
            commandHandlerMap.put(handler.getKeyWord(), handler);
        }

    }




    public static void main(String[] args) {
        List<String> commands = parseFile(args[0]);
        new Interpreter().interpret(commands);

    }



    public void interpret(List<String> commands ) {
        Iterator<String> commandIterator = commands.iterator();

        StringBuilder output = new StringBuilder();
        CommandHandler handler = null;
        while(commandIterator.hasNext()) {
            String command = commandIterator.next();
            if(handler == null) {
                handler =  ProgramStartHandler.getINSTANCE();
            } else {
                handler = commandHandlerMap.get(command);
                if(handler==null) {
                    throw new RuntimeException("Unrecognized command: " + command);
                }
            }
            handler.handle(command, commandIterator, handlers, output);

        }

        if(!handlers.isEmpty()) {
            throw new RuntimeException("Unclosed command: "+ handlers.pop().getKeyWord());
        } else {
            System.out.println(output.toString());
        }
    }

    public static List<String> parseFile(String filename) {
        ReadFile rf = new ReadFile();

        try
        {
            String[] lines = rf.readLines(filename);
            return Arrays.asList(lines);
        }
        catch(IOException e)
        {
            // Print out the exception that occurred
            throw new RuntimeException("Error loading file: "+filename);
        }

    }

    public static class ReadFile
    {
        public String[] readLines(String filename) throws IOException
        {
            FileReader fileReader = new FileReader(filename);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            List<String> lines = new ArrayList<String>();
            String line = null;

            while ((line = bufferedReader.readLine()) != null)
            {
                if(line != null && line.trim().length() > 0) {
                    lines.addAll(Arrays.asList(line.trim().split(" ")));
                }
            }


            bufferedReader.close();

            return lines.toArray(new String[lines.size()]);
        }
    }

}