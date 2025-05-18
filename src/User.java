package main;
import java.util.Scanner;

public class User {
    protected String email;
    protected String password;
    protected boolean isLoggedIn = false;
    private static final String ROLE = "default";

    public User() {};

    public boolean login(Scanner sc) {
        System.out.print("Email: ");
        String inputEmail = sc.nextLine();

        System.out.print("Senha: ");
        String inputPassword = sc.nextLine();

        if (this.email.equals(inputEmail) && this.password.equals(inputPassword)) {
            this.isLoggedIn = true;
            System.out.println("Login realizado com sucesso!");
        } else {
            System.out.println("Email ou senha inválidos!");
        }
        return this.isLoggedIn;
    }

    public void logout() {
        this.isLoggedIn = false;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public String getRole() {
        return ROLE;
    }
}