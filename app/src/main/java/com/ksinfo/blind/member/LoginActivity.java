package com.ksinfo.blind.member;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.blind.R;
import com.ksinfo.blind.util.HttpClientAccessor;

import java.util.HashMap;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {
    Disposable backgroundTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button login = (Button)findViewById(R.id.loginBtn);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try{
                    HashMap<String, String> params = new HashMap<>();

                    EditText email = (EditText) findViewById(R.id.emailEt);
                    EditText password = (EditText) findViewById(R.id.pwEt);

                    params.put("username", email.getText().toString());
                    params.put("password", password.getText().toString());

                    login(params);

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private void login(HashMap<String, String> params) {
        //onPreExecute(task 시작 전 실행될 코드 여기에 작성)
        backgroundTask = Observable.fromCallable(() -> {
            //doInBackground(task에서 실행할 코드 여기에 작성)
            return HttpClientAccessor.accessByPost("loginApp", params);

        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<HashMap<String, String>>() {
            @Override
            public void accept(HashMap<String, String> map) {
                //onPostExecute(task 끝난 후 실행될 코드 여기에 작성)
                if ("OK".equals(map.get("message"))) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.111.4:8282/blind/main"));
                    startActivity(intent);
                } else {
                    TextView textView = (TextView)findViewById(R.id.messageArea);
                    textView.setText(map.get("message"));
                }
                backgroundTask.dispose();
            }
        });
    }
}
