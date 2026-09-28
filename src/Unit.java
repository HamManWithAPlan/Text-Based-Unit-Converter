public class Unit {

    private String name;
    private String shortened;
    private double multiplier;

    public Unit(String name, String shortened, double multiplier) {
        this.name = name;
        this.shortened = shortened;
        this.multiplier = multiplier;
    }

    public String name() {
        return name;
    }

    public String shortened() {
        return shortened;
    }

    public double multiplier() {
        return multiplier;
    }
}