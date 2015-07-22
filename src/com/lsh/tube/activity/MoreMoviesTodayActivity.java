package com.lsh.tube.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.lsh.tube.R;
import com.lsh.tube.adpater.MoviesTodayGridViewAdapter;
import com.lsh.tube.bean.MoviesTodaySearchResultBean.Movie;
import com.lsh.tube.util.MyLog;

public class MoreMoviesTodayActivity extends Activity {

	protected static final String TAG = "MoreMoviesTodayActivity";

	private PullToRefreshGridView pullToRefreshGridView;
	private MoviesTodayGridViewAdapter adapter;
	private ArrayList<Movie> moviesTodayResult;// 今日放映影片所有集合
	private ArrayList<Movie> moviesTodayShowList;// 当前已加载集合

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_more_movies_today_layout);
		initView();
		initData();
	}

	private void initView() {
		pullToRefreshGridView = (PullToRefreshGridView) findViewById(R.id.pull_refresh_grid);
		ILoadingLayout endLabels = pullToRefreshGridView.getLoadingLayoutProxy(false, true);
		endLabels.setPullLabel("加载成功...");// 刚上拉时，显示的提示
		endLabels.setRefreshingLabel("正在加载...");// 刷新时
		endLabels.setReleaseLabel("加载更多...");// 下拉达到一定距离时，显示的提示
	}

	private void initData() {

		moviesTodayResult = getIntent().getParcelableArrayListExtra("moviesTodayResult");
		moviesTodayShowList = new ArrayList<Movie>();
		if (moviesTodayResult.size() > 9) {
			for (int i = 0; i < 9; i++) {
				moviesTodayShowList.add(moviesTodayResult.get(0));
				moviesTodayResult.remove(0);
			}
			adapter = new MoviesTodayGridViewAdapter(this, moviesTodayShowList);
		} else {
			adapter = new MoviesTodayGridViewAdapter(this, moviesTodayResult);
		}

		pullToRefreshGridView.setAdapter(adapter);
		pullToRefreshGridView.setOnRefreshListener(new OnRefreshListener2() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase refreshView) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase refreshView) {
				MyLog.d(TAG, "onPullUpToRefresh");
				new GetDataTask().execute();
			}
		});
	}

	private class GetDataTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (moviesTodayResult.size() > 9) {
				for (int i = 0; i < 9; i++) {
					moviesTodayShowList.add(moviesTodayResult.get(0));
					moviesTodayResult.remove(0);
				}
			} else {
				while (moviesTodayResult.size() != 0) {
					moviesTodayShowList.add(moviesTodayResult.get(0));
					moviesTodayResult.remove(0);
				}
			}

			adapter.notifyDataSetChanged();
			// Call onRefreshComplete when the list has been refreshed.
			pullToRefreshGridView.onRefreshComplete();
		}
	}

}
