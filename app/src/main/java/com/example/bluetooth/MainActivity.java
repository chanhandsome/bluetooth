package com.example.bluetooth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

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

                Intent open_door = new Intent(MainActivity.this, bluetooth_open.class);
                startActivity(open_door);
            }
        });

        open_log.setClickable(true);
        open_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent open_log = new Intent(MainActivity.this, log.class);
                startActivity(open_log);
            }
        });
    }

}