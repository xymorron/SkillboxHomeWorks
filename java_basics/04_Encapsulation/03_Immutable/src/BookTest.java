public class BookTest {
    public static void main(String[] args) {
        Book hobbit = new Book("The Lord of the Rings", "Tolkien John Ronald Reuel",
                1178, "9780261103252");
        System.out.println(hobbit.getName());
        System.out.println(hobbit.getAuthor());
        System.out.println(hobbit.getPagesCount());
        System.out.println(hobbit.getISBN());
    }
}
