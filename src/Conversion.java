public class Conversion {

    private double value;
    private Unit fromUnit;
    private Unit toUnit;
    private double result;

    public Conversion(double value, Unit fromUnit, Unit toUnit, double result) {
        this.value = value;
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
        this.result = result;
    }

    public double value() {
        return value;
    }

    public Unit fromUnit() {
        return fromUnit;
    }

    public Unit toUnit() {
        return toUnit;
    }

    public double result() {
        return result;
    }

    public String description() {
        return value + " " + fromUnit.shortened() + " = " + result + " " + toUnit.shortened();
    }
}