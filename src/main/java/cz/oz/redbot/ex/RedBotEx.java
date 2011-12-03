package cz.oz.redbot.ex;

/**
 *
 * @author Ondrej Zizka
 */
public class RedBotEx extends Exception {

    public RedBotEx(String message, Throwable cause) {
        super(message, cause);
    }

    public RedBotEx(String message) {
        super(message);
    }
    
}// class

