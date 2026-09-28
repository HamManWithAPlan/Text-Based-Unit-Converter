import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        ConversionHistory history = new ConversionHistory(); 

        boolean running = true; // Menu Loop

        while (running) {
            System.out.println();
            System.out.println("--=== Unit Converter ===--");
            System.out.println("1. Make a Conversion");
            System.out.println("2. View Your Conversion History");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            // Menu Options
            switch (choice) {
                case "1":
                    makeConversion(scanner, history);
                    break;
                case "2":
                    history.printHistory();
                    break;
                case "3":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1, 2 or 3.");
            }
        }

        scanner.close();
        System.out.println("Thanks for using this tool.");
    }

    public static void makeConversion(Scanner scanner, ConversionHistory history) {
        System.out.println("Categories [ Length/Distance, Mass, Volume/Kitchen, Area, Time, Temperature ]");
        System.out.print("Enter Category: ");
        String categoryName = scanner.nextLine();
        Category category = new Category(categoryName);

        if (category.getUnits().isEmpty()) {
            System.out.println("Invalid category.");
            return;
        }

        System.out.println("Available units:");
        for (Unit unit : category.getUnits()) {
            System.out.println("- " + unit.name() + " (" + unit.shortened() + ")");
        }

        Unit fromUnit = readUnit(scanner, category, "Unit you would like to convert from: ");
        if (fromUnit == null) {
            return;
        }

        System.out.print("Value to be converted: ");
        double value;
        try {
            value = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
            return;
        }

        Unit toUnit = readUnit(scanner, category, "Unit you would like to convert to: ");
        if (toUnit == null) {
            return;
        }

        double result = category.convert(value, fromUnit, toUnit);

        System.out.println("Result:");
        System.out.println(value + " " + fromUnit.shortened() + " = " + result + " " + toUnit.shortened());

        history.add(value, fromUnit, toUnit, result);
    }

    public static Unit readUnit(Scanner scanner, Category category, String message) {
        System.out.print(message);
        String unitName = scanner.nextLine();
        Unit unit = category.findUnit(unitName);

        if (unit == null) {
            System.out.println("Invalid unit.");
        }

        return unit;
    }

}