package com.example.bluetooth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    Button open_door;
    Button open_log;
    String token = null;



    @Override

//FCM
    protected void onStop() {
        super.onStop();

    // Activity가 종료되기 전에 저장한다.
    //SharedPreferences를 sFile이름, 기본모드로 설정
        SharedPreferences sharedPreferences = getSharedPreferences("sFile1",MODE_PRIVATE);

    //저장을 하기위해 editor를 이용하여 값을 저장시켜준다.
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Token1",token); // key, value를 이용하여 저장하는 형태
        editor.commit();
        System.out.print("여기되냐");

    }


//FCM

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);

        SharedPreferences sharedPreferences = getSharedPreferences("sFile1",MODE_PRIVATE); //저장된 토큰을 불러오기 위한 셋팅
        token = sharedPreferences.getString("Token1",token); //key값과 value값으로 구분된 저장된 토큰값을 불러옵니다.

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

        //푸쉬알림 보류
       // getToken();

    }

    //푸쉬알림 보류류
    /*
    public void getToken(){
        //토큰값을 받아옵니다.
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }
                        ////////////////////토큰이 계속 초기화가 되기때문에 sharedPreferences로 저장하여 초기화 방지////////////////////
                        SharedPreferences sharedPreferences = getSharedPreferences("sFile1",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        token = task.getResult().getToken(); // 사용자가 입력한 저장할 데이터
                        editor.putString("Token1",token); // key, value를 이용하여 저장하는 형태
                        editor.commit();
                        ////////////////////토큰이 계속 초기화가 되기때문에 sharedPreferences로 저장하여 초기화 방지////////////////////
                        System.out.print("뭔데" + token);

                        System.out.print("이건되냐");
                    }
                });
    }
    */

}

