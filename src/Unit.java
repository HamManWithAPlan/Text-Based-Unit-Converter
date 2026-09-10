import java.util.*;

public class Unit {
    public String name;
    public String unitCategory;
    public Category category;
    public int index;

    public Unit(String name, String unitCategory) {
        this.name = name;
        this.unitCategory = unitCategory;

        this.category = new Category(this.unitCategory);
        this.index = this.category.getNames().indexOf(this.name);
    }

    public String shortened() {
        if (index == -1) {
            return this.name;
        }

        List<?> shortenedList = category.getShortened();

        if (shortenedList == null || index >= shortenedList.size()) {
            return this.name; 
        }
        Object shortcut = shortenedList.get(index);

        return (shortcut != null) ? shortcut.toString() : this.name;
    }

    public double multiplier() {
        if (index == -1) {
            return 0;
        }

        List<?> multiplierList = category.getMultipliers();

        if (multiplierList == null || index >= multiplierList.size()) {
            return 0; 
        }
        Object shortcut = multiplierList.get(index);

        return (shortcut != null) ? (Double)shortcut : 0;
    }
}
