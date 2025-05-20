import br.ucs.trabalhopoo.users.Supplier;
import br.ucs.trabalhopoo.menus.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Supplier[] suppliers = new Supplier[20];
        int supplierCount = 0;
        Scanner sc = new Scanner(System.in);

        Menu menu = new Menu(suppliers, supplierCount, sc);

        menu.menu();
        sc.close();
    }
}

