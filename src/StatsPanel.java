import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel
{
    private JLabel stateLabel;
    private JLabel shapeLabel;
    private JLabel yearLabel;

    // initial stats
    private final String defaultStateStat = "State with most sightings: CA";
    private final String defaultShapeStat = "Most common shape: light";
    private final String defaultYearStat = "Year with most sightings: 2008";

    // hardcoded state stats
    private final String[] stateStats =
            {
            "ab: # of sightings: 0",
            "ak: # of sightings: 0",
            "al: # of sightings: 0",
            "ar: # of sightings: 0",
            "az: # of sightings: 0",
            "bc: # of sightings: 0",
            "ca: # of sightings: 0",
            "co: # of sightings: 0",
            "ct: # of sightings: 0",
            "dc: # of sightings: 0",
            "de: # of sightings: 0",
            "fl: # of sightings: 0",
            "ga: # of sightings: 0",
            "hi: # of sightings: 0",
            "ia: # of sightings: 0",
            "id: # of sightings: 0",
            "il: # of sightings: 0",
            "in: # of sightings: 0",
            "ks: # of sightings: 0",
            "ky: # of sightings: 0",
            "la: # of sightings: 0",
            "ma: # of sightings: 0",
            "mb: # of sightings: 0",
            "md: # of sightings: 0",
            "me: # of sightings: 0",
            "mi: # of sightings: 0",
            "mn: # of sightings: 0",
            "mo: # of sightings: 0",
            "ms: # of sightings: 0",
            "mt: # of sightings: 0",
            "nb: # of sightings: 0",
            "nc: # of sightings: 0",
            "nd: # of sightings: 0",
            "ne: # of sightings: 0",
            "nf: # of sightings: 0",
            "nh: # of sightings: 0",
            "nj: # of sightings: 0",
            "nm: # of sightings: 0",
            "ns: # of sightings: 0",
            "nt: # of sightings: 0",
            "nv: # of sightings: 0",
            "ny: # of sightings: 0",
            "oh: # of sightings: 0",
            "ok: # of sightings: 0",
            "on: # of sightings: 0",
            "or: # of sightings: 0",
            "pa: # of sightings: 0",
            "pe: # of sightings: 0",
            "qc: # of sightings: 0",
            "ri: # of sightings: 0",
            "sa: # of sightings: 0",
            "sc: # of sightings: 0",
            "sd: # of sightings: 0",
            "sk: # of sightings: 0",
            "tn: # of sightings: 0",
            "tx: # of sightings: 0",
            "ut: # of sightings: 0",
            "va: # of sightings: 0",
            "vt: # of sightings: 0",
            "wa: # of sightings: 0",
            "wi: # of sightings: 0",
            "wv: # of sightings: 0",
            "wy: # of sightings: 0",
            "yt: # of sightings: 0"
    };

    // hardcoded shape stats
    private final String[] shapeStats =
            {
            "changing: # of sightings: 0",
            "chevron: # of sightings: 0",
            "cigar: # of sightings: 0",
            "circle: # of sightings: 0",
            "cone: # of sightings: 0",
            "cross: # of sightings: 0",
            "cylinder: # of sightings: 0",
            "diamond: # of sightings: 0",
            "disk: # of sightings: 0",
            "egg: # of sightings: 0",
            "fireball: # of sightings: 0",
            "flash: # of sightings: 0",
            "formation: # of sightings: 0",
            "light: # of sightings: 0",
            "other: # of sightings: 0",
            "oval: # of sightings: 0",
            "rectangle: # of sightings: 0",
            "sphere: # of sightings: 0",
            "teardrop: # of sightings: 0",
            "triangle: # of sightings: 0",
            "unknown: # of sightings: 0"
    };

    public StatsPanel()
    {
        // customize panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.BLACK); // Background color to match your preference

        // create labels
        stateLabel = new JLabel(defaultStateStat);
        shapeLabel = new JLabel(defaultShapeStat);
        yearLabel = new JLabel(defaultYearStat);

        // customize labels
        customizeLabel(stateLabel);
        customizeLabel(shapeLabel);
        customizeLabel(yearLabel);

        // add labels to panel
        add(Box.createVerticalGlue());
        add(stateLabel);
        add(Box.createRigidArea(new Dimension(0, 10))); // spacing between labels
        add(shapeLabel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(yearLabel);
        add(Box.createVerticalGlue());
    }

    // label customization
    private void customizeLabel(JLabel label)
    {
        label.setForeground(Color.CYAN);
        label.setFont(new Font("Monospaced", Font.BOLD, 18));
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // center
    }

    // func to update stats based on selected filters
    public void updateStats(String selectedState, String selectedShape)
    {
        String stateStat = defaultStateStat;
        String shapeStat = defaultShapeStat;

        // update state statistics
        for (String state : stateStats)
        {
            if (state.toLowerCase().startsWith(selectedState.toLowerCase())) {
                stateStat = state;
                break;
            }
        }

        // update shape statistics
        for (String shape : shapeStats)
        {
            if (shape.toLowerCase().startsWith(selectedShape.toLowerCase())) {
                shapeStat = shape;
                break;
            }
        }

        // Update labels
        stateLabel.setText(stateStat);
        shapeLabel.setText(shapeStat);
    }
}
