public class Book {
    private final String name;
    private final String author;
    private final int pagesCount;
    private final String iSBN;

    public Book(String name, String author, int pagesCount, String iSBN) {
        this.name = name;
        this.author = author;
        this.pagesCount = pagesCount;
        this.iSBN = iSBN;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public String getISBN() {
        return iSBN;
    }
}
