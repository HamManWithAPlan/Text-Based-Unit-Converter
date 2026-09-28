# Text-Based-Unit-Converter
This Text-Based Unit Converter provides a simple way to convert measurements between different units, without requiring different conversion calculations for each pair of units. This application currently supports Length/Distance, Mass, Volume/Kitchen, Area, and Time conversions.

By reading information via an in-built CSV file resource, this program allows users to select a category and choose the units they want to convert a value between. Furthermore, this application also keeps a history of the user's conversions that were made during the current session, and upon completing a session, creates a new CSV file that shows the conversions made (each session adds more rows to that CSV file).

This program intends to allow users to quickly and efficiently perform conversions in a simple format, while keeping history for overall ease-of-use.

## Program Structure
This program is divided into 4 distinct Java classes, with App.java being the main class and the program Client. App handles the text-based menu and user input via scanners, calling appropriate methods from other classes to make this program run.

The Category class represents a category of Units, and contains an ArrayList of Unit objects. Category is reponsible for reading from the CSV file (units.csv) and supplying the program with that data, as well as performing the actual conversion calculation through the Units it holds.

The Unit class represents a singular Unit, storing its details such as name, shortened-form name and multiplier to its category's base unit. This multiplier is utilised for Category's conversion calculation.

The ConversionHistory class stores all conversions made in the program's session, as well as details such as the value, original unit, target unit and result. ConversionHistory maintains a receursive relationship with another ConversionHistory object, allowing multiple conversions to be held and called at the same time. At the same time, it holds a method that writes the conversions made in a particular session to another CSV file.

This project also contains JUnit test classes for testing the main functionality of the application.

## How to Run the Application
- Compile the Java source files
- Run App.java
- A Menu will be shown with 3 options:
    - Enter 1 - Make a Conversion
        - Input the Unit you want to convert
        - Input the Value you want to convert
        - Input the Unit you want the Value to be converted to
    - Enter 2 - View Conversion History (Won't show anything if you haven't made a conversion)
    - Enter 3 - Exit the program
- Upon finishing Option 1 or 2, the program will take you back to the Menu.