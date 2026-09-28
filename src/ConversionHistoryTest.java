import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class ConversionHistoryTest {

    @Test
    public void testAdd() {
        ConversionHistory history = new ConversionHistory();

        Unit kg = new Unit("Kilogram", "kg", 1);
        Unit g = new Unit("Gram", "g", 0.001);

        history.add(2, kg, g, 2000);

        assertEquals(1, history.getConversions().size());
    }

    @Test
    public void testGetLatest() {
        ConversionHistory history = new ConversionHistory();

        Unit kg = new Unit("Kilogram", "kg", 1);
        Unit g = new Unit("Gram", "g", 0.001);

        history.add(2, kg, g, 2000);

        Conversion latest = history.getLatest();

        assertNotNull(latest);
        assertEquals(2000, latest.result(), 0.0001);
    }

    @Test
    public void testGetLatestWhenEmpty() {
        ConversionHistory history = new ConversionHistory();

        assertNull(history.getLatest());
    }

    @Test
    public void testAddSavesToCSV() throws IOException {

        File tempFile = File.createTempFile(
                "conversion-history-test",
                ".csv"
        );

        try {
            ConversionHistory history =
                    new ConversionHistory(tempFile.getPath());

            Unit kg = new Unit("Kilogram", "kg", 1);
            Unit g = new Unit("Gram", "g", 0.001);

            history.add(2, kg, g, 2000);

            BufferedReader reader =
                    new BufferedReader(new FileReader(tempFile));

            String header = reader.readLine();
            String conversion = reader.readLine();

            reader.close();

            assertEquals(
                    "Original Value,From Unit,Result Value,To Unit",
                    header
            );

            assertEquals(
                    "2.0,kg,2000.0,g",
                    conversion
            );

        } finally {
            tempFile.delete();
        }
    }

    @Test
    public void testAddAppendsToCSV() throws IOException {

        File tempFile = File.createTempFile(
                "conversion-history-test",
                ".csv"
        );

        try {
            ConversionHistory history =
                    new ConversionHistory(tempFile.getPath());

            Unit kg = new Unit("Kilogram", "kg", 1);
            Unit g = new Unit("Gram", "g", 0.001);

            history.add(2, kg, g, 2000);
            history.add(5, kg, g, 5000);

            BufferedReader reader =
                    new BufferedReader(new FileReader(tempFile));

            String header = reader.readLine();
            String firstConversion = reader.readLine();
            String secondConversion = reader.readLine();

            reader.close();

            assertEquals(
                    "Original Value,From Unit,Result Value,To Unit",
                    header
            );

            assertEquals(
                    "2.0,kg,2000.0,g",
                    firstConversion
            );

            assertEquals(
                    "5.0,kg,5000.0,g",
                    secondConversion
            );

        } finally {
            tempFile.delete();
        }
    }
}