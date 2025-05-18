package main;
import java.util.Scanner;

public class Supplier extends User {
    private String name;
    private String phone;
    private Adress adress;
    private static final String ROLE = "supplier";
    private Product products[] = new Product[20];
    private int currentProductIndex = 0;

    public Supplier(Scanner sc) {
        initializeSupplier(sc);
    }

    private void initializeSupplier(Scanner sc) {
        System.out.println("=== Registro de fornecedor ===");

        System.out.print("Digite o nome do fornecedor: ");
        this.name = sc.nextLine();

        System.out.print("Digite o email do fornecedor: ");
        this.email = sc.nextLine();

        System.out.print("Digite uma senha para o fornecedor: ");
        this.password = sc.nextLine();

        System.out.print("Digite o número de telefone: ");
        this.phone = sc.nextLine();

        System.out.println("\n=== Endereço ===");
        System.out.print("Digite a rua: ");
        String street = sc.nextLine();

        System.out.print("Digite o número: ");
        String number = sc.nextLine();

        System.out.print("Digite o complemento: ");
        String complement = sc.nextLine();

        System.out.print("Digite o bairro: ");
        String neighborhood = sc.nextLine();

        System.out.print("Digite o cep: ");
        String zipCode = sc.nextLine();

        System.out.print("Digite a cidade: ");
        String city = sc.nextLine();

        System.out.print("Digite o estado: ");
        String state = sc.nextLine();

        this.adress = new Adress(street, number, complement, neighborhood, zipCode, city, state);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public void addProduct(Scanner sc) {
        if (currentProductIndex >= products.length) {
            System.out.println("Limite máximo de produtos atingido!");
            return;
        }

        System.out.println("=== Cadastro de Produto ===");

        System.out.print("Digite o nome do produto: ");
        String name = sc.nextLine();

        System.out.print("Digite a descrição do produto: ");
        String description = sc.nextLine();

        System.out.print("Digite o link para a imagem do produto: ");
        String imgURL = sc.nextLine();
        sc.nextLine();

        products[currentProductIndex] = new Product(name, description, imgURL, this);
        currentProductIndex++;

        System.out.println("Produto cadastrado com sucesso!");
    }
    
    public void removeProduct(Scanner sc) {
    	if (currentProductIndex == 0) {
            System.out.println("Não exite produto para ser removido");
            return;
        }
        System.out.print("Digite o índice do produto a remover: ");
        int idxDel = sc.nextInt();
        
        for (int i = idxDel; i < this.currentProductIndex - 1; i++) {
        	this.products[i] = this.products[i + 1];
        }
        this.products[--this.currentProductIndex] = null;
        System.out.println("Produto removido.");
    }
    
    public void listAllProduct() {
    	  System.out.println("Produtos cadastrados:");
    	    for (int i = 0; i < currentProductIndex; i++) {
    	        System.out.println(i + " - " + products[i].toString());
    	    }
    }
    
    public Product getProductByIndex(int index) {
        if (index >= 0 && index < currentProductIndex) {
            return products[index];
        }
        return null;
    }
    
    public Product findProductByName(String name) {
        for (int i = 0; i < currentProductIndex; i++) {
            if (products[i].getName().equalsIgnoreCase(name)) {
                return products[i];
            }
        }
        return null;
    }

    @Override
    public String getRole() {
        return ROLE;
    }

    @Override
    public String toString() {
        return "Fornecedor: " + this.name + "\n" +
                " Telefone: " + this.phone + "\n" +
                " Email: " + this.email + "\n" +
                this.adress.toString();
    }
}