package DemoApp;

public enum Command {
    ADD("add"), SHOW("show"), QUIT("quit"), HELP("help"), SETSALE("setSale"), UNKNOWN("?");

    private String commandword;


    Command(String commandword)
    {
        this.commandword = commandword;
    }

    /**
     * @return returns the command as String
     */
    public String toString()
    {
        return commandword;
    }
}
