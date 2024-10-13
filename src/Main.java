// Hadley Bennett - UFO Sighting Data Visualization
// Data Citation: https://www.kaggle.com/datasets/NUFORC/ufo-sightings?resource=download&select=complete.csv
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main extends JFrame
{
    private Image backgroundImage;

    public Main()
    {
        // title of GUI
        setTitle("UFO SIGHTING DATA 2005-2010");

        // set background image
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
        rawDataButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TablePanel(); // Show the table GUI
                // Add functionality to show raw data here
            }
        });

        // STATISTICS BUTTON
        statisticsButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Statistics button clicked!");
                // Add functionality to show statistics here
            }
        });

        // VISUALIZATION BUTTON
        visualizationButton.addActionListener(new ActionListener()
        {
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
        setSize(400, 400);
        // center window
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // make startPanel buttons round!
    private JButton roundedButton(String text)
    {
        JButton button = new JButton(text)
        {
            @Override
            protected void paintComponent(Graphics g) {
                //set color to white and round buttons
                g.setColor(Color.WHITE);
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g);
            }
        };

        button.setPreferredSize(new Dimension(200, 50)); // button size
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // center align
        button.setFont(button.getFont().deriveFont(Font.BOLD)); // bold font
        button.setBorderPainted(false); // - border
       // button.setFocusPainted(false); // - outline
        button.setContentAreaFilled(false); // allows customization
        return button;
    }

    public static void main(String[] args)
    {
        new Main();
    }
}
