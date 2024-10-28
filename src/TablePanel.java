import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TablePanel extends JPanel
{
    // variables
    private List<String[]> data; // holds data
    private List<Integer> recordMap; // map records to their data
    private DefaultTableModel tableModel;
    private JTable table;
    private TableRowSorter<DefaultTableModel> sorter; // sorter declaration
    private StatsPanel statsPanel;
    private CreateChartPanel chartPanel;
    private JComboBox<String> stateFilter;
    private JComboBox<String> shapeFilter;

    // creates tablePanel
    public TablePanel(StatsPanel statsPanel, CreateChartPanel chartPanel)
    {
        this.statsPanel = statsPanel;
        this.chartPanel = chartPanel;

        // set layout for this JPanel
        setLayout(new BorderLayout());

        // load data from Data.java
        Data loadData = new Data("complete.csv");
        data = loadData.getRecords(); // call func from Data.java
        recordMap = new ArrayList<>(); // initialize list

        // instantiate table obj-- non-editable
        tableModel = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false; // cells non-editable
            }
        };

        // add columns -- add header field names
        tableModel.addColumn("Date");
        tableModel.addColumn("State");
        tableModel.addColumn("Shape");

        // traverse data -- put all data in table
        for (int i = 0; i < data.size(); i++) {
            String[] record = data.get(i); // create a record
            tableModel.addRow(new Object[]{record[0], record[2], record[4]}); // dateTime, state, shape
            recordMap.add(i); // store records initial index
        }

        // create table
        table = new JTable(tableModel);
        table.setBackground(new Color(173, 216, 230)); // background light blue

        // initialize sorter and use with table
        sorter = new TableRowSorter<>(tableModel); // create a new sorter
        table.setRowSorter(sorter); // set the sorter for the table

        JScrollPane scrollPane = new JScrollPane(table); // add a scroll
        add(scrollPane, BorderLayout.CENTER);

        // add click listener for clicking a record
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // if user clicks once
                    int row = table.getSelectedRow(); // row obj
                    if (row != -1) {
                        showRecordDetails(recordMap.get(table.convertRowIndexToModel(row))); // grabs details for row
                    }
                }
            }
        });

        // create filterPanel
        JPanel filterPanel = createFilterPanel();
        add(filterPanel, BorderLayout.NORTH); // add filters at the top
    }

    // ---------------------------------- Creates Panels and Labels ---------------------------------------------------

    private void showRecordDetails(int originalIndex) {
        String[] record = data.get(originalIndex); // get the initial record using the index

        // create a details panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(new Color(230, 230, 255)); // light purple background

        // add fields to panel -- bolded text
        detailsPanel.add(createLabeledComponent("City:", record[1], true));
        // if duration = 0 --> change it to displays "concurrent"
        detailsPanel.add(createLabeledComponent("Duration (sec):", "0".equals(record[5]) ? "concurrent" : record[5], true));
        detailsPanel.add(createLabeledComponent("Summary:", record[6], false));

        // create detailsDialog -- JDialog good for pop up information!
        JDialog detailsDialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Record Details", Dialog.ModalityType.APPLICATION_MODAL);
        // create -- set title
        detailsDialog.setContentPane(detailsPanel); // displays the detailsPanel
        detailsDialog.setSize(800, 300); // set size
        detailsDialog.setLocationRelativeTo(this); // center in main frame
        detailsDialog.setVisible(true);
    }

    // func adds label to value
    private JPanel createLabeledComponent(String label, String value, boolean bold)
    {
        JPanel panel = new JPanel(); // create panel
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(new Color(173, 216, 230)); // background light blue

        JLabel l = new JLabel(label); // label obj
        panel.add(l); // adds label

        JLabel valueLabel = new JLabel(value); // value obj
        panel.add(valueLabel); // add value

        panel.add(Box.createRigidArea(new Dimension(0, 5))); // spacing
        return panel;
    }

    // Filter displays
    private JPanel createFilterPanel()
    {
        JPanel filterPanel = new JPanel(); // filters panel
        filterPanel.setLayout(new FlowLayout());

        // state filter -- combo box
        stateFilter = new JComboBox<>();
        stateFilter.addItem("All States");
        stateFilter.addItem("CA");
        stateFilter.addItem("NY");
        stateFilter.addItem("TX");
        stateFilter.addItem("FL");
        stateFilter.addItem("TN");
        stateFilter.addItem("DC");
        stateFilter.addItem("PE");
        stateFilter.addItem("LA");
        stateFilter.addItem("CT");
        stateFilter.addItem("PA");
        stateFilter.addItem("KY");
        stateFilter.addItem("CO");
        stateFilter.addItem("KS");
        stateFilter.addItem("OR");
        stateFilter.addItem("WY");
        stateFilter.addItem("OK");
        stateFilter.addItem("GA");
        stateFilter.addItem("OH");
        stateFilter.addItem("SK");
        stateFilter.addItem("WI");
        stateFilter.addItem("SD");
        stateFilter.addItem("SC");
        stateFilter.addItem("SA");
        stateFilter.addItem("WA");
        stateFilter.addItem("NV");
        stateFilter.addItem("NT");
        stateFilter.addItem("NS");
        stateFilter.addItem("BC");
        stateFilter.addItem("NM");
        stateFilter.addItem("VT");
        stateFilter.addItem("NJ");
        stateFilter.addItem("NH");
        stateFilter.addItem("AZ");
        stateFilter.addItem("NF");
        stateFilter.addItem("NE");
        stateFilter.addItem("RI");
        stateFilter.addItem("ND");
        stateFilter.addItem("NC");
        stateFilter.addItem("NB");
        stateFilter.addItem("AR");
        stateFilter.addItem("VA");
        stateFilter.addItem("AL");
        stateFilter.addItem("AK");
        stateFilter.addItem("MT");
        stateFilter.addItem("MS");
        stateFilter.addItem("IN");
        stateFilter.addItem("IL");
        stateFilter.addItem("MO");
        stateFilter.addItem("MN");
        stateFilter.addItem("AD");
        stateFilter.addItem("UT");
        stateFilter.addItem("MI");
        stateFilter.addItem("YT");
        stateFilter.addItem("ID");
        stateFilter.addItem("IA");
        stateFilter.addItem("ME");
        stateFilter.addItem("MD");
        stateFilter.addItem("MB");
        stateFilter.addItem("MA");
        stateFilter.addItem("QC");
        stateFilter.addItem("DE");
        stateFilter.addItem("HI");

        // shape filter -- combo box
        shapeFilter = new JComboBox<>();
        shapeFilter.addItem("All Shapes");
        shapeFilter.addItem("Circle");
        shapeFilter.addItem("Triangle");
        shapeFilter.addItem("Sphere");
        shapeFilter.addItem("Other");
        shapeFilter.addItem("Light");
        shapeFilter.addItem("Egg");
        shapeFilter.addItem("Oval");
        shapeFilter.addItem("Teardrop");
        shapeFilter.addItem("Cross");
        shapeFilter.addItem("Formation");
        shapeFilter.addItem("Changing");
        shapeFilter.addItem("Cone");
        shapeFilter.addItem("Unknown");
        shapeFilter.addItem("Diamond");
        shapeFilter.addItem("Disk");
        shapeFilter.addItem("Cigar");
        shapeFilter.addItem("Rectangle");
        shapeFilter.addItem("Cylinder");
        shapeFilter.addItem("Chevron");
        shapeFilter.addItem("Fireball");
        shapeFilter.addItem("Flash");

        // date filter -- combo box
        JComboBox<String> dateFilter = new JComboBox<>();
        dateFilter.addItem("Sort by Date");
        dateFilter.addItem("Ascending");
        dateFilter.addItem("Descending");

        // add filters to panel
        filterPanel.add(new JLabel("State:"));
        filterPanel.add(stateFilter);
        filterPanel.add(new JLabel("Shape:"));
        filterPanel.add(shapeFilter);
        filterPanel.add(new JLabel("Date:"));
        filterPanel.add(dateFilter);

        // apply button to trigger filter display -- uses action listener
        JButton applyFilterButton = new JButton("Apply");
        applyFilterButton.addActionListener(e ->
        {
            applyFilters(stateFilter, shapeFilter, dateFilter); // apply filters
            updateChart();
        });

        filterPanel.add(applyFilterButton); // add apply button to filterPanel
        return filterPanel;
    }

    // update  chart based on the selected state/shape filters
    private void updateChart()
    {
        String selectedState = (String) stateFilter.getSelectedItem();
        String selectedShape = (String) shapeFilter.getSelectedItem();

        // call func in ChartPanel
        chartPanel.updateChart(selectedState, selectedShape);
    }


    // ----------------------------------  Filter Logic  ---------------------------------------------------

    private void applyFilters(JComboBox<String> stateFilter, JComboBox<String> shapeFilter, JComboBox<String> dateFilter) {
        List<RowFilter<Object, Object>> filters = new ArrayList<>(); // list of filters

        // state filter logic
        // (?!) is ignoring case --> Pattern matches string --> indices is the column index
        String selectedState = (String) stateFilter.getSelectedItem();
        if (!selectedState.equals("All States"))
        {
            filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(selectedState), 1)); // case-insensitive filter for state (column 1)
        }

        // shape filter logic
        // (?!) is ignoring case --> Pattern matches string --> indices is the column index
        String selectedShape = (String) shapeFilter.getSelectedItem();
        if (!selectedShape.equals("All Shapes"))
        {
            filters.add(RowFilter.regexFilter("(?i)" + Pattern.quote(selectedShape), 2)); // case-insensitive filter for shape (column 2)
        }

        // apply all the filters
        if (!filters.isEmpty())
        {
            RowFilter<Object, Object> compoundRowFilter = RowFilter.andFilter(filters); // combines all filters into one compound
            sorter.setRowFilter(compoundRowFilter); // sort using the compound
        }
        else
        {
            sorter.setRowFilter(null); // remove filters if none are selected
        }

        // sort by date
        String selectedDate = (String) dateFilter.getSelectedItem();
        if (selectedDate.equals("Ascending"))
        {
            sorter.setSortKeys(List.of(new RowSorter.SortKey(0, SortOrder.ASCENDING)));
        }
        else if (selectedDate.equals("Descending"))
        {
            sorter.setSortKeys(List.of(new RowSorter.SortKey(0, SortOrder.DESCENDING)));
        }
        // update panel
        statsPanel.updateStats(selectedState, selectedShape);
    }

}
