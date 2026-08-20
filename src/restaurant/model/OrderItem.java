package restaurant.model;

public class OrderItem {
    private final MenuItem item;
    private final int quantity;

    public OrderItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public MenuItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public double calculateSubtotal() {
        return item.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return "    \nOrderItem " +
                "       \nItem: " + item +
                "       \nQuantity: " + quantity +
                "       \nSubTotal: " + calculateSubtotal();
    }
}
