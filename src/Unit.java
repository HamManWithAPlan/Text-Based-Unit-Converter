public class Unit {
    public String name;
    public String shortened;
    public double multiplier;

    // Stores Unit data
    public Unit(String name, String shortened, double multiplier) {
        this.name = name;
        this.shortened = shortened;
        this.multiplier = multiplier;
    }

    public String shortened() {
        return shortened;
    }

    public double multiplier() {
        return multiplier;
    }
}