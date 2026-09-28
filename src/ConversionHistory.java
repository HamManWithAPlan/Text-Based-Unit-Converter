import java.io.BufferedWriter;
import java.io.IOException;

public class ConversionHistory {
    private final double VALUE;
    private final Unit FROM_UNIT;
    private final Unit TO_UNIT;
    private final double RESULT;
    private ConversionHistory next; // Links this Conversion to the next Conversion

    public ConversionHistory(double value, Unit from, Unit to, double result) {
        this.VALUE = value;
        this.FROM_UNIT = from;
        this.TO_UNIT = to;
        this.RESULT = result;
        this.next = null;
    }

    // Recursively adds a new Conversion
    public void add(double value, Unit from, Unit to, double result) {
        if (next == null) {
            next = new ConversionHistory(value, from, to, result);
        } else {
            next.add(value, from, to, result);
        }
    }

    // Finds the most recent Conversion
    public ConversionHistory getLatest() {
        if (next == null) {
            return this;
        }
        return next.getLatest();
    }

    // Returns Conversion as a String
    public String description() {
        return VALUE + " " + FROM_UNIT.shortened() + " = " + RESULT + " " + TO_UNIT.shortened();
    }

    // Prints every stored Conversion
    public void printHistory() {
        System.out.println(description());
        if (next != null) {
            next.printHistory();
        }
    }

    // Writes Conversions to new CSV file
    public void saveToCSV(String filePath) throws IOException {
        java.io.File file = new java.io.File(filePath);
        boolean isNewFile = !file.exists(); // Check if file needs header

        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(filePath, true))) { // The 'true' argument makes it so each time method is called, doesn't overwrite the old file
            // Write the CSV column headers
            if (isNewFile) {
                writer.write("Original Value,From Unit,Result Value,To Unit");
                writer.newLine();
            }
            
            writeNode(writer);
        }
    }

    // Recursively writes each Conversion
    private void writeNode(BufferedWriter writer) throws IOException {
        // Line formatting
        String line = String.format("%f,%s,%f,%s", this.VALUE, this.FROM_UNIT.shortened(), this.RESULT, this.TO_UNIT.shortened());
        
        writer.write(line);
        writer.newLine();

        // If there is another record, pass the writer down to it
        if (this.next != null) {
            this.next.writeNode(writer);
        }
    }
}