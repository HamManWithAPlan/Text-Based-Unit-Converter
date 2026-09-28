import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Category {

    private String name;
    private final ArrayList<Unit> units = new ArrayList<>();

    public Category(String name) {
        this.name = name;
        loadCategoryData();
    }

    public String name() {
        return name;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    // Finds a Unit by its name or abbreviation, ignoring capitalisation
    public Unit findUnit(String name) {
        if (name == null) {
            return null;
        }

        for (Unit unit : units) {
            if (unit.name().equalsIgnoreCase(name)
                    || unit.shortened().equalsIgnoreCase(name)) {
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
        if (name.equalsIgnoreCase("Temperature")) {
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

    private void loadCategoryData() {
        InputStream input = Category.class.getResourceAsStream("/units.csv");

        if (input == null) {
            return;
        }

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
                String category = data[2].trim();
                double multiplier = Double.parseDouble(data[3].trim());

                if (category.equalsIgnoreCase(name)) {
                    units.add(new Unit(unitName, shortened, multiplier));
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading Unit data.");
        }
    }
}