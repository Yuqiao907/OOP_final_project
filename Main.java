import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        /*
         * SwingUtilities.invokeLater is used because Swing GUI code should be created
         * on the Event Dispatch Thread. This makes the GUI more stable.
         */
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                /*
                 * One shared LiteratureManager object is created.
                 * All GUI windows use this same manager, so all records and folders
                 * are stored in one consistent system.
                 */
                LiteratureManager manager = new LiteratureManager();

                MainGUI frame = new MainGUI(manager);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
