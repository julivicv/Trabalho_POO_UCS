import java.util.Scanner;

public class Menu {
    private static Scanner sc = new Scanner(System.in);
    private static Admin adm = new Admin("a", "a");
    private User user;
    private Supplier[] suppliers;

    public Menu (Supplier[] suppliers) {
        this.suppliers = suppliers;
    }


    public void menu() {
        int opc;
        do {
            System.out.println("Digite o número da operação desejada: ");
            System.out.println(" 1 - Logar como Admin");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1:
                    while (!adm.isLoggedIn()) {
                        adm.login(sc);
                    }
                    this.menuAdm();
                default:
                    System.out.println("Operação inválida: Por favor tente novamente.");
            }
        } while (opc != 0);
    }

    protected void menuAdm() {
        int opc;
        System.out.println("teste: " + adm.getRole());
        do {
            System.out.println("Digite o número da operação desejada: ");
            System.out.println(" 1 - Gerenciar fornecedores");
            System.out.println(" 2 - Gerenciar produtos de fornecedor");
            System.out.println(" 3 - Gerenciar estoque de produtos");
            opc = sc.nextInt();

            switch (opc) {
                case 1:
            }
        } while (opc != 0);
    }
}
