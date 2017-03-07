package com.raulmartinezperez.ubiqumproject;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class AddProduct extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);

        //Added support for icon in Action Bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        addProduct();
    }


    private void addProduct() {

        final TextView priceField = (TextView) findViewById(R.id.price_field);
        final TextView nameField = (TextView) findViewById(R.id.name_field);
        final View addProductButton = findViewById(R.id.add_button);

        addProductButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (nameField.getText().length() == 0 || priceField.getText().length() == 0) {
                    ShowAlert(getString(R.string.add_product_alert), getString(R.string.add_product_message));
                }else if(Integer.parseInt(priceField.getText().toString()) == 0) {
                    ShowAlert(getString(R.string.add_product_alert), getString(R.string.add_product_free_product));

                } /*else {
                    gotoMainActivity();
                }*/
            }
        });
    }

    /*private void gotoMainActivity() {

        TextView priceField = (TextView) findViewById(R.id.price_field);
        TextView nameField = (TextView) findViewById(R.id.name_field);


        // New product passed to the main activity.
        // No need startActivity method to return to Main. Only finish this Activity.
        Intent data = new Intent();
        Product newProduct = new Product(Integer.parseInt(priceField.getText().toString()), nameField.getText().toString());
        newProduct.setName(nameField.getText().toString());
        data.putExtra("product", newProduct);
        setResult(MainActivity.RESULT_OK, data);
        finish();
    }*/

    //Custom method to show alerts
    private void ShowAlert(String title, String message) {

        AlertDialog alertDialog = new AlertDialog.Builder(AddProduct.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

}
