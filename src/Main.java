import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main extends JFrame
{
    private Image backgroundImage; // background image variable

    public Main()
    {
        // title of GUI
        setTitle("UFO SIGHTING DATA 2005-2010");

        // read and set background image
        try
        {
            backgroundImage = ImageIO.read(new File("alienbackground.jpg"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        // create start panel
        JPanel startPanel = new JPanel()
        {
            // puts background image on panel
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                if (backgroundImage != null)
                {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        // set to boxlayout -- vertical align
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));
        startPanel.setOpaque(false); // transparent panel

        // create button for startPanel
        JButton enterDataButton = roundedButton("Enter Data");

        //----------------------------------Add action listener to the button-----------------------------------------

        // ENTER DATA BUTTON
        enterDataButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                showMainPanel(); // display all panels in one window
            }
        });
        //-------------------------------------------------------------------------------------------------------------

        // manage spacing for the button
        startPanel.add(Box.createVerticalGlue());
        startPanel.add(enterDataButton);
        startPanel.add(Box.createVerticalGlue());

        // add startPanel to frame
        add(startPanel);

        // close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set window size
        setSize(400, 600);
        // center window
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // function to show main panel with all panels and filters
    private void showMainPanel()
    {
        // create a new window (JFrame) for the main panel
        JFrame mainFrame = new JFrame("UFO Data Analysis");
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // close this window only

        // create a panel for filters (you can add filter components here)
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new FlowLayout());
        filterPanel.add(new JLabel("Filter Options:")); // add a label for filter options

        // create a container panel that will hold all subpanels (table, stats, chart)
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        // create instances of the panels
        StatsPanel statsPanel = new StatsPanel(); // instantiate the statsPanel
        CreateChartPanel chartPanel = new CreateChartPanel();
        TablePanel tablePanel = new TablePanel(statsPanel, chartPanel); // instantiate the tablePanel

        // add each panel to the content panel
        contentPanel.add(tablePanel, BorderLayout.NORTH); // tablePanel at the top
        contentPanel.add(statsPanel, BorderLayout.CENTER); // statsPanel in the center
        contentPanel.add(chartPanel, BorderLayout.SOUTH); // chartPanel at the bottom

        // add everything to the main frame
        mainFrame.add(filterPanel, BorderLayout.NORTH); // filters at the top
        mainFrame.add(contentPanel, BorderLayout.CENTER); // main content area with panels

        // set window size and location
        mainFrame.setSize(800, 600); // set the size of the window
        mainFrame.setLocationRelativeTo(null); // center window on screen
        mainFrame.setVisible(true); // make the window visible
    }

    // make startPanel button round!
    private JButton roundedButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                //set color to white and round buttons
                g.setColor(Color.WHITE);
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g);
            }
        };

        button.setPreferredSize(new Dimension(600, 50)); // button size
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // center align
        button.setFont(button.getFont().deriveFont(Font.BOLD)); // bold font
        button.setBorderPainted(false); // - border
        button.setContentAreaFilled(false); // allows customization
        return button;
    }

    public static void main(String[] args)
    {
        // instantiate and call splashScreen
        splashScreen splash = new splashScreen();
        splash.showSplash();

        // wait 7 secs before main window shows
        Timer timer = new Timer(8000, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                splash.dispose(); // close the splashScreen
                new Main(); // open main window
                ((Timer) e.getSource()).stop(); // stop timer
            }
        });
        timer.setRepeats(false); // no repeated timers
        timer.start();
    }
}

//------------------------------------ Splash Screen Implementation --------------------------------------------------
class splashScreen extends JFrame
{
    // variables
    private JLabel messageLabel;
    private Timer typingTimer;
    private String message = "Loading Data from the National UFO Reporting Center...";
    private int index = 0;

    public splashScreen()
    {
        // create window
        setUndecorated(true);
        setSize(900, 300);
        setLocationRelativeTo(null);
        messageLabel = new JLabel("", SwingConstants.CENTER);
        // customizes label
        messageLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        messageLabel.setForeground(Color.CYAN);
        messageLabel.setBackground(Color.BLACK);
        messageLabel.setOpaque(true);
        add(messageLabel);
        setVisible(true);
    }

    public void showSplash()
    {
        // sets typing speed
        typingTimer = new Timer(100, new ActionListener()
        {
            // traverse message to type
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (index < message.length())
                {
                    messageLabel.setText(messageLabel.getText() + message.charAt(index));
                    index++;
                } else
                {
                    typingTimer.stop(); // stop when full message is displayed
                }
            }
        });
        typingTimer.start();
    }
}

