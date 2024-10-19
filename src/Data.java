import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Data
{
    // variables
    private List<String[]> records;
    private static Data instance;

    // constructor -- prevent direct instantiation
    Data(String filePath)
    {
        loadData(filePath);
    }

    // func gets instance
    public static Data getInstance(String filePath)
    {
        if (instance == null)
        {
            instance = new Data(filePath);
        }
        return instance;
    }

    // func uses stream to read the data from file
    private void loadData(String filePath)
    {
        try
        {
            records = Files.lines(Paths.get(filePath))
                    .skip(1) // skip header
                    .map(line -> line.split(",")) // split  lines into fields
                    .filter(record -> record.length > 6 && !record[2].isEmpty()) // filter out empty states
                    .collect(Collectors.toList()); // put results into a list
        }
        catch (IOException e)
        {
            e.printStackTrace();
            records = List.of(); // Initialize as an empty list on error
        }
    }

    public List<String[]> getRecords()
    {
        return records;
    }
}
