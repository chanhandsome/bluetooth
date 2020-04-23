package com.example.bluetooth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class first extends AppCompatActivity {

    Button open_door;
    Button open_log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);


        open_door = (Button) findViewById(R.id.open_door);
        open_log = (Button) findViewById(R.id.open_log);

        open_door.setClickable(true);
        open_door.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent otp_send = new Intent(first.this, MainActivity.class);
            }
        });
    }
}