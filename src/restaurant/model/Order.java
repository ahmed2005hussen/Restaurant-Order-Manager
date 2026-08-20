package restaurant.model;

import java.util.ArrayList;

public class Order {
    private int orderId;
    private String customerName;
    private ArrayList<OrderItem> items;
    private double total;
    private OrderStatus status;

    public Order(int orderId, String customerName, OrderStatus status) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.items = new ArrayList<>();
        this.total = 0.0;
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void addItem(OrderItem orderItem) {
        items.add(orderItem);
        calculateTotal();
    }

    public boolean removeItem(int ItemId) {

        for (OrderItem item : items) {
            if (item.getItem().getId() == ItemId) {
                items.remove(item);
                calculateTotal();
                return true;
            }
        }
        return false;
    }

    public void calculateTotal() {
        total = 0.0;

        for (OrderItem orderItem : items) {
            total += orderItem.calculateSubtotal();
        }
    }

    public void updateStatus(OrderStatus status) {
        this.status = status;
    }

    public void displayOrder() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Order Status: " + status);

        System.out.println("Items:");

        for (OrderItem orderItem : items) {
            System.out.println(
                    orderItem.getItem().getName()
                            + " x " + orderItem.getQuantity()
                            + " = " + orderItem.calculateSubtotal()
            );
        }

        System.out.println("Total: " + total);
    }

}