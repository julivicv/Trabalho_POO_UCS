import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Supplier[] suppliers = new Supplier[20];

        Menu menu = new Menu(suppliers);

        menu.menu();

    }
}

