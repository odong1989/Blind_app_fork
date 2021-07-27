package com.ksinfo.blind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ksinfo.blind.member.LoginActivity;
import com.ksinfo.blind.member.MemberJoinActivity;
import com.ksinfo.blind.util.HttpClientAccessor;

import java.util.HashMap;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Disposable backgroundTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_test = (Button)findViewById(R.id.btn1);
        Button move_join = (Button)findViewById(R.id.join);
        Button move_login = (Button)findViewById(R.id.login);
        textView = (TextView)findViewById(R.id.text1);

        btn_test.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try{
                    java.util.HashMap<String, String> params = new HashMap<>();
                    params.put("content", "abc");
                    connectionTest(params);

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        move_join.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MemberJoinActivity.class);
                startActivity(intent);
            }
        });

        move_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void connectionTest(HashMap<String, String> params) {
        //onPreExecute(task 시작 전 실행될 코드 여기에 작성)
        backgroundTask = Observable.fromCallable(() -> {
            //doInBackground(task에서 실행할 코드 여기에 작성)
            return HttpClientAccessor.accessByPost("testAndroidAccess", params);

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