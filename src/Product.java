public class Product {
    private String name;
    private String description;
    private String imgURL;
    private Supplier supplier;

    public Product(String name, String description, String imgURL, Supplier supplier) {
        this.name = name;
        this.description = description;
        this.imgURL = imgURL;
        this.supplier = supplier;
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
    
    @Override
    public String toString() {
        return "Produto: " + name + "\n" +
               "Descrição: " + description + "\n" +
               "Imagem: " + imgURL + "\n";
    }
}
