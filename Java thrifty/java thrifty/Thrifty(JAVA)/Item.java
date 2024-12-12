public abstract class Item {
    private String name;
    private String description;
    private String price;

    public Item(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public abstract String toFileString();

    public abstract String[] toRow();
}
