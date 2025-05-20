package br.ucs.trabalhopoo.users;

public class Admin extends User {
    private static final String ROLE = "admin";

    public Admin(String email, String pass) {
        this.email = email;
        this.password = pass;
    }

    public Admin() {}

    @Override
    public String getRole() {
        return ROLE;
    }
}
