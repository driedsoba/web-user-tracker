package com.eshop.web.jdbc;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Integer, CartItem> items = new HashMap<>();

    public void addItem(Product product, int quantity) {
        CartItem item = items.get(product.getId());

        if (item == null) {
            item = new CartItem(product, quantity);
            items.put(product.getId(), item);
        } else {
            item.incrementQuantityBy(quantity);
        }
    }

    public void removeItem(int productId) {
        items.remove(productId);
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public double getTotal() {
        double total = 0;
        for (CartItem item : items.values()) {
            total += item.getTotalPrice();
        }
        return total;
    }
}
