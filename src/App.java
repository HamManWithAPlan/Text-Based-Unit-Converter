import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Initialise scanner to read input
        ConversionHistory history = null; // No conversions yet

        boolean running = true; // Create menu loop

        while (running) {
            // Print menu
            System.out.println();
            System.out.println("--=== Unit Converter ===--");
            System.out.println("Categories [ Length/Distance, Mass, Volume/Kitchen, Area, Time ]");
            System.out.println("1. Make a conversion");
            System.out.println("2. View conversion history");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            // Menu options in switch case
            switch (choice) {
                case "1":
                    history = makeConversion(scanner, history);
                    break;
                case "2":
                    System.out.println();
                    System.out.println("--=== Conversion History ===--");
                    if (history == null) {
                        System.out.println("No conversion history.");
                    } else {
                        history.printHistory();
                    }
                    break;
                case "3":
                    // If conversions have been made, save history to a CSV file
                    if (history != null) {
                        try {
                            String fileName = "conversion_history.csv";
                            history.saveToCSV(fileName);
                            System.out.println("History saved to '" + fileName + "' successfully!");
                        } catch (java.io.IOException e) {
                            System.out.println("Error saving history to file: " + e.getMessage());
                        }
                    }
                    // Stop loop
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }

        // Program end
        scanner.close();
        System.out.println("Thanks for using this tool.");
    }

    public static ConversionHistory makeConversion(Scanner scanner, ConversionHistory history) {
        // Show Categories, then ask for and receive Category
        System.out.println();
        System.out.println("Categories [ Length/Distance, Mass, Volume/Kitchen, Area, Time ]");
        System.out.print("Category you would like to use: ");
        String chosenCategory = scanner.nextLine();
        Category category = new Category(chosenCategory);

        // If Category is not real
        if (category.getUnits().isEmpty()) {
            System.out.println("Invalid category.");
            return history;
        }

        // Find Units from given Category
        System.out.println("Units:");
        for (Unit unit : category.getUnits()) {
            System.out.println(unit.name + " (" + unit.shortened + ")");
        }

        // Ask for and receive First Unit
        System.out.print("Unit you would like to convert: ");
        String firstUnit = scanner.nextLine();
        Unit unit1 = category.findUnit(firstUnit);

        // If First Unit is not real
        if (unit1 == null) {
            System.out.println("Invalid unit.");
            return history;
        }

        // Ask for and receive Value
        System.out.print("Value to be converted: ");
        String valueAmount = scanner.nextLine();
        double value;

        // Convert to double
        try {
            value = Double.parseDouble(valueAmount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid value.");
            return history;
        }

        // Ask for and receive Second Unit
        System.out.print("Unit you would like the value to be converted to: ");
        String secondUnit = scanner.nextLine();
        Unit unit2 = category.findUnit(secondUnit);

        // If Second Unit is not real
        if (unit2 == null) {
            System.out.println("Invalid unit.");
            return history;
        }

        // Get calculation, show conversion
        double result = category.convert(value, unit1, unit2);
        System.out.println();
        System.out.println(value + " " + unit1.shortened() + " is equal to " + result + " " + unit2.shortened());

        // Create the first history record
        if (history == null) {
            history = new ConversionHistory(value, unit1, unit2, result);
        } else {
            // Recursively add another record
            history.add(value, unit1, unit2, result);
        }

        // Conversion end
        System.out.println("Conversion added to history.");
        return history;
    }
}