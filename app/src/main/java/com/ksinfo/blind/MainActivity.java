package com.ksinfo.blind;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ksinfo.blind.util.HttpClient;

import java.util.HashMap;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Disposable backgroundTask;
    public static String ip = "192.168.111.4"; // Your ip address

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_test = (Button)findViewById(R.id.btn1);
        textView = (TextView)findViewById(R.id.text1);

        btn_test.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try{
                    java.util.HashMap<String, String> params = new HashMap<String, String>();
                    params.put("content", "abc");
                    connectionTest(params);

                } catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    private void connectionTest(HashMap<String, String> params) {
        HashMap<String, String> map = new HashMap<>();

        //onPreExecute(task 시작 전 실행될 코드 여기에 작성)
        backgroundTask = Observable.fromCallable(() -> {
            //doInBackground(task에서 실행할 코드 여기에 작성)
            HttpClient.Builder http = new HttpClient.Builder
                    ("POST", "http://" + ip + ":8282/blind/testAndroidAccess");

            // Parameter Setting
            http.addAllParameters(params);

            HttpClient post = http.create();
            post.request();

            int statusCode = post.getHttpStatusCode();
            String body = post.getBody();
            Gson gson = new Gson();
            java.util.HashMap<String, String> data = gson.fromJson(body, java.util.HashMap.class);

            return data;

        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<HashMap<String, String>>() {
            @Override
            public void accept(HashMap<String, String> map) {
                //onPostExecute(task 끝난 후 실행될 코드 여기에 작성)
                textView.setText(map.get("message"));

                backgroundTask.dispose();
            }
        });
    }
}