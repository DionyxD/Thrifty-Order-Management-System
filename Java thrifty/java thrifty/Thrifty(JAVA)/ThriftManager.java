import java.util.ArrayList;
import java.util.List;

public class ThriftManager extends BaseManager<Thrift> {

    public ThriftManager(String filePath) {
        super(filePath);
    }

    @Override
    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("No Items available.");
            return;
        }

        String[] headers = { "Item Name", "Description", "Price", "Status" };
        ArrayList<String[]> rows = new ArrayList<>();
        for (Thrift thrift : items) {
            rows.add(thrift.toRow());
        }
        formatAsTable(headers, rows);
    }

    @Override
    protected Thrift fromFileString(String line) {
        return Thrift.fromFileString(line);
    }

    @Override
    protected String toFileString(Thrift item) {
        return item.toFileString();
    }

    public void markAsCompleted(String name) {
        for (Thrift thrift : items) {
            if (thrift.getName().equalsIgnoreCase(name)) {
                thrift.markAsCompleted();
                saveItemsToFile();
                System.out.println("Item marked as sold: " + thrift.getName());
                return;
            }
        }
        System.out.println("Item not found: " + name);
    }

    private void formatAsTable(String[] headers, List<String[]> rows) {
        int[] colWidths = new int[headers.length];
        for (int i = 0; i < headers.length; i++) {
            colWidths[i] = headers[i].length();
            for (String[] row : rows) {
                colWidths[i] = Math.max(colWidths[i], row[i].length());
            }
        }

        printRow(headers, colWidths);
        printSeparator(colWidths);

        for (String[] row : rows) {
            printRow(row, colWidths);
        }
    }

    private void printRow(String[] row, int[] colWidths) {
        for (int i = 0; i < row.length; i++) {
            System.out.print(padRight(row[i], colWidths[i]) + " | ");
        }
        System.out.println();
    }

    private void printSeparator(int[] colWidths) {
        for (int width : colWidths) {
            System.out.print("-".repeat(width) + "-+-");
        }
        System.out.println();
    }

    private String padRight(String text, int length) {
        return String.format("%-" + length + "s", text);
    }
}
