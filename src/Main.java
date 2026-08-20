import restaurant.model.MenuItem;
import restaurant.model.Order;
import restaurant.service.Restaurant;

import java.util.Scanner;

class Main {
    private final Scanner sc = new Scanner(System.in);
    private final Restaurant restaurant = new Restaurant();

    int menu() {
        System.out.println("""
                
                Restaurant Menu:
                1. Add Menu Item
                2. Remove Menu Item
                3. Display Menu
                4. Search Menu Item
                5. Create Order
                6. Add Item to Order
                7. Remove Item from Order
                8. Display Order
                9. Add Order to Kitchen Queue
                10. Process Next Order
                11. Search Order
                12. Check Order Status
                13. Display Completed Orders
                14. Cancel Order
                15. Exit"""
        );

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        return choice;

    }

    void addMenuItem() {

        System.out.print("Enter Item ID: ");
        int itemId = sc.nextInt();
        sc.nextLine();

        System.out.print("\nEnter Item name: ");
        String name = sc.nextLine();

        System.out.print("\nEnter Item Price: ");
        double price = sc.nextDouble();
        sc.nextLine();

        if(price < 0){
            System.out.println("Price can not be zero ");
            System.out.println("-----------------");
            return;
        }

        System.out.print("\nEnter Item Category: ");
        String category = sc.nextLine();

        MenuItem item = new MenuItem(itemId, name, price, category);
        boolean created = restaurant.addMenuItem(item);

        if (created) {
            System.out.println("Menu item added ");
        } else {
            System.out.println("Used id, try again with another id");
        }
        System.out.println("-----------------------------------");
    }

    void removeMenuItem() {

        System.out.print("Enter Item ID: ");
        int itemId = sc.nextInt();
        sc.nextLine();

        boolean deleted = restaurant.removeMenuItem(itemId);
        if (deleted) {
            System.out.println("Menu item removed");
        } else {
            System.out.println("Menu item not found ");
        }

        System.out.println("-----------------------------");

    }

    void searchMenuItem() {
        System.out.print("Enter Item ID: ");
        int itemId = sc.nextInt();
        sc.nextLine();

        MenuItem item = restaurant.findMenuItemById(itemId);
        System.out.println(item == null ? "Menu item not found " : item);
        System.out.println("---------------------------");
    }

    void createOrder() {
        System.out.print("Enter order ID: ");
        int orderId = sc.nextInt();
        sc.nextLine();

        System.out.print("\nEnter Customer name: ");
        String name = sc.nextLine();

        boolean created = restaurant.createOrder(orderId, name);

        if (created) {
            System.out.println("Order created ");
        } else {
            System.out.println("ID already exists, try again with different id");
        }
        System.out.println("-----------------------");
    }

    void addItemToOrder() {

        System.out.print("Enter order ID: ");
        int orderId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter menu item ID: ");
        int menuItemId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();

        System.out.println(restaurant.addItemToOrder(orderId, menuItemId, quantity));

        System.out.println("------------------------");

    }

    void removeItemFromOrder() {

        System.out.print("Enter order ID: ");
        int orderId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter menu item ID: ");
        int menuItemId = sc.nextInt();
        sc.nextLine();

        System.out.println(restaurant.removeItemFromOrder(orderId, menuItemId));

        System.out.println("------------------------");

    }

    void showOrder() {
        System.out.print("Enter order ID: ");
        int orderId = sc.nextInt();
        sc.nextLine();

        Order order = restaurant.findOrder(orderId);
        if (order == null)
            System.out.println("Order not found.");
        else
            order.displayOrder();

        System.out.println("-------------------------");
    }

    void addOrderToKitchen() {

        System.out.print("Enter order ID: ");
        int orderId = sc.nextInt();
        sc.nextLine();

        System.out.println(restaurant.addOrderToKitchen(orderId));

        System.out.println("-----------------------");
    }

    void checkStatus() {

        System.out.print("Enter order ID: ");
        int orderId = sc.nextInt();
        sc.nextLine();

        Order order = restaurant.findOrder(orderId);
        if (order == null)
            System.out.println("Order not found ");
        else
            System.out.println("Order status: " + order.getStatus());

        System.out.println("-----------------------------");
    }

    void cancelOrder(){
        System.out.print("Enter order ID: ");
        int orderId = sc.nextInt();
        sc.nextLine();

        System.out.println(restaurant.cancelOrder(orderId));

    }

    void main(String[] args) {
        System.out.println("Welcome to the Restaurant Order Management System");
        System.out.println("--------------------------------------------------");

        loop:
        while (true) {
            int chioce = menu();
            switch (chioce) {
                case 1 -> addMenuItem();

                case 2 -> removeMenuItem();

                case 3 -> restaurant.displayMenu();

                case 4 -> searchMenuItem();

                case 5 -> createOrder();

                case 6 -> addItemToOrder();

                case 7 -> removeItemFromOrder();

                case 8, 11 -> showOrder();

                case 9 -> addOrderToKitchen();

                case 10 -> restaurant.processNextOrder();

                case 12 -> checkStatus();

                case 13 -> restaurant.displayCompletedOrders();

                case 14 -> cancelOrder();

                case 15 -> {
                    break loop;
                }
                default -> System.out.println("Invalid choice. Enter a number from 1 to 15 ");
            }
        }
        System.out.println("Goodbye!");
    }
}
