package main;
import GUI.RecordFrame;
import GUI.RecordPanel;

/**
 * The main class of the program.
 * 
 * 
 * @author Brandon M Martin
 * @version 3/20/2014
 *
 */
public class Main {
    
    /**
     * Main method that runs the program
     * 
     * @param args 
     */
    public static void main(String[] args) {    
        
        RecordFrame frame = new RecordFrame();
        RecordPanel panel = new RecordPanel();
        frame.add(panel);
    }

}
