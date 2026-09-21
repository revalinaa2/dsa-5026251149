package lw01.unguided;

public class LaptopRental extends Rental {

    private static final int DAILY_RATE = 40000;
    private static final int SETUP_FEE = 10000;

     public LaptopRental(String id, int days, int units) {
        super(id, days, units);
    }

    @Override
    public int calculateCharge() {
        int perUnit = getDays() * DAILY_RATE + SETUP_FEE;
        return perUnit * getUnits();
    }

    @Override
    public String label() {
        return "Laptop";
    }
}
