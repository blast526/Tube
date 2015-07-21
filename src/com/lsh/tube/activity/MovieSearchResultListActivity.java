package com.lsh.tube.activity;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.lsh.tube.R;
import com.lsh.tube.adpater.MovieSearchResultListAdapter;
import com.lsh.tube.bean.MovieKeySearchResultBean.MovieInfo;

/**
 * 
 * @Description 影片搜索结果列表页面
 * @author Blast
 * @date 2015-7-21 下午5:47:35
 */
public class MovieSearchResultListActivity extends ListActivity implements OnClickListener {

	private ArrayList<MovieInfo> result;
	private MovieSearchResultListAdapter adapter;
	private ImageView ivBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_search_result_list_layout);

		ivBack = (ImageView) findViewById(R.id.ivBack);
		ivBack.setOnClickListener(this);
		result = getIntent().getParcelableArrayListExtra("movieSearchResultList");
		adapter = new MovieSearchResultListAdapter(this, result);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		MovieInfo movieInfo = adapter.getItem(position);
		Intent intent = new Intent(this, MovieInfoDetailActivity.class);
		intent.putExtra("movieInfoDetail", movieInfo);
		startActivity(intent);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivBack:
			finish();
			break;

		default:
			break;
		}
	}

}
