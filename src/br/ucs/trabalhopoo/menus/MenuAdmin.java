package br.ucs.trabalhopoo.menus;

import br.ucs.trabalhopoo.classes.Product;
import br.ucs.trabalhopoo.classes.Stock;
import br.ucs.trabalhopoo.users.Supplier;

import java.util.Scanner;

public class MenuAdmin extends Menu {

    public MenuAdmin(Supplier[] suppliers, int supplierCount, Scanner sc) {
        super(suppliers, supplierCount, sc);
    }

    @Override
    public void menu() {
        int opc;
        do {
            System.out.println("\nDigite o número da operação desejada:");
            System.out.println(" 1 - Gerenciar fornecedores");
            System.out.println(" 2 - Gerenciar produtos de fornecedor");
            System.out.println(" 3 - Gerenciar estoque de produtos");
            System.out.println(" 4 - Criar dados de teste");
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
                    super.createTestData();
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

    @Override
    protected boolean menuManageSuppliers() {
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
                    this.addSupplier();
                    break;
                case 2:
                    this.updateSupplier();
                    break;
                case 3:
                    this.removeSupplier();
                    break;
                case 4:
                    this.listSuppliers();
                    break;
                case 5:
                    this.listSuppliersByIdx();
                    break;
                case 6:
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

    @Override
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
                System.out.println("\nGerenciamento de produtos do fornecedor " + selectedSupplier.getName());
                System.out.println(" 1 - Adicionar produto");
                System.out.println(" 2 - Listar produtos");
                System.out.println(" 3 - Alterar produto");
                System.out.println(" 4 - Excluir produto");
                System.out.println(" 5 - Consultar produto por nome");
                System.out.println(" 6 - Consultar produto por código");
                System.out.println(" 0 - Voltar");
                opcProduto = sc.nextInt();
                sc.nextLine();

                switch (opcProduto) {
                    case 1:
                        this.addProduct(selectedSupplier);
                        break;
                    case 2:
                        this.listProducts(selectedSupplier);
                        break;
                    case 3:
                        this.updateProduct(selectedSupplier);
                        break;
                    case 4:
                        this.removeProduct(selectedSupplier);
                        break;
                    case 5:
                        this.listProductsByName(selectedSupplier);
                        break;
                    case 6:
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

    @Override
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
                    System.out.println("\nGerenciamento de estoque do produto " + selectedProduct.getName());
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
                            this.addStock(selectedProduct);
                            break;
                        case 2:
                            this.removeStock(selectedProduct);
                            break;
                        case 3:
                            this.updateStockQtd(stock);
                            break;
                        case 4:
                            this.updateStockPrice(stock);
                            break;
                        case 5:
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
}
