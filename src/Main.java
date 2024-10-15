// Hadley Bennett - UFO Sighting Data Visualization
// Data Citation: https://www.kaggle.com/datasets/NUFORC/ufo-sightings?resource=download&select=complete.csv
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main extends JFrame {
    private Image backgroundImage; // background image variable

    public Main() {
        // title of GUI
        setTitle("UFO SIGHTING DATA 2005-2010");

        // read and set background image
        try {
            backgroundImage = ImageIO.read(new File("alienbackground.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // create start panel
        JPanel startPanel = new JPanel() {
            // puts background image on panel
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        // set to boxlayout -- vertical align
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));
        startPanel.setOpaque(false); // transparent panel

        // create buttons for startPanel
        JButton rawDataButton = roundedButton("Data");
        JButton statisticsButton = roundedButton("Statistics");
        JButton visualizationButton = roundedButton("Visualization");

        //----------------------------------Add action listeners to the buttons-----------------------------------------

        // DATA BUTTON
        rawDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TablePanel(); //call tablePanel
            }
        });

        // STATISTICS BUTTON
        statisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Statistics button clicked!");
                // Add functionality to show statistics here
            }
        });

        // VISUALIZATION BUTTON
        visualizationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Visualization button clicked!");
                // Add functionality to show visualization here
            }
        });
        //-------------------------------------------------------------------------------------------------------------

        // manage spacing between buttons
        startPanel.add(Box.createVerticalGlue());
        startPanel.add(rawDataButton);
        startPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        startPanel.add(statisticsButton);
        startPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        startPanel.add(visualizationButton);
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

    // make startPanel buttons round!
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
        new Timer(8000, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                splash.dispose(); // close the splashScreen
                new Main(); // open main window
            }
        }).start();
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
            messageLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
            messageLabel.setForeground(Color.CYAN);
            messageLabel.setBackground(Color.BLACK);
            messageLabel.setOpaque(true);
            add(messageLabel);
            setVisible(true);
        }

        public void showSplash()
        {
            typingTimer = new Timer(120, new ActionListener()
            {
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
