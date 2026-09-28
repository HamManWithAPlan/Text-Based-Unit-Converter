import java.io.*;
import java.util.ArrayList;

public class Category {
    public String name;
    private final ArrayList<Unit> units = new ArrayList<>(); // Stores all Units in given Category here

    public Category(String name) {
        this.name = name;
        loadCategoryData(); // On creation, gets info
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    // Finds Unit by name, ignoring capitalisation
    public Unit findUnit(String name) {
        for (Unit unit : units) {
            if (unit.name.equalsIgnoreCase(name)) {
                return unit;
            }
        }
        return null;
    }

    /* 
     * Calculates conversion between Units.
     * First - converts original value to the category's base Unit,
     * Then - converts that base Unit's value into the target Unit
    */
    public double convert(double value, Unit from, Unit to) {
        double baseValue = value * from.multiplier();
        return baseValue / to.multiplier();
    }

    private void loadCategoryData() {
        // Access resources for Unit and Category data
        try (InputStream input = Category.class.getResourceAsStream("/units.csv")) {
            if (input == null) {
                System.err.println("Could not find units.csv.");
                return;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(input)); // Read text line by line
            br.readLine(); // Skip Header of CSV file
            String line;

            while ((line = br.readLine()) != null) {
                // Separate the Unit data into columns
                String[] columns = line.split(",");

                if (columns.length >= 4) {
                    // Differentiate column data
                    String unitName = columns[0].trim();
                    String unitShortened = columns[1].trim();
                    String unitCategory = columns[2].trim();

                    // Only load Units belonging to given Category
                    if (unitCategory.equalsIgnoreCase(this.name)) {
                        try {
                            double unitMultiplier = Double.parseDouble(columns[3].trim()); // Data read from CSV file are Strings by default, must convert
                            Unit unit = new Unit(unitName, unitShortened, unitMultiplier);
                            units.add(unit);
                        } catch (NumberFormatException e) {
                            System.err.println("Multiplier error: " + line);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading csv file: " + e.getMessage());
        }
    }
}