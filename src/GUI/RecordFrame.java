package GUI;

import javax.swing.JFrame;

/**
 * The frame for the program
 * 
 * 
 * @author Brandon M Martin
 *
 */
@SuppressWarnings("serial")
public class RecordFrame extends JFrame {
    
    /**
     * Constructs the frame
     */
    public RecordFrame() {
        super("RecordShop");
        initWindow();
    }
    
    /**
     * Initializes the window with height, resizible, visibility, and close operation. 
     */
    private void initWindow() {
        setResizable(false);
        setSize(350, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    

}
