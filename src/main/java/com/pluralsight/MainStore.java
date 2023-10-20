package com.pluralsight;
import java.util.Scanner;
public class MainStore {
    public static void main(String[] args) {
        OnlineStore onlineStore = new OnlineStore();
        onlineStore.loadProducts("src/main/resources/products.csv");

        Scanner scanProductItems = new Scanner(System.in);
        boolean exitStore = false;

        while (!exitStore) {
            onlineStore.displayHomeScreen();
            System.out.print("What would you like to do?\n" +
                    "Enter here: ");
            int prompt = scanProductItems.nextInt();
            scanProductItems.nextLine();

            switch (prompt) {
                case 1:
                    onlineStore.displayProduct();
                    boolean returnHome = false;
                    while (!returnHome) {
                        System.out.print("Enter the Keyword for item or press 'return' to go back to HomeScreen");
                        String kWord = scanProductItems.nextLine();
                        if (kWord.equalsIgnoreCase("return")){
                            returnHome = true;
                        }else {
                            onlineStore.searchProdByName(kWord);
                        }
                    }
                    boolean addToCart = false;
                    while (!addToCart){
                        System.out.println("Enter Product name to add to cart or 'return' to go back to HomeScreen: ");
                        String userInput = scanProductItems.nextLine();
                        if (userInput.equalsIgnoreCase("return")) {
                            addToCart = true;
                        }else {
                            try {
                                int index = Integer.parseInt(userInput) - 1;
                                onlineStore.addToCart(index);
                            }catch (NumberFormatException error) {
                                System.out.println("ERROR! ERROR!" +
                                        "Try again:" +
                                        "Enter valid prompt or press 'return' to go back to HomeScreen");
                            }
                        }
                    }
                    break;
                case 2:
                    onlineStore.displayCart();
                    boolean cartOptions = false;
                    while (!cartOptions) {
                        System.out.println("Enter cart item name to remove or 'return' to go back to HomeScreen");
                        String userInput = scanProductItems.nextLine();

                        if (userInput.equalsIgnoreCase("return")) {
                            cartOptions = true;
                        }else if (userInput.equalsIgnoreCase("checkout")) {
                            System.out.println("checkout\n" +
                                    "Thank you for shopping here with us!");
                            exitStore = true;
                            cartOptions = true;
                        }else {
                            try {
                                int index = Integer.parseInt(userInput) - 1;
                                onlineStore.removeCartItems(index);
                            }catch (NumberFormatException error) {
                                System.out.println("ERROR! ERROR!\n" +
                                        "Try again:\n" +
                                        "Enter valid prompt, 'checkout', or 'return' to go back to HomeScreen");
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("Thank you for shopping at our OnlineStore!\n" +
                            "Goodbye, have a great day!");
                    exitStore = true;
                    break;
                default:
                    System.out.println("Invalid prompt!\n" +
                            "Please enter the following prompt #(1, 2, or 3).");
                    break;
            }
        }
        scanProductItems.close();
    }
}
