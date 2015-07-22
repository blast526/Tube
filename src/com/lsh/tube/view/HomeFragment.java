package com.lsh.tube.view;

import java.util.ArrayList;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lsh.tube.R;
import com.lsh.tube.activity.MoreMoviesTodayActivity;
import com.lsh.tube.activity.MovieInfoDetailActivity;
import com.lsh.tube.activity.MovieSearchResultListActivity;
import com.lsh.tube.adpater.MoviesTodayGridViewAdapter;
import com.lsh.tube.bean.MovieIDSearchResultBean;
import com.lsh.tube.bean.MovieKeySearchResultBean;
import com.lsh.tube.bean.MovieKeySearchResultBean.MovieInfo;
import com.lsh.tube.bean.MoviesTodaySearchResultBean;
import com.lsh.tube.bean.MoviesTodaySearchResultBean.Movie;
import com.lsh.tube.net.MovieIDSearch;
import com.lsh.tube.net.MovieKeySearch;
import com.lsh.tube.net.MoviesTodaySearch;
import com.lsh.tube.util.CommonUtil;
import com.lsh.tube.util.GsonTools;
import com.lsh.tube.util.MyLog;
import com.lsh.tube.util.SharedPreferencesUtils;

/**
 * 
 * @Description 影音资讯Fragment
 * @author Blast
 * @date 2015-7-21 下午6:00:20
 */
public class HomeFragment extends BaseFragment implements OnClickListener, OnItemClickListener {

	protected static final String TAG = "HomeFragment";

	private EditText etSearchMovieTitle;
	private ImageView ivSearchMovie;
	private TextView tvMoreMovieInfo;
	private GridView gvMoviesToday;

	private MovieKeySearch movieKeySearch;
	private MoviesTodaySearch moviesTodaySearch;
	private MovieIDSearch movieIDSearch;

	private ArrayList<Movie> moviesTodayResult;
	private MoviesTodayGridViewAdapter adapter;

	private ConnectionChangeReceiver connectionChangeReceiver;
	private LocalBroadcastManager localBroadcastManager;

	@Override
	public View initView(LayoutInflater inflater) {
		view = inflater.inflate(R.layout.home_fragment_layout, null);
		etSearchMovieTitle = (EditText) view.findViewById(R.id.etSearchMovieTitle);
		ivSearchMovie = (ImageView) view.findViewById(R.id.ivSearchMovie);
		tvMoreMovieInfo = (TextView) view.findViewById(R.id.tvMoreMovieInfo);
		gvMoviesToday = (GridView) view.findViewById(R.id.gvMoviesToday);
		setListener();

		return view;
	}

	private void setListener() {
		ivSearchMovie.setOnClickListener(this);
		tvMoreMovieInfo.setOnClickListener(this);
		gvMoviesToday.setOnItemClickListener(this);
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		// 影片关键字搜索
		movieKeySearch = new MovieKeySearch(context);
		movieKeySearch.setSearchSuccessCallback(new MovieKeySearch.SearchSuccessCallback() {

			@Override
			public void onSuccess(String response) {
				MyLog.d(TAG, response);
				MovieKeySearchResultBean movieKeySearchResultBean = GsonTools.changeGsonToBean(response, MovieKeySearchResultBean.class);
				ArrayList<MovieInfo> result = movieKeySearchResultBean.result;
				Intent intent = new Intent(context, MovieSearchResultListActivity.class);
				intent.putExtra("movieSearchResultList", result);
				startActivity(intent);
			}
		});

		initMoviesToday();
	}

