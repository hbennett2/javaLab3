// Implements tablePanel which extends displayPanel
// This class includes details display logic AS A JDIALOG (no need for another class)
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class TablePanel extends JFrame
{
    // ---------------------------------- Logic ---------------------------------------------------

    private List<String[]> data; // holds data
    private List<Integer> recordMap; // map records to their data

    public TablePanel()
    {
        // set GUI title
        setTitle("UFO Sightings Data");

        // load data from Data.java
        Data loadData = new Data("complete.csv");
        data = loadData.getRecords(); // call func from Data.java
        recordMap = new ArrayList<>(); // initialize list

        // instantiate table obj-- non-editable
        DefaultTableModel tableModel = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false; // cells non-editable
            }
        };

        // add columns -- add header field names
        tableModel.addColumn("Date/Time");
        tableModel.addColumn("State");
        tableModel.addColumn("Shape");

        // put all data in table
        for (int i = 0; i < data.size(); i++)
        {
            String[] record = data.get(i);
            tableModel.addRow(new Object[]{record[0], record[2], record[3]}); // dateTime, state, shape
            recordMap.add(i); // store initial index
        }

        // create table
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table); // add a scroll
        add(scrollPane, BorderLayout.CENTER);

        // add listener for clicking a record
        table.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (e.getClickCount() == 1) // if user clicks once
                {
                    int row = table.getSelectedRow();

                    if (row != -1)
                    {
                        showRecordDetails(recordMap.get(row)); // grabs details for row
                    }
                }
            }
        });

        setSize(600, 400); // window size
        setLocationRelativeTo(null); // centers window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // close window
        setVisible(true);
    }

    // ---------------------------------- Creates Panels and Labels ---------------------------------------------------

    private void showRecordDetails(int originalIndex)
    {
        String[] record = data.get(originalIndex); // get the initial record using the index

        // create a details panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(new Color(230, 230, 255)); // light purple background

        // add fields to panel -- bolded text
        detailsPanel.add(createLabeledComponent("City:", record[1], true));
        // if duration = 0, change it to displays "concurrent"
        detailsPanel.add(createLabeledComponent("Duration (sec):", "0".equals(record[5]) ? "concurrent" : record[5], true));

        // create/add area summary field
        JTextArea summaryArea = new JTextArea(record[6]);
        summaryArea.setLineWrap(true); // enable line wrapping
        summaryArea.setWrapStyleWord(true); // wrap on word boundaries
        summaryArea.setEditable(false); // make non-editable
        summaryArea.setBackground(new Color(230, 230, 255)); // light purple background color

        // add summaryArea to scrollPane -- fixes bug where summary txt ets cut off
        JScrollPane scrollPane = new JScrollPane(summaryArea);
        scrollPane.setPreferredSize(new Dimension(380, 100)); // adjust size to fit dialog

        detailsPanel.add(createLabeledComponent("Summary:", "", false)); // add label and bold
        detailsPanel.add(scrollPane); // add the scroll pane to the details panel

        // create detailsDialog -- JDialog good for pop up information!
        JDialog detailsDialog = new JDialog(this, "Record Details", true); // create -- set title
        detailsDialog.setContentPane(detailsPanel); // displays the detailsPanel
        detailsDialog.setSize(400, 300); // set size
        detailsDialog.setLocationRelativeTo(this); // center in main frame
        detailsDialog.setVisible(true);
    }


    // func adds label to value
    private JPanel createLabeledComponent(String label, String value, boolean bold)
    {
        JPanel panel = new JPanel(); // create panel
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(new Color(230, 230, 255)); //background light purple

        JLabel l = new JLabel(label); // label obj
        panel.add(l); // adds label

        JLabel valueLabel = new JLabel(value); // value obj
        panel.add(valueLabel); // add value

        panel.add(Box.createRigidArea(new Dimension(0, 5))); // spacing
        return panel;
    }

}
