package com.raulmartinezperez.ubiqumproject;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart {

    private List<Product> products;

    Cart() {
        this.products = new ArrayList<>();
    }

    void addProduct(Product p) {

        if (products.isEmpty()) {
            products.add(p);
        } else {

            for (Product product : products) {

                if (Objects.equals(product.getName(), p.getName())) {
                    this.products.remove(product);
                    break;
                }

            }
            this.products.add(p);
        }
    }

    public List<Product> getProducts() {
        return products;
    }
}
