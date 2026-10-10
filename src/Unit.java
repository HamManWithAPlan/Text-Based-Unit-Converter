public class Unit {
    private final String NAME;
    private final String SHORTENED;
    private final double MULTIPLIER;

    public Unit(String name, String shortened, double multiplier) {
        this.NAME = name;
        this.SHORTENED = shortened;
        this.MULTIPLIER = multiplier;
    }

    public String name() {
        return NAME;
    }

    public String shortened() {
        return SHORTENED;
    }

    public double multiplier() {
        return MULTIPLIER;
    }
}