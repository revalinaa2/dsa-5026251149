package lw01.unguided;

public class ProjectorRental extends Rental {

    public ProjectorRental(String id, int days, int units) {
        super(id, days, units);
    }

    @Override
    public int calculateCharge() {
        int days = getDays();
        int perUnit;

        if (days <= 3) {
            perUnit = days * 60000;
        } else {
            perUnit = (3 * 60000) + ((days - 3) * 45000);
        }
        perUnit += 20000;

        return perUnit * getUnits();
    }

    @Override
    public String label() {
        return "Projector";
    }
}