public class Personal {
    private String name;
    private int id;
    private String department;
    private String email;


    public Personal(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Personal(String name) {
        this(name, 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
