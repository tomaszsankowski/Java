package org.example;

public class Main {
    public static void main(String[] args) {
        MageRepository mageRepository = new MageRepository();
        MageController mageController = new MageController(mageRepository);
        System.out.println(mageController.save("Gandalf", 100));
        System.out.println(mageController.save("Saruman", 99));
        System.out.println(mageController.save("Radagast", 98));
        System.out.println(mageController.save("", -2));
        System.out.println(mageController.save("Gandalf", 3));
        System.out.println(mageController.find("Gandalf"));
        System.out.println(mageController.find("Saruman"));
        System.out.println(mageController.find("Radagast"));
        System.out.println(mageController.find(""));
        System.out.println(mageController.find("Sauron"));
        System.out.println(mageController.delete("Gandalf"));
        System.out.println(mageController.delete("Saruman"));
        System.out.println(mageController.delete("Radagast"));
        System.out.println(mageController.delete(""));
        System.out.println(mageController.delete("Sauron"));
    }
}