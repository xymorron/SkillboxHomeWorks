public class LegalPerson extends Client {

    public LegalPerson(double amountToPut) {
        super.put(amountToPut);
    }

    public LegalPerson() {
        this(0);
    }

    private double addCommission(double amountToTake) {
        return amountToTake * (100 + 1) / 100;
    }

    public void take(double amountToTake) {
        super.take(addCommission(amountToTake));

        }
}

