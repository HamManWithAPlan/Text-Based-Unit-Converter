import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Category {
    private final String NAME;
    private final ArrayList<Unit> UNITS = new ArrayList<>();

    public Category(String name) {
        this.NAME = name;
    }

    public String name() {
        return NAME;
    }

    public ArrayList<Unit> getUnits() {
        return UNITS;
    }

    public Unit findUnit(String name) {
        if (name == null) {
            return null;
        }

        for (Unit unit : UNITS) {
            if (unit.name().equalsIgnoreCase(name) || unit.shortened().equalsIgnoreCase(name)) {
                return unit;
            }
        }

        return null;
    }

    /*
     * Calculates the conversion between Units - calls different calculation for Temperature
     * First - converts the original value to the category's base Unit.
     * Then - converts that base Unit's value into the target Unit.
     */
    public double convert(double value, Unit from, Unit to) {
        if (NAME.equalsIgnoreCase("Temperature")) {
            return convertTemperature(value, from, to);
        }

        return value * from.multiplier() / to.multiplier();
    }

    /* 
     * Calculates conversion for Temperature
     * First - Converts value to Celsius (base point for 0)
     * Then - Converts the value to specific Units based on their unique formulas
     */
    private double convertTemperature(double value, Unit from, Unit to) {
        double celsius;

        if (from.shortened().equalsIgnoreCase("C")) {
            celsius = value;
        } else if (from.shortened().equalsIgnoreCase("F")) {
            celsius = (value - 32) * 5 / 9;
        } else if (from.shortened().equalsIgnoreCase("K")) {
            celsius = value - 273.15;
        } else {
            return value;
        }

        if (to.shortened().equalsIgnoreCase("C")) {
            return celsius;
        } else if (to.shortened().equalsIgnoreCase("F")) {
            return celsius * 9 / 5 + 32;
        } else if (to.shortened().equalsIgnoreCase("K")) {
            return celsius + 273.15;
        }

        return celsius;
    }

    public static ArrayList<Category> loadCategories() {
        ArrayList<Category> categories = new ArrayList<>();

        categories.add(new Category("Length/Distance"));
        categories.add(new Category("Mass"));
        categories.add(new Category("Volume/Kitchen"));
        categories.add(new Category("Area"));
        categories.add(new Category("Time"));
        categories.add(new Category("Temperature"));

        InputStream input = Category.class.getResourceAsStream("/units.csv");

        if (input == null) {
            return categories;
        }

        /* 
        * Receives CSV Data
        * First - Skips the header and loops through each line in the CSV
        * Then - Extracts and formats the data found
        * Then - Uses the extracted data to create the new Unit for the Category
        */
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length != 4) {
                    continue;
                }

                String unitName = data[0].trim();
                String shortened = data[1].trim();
                String categoryName = data[2].trim();
                double multiplier = Double.parseDouble(data[3].trim());

                Unit unit = new Unit(unitName, shortened, multiplier);

                for (Category category : categories) {
                    if (category.name().equalsIgnoreCase(categoryName)) {
                        category.UNITS.add(unit);
                        break;
                    }
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading Unit data.");
        }

        return categories;
    }
}