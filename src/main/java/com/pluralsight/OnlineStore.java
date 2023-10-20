package com.pluralsight;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class OnlineStore {
    private List <Product> products;
    private List <Product> cart;

    public OnlineStore(){
        products = new ArrayList<>();
        cart = new ArrayList<>();
    }
    public void loadProducts(String fileName) {
        try (BufferedReader buffReader = new BufferedReader(new FileReader(fileName))){
            String storeStuff;
            while ((storeStuff = buffReader.readLine()) != null){
                String[] parts = storeStuff.split("\\|");
                if (!parts[0].contains("SKU")) {
                    String sku = parts[0];
                    String pName = parts[1];
                    double prodPrice = Double.parseDouble(parts[2]);
                    String departmentStore = parts[3];
                    products.add(new Product(sku, pName, prodPrice, departmentStore));
                }
            }
        }catch (IOException error){
            error.printStackTrace();
        }
    }
    public void displayHomeScreen(){
        System.out.print("Hello There!\n" +
                "Welcome to the Online Store!\n" +
                "Please choose from the following options:");
        System.out.print("1) Display products");
        System.out.print("2) Display cart items");
        System.out.print("3) Exit");
    }
    public void displayProduct(){
        System.out.println("Hi Welcome,\n" +
                "Here are our lists of products");
        for (int i = 0; i <products.size(); i++){
            Product product = products.get(i);
            System.out.println(product.getSKU() + "|" + product.getName() + "| $" + product.getPrice() + "|" + product.getDepartment());
        }
    }
    public void  searchProdByName (String kWord) {
        System.out.println("Search Results");
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(kWord.toLowerCase())) {
                System.out.println(product.getSKU() + "|" + product.getName() + "|$" + product.getPrice() + "|" + product.getDepartment());
            }
        }
    }
    public void searchProdByPrice (double minPrice, double maxPrice) {
        System.out.println("Search Results");
        for (Product product : products) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                System.out.println(product.getSKU() + "|" + product.getName() + "-$" + product.getPrice() + "-" + product.getDepartment());
            }
        }
    }
    public void searchProdByDepart (String department) {
        System.out.println("Search Results");
        for (Product product : products) {
            if (product.getDepartment().equalsIgnoreCase(department)) {
                System.out.println(product.getSKU() + "|" + product.getName() + "|$" + product.getPrice() + "|" + product.getDepartment());
            }
        }
    }
    public void addToCart(int cartItem) {
        if (cartItem >= 0 && cartItem < products.size()) {
            cart.add(products.get(cartItem));
            System.out.println("Product added to cart.");
        }else {
            System.out.println("Invalid product!");
        }
    }

    public void displayCart() {
        System.out.println("Cart Items: ");
        for(int i = 0; i < cart.size(); i++) {
            Product product = cart.get(i);
            System.out.println(product.getSKU() + "|" + product.getName() + "|$" + product.getPrice() + "|" + product.getDepartment());
        }
        System.out.println("Here are your Total Sales Amount: $" + calculateTotalSalesAmount());
    }
    public void removeCartItems (int cartItem) {
        if (cartItem >= 0 && cartItem < cart.size()){
            cart.remove(cartItem);
            System.out.println("Product has been removed from cart");
        }else {
            System.out.println("Invalid Item\n" +
                    "Please Try Again: ");
        }
    }

    public double calculateTotalSalesAmount() {
        double totalItems = 0;
        for (Product product : cart) {
            totalItems += product.getPrice();
        }
        return totalItems;
    }
}
