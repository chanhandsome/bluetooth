package com.example.bluetooth;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androdocs.httprequest.HttpRequest;
import com.example.bluetooth.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class log extends AppCompatActivity {

    String name = "";
    TextView textView1;

    //로그 데이터 출력 구문 -> textView1 에 name의 값을 출력시킨다.
    Handler mhandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
           if(name!= null) {
               textView1.setText(name);
           }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log);

        textView1 = (TextView) findViewById(R.id.textview_main_list) ;

        //수신 스레드 실행
         new logdata().execute();


    }


//수신 쓰레드
    class logdata extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //Showing the ProgressBar, Making the main design GONE
        }

        protected String doInBackground(String... args) {
            String save = "http://192.168.0.3:8989/input.php";
            //이부분은 지워도 되긴할것같은디 모르겠음


            try {

                //쓰레드로 뭉친부분 만들기
                URL url = new URL("http://192.168.43.197:8283/capd/try.php");
                // 영아 php : http://125.143.140.191:8888/result.php
                // 류찬 php : http://218.155.126.170:8283/capd/try.php
                // 류찬 학교 php : http://192.168.137.1:8283/capd/try.php
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET"); //전송방식
                connection.setDoOutput(true);       //데이터를 쓸 지 설정
                connection.setDoInput(true);        //데이터를 읽어올지 설정

                InputStream is = connection.getInputStream();
                //이 부분에서 에러가 발생한다.
                //php로 바꾼 뒤 에러 해결
                StringBuilder sb = new StringBuilder();
                BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
                String result;


                while((result = br.readLine())!=null){
                    sb.append(result+"\n");
                }

                // php 데이터값 result에 입력받기
                result = sb.toString();
                // handler함수를 통해 값을 출력함수구문에 전달 'name'을 통해 편집
                name += result;
                //Data 이동을 위함
                Message msg = mhandler.obtainMessage();
                mhandler.sendMessage(msg);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return save;

        }

        @Override
        protected void onPostExecute(String result) {
            //사용하지 않는다
        }
    }


    Handler mHandler = new Handler();


}