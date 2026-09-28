import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ConversionHistory {

    private final ArrayList<Conversion> conversions = new ArrayList<>();
    private String fileName = "conversion_history.csv";

    public ConversionHistory() {
    }

    public ConversionHistory(String fileName) {
        this.fileName = fileName;
    }

    public void add(double value, Unit fromUnit, Unit toUnit, double result) {
        Conversion conversion = new Conversion(value, fromUnit, toUnit, result);
        conversions.add(conversion);

        try {
            saveConversionToCSV(conversion);
        } catch (IOException e) {
            System.out.println("Error saving conversion history: " + e.getMessage());
        }
    }

    private void saveConversionToCSV(Conversion conversion) throws IOException {
        File file = new File(fileName);

        boolean newFile = !file.exists() || file.length() == 0;

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(file, true))) {

            if (newFile) {
                writer.write("Original Value,From Unit,Result Value,To Unit");
                writer.newLine();
            }

            writer.write(
                    conversion.value()
                    + ","
                    + conversion.fromUnit().shortened()
                    + ","
                    + conversion.result()
                    + ","
                    + conversion.toUnit().shortened()
            );
            writer.newLine();
        }
    }

    public void printHistory() {
        File file = new File(fileName);

        if (!file.exists() || file.length() == 0) {
            System.out.println("No conversion history.");
            return;
        }

        System.out.println("===== Conversion History =====");

        try (BufferedReader reader = new BufferedReader(
                new FileReader(file))) {

            // Skip the CSV header
            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                System.out.println(
                        data[0] + " " + data[1]
                        + " = "
                        + data[2] + " " + data[3]
                );
            }

        } catch (IOException e) {
            System.out.println(
                    "Error reading conversion history: "
                    + e.getMessage()
            );
        }
    }

    public ArrayList<Conversion> getConversions() {
        return conversions;
    }

    public Conversion getLatest() {
        if (conversions.isEmpty()) {
            return null;
        }

        return conversions.get(conversions.size() - 1);
    }
}