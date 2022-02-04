public class Printer {
    private String queue = "";
    private int pendingPagesCount = 0;
    private int totalPagesCount = 0;

    public Printer(){}

    public void append(String data, String name, int pageCount) {
        queue += data + " " + name + " " + pageCount + "\n";
        pendingPagesCount += pageCount;
        totalPagesCount += pageCount;
    }

    public void append(String data, String name) {
        append(data, name, 1);
    }

    public void append(String data) {
        append(data, "no_name");
    }

    public void clear() {
        queue = "";
        pendingPagesCount = 0;
    }

    public void print(String title) {
        System.out.println(title);
        if (queue.isEmpty()) {
            System.out.println("There is no docs in the queue");
        } else {
            System.out.println(queue);
            clear();
        }
    }

    public int getPendingPagesCount() {
        return pendingPagesCount;
    }

    public int getTotalPagesCount() {
        return totalPagesCount;
    }
}
