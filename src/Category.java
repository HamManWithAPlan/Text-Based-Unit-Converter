import java.io.*;
import java.util.*;

public class Category {
    public String name;

    public Category(String name) {
        this.name = name;
    }

    public List<?> getNames() {
        return getInfo("name");
    }

    public List<?> getShortened() {
        return getInfo("shortened");
    }

    public List<?> getMultipliers() {
        return getInfo("multiplier");
    }

    public List<?> getInfo(String type) {
        if (type == null) {
            return null;
        }

        List<String> nameInfo = new ArrayList<>();
        List<String> shortenedInfo = new ArrayList<>();
        List<Double> multiplierInfo = new ArrayList<>();

        // Find file and initialise csv logic
        String csvFile = "../units.csv";
        String line = "";
        String csvSplit = ",";

        // Safely open file
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            // Skip header
            String headerLine = br.readLine();

            // Read line by line, splitting rows by commas
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(csvSplit);

                if (columns.length > 3) {
                    String unitName = columns[0].trim();
                    String unitShortened = columns[1].trim();
                    String unitCategory = columns[2].trim();
                    double unitMultiplier = Double.parseDouble(columns[3].trim());

                    // If category in this row matches with the given category, it is a unit
                    if (unitCategory.equalsIgnoreCase(this.name)) {
                        nameInfo.add(unitName);
                        shortenedInfo.add(unitShortened);
                        multiplierInfo.add(unitMultiplier);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the csv file: " + e.getMessage());
        }

        // Completed list
        return switch (type.toLowerCase().trim()) {
            case "name" -> nameInfo;
            case "shortened" -> shortenedInfo;
            case "multiplier" -> multiplierInfo;
            default -> null;
        } ;
    }
}
