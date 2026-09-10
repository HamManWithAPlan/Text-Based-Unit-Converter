import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // Ask for Category
        System.out.println("Categories [ Length/Distance, Mass, Volume/Kitchen, Area, Temperature, Time ]");
        System.out.print("Category you would like to use: ");
        String chosenCategory = scanner.nextLine();

        Category category = new Category(chosenCategory);

        // Receive the First Unit
        System.out.println("Units " + category.getNames().toString());
        System.out.print("Unit you would like to convert: ");
        String firstUnit = scanner.nextLine();

        // First Unit object
        Unit unit1 = new Unit(firstUnit, chosenCategory);

        // Receive value
        System.out.print("Value to be converted: ");
        String valueAmount = scanner.nextLine();

        // Receive the Second Unit
        System.out.print("Unit you would like the value to be converted to: ");
        String secondUnit = scanner.nextLine();

        // Second Unit object
        Unit unit2 = new Unit(secondUnit, chosenCategory);

        // Calculate result
        double base = (Double.parseDouble(valueAmount) / unit2.multiplier());
        double result = base * unit1.multiplier();

        // Print result
        System.out.println(valueAmount + " " + unit1.shortened() + " is equal to " + result + " " + unit2.shortened());

        scanner.close();
    }
}