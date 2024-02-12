public class Item {
    String name;
    boolean expense;
    int quantity;
    long price;

    public Item(String name, boolean expense, int quantity, long price) {
        this.name = name;
        this.expense = expense;
        this.quantity = quantity;
        this.price = price;
    }

    long getTotal () {
        return quantity * price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", expense=" + expense +
                ", quantity=" + quantity +
                ", sum=" + price +
                '}';
    }
}