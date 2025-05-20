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
            System.out.println(" 4 - Criar dados de teste");  // New option
            System.out.println(" 0 - Sair");

            opc = sc.nextInt();
            boolean success = false;

            switch (opc) {
                case 1:
                    while (!success) {
                        success = this.menuManageSuppliers();
                    }
                    break;
                case 2:
                    while (!success) {
                        success = this.menuManageProducts();
                    }
                    break;
                case 3:
                    while (!success) {
                        success = this.menuManageStock();
                    }
                    break;
                case 4:
                    createTestData();
                    break;
                case 0:
                    System.out.println("Saindo do sistema.");
                    opc = 0;
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opc != 0);
    }

    private boolean menuManageSuppliers() {
        int opcFornecedor;
        do {
            System.out.println("\nDigite o número da operação desejada para fornecedores:");
            System.out.println(" 1 - Cadastrar fornecedor");
            System.out.println(" 2 - Alterar fornecedor");
            System.out.println(" 3 - Remover fornecedor");
            System.out.println(" 4 - Listar fornecedores");
            System.out.println(" 5 - Consultar fornecedor por código");
            System.out.println(" 6 - Consultar fornecedor por nome");
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
                    System.out.println("Digite o índice do fornecedor a alterar:");
                    for (int i = 0; i < supplierCount; i++) {
                        System.out.println(i + " - " + suppliers[i].toString());
                    }
                    int idxAlt = sc.nextInt();
                    sc.nextLine();
                    if (idxAlt >= 0 && idxAlt < supplierCount) {
                        System.out.println("Alterando dados do fornecedor:");
                        suppliers[idxAlt] = new Supplier(sc);
                        System.out.println("Fornecedor alterado com sucesso.");
                    } else {
                        System.out.println("Índice inválido.");
                    }
                    break;
                case 3:
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
                case 4:
                    System.out.println("Lista de Fornecedores:");
                    if (suppliers[0] == null) {
                        System.out.println("Nenhum fornecedor cadastrado.");
                    } else {
                        for (int i = 0; i < supplierCount; i++) {
                            System.out.println("============================\n" +
                                    i + " - " + suppliers[i].toString() +
                                    "\n============================");
                        }
                    }
                    break;
                case 5:
                    System.out.print("Digite o código do fornecedor: ");
                    int codigoBusca = sc.nextInt();
                    sc.nextLine();
                    boolean encontrado = false;
                    for (int i = 0; i < supplierCount; i++) {
                        if (i + 1 == codigoBusca) {
                            System.out.println("Fornecedor encontrado: " + suppliers[i].toString());
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Fornecedor com esse código não encontrado.");
                    }
                    break;
                case 6:
                    System.out.print("Digite o nome (ou parte dele) do fornecedor: ");
                    String nomeBusca = sc.nextLine().toLowerCase();
                    boolean achou = false;
                    for (int i = 0; i < supplierCount; i++) {
                        if (suppliers[i].getName().toLowerCase().contains(nomeBusca)) {
                            System.out.println("Fornecedor encontrado: " + suppliers[i].toString());
                            achou = true;
                        }
                    }
                    if (!achou) {
                        System.out.println("Nenhum fornecedor com esse nome encontrado.");
                    }
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    return true;
                default:
                    System.out.println("Opção inválida.");
                    return false;
            }
        } while (opcFornecedor != 0);
        return true;
    }

    private boolean menuManageProducts() {
        System.out.println("Selecione o fornecedor:");
        for (int i = 0; i < supplierCount; i++) {
            System.out.println(i + " - " + suppliers[i].getName());
        }
        int idxSupplier = sc.nextInt();
        sc.nextLine();

        if (idxSupplier >= 0 && idxSupplier < supplierCount) {
            Supplier selectedSupplier = suppliers[idxSupplier];
            int opcProduto;
            do {
                System.out.println("\nGerenciamento de produtos do fornecedor " + selectedSupplier.getName());
                System.out.println(" 1 - Adicionar produto");
                System.out.println(" 2 - Listar produtos");
                System.out.println(" 3 - Alterar produto");
                System.out.println(" 4 - Excluir produto");
                System.out.println(" 5 - Consultar produto por nome");
                System.out.println(" 0 - Voltar");
                opcProduto = sc.nextInt();
                sc.nextLine();

                switch (opcProduto) {
                    case 1:
                        selectedSupplier.addProduct(sc);
                        break;
                    case 2:
                        selectedSupplier.listAllProduct();
                        break;
                    case 3:
                        selectedSupplier.listAllProduct();
                        System.out.print("Digite o índice do produto a alterar: ");
                        int idxAlt = sc.nextInt();
                        sc.nextLine();
                        Product prodAlt = selectedSupplier.getProductByIndex(idxAlt);
                        if (prodAlt != null) {
                            System.out.print("Novo nome: ");
                            prodAlt.setName(sc.nextLine());
                            System.out.print("Nova descrição: ");
                            prodAlt.setDescription(sc.nextLine());
                            System.out.print("Novo nome de imagem: ");
                            String newImg = sc.nextLine();
                            prodAlt.setImgURL(newImg);
                            System.out.println("Produto alterado com sucesso.");
                        } else {
                            System.out.println("Produto não encontrado.");
                        }
                        break;
                    case 4:
                        selectedSupplier.listAllProduct();
                        selectedSupplier.removeProduct(sc);

                        break;
                    case 5:
                        System.out.print("Digite o nome do produto: ");
                        String nomeProd = sc.nextLine();
                        Product encontrado = selectedSupplier.findProductByName(nomeProd);
                        if (encontrado != null) {
                            System.out.println("Produto encontrado: " + encontrado.getName() + " - " + encontrado.getDescription());
                        } else {
                            System.out.println("Produto não encontrado.");
                        }
                        break;
                    case 0:
                        System.out.println("Voltando ao menu anterior.");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } while (opcProduto != 0);
        } else {
            System.out.println("Fornecedor inválido.");
            return false;
        }
        return true;
    }

    private boolean menuManageStock() {
        System.out.println("Selecione o fornecedor:");
        for (int i = 0; i < supplierCount; i++) {
            System.out.println(i + " - " + suppliers[i].getName());
        }
        int idxSupplier = sc.nextInt();
        sc.nextLine();

        if (idxSupplier >= 0 && idxSupplier < supplierCount) {
            Supplier selectedSupplier = suppliers[idxSupplier];
            Product[] products = selectedSupplier.getProducts();
            int productCurrIdx = selectedSupplier.getCurrentProductIndex();

            System.out.println("Selecione o produto:");
            for (int i = 0; i < productCurrIdx; i++) {
                System.out.println(i + " - " + products[i].getName());
            }
            int idxProduct = sc.nextInt();
            sc.nextLine();

            if (idxProduct >= 0 && idxProduct < productCurrIdx) {
                Product selectedProduct = products[idxProduct];
                Stock stock = selectedProduct.getStock();
                int opcProduto;
                do {
                    System.out.println("\nGerenciamento de estoque do fornecedor " + selectedSupplier.getName());
                    System.out.println(" 1 - Adicionar estoque");
                    System.out.println(" 2 - Excluir estoque");
                    System.out.println(" 3 - Alterar quantidade");
                    System.out.println(" 4 - Alterar preço");
                    System.out.println(" 5 - Consultar estoque do produto");
                    System.out.println(" 0 - Voltar");
                    opcProduto = sc.nextInt();
                    sc.nextLine();

                    switch (opcProduto) {
                        case 1:
                            selectedProduct.addStock(sc);
                            break;
                        case 2:
                            selectedProduct.removeStock();
                            break;
                        case 3:
                            if (stock != null) {
                                System.out.println("\n" + stock + "\n");
                                System.out.print("Digite a nova quantidade: ");
                                int newQtd = sc.nextInt();
                                sc.nextLine();
                                stock.setQuantity(newQtd);
                                System.out.println("Quantidade alterada com sucesso.");
                            }
                            break;
                        case 4:
                            if (stock != null) {
                                System.out.println("\n" + stock + "\n");
                                System.out.print("Digite o novo preço: ");
                                double newPrice = sc.nextDouble();
                                sc.nextLine();
                                stock.setPrice(newPrice);
                                System.out.println("Preço alterado com sucesso.");
                            }
                            break;
                        case 5:
                            System.out.println("\n" + stock + "\n");
                            break;
                        case 0:
                            System.out.println("Voltando ao menu anterior.");
                            break;
                        default:
                            System.out.println("Opção inválida.");
                    }
                } while (opcProduto != 0);
            } else {
                System.out.println("Produto inválido.");
                return false;
            }
        } else {
            System.out.println("Fornecedor inválido.");
            return false;
        }
        return true;
    }

    private void createTestData() {
        // Create test suppliers
        if (supplierCount < suppliers.length) {
            // Supplier 1
            Supplier supplier1 = new Supplier("Tech Solutions", "tech@solutions.com", "pass123", "11999887766",
                    new Adress("Rua Tech", "123", "Sala 1", "Centro", "01234-567", "São Paulo", "SP"));
            suppliers[supplierCount++] = supplier1;

            // Add products for supplier 1
            Product laptop = new Product("Laptop Pro", "Laptop de alta performance", "laptop.jpg");
            laptop.addStock(15, 5999.99);
            supplier1.addProduct(laptop);

            Product mouse = new Product("Mouse Gamer", "Mouse com 6 botões", "mouse.jpg");
            mouse.addStock(30, 199.99);
            supplier1.addProduct(mouse);

            // Supplier 2
            Supplier supplier2 = new Supplier("Office Supplies", "office@supplies.com", "pass456", "11988776655",
                    new Adress("Rua Office", "456", "Loja 2", "Vila Nova", "04567-890", "São Paulo", "SP"));
            suppliers[supplierCount++] = supplier2;

            // Add products for supplier 2
            Product paper = new Product("Papel A4", "Pacote com 500 folhas", "paper.jpg");
            paper.addStock(100, 24.99);
            supplier2.addProduct(paper);

            Product pen = new Product("Caneta Azul", "Caixa com 50 unidades", "pen.jpg");
            pen.addStock(50, 45.99);
            supplier2.addProduct(pen);

            System.out.println("Dados de teste criados com sucesso!");
        } else {
            System.out.println("Não há espaço para criar fornecedores de teste.");
        }
    }

}
