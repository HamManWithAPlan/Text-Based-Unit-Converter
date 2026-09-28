import static org.junit.Assert.*;
import org.junit.Test;

public class ConversionHistoryTest {
    @Test
    public void testDescription() {
        Unit kg = new Unit("Kilogram", "kg", 1.0);
        Unit g = new Unit("Gram", "g", 0.001);
        ConversionHistory history = new ConversionHistory(2.0, kg, g, 2000.0);

        assertEquals("2.0 kg = 2000.0 g", history.description());
    }

    @Test
    public void testAddConversion() {
        Unit kg = new Unit("Kilogram", "kg", 1.0);
        Unit g = new Unit("Gram", "g", 0.001);
        ConversionHistory history = new ConversionHistory(2.0, kg, g, 2000.0);

        history.add(5.0, kg, g, 5000.0);
        ConversionHistory latest = history.getLatest();

        assertEquals("5.0 kg = 5000.0 g", latest.description());
    }

    @Test
    public void testAddMultipleConversions() {
        Unit kg = new Unit("Kilogram", "kg", 1.0);
        Unit g = new Unit("Gram", "g", 0.001);
        ConversionHistory history = new ConversionHistory(2.0, kg, g, 2000.0);

        history.add(5.0, kg, g, 5000.0);
        history.add(10.0, kg, g, 10000.0);
        ConversionHistory latest = history.getLatest();

        assertEquals("10.0 kg = 10000.0 g", latest.description());
    }

    @Test
    public void testGetLatestSingleConversion() {
        Unit kg = new Unit("Kilogram", "kg", 1.0);
        Unit g = new Unit("Gram", "g", 0.001);
        ConversionHistory history = new ConversionHistory(2.0, kg, g, 2000.0);
        ConversionHistory latest = history.getLatest();

        assertEquals("2.0 kg = 2000.0 g", latest.description());
    }
}