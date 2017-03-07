package com.raulmartinezperez.ubiqumproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by rubenperegrina on 7/3/17.
 */

public class Messages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_activity);

        //Added support for icon in Action Bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        Messages();
    }

    private void Messages() {
        final View messagesButton = findViewById(R.id.go_to_messages_button);

        messagesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                gotomessages();
            }
        });
    }

    private void gotomessages() {
        startActivity(messages);
    }

}
