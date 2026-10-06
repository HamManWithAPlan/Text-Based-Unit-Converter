import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class CategoryTest {
    private Category getCategory(String name) {
        ArrayList<Category> categories = Category.loadCategories();

        for (Category category : categories) {
            if (category.name().equalsIgnoreCase(name)) {
                return category;
            }
        }

        return new Category(name);
    }

    @Test
    public void testFindUnit() {
        Category category = getCategory("Mass");
        Unit unit = category.findUnit("Kilogram");

        assertNotNull(unit);
        assertEquals("kg", unit.shortened());
    }

    @Test
    public void testFindUnitLowercase() {
        Category category = getCategory("Mass");
        Unit unit = category.findUnit("kilogram");

        assertNotNull(unit);
        assertEquals("kg", unit.shortened());
    }

    @Test
    public void testFindUnitAbbreviation() {
        Category category = getCategory("Mass");
        Unit unit = category.findUnit("kg");

        assertNotNull(unit);
        assertEquals("Kilogram", unit.name());
    }

    @Test
    public void testFindUnitNull() {
        Category category = getCategory("Mass");
        Unit unit = category.findUnit(null);

        assertNull(unit);
    }

    @Test
    public void testFindInvalidUnit() {
        Category category = getCategory("Mass");
        Unit unit = category.findUnit("Banana");

        assertNull(unit);
    }

    @Test
    public void testConvert() {
        Category category = getCategory("Mass");
        Unit kg = category.findUnit("Kilogram");
        Unit g = category.findUnit("Gram");

        double result = category.convert(2, kg, g);

        assertEquals(2000, result, 0.0001);
    }

    @Test
    public void testConvertNegative() {
        Category category = getCategory("Mass");
        Unit kg = category.findUnit("Kilogram");
        Unit g = category.findUnit("Gram");

        double result = category.convert(-2, kg, g);

        assertEquals(-2000, result, 0.0001);
    }

    @Test
    public void testConvertSameUnit() {
        Category category = getCategory("Mass");
        Unit kg = category.findUnit("Kilogram");

        double result = category.convert(5, kg, kg);

        assertEquals(5, result, 0.0001);
    }

    @Test
    public void testConvertLength() {
        Category category = getCategory("Length/Distance");
        Unit metre = category.findUnit("Metre");
        Unit centimetre = category.findUnit("Centimetre");

        double result = category.convert(2, metre, centimetre);

        assertEquals(200, result, 0.0001);
    }

    @Test
    public void testConvertZero() {
        Category category = getCategory("Mass");
        Unit kg = category.findUnit("Kilogram");
        Unit g = category.findUnit("Gram");

        double result = category.convert(0, kg, g);

        assertEquals(0, result, 0.0001);
    }

    @Test
    public void testInvalidCategory() {
        Category category = new Category("Banana");

        assertTrue(category.getUnits().isEmpty());
    }

    @Test
    public void testLoadCategories() {
        ArrayList<Category> categories = Category.loadCategories();

        assertEquals(6, categories.size());

        Category temperature = getCategory("Temperature");

        assertFalse(temperature.getUnits().isEmpty());
    }

    @Test
    public void testDescription() {
        Unit kg = new Unit("Kilogram", "kg", 1);
        Unit g = new Unit("Gram", "g", 0.001);
        Conversion conversion = new Conversion(2, kg, g, 2000);

        assertEquals("2.0 kg = 2000.0 g", conversion.description());
    }
}