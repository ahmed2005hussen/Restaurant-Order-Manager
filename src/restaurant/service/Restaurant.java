package restaurant.service;

import restaurant.model.MenuItem;
import restaurant.model.Order;
import restaurant.model.OrderItem;
import restaurant.model.OrderStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Restaurant {
    private final ArrayList<MenuItem> menu = new ArrayList<>();
    private final LinkedList<Order> kitchenQueue = new LinkedList<>();
    private final HashMap<Integer, Order> orders = new HashMap<>();
    private final LinkedHashMap<Integer, Order> completedOrders = new LinkedHashMap<>();

    public MenuItem findMenuItemById(int id) {
        for (MenuItem item : menu) {
            if (item.getId() == id)
                return item;
        }
        return null;
    }

    public boolean addMenuItem(MenuItem item) {
        if (findMenuItemById(item.getId()) != null)
            return false;

        menu.add(item);
        return true;
    }

    public boolean removeMenuItem(int id) {
        MenuItem menuItem = findMenuItemById(id);
        if (menuItem == null)
            return false;

        menu.remove(menuItem);
        return true;
    }

    public void displayMenu() {
        if (menu.isEmpty()) {
            System.out.println("Menu is empty");
            return;
        }
        System.out.println("\n Menu Items");

        for (MenuItem item : menu) {
            System.out.println(item);
        }
    }

    public boolean createOrder(int id, String customer) {
        if (orders.containsKey(id))
            return false;
        orders.put(id, new Order(id, customer, OrderStatus.PENDING));
        return true;
    }

    public Order findOrder(int id) {
        return orders.get(id);
    }

    private boolean canModifiedOrder(Order order) {
        return order.getStatus() != OrderStatus.COMPLETED
                && order.getStatus() != OrderStatus.CANCELLED;
    }

    public String addItemToOrder(int orderId, int itemId, int quantity) {

        if (quantity <= 0)
            return "Quantity should be greater than 0 ";

        Order order = orders.get(orderId);

        if (order == null)
            return "Order not found";

        if (!canModifiedOrder(order))
            return "Can not add item to the order because it is: " + order.getStatus();

        MenuItem item = findMenuItemById(itemId);
        if (item == null)
            return "Menu item not found";

        OrderItem orderItem = new OrderItem(item, quantity);

        order.addItem(orderItem);

        return "Item added , Total is: " + order.getTotal();
    }

    public String removeItemFromOrder(int orderId, int itemId) {
        Order order = orders.get(orderId);

        if (order == null)
            return "Order not found";

        if (!canModifiedOrder(order))
            return "Can not remove item from the order because it is: " + order.getStatus();

        boolean deleted = order.removeItem(itemId);

        if (deleted) {
            return "Item removed, total is: " + order.getTotal();
        }
        return "That item is not in this order ";

    }

    public String addOrderToKitchen(int orderId) {
        Order order = orders.get(orderId);
        if (order == null)
            return "Order not found";

        if (order.getStatus() == OrderStatus.IN_KITCHEN)
            return "already in the kitchen ";

        if (!canModifiedOrder(order))
            return "Can not add to kitchen because it is: " + order.getStatus();

        order.updateStatus(OrderStatus.IN_KITCHEN);

        kitchenQueue.addLast(order);

        return "Order added to the kitchen.";
    }

    public void processNextOrder() {

        if (kitchenQueue.isEmpty()) {
            System.out.println("The kitchen queue is empty ");
            return;
        }

        Order order = kitchenQueue.peekFirst();

        if (order.getItems().isEmpty()) {
            System.out.println("This order has no items and cannot be processed ");
            return;
        }

        kitchenQueue.removeFirst();

        order.updateStatus(OrderStatus.COMPLETED);

        completedOrders.put(order.getOrderId(), order);
        System.out.println("Order #" + order.getOrderId() + " completed.");
        System.out.println("--------------------------------");
    }

    public void displayCompletedOrders() {
        if (completedOrders.isEmpty()) {
            System.out.println("There are no completed orders.");
            return;
        }
        System.out.println("\nCompleted Orders");

        for (Order order : completedOrders.values()) {
            order.displayOrder();
        }
    }

    public String cancelOrder(int orderId) {
        Order order = orders.get(orderId);

        if (order == null)
            return "Order not found";

        if (order.getStatus() == OrderStatus.COMPLETED)
            return "A completed order cannot be cancelled.";

        if (order.getStatus() == OrderStatus.CANCELLED)
            return "This order is already cancelled.";

        if (order.getStatus() == OrderStatus.IN_KITCHEN)
            kitchenQueue.remove(order);

        order.updateStatus(OrderStatus.CANCELLED);

        return "Order cancelled.";
    }
}
