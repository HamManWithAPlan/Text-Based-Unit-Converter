import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ConversionHistory {
    private final ArrayList<Conversion> CONVERSIONS = new ArrayList<>();
    private String fileName = "conversion_history.csv";

    public ConversionHistory() {
        this.fileName = resolveMainDirectoryPath("conversion_history.csv");
    }

    public ConversionHistory(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Prevents path breaking if the app is executed from within the 'src' folder.
     * This is so 'conversion_history.csv' is always read from and written to the main directory
     */
    private String resolveMainDirectoryPath(String targetFile) {
        String currentDir = System.getProperty("user.dir");

        if (currentDir.endsWith("src")) {
            return ".." + File.separator + targetFile;
        }
        
        return targetFile;
    }

    public void add(double value, Unit fromUnit, Unit toUnit, double result) {
        Conversion conversion = new Conversion(value, fromUnit, toUnit, result);
        CONVERSIONS.add(conversion);

        try {
            saveConversionToCSV(conversion);
        } catch (IOException e) {
            System.out.println("Error saving conversion history: " + e.getMessage());
        }
    }

    private void saveConversionToCSV(Conversion conversion) throws IOException {
        File file = new File(fileName);

        boolean newFile = !file.exists() || file.length() == 0;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            if (newFile) {
                writer.write("Original Value,From Unit,Result Value,To Unit");
                writer.newLine();
            }

            writer.write(conversion.value() + "," + conversion.fromUnit().shortened() + "," + conversion.result() + "," + conversion.toUnit().shortened());
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

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine(); // Skip the CSV header
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                System.out.println(data[0] + " " + data[1] + " = " + data[2] + " " + data[3]);
            }
        } catch (IOException e) {
            System.out.println("Error reading conversion history: " + e.getMessage());
        }
    }

    public ArrayList<Conversion> getConversions() {
        return CONVERSIONS;
    }

    public Conversion getLatest() {
        if (CONVERSIONS.isEmpty()) {
            return null;
        }

        return CONVERSIONS.get(CONVERSIONS.size() - 1);
    }
}