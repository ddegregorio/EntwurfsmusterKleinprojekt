package DemoApp;

import java.util.Scanner;

public class Parser {
    private Scanner reader;
    private ValidCommands validCommands;

    public Parser() {
        reader = new Scanner(System.in);
        validCommands = new ValidCommands();
    }

    /**
     * parses the Line and returns a command object
     * that it could read from the line
     *
     * @return
     */
    public Command getCommand(){
        String line = reader.nextLine();

        Scanner tokenizer = new Scanner(line);

        if(tokenizer.hasNext())
        {
            return validCommands.getCommand(tokenizer.next());
        }

        return Command.UNKNOWN;
    }
}

