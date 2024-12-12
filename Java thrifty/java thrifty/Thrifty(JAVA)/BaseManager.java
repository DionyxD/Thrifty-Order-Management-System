import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseManager<T> {
    protected List<T> items;
    protected String filePath;

    public BaseManager(String filePath) {
        this.filePath = filePath;
        this.items = new ArrayList<>();
        loadItemsFromFile();
    }

    public void addItem(T item) {
        items.add(item);
        saveItemsToFile();
    }

    public void removeItem(T item) {
        items.remove(item);
        saveItemsToFile();
    }

    public abstract void displayItems();

    protected abstract T fromFileString(String line);

    protected abstract String toFileString(T item);

    private void loadItemsFromFile() {
        File file = new File(filePath);
        if (!file.exists())
            return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                items.add(fromFileString(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void saveItemsToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (T item : items) {
                bw.write(toFileString(item));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
