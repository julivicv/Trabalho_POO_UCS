package br.ucs.trabalhopoo.menus;

import java.util.Scanner;

import br.ucs.trabalhopoo.classes.*;
import br.ucs.trabalhopoo.users.*;

public class Menu {
    private static final Admin adm = new Admin("a", "a");
    protected final Scanner sc;
    protected User user;
    protected Supplier[] suppliers;
    protected int supplierCount;

    public Menu(Supplier[] suppliers, int supplierCount, Scanner sc) {
        this.suppliers = suppliers;
        this.supplierCount = supplierCount;
        this.sc = sc;
    }

    public void menu() {
        int opc;
        do {
            System.out.println("Digite o número da operação desejada:");
            System.out.println(" 1 - Logar como Admin");
            System.out.println(" 2 - Consultar fornecedores");
            System.out.println(" 3 - Consultar produtos de fornecedor");
            System.out.println(" 4 - Consultar estoque de produtos");
            System.out.println(" 5 - Criar dados de teste");
            System.out.println(" 0 - Sair");

            opc = sc.nextInt();
            boolean success = false;
            sc.nextLine();

            switch (opc) {
                case 1:
                    while (!adm.isLoggedIn()) {
                        adm.login(sc);
                    }
                    this.menuAdm();
                    break;
                case 2:
                    while (!success) {
                        success = this.menuManageSuppliers();
                    }
                    break;
                case 3:
                    while (!success) {
                        success = this.menuManageProducts();
                    }
                    break;
                case 4:
                    while (!success) {
                        success = this.menuManageStock();
                    }
                    break;
                case 5:
                    this.createTestData();
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
        MenuAdmin menuAdmin = new MenuAdmin(suppliers, supplierCount, sc);
        menuAdmin.menu();
        adm.logout();
    }

    protected boolean menuManageSuppliers() {
        int opcFornecedor;
        do {
            System.out.println("\nDigite o número da operação desejada para fornecedores:");
            System.out.println(" 1 - Listar fornecedores");
            System.out.println(" 2 - Consultar fornecedor por código");
            System.out.println(" 3 - Consultar fornecedor por nome");
            System.out.println(" 0 - Voltar");

            opcFornecedor = sc.nextInt();
            sc.nextLine();

            switch (opcFornecedor) {
                case 1:
                    this.listSuppliers();
                    break;
                case 2:
                    this.listSuppliersByIdx();
                    break;
                case 3:
                    this.listSuppliersByName();
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

    protected void addSupplier() {
        if (supplierCount < suppliers.length) {
            Supplier newSupplier = new Supplier(sc);
            suppliers[supplierCount++] = newSupplier;
            System.out.println("Fornecedor cadastrado com sucesso!");
        } else {
            System.out.println("Limite de fornecedores atingido.");
        }
    }

    protected void updateSupplier() {
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
    }

    protected void removeSupplier() {
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
    }

    protected void listSuppliers() {
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
    }

    protected void listSuppliersByIdx() {
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
    }

    protected void listSuppliersByName() {
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
    }

    protected boolean menuManageProducts() {
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
                System.out.println("\nListagem de produtos do fornecedor " + selectedSupplier.getName());
                System.out.println(" 1 - Listar produtos");
                System.out.println(" 2 - Consultar produto por nome");
                System.out.println(" 3 - Consultar produto por código");
                System.out.println(" 0 - Voltar");
                opcProduto = sc.nextInt();
                sc.nextLine();

                switch (opcProduto) {
                    case 1:
                        this.listProducts(selectedSupplier);
                        break;
                    case 2:
                        this.listProductsByName(selectedSupplier);
                        break;
                    case 3:
                        this.listProductsByIdx(selectedSupplier);
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

    protected void addProduct(Supplier supplier) {
        supplier.addProduct(sc);
    }

    protected void listProducts(Supplier supplier) {
        supplier.listAllProduct();
    }

    protected void updateProduct(Supplier supplier) {
        supplier.listAllProduct();
        System.out.print("Digite o índice do produto a alterar: ");
        int idxAlt = sc.nextInt();
        sc.nextLine();
        Product prodAlt = supplier.getProductByIndex(idxAlt);
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
    }

    protected void removeProduct(Supplier supplier) {
        supplier.listAllProduct();
        supplier.removeProduct(sc);
    }

    protected void listProductsByName(Supplier supplier) {
        System.out.print("Digite o nome do produto: ");
        String nomeProd = sc.nextLine();
        Product encontrado = supplier.findProductByName(nomeProd);
        if (encontrado != null) {
            System.out.println(encontrado + "\n");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    protected void listProductsByIdx(Supplier supplier) {
        System.out.println("Lista de produtos do fornecedor " + supplier.getName() + ":");
        supplier.listAllProduct();
        System.out.print("Digite o código do produto: ");
        int idx = sc.nextInt();
        sc.nextLine();
        Product encontrado = supplier.getProductByIndex(idx);
        if (encontrado != null) {
            System.out.println(encontrado + "\n");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    protected boolean menuManageStock() {
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
                    System.out.println("\nListagem de estoque do produto " + selectedProduct.getName());
                    System.out.println(" 1 - Consultar estoque do produto");
                    System.out.println(" 0 - Voltar");
                    opcProduto = sc.nextInt();
                    sc.nextLine();

                    switch (opcProduto) {
                        case 1:
                            this.listStock(stock);
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

    protected void addStock(Product product) {
        product.addStock(sc);
    }

    protected void removeStock(Product product) {
        product.removeStock();
    }

    protected void updateStockQtd(Stock stock) {
        if (stock != null) {
            System.out.println("\n" + stock + "\n");
            System.out.print("Digite a nova quantidade: ");
            int newQtd = sc.nextInt();
            sc.nextLine();
            stock.setQuantity(newQtd);
            System.out.println("Quantidade alterada com sucesso.");
        }
    }

    protected void updateStockPrice(Stock stock) {
        if (stock != null) {
            System.out.println("\n" + stock + "\n");
            System.out.print("Digite o novo preço: ");
            double newPrice = sc.nextDouble();
            sc.nextLine();
            stock.setPrice(newPrice);
            System.out.println("Preço alterado com sucesso.");
        }
    }

    protected void listStock(Stock stock) {
        System.out.println("\n" + stock + "\n");
    }

    protected void createTestData() {
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
