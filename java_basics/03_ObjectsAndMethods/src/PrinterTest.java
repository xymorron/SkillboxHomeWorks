public class PrinterTest {
    public static void main(String[] args) {
        Printer printer1 = new Printer();
        printer1.append("text_for_doc1");
        printer1.append("text_for_doc2", "doc2");
        printer1.append("text_for_doc3", "doc3", 4);
        System.out.println("PendingPagesCount " + printer1.getPendingPagesCount());
        printer1.print("Printing..");
        printer1.print("Queue cleared after printing");
        System.out.println("PendingPagesCount " + printer1.getPendingPagesCount());
        System.out.println("TotalPagesCount " + printer1.getTotalPagesCount());
    }
}
