# Text-Based-Unit-Converter
This Text-Based Unit Converter provides a simple way to convert measurements between different units, without requiring different conversion calculations for each pair of units (with certain exceptions). This application currently supports Length/Distance, Mass, Volume/Kitchen, Area, Time, and Temperature conversions.

By reading information via an in-built CSV file resource, this program allows users to select a category, choose a starting and destination unit, enter a value, and receive the converted result. Furthermore, this application also keeps a history of the user's conversions, and creates a new CSV file to hold those conversions on the first time a conversion is made (each conversion adds more rows to that CSV file, and while this file exists, the application will be able to access its data as well).

This program intends to allow users to quickly and efficiently perform conversions in a simple format, while keeping history for overall ease-of-use.

## Program Structure
This application is separated into several classes, with each class having specific responsibilities.

- App.java contains the Main Menu outputted in the Terminal, and handles user input. It also coordinates the Conversion process and saves the Conversion History when the application ends.
- Category.java represents a Category of Units. It contains an ArrayList of Unit objects, and is responsible for finding Units and performing the Conversion calculations.
- Unit.java represents an individual Unit. Each Unit has a name, abbreviation and multiplier to their Category's base Unit.
- Conversion.java represents one completed Conversion. It stores the original value, starting unit, destination unit and result of an applied Conversion.
- ConversionHistory.java stores an ArrayList of Conversion objects. It displays the Conversion History, and saves it to a CSV file.

Units.csv contains all the Unit information used by the application, allowing data to be updated outside of the application's code.

The application also contains JUnit tests which test unit searching, conversion calculations, invalid inputs, conversion history and CSV file saving.

## How to Run the Application
- Open a Terminal in the Text-Based-Unit-Converter folder.
- Enter `cd src`.
- Enter `javac App.java Category.java Unit.java Conversion.java ConversionHistory.java`.
- Enter `java App`.
- Follow the instructions given by the Menu displayed in the Terminal.
- Select Option 3 to exit and save the Conversion History.

This application required Java to be installed on the running machine.


## How to Run the Tests
- The JUnit library (.jar file) is provided in the `lib` folder.
- From the main project folder, compile the source files and tests using the JUnit file.
- The tests cover normal conversions, invalid units, lowercase unit names, abbreviations, negative values, zero values, same-unit conversions, invalid categories and conversion history file saving.