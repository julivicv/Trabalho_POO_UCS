import java.util.Scanner;

public class Product {
    private String name;
    private String description;
    private String imgURL;
    private Stock stock;

    public Product(String name, String description, String imgURL) {
        this.name = name;
        this.description = description;
        this.imgURL = imgURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public Stock getStock() {
        return stock;
    }

    public void addStock(int quantity, double price) {
        if (stock == null) {
            stock = new Stock(quantity, price);
        }
    }

    public void addStock(Scanner sc) {
        if (stock == null) {
            System.out.println("=== Cadastro de Estoque ===");

            System.out.print("Digite o quantidade de estoque disponível: ");
            int qtd = sc.nextInt();

            System.out.print("Digite o preço do produto: ");
            double price = sc.nextDouble();
            sc.nextLine();

            stock = new Stock(qtd, price);
        } else {
            System.out.println("Estoque já cadastrado");
        }
    }

    public void removeStock() {
        this.stock = null;
        System.out.println("Estoque removido do produto.");
    }

    @Override
    public String toString() {
        return "Produto: " + name + "\n" +
                "Descrição: " + description + "\n" +
                "Imagem: " + imgURL + "\n" +
                (stock != null
                        ? stock.toString()
                        : "Sem estoque cadastrado"
                ) + "\n";
    }
}
