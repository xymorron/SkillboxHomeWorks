public class PhysicalPerson extends Client {

    public PhysicalPerson(double amountToPut) {
        super.put(amountToPut);
    }

    public PhysicalPerson() {
        this(0);
    }


}
