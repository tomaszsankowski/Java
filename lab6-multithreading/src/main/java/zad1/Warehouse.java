package zad1;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    List<Fruit> products;
    int capacity;

    public Warehouse(int capacity) {
        products = new ArrayList<>();
        this.capacity = capacity;
    }

    public List<Fruit> getProducts() {
        return products;
    }

    public void setProducts(List<Fruit> products) {
        this.products = products;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void addProduct(Fruit product, int count, int number, int fruit_num) {
        int taken = 0;
        while(taken < count && products.size() < capacity) {
            products.add(product);
            taken++;
        }

        String fruit_name = switch (fruit_num) {
            case 0 -> "Apple";
            case 1 -> "Banana";
            case 2 -> "Strawberry";
            default -> throw new IllegalStateException(STR."Unexpected value: \{fruit_num}");
        };

        System.out.println(STR."Producer \{number} produced \{count} of \{fruit_name} (warehouse took \{taken}.)");

        this.printProducts();
    }

    public synchronized int getProduct(Class<? extends Fruit> fruitClass, int count, int number, int fruit_num) {
        int taken = 0;
        List<Fruit> toRemove = new ArrayList<>();
        for(Fruit product : products) {
            if(fruitClass.isInstance(product) && taken < count) {
                toRemove.add(product);
                taken++;
            }
        }

        products.removeAll(toRemove);

        String fruit_name = switch (fruit_num) {
            case 0 -> "Apple";
            case 1 -> "Banana";
            case 2 -> "Strawberry";
            default -> throw new IllegalStateException(STR."Unexpected value: \{fruit_num}");
        };

        System.out.println(STR."Consumer \{number} consumed \{taken}/\{count} of \{fruit_name}.");

        this.printProducts();

        return taken;
    }

    public void printProducts() {
        System.out.println("Products:");
        for(Fruit product : products) {
            System.out.print(STR."\{product} ");
        }
        System.out.println();
    }
}