	/**
	 * 网络请求加载今日放映影片
	 */
	public void initMoviesToday() {
		// 获取我的住址存储的城市id
		String cityid = SharedPreferencesUtils.getString(context, "city_id");
		// 今日放映影片查询
		moviesTodaySearch = new MoviesTodaySearch(context);
		moviesTodaySearch.setSearchSuccessCallback(new MoviesTodaySearch.SearchSuccessCallback() {

			@Override
			public void onSuccess(String response) {
				MyLog.d(TAG, response);
				MoviesTodaySearchResultBean moviesTodaySearchResultBean = GsonTools.changeGsonToBean(response, MoviesTodaySearchResultBean.class);
				moviesTodayResult = moviesTodaySearchResultBean.result;
				ArrayList<Movie> homeShowList = new ArrayList<MoviesTodaySearchResultBean.Movie>();

				if (moviesTodayResult.size() > 6) {
					for (int i = 0; i < 6; i++) {
						homeShowList.add(moviesTodayResult.get(i));
					}
					adapter = new MoviesTodayGridViewAdapter(context, homeShowList);
				} else {
					adapter = new MoviesTodayGridViewAdapter(context, moviesTodayResult);
				}
				gvMoviesToday.setAdapter(adapter);

			}
		});
		if (TextUtils.isEmpty(cityid)) {
			Toast.makeText(context, "请先到设置界面设置所在城市", 0).show();
		} else {
			moviesTodaySearch.search(cityid);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivSearchMovie:
			if (TextUtils.isEmpty(etSearchMovieTitle.getText())) {
				Toast.makeText(context, "请输入影片全名", 0).show();
			} else {
				if (CommonUtil.checkNet(context)) {
					movieKeySearch.search(etSearchMovieTitle.getText().toString());
				} else {
					MyLog.d(TAG, CommonUtil.checkNet(context) + "");
					Toast.makeText(context, "无法连接到服务器或网络", 1).show();
				}
			}
			break;
		case R.id.tvMoreMovieInfo:

			if (CommonUtil.checkNet(context)) {
				Intent intent = new Intent(context, MoreMoviesTodayActivity.class);
				intent.putExtra("moviesTodayResult", moviesTodayResult);
				startActivity(intent);
			} else {
				MyLog.d(TAG, CommonUtil.checkNet(context) + "");
				Toast.makeText(context, "无法连接到服务器或网络", 1).show();
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		movieIDSearch = new MovieIDSearch(context);
		movieIDSearch.setSearchSuccessCallback(new MovieIDSearch.SearchSuccessCallback() {

			@Override
			public void onSuccess(String response) {
				MyLog.d(TAG, response);
				MovieIDSearchResultBean movieIDSearchResultBean = GsonTools.changeGsonToBean(response, MovieIDSearchResultBean.class);
				MovieInfo movieInfo = movieIDSearchResultBean.result;
				Intent intent = new Intent(context, MovieInfoDetailActivity.class);
				intent.putExtra("movieInfoDetail", movieInfo);
				startActivity(intent);
			}
		});
		Movie movie = adapter.getItem(position);
		movieIDSearch.search(movie.movieId);
	}

	/**
	 * 
	 * @Description 监听网络状态变化
	 * 离线打开应用，点击<更多>提示无网络，
	 * 直接打开网络，连接后再点击<更多>，直接崩溃,
	 * 因为上一个界面中今日放映影片的网络请求并未重新执行，
	 * 所以moviesTodayResult为空
	 * @author Blast
	 * @date 2015-7-22 下午11:04:39
	 */
	class ConnectionChangeReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			if (CommonUtil.checkNet(context)) {
				initMoviesToday();
			}
			adapter.notifyDataSetChanged();
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// 发送广播并注册
		localBroadcastManager = LocalBroadcastManager.getInstance(activity);
		Intent intent = new Intent("com.lsh.tube.CONNECTION_CHANGE_BROADCAST");
		localBroadcastManager.sendBroadcast(intent);
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("com.lsh.tube.CONNECTION_CHANGE_BROADCAST");
		connectionChangeReceiver = new ConnectionChangeReceiver();
		localBroadcastManager.registerReceiver(connectionChangeReceiver, intentFilter);
	}

	@Override
	public void onDetach() {
		super.onDetach();
		// 注销广播
		localBroadcastManager.unregisterReceiver(connectionChangeReceiver);
	}

	// class MovieKeySearchTask extends AsyncTask<String, Void, Void> {
	//
	// @Override
	// protected Void doInBackground(String... params) {
	// String searchMovieTitle = params[0];
	// movieKeySearch.search(searchMovieTitle);
	// return null;
	// }
	//
	// @Override
	// protected void onPostExecute(Void result) {
	// super.onPostExecute(result);
	// movieKeySearch.setSearchSuccessCallback(new
	// MovieKeySearch.SearchSuccessCallback() {
	//
	// @Override
	// public void onSuccess(String response) {
	// MyLog.d(TAG, response);
	// MovieKeySearchResultBean changeGsonToBean =
	// GsonTools.changeGsonToBean(response, MovieKeySearchResultBean.class);
	// ArrayList<MovieInfo> result = changeGsonToBean.result;
	// Intent intent = new Intent(context, MovieInfoDetailActivity.class);
	// intent.putExtra("movieSearchResultList", result);
	// startActivity(intent);
	// }
	// });
	// }
	//
	// }

}
