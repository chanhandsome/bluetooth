package com.example.bluetooth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bluetooth.R;


public class otp_compare extends AppCompatActivity {

    TextView TextView_get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_result);

        //문 열리고 처음화면으로 + 안열리면 다시받기 기능 구현해야함
        //아니면 이화면 없이 bluetooth_open 에서 상태메세지 구현하기

        TextView_get = findViewById(R.id.TextView_get);
        Intent rc_otp = getIntent();

        Bundle com_first = rc_otp.getExtras();
        String password = com_first.getString("mBtnSendData");
        String checkdata = com_first.getString("passresult");

        TextView_get.setText("< " + password + " >의 값을 입력해 주세요");


        if (checkdata == "1") {
            Toast.makeText(getApplicationContext(), "문이 열렸습니다.", Toast.LENGTH_LONG).show();
            checkdata = "";
        } else if (checkdata == "2") {
            Toast.makeText(getApplicationContext(), "암호 3회 오류입니다. 비밀번호가 초기화됩니다.", Toast.LENGTH_LONG).show();
            checkdata = "";
        }
    }
}
