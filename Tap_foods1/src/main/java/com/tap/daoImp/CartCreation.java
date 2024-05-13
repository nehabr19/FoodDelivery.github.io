package com.tap.daoImp;

import java.util.HashMap;
import java.util.Map;


import com.tap.model.CartItems;

public class CartCreation {
    private Map<Integer, CartItems> cart;

    public CartCreation() {
        cart = new HashMap<>();
    }

    public void addCartItem(CartItems ci) {
        if (cart.containsKey(ci.getItemId())) {
            CartItems existingItem = cart.get(ci.getItemId());
            int existingQuantity = existingItem.getQuantity();
            int newQuantity = existingQuantity + ci.getQuantity();
            existingItem.setQuantity(newQuantity);
            cart.put(existingItem.getItemId(), existingItem);
        } else {
            cart.put(ci.getItemId(), ci);
        }
    }

    public void updateCartItem(int itemId, int quantity) {
        boolean itemFound = false; // Flag to track if the item is found in the cart
        for (Map.Entry<Integer, CartItems> entry : cart.entrySet()) {
            CartItems item = entry.getValue();
            if (item.getItemId() == itemId) {
                item.setQuantity(quantity);
                cart.put(itemId, item);
                itemFound = true;
                break; // Exit the loop once item is updated
            }
        }
        if (!itemFound) {
            // If itemId is not found in the cart
            System.err.println("Item with ID " + itemId + " not found in the cart.");
        }
    }

    public void increaseCartItemQuantity(int itemId, int quantity) {
        if (cart.containsKey(itemId)) {
            CartItems item = cart.get(itemId);
            
            int currentQuantity = item.getQuantity();
            item.setQuantity(currentQuantity + quantity);
            cart.put(itemId, item);
        } else {
            // Handle the case where the item is not in the cart
            System.err.println("Item with ID " + itemId + " not found in the cart.");
        }
    }


    public void printCartItems() {
        System.out.println("Items in the cart:");
        for (Map.Entry<Integer, CartItems> entry : cart.entrySet()) {
            int itemId = entry.getKey();
            CartItems item = entry.getValue();
            System.out.println("Item ID: " + itemId + ", Name: " + item.getName() + ", Quantity: " + item.getQuantity());
        }
    }



    public Map<Integer, CartItems> getCart() {
        return cart;
    }
    public static void main(String[] args) {
        // Create an instance of CartCreation
        CartCreation cart = new CartCreation();
        
        // Create some CartItems for testing
       
        
        // Add items to the cart
        
        
        
        
        // Print the updated items in the cart
        cart.printCartItems();
    }

}
