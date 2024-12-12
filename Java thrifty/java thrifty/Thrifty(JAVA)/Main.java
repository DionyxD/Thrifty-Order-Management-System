import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ThriftManager thriftManager = new ThriftManager("tDB.txt");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            clearConsole();
            System.out.println("\n--- Thrifty Management System ---");
            thriftManager.displayItems();
            System.out.println("\n1. ADD ITEM");
            System.out.println("2. MARK ITEM AS SOLD");
            System.out.println("3. REMOVE ITEM");
            System.out.println("4. EXIT PROGRAM");
            System.out.print("CHOOSE AN OPTION: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("ENTER ITEM NAME: ");
                    String name = scanner.nextLine();
                    System.out.print("ENTER ITEM'S DESCRIPTION: ");
                    String description = scanner.nextLine();
                    System.out.print("ENTER ITEM'S PRICE: ");
                    String price = scanner.nextLine();

                    Thrift thrift = new Thrift(name, description, price);
                    thriftManager.addItem(thrift);
                    System.out.println("ITEM ADDED SUCCESFULLY!");
                    break;

                case "2":
                    System.out.print("ENTER SOLD ITEM: ");
                    String soldItem = scanner.nextLine();
                    thriftManager.markAsCompleted(soldItem);
                    break;

                case "3":
                    System.out.print("ENTER ITEM TO REMOVE: ");
                    String deleteItem = scanner.nextLine();
                    thriftManager.removeItem(new Thrift(deleteItem, "", ""));
                    break;

                case "4":
                    System.out.println("HOPE WE SERVED YOU WELL!");
                    scanner.close();
                    return;

                default:
                    System.out.println("INVALID CHOICE, PLEASE TRY AGAIN.");
            }
        }
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J"); // ANSI escape code
        System.out.flush();
    }

}
