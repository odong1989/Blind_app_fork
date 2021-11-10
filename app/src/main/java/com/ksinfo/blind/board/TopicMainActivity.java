package com.ksinfo.blind.board;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.blind.R;
import com.ksinfo.blind.board.vo.PostVO;
import com.ksinfo.blind.board.vo.BoardVO;
import com.ksinfo.blind.util.RetrofitFactory;

import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class TopicMainActivity extends AppCompatActivity {
	Disposable backgroundTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.topic_main);

//		.addConverterFactory(ScalarsConverterFactory.create())
		// Scalars : 가져올 값이 (1)단순한 string (2) 0,1,2,3등 간단숫자, (3) true/false 등의 형태로 구분되는 방식의 맵핑.
		// 여기선 JSON형태로 할거라 밑의 JacksonConverterFactory를 사용.

		BoardApi boardApi = RetrofitFactory.createJsonRetrofit().create(BoardApi.class);
		boardApi.getBoardList().enqueue(new Callback<List<BoardVO>>() {
			@Override
			public void onResponse(@NonNull Call<List<BoardVO>> call, Response<List<BoardVO>> response) {
				if (response.isSuccessful()) {
				List<BoardVO> boardList = response.body();
				Log.d("boardList", boardList.toString());
				}
			}
			@Override
			public void onFailure(Call<List<BoardVO>> call, Throwable t) {
				t.printStackTrace();
			}
		});

			/*
			@Override
			public void onResponse(Call<PostVO> call, Response<PostVO> response) {
				if (response.isSuccessful()) {
					PostVO post = response.body();
					Log.d("post", post.toString());
				}
			}

			@Override
			public void onFailure(Call<PostVO> call, Throwable t) {

			}
		});
		 */
	}
}