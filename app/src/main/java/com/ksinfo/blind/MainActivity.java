package com.ksinfo.blind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ksinfo.blind.member.LoginActivity;
import com.ksinfo.blind.member.MemberJoinActivity;
import com.ksinfo.blind.util.HttpClientAccessor;

import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Disposable backgroundTask;

    ViewPager2 viewPager2;
    ArrayList<ViewPagerItem> viewPagerItemArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.viewpager);
        int[] images = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e};
        String[] heading = {"アイデア", "自由な会話", "コネクト", "趣味", "楽な会話"};
        String[] desc = {getString(R.string.a_desc),
                getString(R.string.b_desc),
                getString(R.string.c_desc),
                getString(R.string.d_desc)
                , getString(R.string.e_desc)};

        viewPagerItemArrayList = new ArrayList<>();

        for (int i = 0; i < images.length; i++) {

            ViewPagerItem viewPagerItem = new ViewPagerItem(images[i], heading[i], desc[i]);
            viewPagerItemArrayList.add(viewPagerItem);

        }

        VPAdapter vpAdapter = new VPAdapter(viewPagerItemArrayList);

        viewPager2.setAdapter(vpAdapter);

        viewPager2.setClipToPadding(false);

        viewPager2.setClipChildren(false);

        viewPager2.setOffscreenPageLimit(2);

        viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

        Button move_join = (Button)findViewById(R.id.join);
        move_join.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MemberJoinActivity.class);
                startActivity(intent);
            }
        });


        /*
        Button btn_test = (Button)findViewById(R.id.btn1);

        Button move_login = (Button)findViewById(R.id.login);
        move_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

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
     */
    }
}