public class Thrift extends Item {
    private boolean completed;

    public Thrift(String name, String description, String price) {
        super(name, description, price);
        this.completed = false;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markAsCompleted() {
        this.completed = true;
    }

    @Override
    public String toFileString() {
        return getName() + "|" + getDescription() + "|" + getPrice() + "|" + completed;
    }

    @Override
    public String[] toRow() {
        String status = completed ? "SOLD" : "FOR SALE";
        return new String[] { getName(), getDescription(), getPrice(), status };
    }

    public static Thrift fromFileString(String line) {
        String[] parts = line.split("\\|");
        Thrift thrift = new Thrift(parts[0], parts[1], parts[2]);
        if (Boolean.parseBoolean(parts[3])) {
            thrift.markAsCompleted();
        }
        return thrift;
    }
}
