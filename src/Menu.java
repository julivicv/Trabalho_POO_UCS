import java.util.Scanner;

public class Menu {
    private static Scanner sc = new Scanner(System.in);
    private static Admin adm = new Admin("a", "a");
    private User user;
    private Supplier[] suppliers;
    private int supplierCount = 0;

    public Menu(Supplier[] suppliers) {
        this.suppliers = suppliers;
    }

    public void menu() {
        int opc;
        do {
            System.out.println("Digite o número da operação desejada:");
            System.out.println(" 1 - Logar como Admin");
            System.out.println(" 0 - Sair");
            opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1:
                    while (!adm.isLoggedIn()) {
                        adm.login(sc);
                    }
                    this.menuAdm();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema.");
                    break;
                default:
                    System.out.println("Operação inválida: Por favor tente novamente.");
            }
        } while (opc != 0);
    }

    protected void menuAdm() {
        int opc;
        do {
            System.out.println("\nDigite o número da operação desejada:");
            System.out.println(" 1 - Gerenciar fornecedores");
            System.out.println(" 2 - Gerenciar produtos de fornecedor");
            System.out.println(" 3 - Gerenciar estoque de produtos");
            System.out.println(" 0 - Sair");

            opc = sc.nextInt();

            switch (opc) {
                case 1:
                    int opcFornecedor;
                    do {
                        System.out.println("\nDigite o número da operação desejada para fornecedores:");
                        System.out.println(" 1 - Cadastrar fornecedor");
                        System.out.println(" 2 - Remover fornecedor");
                        System.out.println(" 3 - Listar fornecedores");
                        System.out.println(" 0 - Voltar");

                        opcFornecedor = sc.nextInt();
                        sc.nextLine();

                        switch (opcFornecedor) {
                            case 1:
                                if (supplierCount < suppliers.length) {
                                    Supplier newSupplier = new Supplier(sc);
                                    suppliers[supplierCount++] = newSupplier;
                                    System.out.println("Fornecedor cadastrado com sucesso!");
                                } else {
                                    System.out.println("Limite de fornecedores atingido.");
                                }
                                break;
                            case 2:
                                System.out.println("Digite o índice do fornecedor a remover:");
                                for (int i = 0; i < supplierCount; i++) {
                                    System.out.println(i + " - " + suppliers[i].toString());
                                }
                                int idx = sc.nextInt();
                                if (idx >= 0 && idx < supplierCount) {
                                    for (int i = idx; i < supplierCount - 1; i++) {
                                        suppliers[i] = suppliers[i + 1];
                                    }
                                    suppliers[--supplierCount] = null;
                                    System.out.println("Fornecedor removido.");
                                } else {
                                    System.out.println("Índice inválido.");
                                }
                                break;
                            case 3:
                                System.out.println("Lista de Fornecedores:");
                                for (int i = 0; i < supplierCount; i++) {
                                    System.out.println(i + " - " + suppliers[i].toString());
                                }
                                break;
                            case 0:
                                System.out.println("Voltando ao menu principal...");
                                break;
                            default:
                                System.out.println("Opção inválida.");
                        }
                    } while (opcFornecedor != 0);
                    break;
                case 2:
                    // Lógica para gerenciar produtos de fornecedor
                    break;
                case 3:
                    // Lógica para gerenciar estoque de produtos
                    break;
                case 0:
                    System.out.println("Saindo do sistema.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opc != 0);
    }
}
