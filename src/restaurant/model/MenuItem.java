package restaurant.model;

public class MenuItem {
    private int id;
    private String name;
    private double price;
    private String category;

    public MenuItem(int id , String name, double price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) return;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "\nMenu Item" +
                "   \nID: " + id +
                "   \nName: " + name +
                "   \nPrice: " + price +
                "   \nCategory: " + category;
    }
}
