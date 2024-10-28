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
            "ab: # of sightings: 126",
            "ak: # of sightings: 86",
            "al: # of sightings: 237",
            "ar: # of sightings: 216",
            "az: # of sightings: 936",
            "bc: # of sightings: 183",
            "ca: # of sightings: 3607",
            "co: # of sightings: 244",
            "ct: # of sightings: 345",
            "dc: # of sightings: 42",
            "de: # of sightings: 67",
            "fl: # of sightings: 1488",
            "ga: # of sightings: 513",
            "hi: # of sightings: 118",
            "ia: # of sightings: 247",
            "id: # of sightings: 0",
            "il: # of sightings: 1037",
            "in: # of sightings: 522",
            "ks: # of sightings: 249",
            "ky: # of sightings: 434",
            "la: # of sightings: 210",
            "ma: # of sightings: 441",
            "mb: # of sightings: 54",
            "md: # of sightings: 269",
            "me: # of sightings: 169",
            "mi: # of sightings: 726",
            "mn: # of sightings: 358",
            "mo: # of sightings: 565",
            "ms: # of sightings: 136",
            "mt: # of sightings: 157",
            "nb: # of sightings: 33",
            "nc: # of sightings: 676",
            "nd: # of sightings: 43",
            "ne: # of sightings: 146",
            "nf: # of sightings: 11",
            "nh: # of sightings: 169",
            "nj: # of sightings: 534",
            "nm: # of sightings: 285",
            "ns: # of sightings: 69",
            "nt: # of sightings: 4",
            "nv: # of sightings: 310",
            "ny: # of sightings: 1041",
            "oh: # of sightings: 696",
            "ok: # of sightings: 246",
            "on: # of sightings: 507",
            "or: # of sightings: 879",
            "pa: # of sightings: 903",
            "pe: # of sightings: 8",
            "qc: # of sightings: 85",
            "ri: # of sightings: 99",
            "sa: # of sightings: 2",
            "sc: # of sightings: 340",
            "sd: # of sightings: 63",
            "sk: # of sightings: 42",
            "tn: # of sightings: 412",
            "tx: # of sightings: 1499",
            "ut: # of sightings: 192",
            "va: # of sightings: 465",
            "vt: # of sightings: 100",
            "wa: # of sightings: 1282",
            "wi: # of sightings: 356",
            "wv: # of sightings: 154",
            "wy: # of sightings: 60",
            "yt: # of sightings: 8"
    };

    // hardcoded shape stats
    private final String[] shapeStats =
            {
            "changing: # of sightings: 732",
            "chevron: # of sightings: 285",
            "cigar: # of sightings: 682",
            "circle: # of sightings: 2315",
            "cone: # of sightings: 108",
            "cross: # of sightings: 73",
            "cylinder: # of sightings: 401",
            "diamond: # of sightings: 363",
            "disk: # of sightings: 1469",
            "egg: # of sightings: 210",
            "fireball: # of sightings: 1465",
            "flash: # of sightings: 493",
            "formation: # of sightings: 732",
            "light: # of sightings: 5690",
            "other: # of sightings: 1834",
            "oval: # of sightings: 1218",
            "rectangle: # of sightings: 444",
            "sphere: # of sightings: 1573",
            "teardrop: # of sightings: 268",
            "triangle: # of sightings: 2549",
            "unknown: # of sightings: 2111"
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
            if (state.toLowerCase().startsWith(selectedState.toLowerCase()))
            {
                stateStat = state;
                break;
            }
        }

        // update shape statistics
        for (String shape : shapeStats)
        {
            if (shape.toLowerCase().startsWith(selectedShape.toLowerCase()))
            {
                shapeStat = shape;
                break;
            }
        }

        // update labels
        stateLabel.setText(stateStat);
        shapeLabel.setText(shapeStat);
    }
}
