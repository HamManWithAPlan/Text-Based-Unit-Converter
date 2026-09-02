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

        // Ask for First Unit
        System.out.println("Units " + category.getNames().toString());
        System.out.print("Unit you would like to convert: ");
        String firstUnit = scanner.nextLine();

        System.out.print("Value to be converted: ");
        String valueAmount = scanner.nextLine();

        System.out.print("Unit you would like the value to be converted to: ");
        String secondUnit = scanner.nextLine();

        


        System.out.print(firstUnit + ", " + valueAmount + ", " + secondUnit);
        

        // Ask for Second Unit

        scanner.close();
    }
}