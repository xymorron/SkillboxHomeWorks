public class IndividualBusinessman extends Client {
    public IndividualBusinessman(double amountToPut) {
        put(amountToPut);
    }

    public IndividualBusinessman() {
        this(0);
    }

    private double subtractCommission(double amountToTake) {
        double commissionPercent = (amountToTake >= 1000)? 0.5: 1.0;
        return amountToTake * (100 - commissionPercent) / 100;
    }

    public void put(double amountToPut) {
        super.put(subtractCommission(amountToPut));

    }
}
