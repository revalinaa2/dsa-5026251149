package lw01.unguided;

public abstract class Rental implements Chargeable {
    private String id;
    private int days;
    private int units;

    protected Rental(String id, int days, int units) {
        if (days <= 0) {
            throw new IllegalArgumentException("days and units must be positive");
        }
        this.id = id;
        this.days = days;
        this.units = units;
    }

    public String getId() {
        return id;
    }

    public int getDays() {
        return days;
    }

    public int getUnits() {
        return units;
    }

    @Override
    public abstract int calculateCharge();

    public int calculateCharge(int units) {
        return units * calculateCharge();
    }

    public String label() {
        return "Rental";
    }

    public String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }
}