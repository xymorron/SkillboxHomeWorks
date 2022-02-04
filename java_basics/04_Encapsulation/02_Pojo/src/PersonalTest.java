public class PersonalTest {
    public static void main(String[] args) {
        Personal misha = new Personal("Mihail", 666);
        misha.setName("Mihail Bulgakov");
        misha.setDepartment("Literature");
        misha.setEmail("mb@email.com");

        System.out.println(misha.getName());
        System.out.println(misha.getId());
        System.out.println(misha.getDepartment());
        System.out.println(misha.getEmail());
    }
}
