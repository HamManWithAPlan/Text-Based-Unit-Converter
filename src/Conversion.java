public class Conversion {

    private final double VALUE;
    private final Unit FROM_UNIT;
    private final Unit TO_UNIT;
    private final double RESULT;

    public Conversion(double value, Unit fromUnit, Unit toUnit, double result) {
        this.VALUE = value;
        this.FROM_UNIT = fromUnit;
        this.TO_UNIT = toUnit;
        this.RESULT = result;
    }

    public double value() {
        return VALUE;
    }

    public Unit fromUnit() {
        return FROM_UNIT;
    }

    public Unit toUnit() {
        return TO_UNIT;
    }

    public double result() {
        return RESULT;
    }

    public String description() {
        return VALUE + " " + FROM_UNIT.shortened() + " = " + RESULT + " " + TO_UNIT.shortened();
    }
}