package com.example.bluetooth;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
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


    TextView temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        temp = (TextView) findViewById(R.id.temp);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.log);

        System.out.println("실행 되는지");
        //송신용으로 할라했는데 아닌것같음
        //new mThread().start();

        //수신 스레드 실행
        new logdata().execute();
    }


//수신 쓰레드
    class logdata extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            /* Showing the ProgressBar, Making the main design GONE */
        }

        protected String doInBackground(String... args) {
            String save = "http://192.168.0.3:8989/input.php";
            //이부분은 지워도 되긴할것같은디 모르겠음

            try {

                //쓰레드로 뭉친부분 만들기
                URL url = new URL("http://192.168.0.13/success1.php");
                // http://192.168.0.3:8989/input.php
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

                result = sb.toString();
                //결과값 뭔지 확인할것
                System.out.println("결과 출력 미리보기 : " + "result : " + result + "여기까지");


            } catch (MalformedURLException e) {
                e.printStackTrace();
                System.out.println("에러");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("에러2");
            }
            return save;
        }

        @Override
        protected void onPostExecute(String result) {


        }
    }


    //전송 쓰레드
    class mThread extends Thread{
        @Override
        public void run() {
            System.out.println("실행 되는ㄷ지");
            try {
                URL url = new URL("http://192.168.2.4:8989/input.php");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    readStream(in);
                    urlConnection.disconnect();
                    System.out.println(in + "값 확인하기");

                }else{
                    Toast.makeText(getApplicationContext(), "에러발생", Toast.LENGTH_SHORT).show();
                    System.out.println("에러0");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
                System.out.println("에러1");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("에러2");
            }

        }
    }
    public void readStream(InputStream in){
        final String data = readData(in);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                temp.setText(data);
            }
        });
    }
    public String readData(InputStream is){
        String data = "";
        Scanner s = new Scanner(is);
        while(s.hasNext()) data += s.nextLine() + "\n";
        s.close();
        return data;
    }

    Handler mHandler = new Handler();
}