public class Stock {
    private int quantity;
    private double price;

    public Stock(int quantity, double price) {
        this.quantity = quantity;
        this.price = price;
    }

    public Stock() {}

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Estoque: " + quantity + "\n" +
                "Preço: " + price + "\n";
    }
}
