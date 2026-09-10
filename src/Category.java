import java.io.*;
import java.util.*;

public class Category {
    public String name;
    
    // Cache the lists in memory
    private final List<String> nameInfo = new ArrayList<>();
    private final List<String> shortenedInfo = new ArrayList<>();
    private final List<Double> multiplierInfo = new ArrayList<>();

    public Category(String name) {
        this.name = name;
        loadCategoryData(); // Read the CSV on creation
    }

    public List<String> getNames() {
        return nameInfo;
    }

    public List<String> getShortened() {
        return shortenedInfo;
    }

    public List<Double> getMultipliers() {
        return multiplierInfo;
    }

    private void loadCategoryData() {
        String csvFile = "units.csv";
        String line;
        String csvSplit = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine(); // Skip header

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(csvSplit);

                // Ensure row has all 4 required columns
                if (columns.length >= 4) {
                    String unitName = columns[0].trim();
                    String unitShortened = columns[1].trim();
                    String unitCategory = columns[2].trim();
                    
                    if (unitCategory.equalsIgnoreCase(this.name)) {
                        try {
                            double unitMultiplier = Double.parseDouble(columns[3].trim());
                            
                            // Save to memory
                            nameInfo.add(unitName);
                            shortenedInfo.add(unitShortened);
                            multiplierInfo.add(unitMultiplier);
                        } catch (NumberFormatException e) {
                            System.err.println("Skipping malformed multiplier in line: " + line);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the csv file: " + e.getMessage());
        }
    }
}
