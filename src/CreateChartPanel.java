import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateChartPanel extends JPanel
{
    private JPanel chartPanelContainer;  // container for the chart
    private List<String[]> data;         // holds data

    // Constructor
    public CreateChartPanel()
    {
        // Load data (assuming Data class is used)
        Data loadData = new Data("complete.csv");
        this.data = loadData.getRecords();  // Load data internally

        // layout
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 340)); // Set preferred size for panel

        // create chart container
        chartPanelContainer = new JPanel(new BorderLayout());
        add(chartPanelContainer, BorderLayout.CENTER);

        updateChart("All States", "All Shapes");
    }

    // func to update chart based on selected filters (state and shape)
    public void updateChart(String selectedState, String selectedShape)
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Map to hold the number of sightings per year
        Map<String, Integer> sightingsPerYear = new HashMap<>();

        // iterate over the data -- filter by state and shape
        for (String[] record : data)
        {
            String state = record[2];     // state
            String shape = record[4];     // shape
            String year = record[0].split("/")[2];  // get year from the date column

            boolean stateMatches = selectedState.equals("All States") || state.equalsIgnoreCase(selectedState);
            boolean shapeMatches = selectedShape.equals("All Shapes") || shape.equalsIgnoreCase(selectedShape);

            // if the state and shape match --> count sightings by year
            if (stateMatches && shapeMatches)
            {
                sightingsPerYear.put(year, sightingsPerYear.getOrDefault(year, 0) + 1);
            }
        }

        // add data
        for (Map.Entry<String, Integer> entry : sightingsPerYear.entrySet())
        {
            String year = entry.getKey();
            Integer count = entry.getValue();
            dataset.addValue(count, "Sightings", year);
        }

        // create the chart
        JFreeChart chart = ChartFactory.createBarChart
                (
                "UFO Sightings by Year (" + selectedState + ", " + selectedShape + ")",
                "Year",
                "Number of Sightings",
                dataset,
                PlotOrientation.VERTICAL,
                false,  // show legend
                true,   // use tooltips
                false   // no URL
        );

        // customize
        CategoryPlot plot = chart.getCategoryPlot();
        // customize font
        plot.getDomainAxis().setLabelFont(new Font("Monospaced", Font.PLAIN, 12));
        plot.getRangeAxis().setLabelFont(new Font("Monospaced", Font.PLAIN, 12));
        // customize font
        plot.getDomainAxis().setTickLabelFont(new Font("Monospaced", Font.PLAIN, 10));
        plot.getRangeAxis().setTickLabelFont(new Font("Monospaced", Font.PLAIN, 10));
        // customize colors
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, Color.CYAN);
        // remove padding
        plot.setInsets(new RectangleInsets(10, 10, 10, 10));

        // switch old chart with new one
        chartPanelContainer.removeAll();
        chartPanelContainer.add(new ChartPanel(chart), BorderLayout.CENTER);
        chartPanelContainer.validate();
    }
}

