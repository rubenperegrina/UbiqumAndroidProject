package com.raulmartinezperez.ubiqumproject;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class CheckOut extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        //Added support to show icon in Action Bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        setTotalPrice();
        setListeners();

    }

    private void setListeners() {

        View confirmButton = findViewById(R.id.checkout_confirm_button);

        confirmButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ShowAlert(getString(R.string.confirm_alert), getString(R.string.confirm_message));
            }
        });

    }

    private void confirmCheckOut() {

        Intent data = new Intent();
        setResult(MainActivity.RESULT_OK, data);
        finish();
    }

    private void setTotalPrice() {

        Intent intent = getIntent();
        String value = intent.getStringExtra("total");

        TextView price = (TextView) findViewById(R.id.total_price_checkout);

        price.setText(value + getString(R.string.current_coin));

    }

    private void ShowAlert(String title, String message) {

        AlertDialog alertDialog = new AlertDialog.Builder(CheckOut.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.sure_button),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        confirmCheckOut();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.cancel_button),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
