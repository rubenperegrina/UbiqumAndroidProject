package com.raulmartinezperez.ubiqumproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    //Global variables
    private static final int PRODUCT_REQUEST = 0;
    private static final int TOTAL_PRICE = 1;

    private Cart myCart = new Cart();
    private List<Product> productsInCart = myCart.getProducts();

    //Variable sent in startActivityForResult
    private String totalCost = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Added support to show icon in ActionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        updateProductList();
        clickListener();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //Switch to do something depending the activity
        switch (requestCode) {

            //Added product
            case PRODUCT_REQUEST:

                if (resultCode == RESULT_OK) {

                    Product newP = (Product) data.getSerializableExtra("product");
                    addNewProduct(newP);

                    Toast.makeText(this, newP.getName() + getString(R.string.product_added), Toast.LENGTH_LONG).show();
                }

                break;

            //CheckOut
            case TOTAL_PRICE:

                if (resultCode == RESULT_OK) {

                    productsInCart.removeAll(myCart.getProducts());
                    updateProductList();
                    totalCost = "";

                    Toast.makeText(this, R.string.order_sent, Toast.LENGTH_LONG).show();

                }
                break;
        }

    }

    private void addNewProduct(Product product) {

        myCart.addProduct(product);

        updateProductList();
    }


    private void updateProductList() {

        /*sortList();*/

        //TextViews
        TextView productList = (TextView) findViewById(R.id.products);
        TextView totalPrice = (TextView) findViewById(R.id.totalPrice);

        //Allow scroll when the list of products is too long
        productList.setMovementMethod(new ScrollingMovementMethod());

        //Variables where strings of products and prices are stored
        String names = "";
        Integer prices = 0;

        for (Product p : productsInCart) {

            //Text formatted to show it aligned like Ferran said. Used monospace font to exact match
            names += String.format("%10s%2s%-10s\n", p.getPrice()+getString(R.string.current_coin),"",p.getName());
            prices += p.getPrice();

        }

        if (productsInCart.isEmpty()) {
            productList.setText(R.string.empty_cart);
            totalPrice.setText("");
        } else {
            productList.setText(names);
            totalCost = prices.toString();
            totalPrice.setText(getString(R.string.total_to_pay) + " " + prices.toString() + getString(R.string.current_coin));

        }

    }

    //Sort List aphabetically by product Name (Google is your friend)
    /*private void sortList() {

        if (productsInCart.size() > 0) {
            Collections.sort(productsInCart, new Comparator<Product>() {
                @Override
                public int compare(final Product p1, final Product p2) {
                    return p1.getName().compareTo(p2.getName());
                }
            });
        }
    }*/

    private void clickListener() {

        //Buttons and their clicks
        View addButton = findViewById(R.id.add_more_button);
        View checkOutButton = findViewById(R.id.purchase_button);

        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                gotoAddProduct();

            }
        });


        View gotomessages = findViewById(R.id.go_to_messages_button);

        gotomessages.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                gotomessages();

            }
        });

        checkOutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //One product needed to purchase
                if (productsInCart.size() == 0) {

                    Toast.makeText(MainActivity.this, R.string.alert_cart_empty, Toast.LENGTH_LONG).show();

                } else {
                    gotoCheckout();
                }
            }
        });


    }

    private void gotoAddProduct() {

        Intent intent = new Intent(this, AddProduct.class);
        startActivityForResult(intent, PRODUCT_REQUEST);
    }

    private void gotoCheckout() {

        Intent myIntent = new Intent(MainActivity.this, CheckOut.class);
        myIntent.putExtra("total", totalCost);
        startActivityForResult(myIntent, TOTAL_PRICE);
    }

    private void gotomessages() {

        Intent intent = new Intent(this, Messages.class);
        startActivityForResult(intent, PRODUCT_REQUEST);
    }



}
