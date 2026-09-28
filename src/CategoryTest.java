import static org.junit.Assert.*;
import org.junit.Test;

public class CategoryTest {
    @Test
    public void testFindUnitByName() {
        Category category = new Category("Mass");
        Unit unit = category.findUnit("Kilogram");

        assertNotNull(unit);
        assertEquals("kg", unit.shortened());
    }
    
    @Test
    public void testFindInvalidUnit() {
        Category category = new Category("Mass");
        Unit unit = category.findUnit("Banana");

        assertNull(unit);
    }

    @Test
    public void testConvertKilogramsToGrams() {
        Category category = new Category("Mass");
        Unit kg = category.findUnit("Kilogram");
        Unit g = category.findUnit("Gram");
        double result = category.convert(2.0, kg, g);

        assertEquals(2000.0, result, 0.0001);
    }

    @Test
    public void testConvertZero() {
        Category category = new Category("Mass");
        Unit kg = category.findUnit("Kilogram");
        Unit g = category.findUnit("Gram");
        double result = category.convert(0.0, kg, g);

        assertEquals(0.0, result, 0.0001);
    }
}