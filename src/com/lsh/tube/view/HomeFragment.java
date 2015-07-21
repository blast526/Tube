package com.lsh.tube.view;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lsh.tube.R;
import com.lsh.tube.activity.MovieSearchResultListActivity;
import com.lsh.tube.bean.MovieKeySearchResultBean;
import com.lsh.tube.bean.MovieKeySearchResultBean.MovieInfo;
import com.lsh.tube.net.MovieKeySearch;
import com.lsh.tube.util.GsonTools;
import com.lsh.tube.util.MyLog;

public class HomeFragment extends BaseFragment implements OnClickListener {

	protected static final String TAG = "HomeFragment";

	private TextView tvMoreMovieInfo;
	private TextView etSearchMovieTitle;
	private ImageView ivSearchMovie;

	private MovieKeySearch movieKeySearch;

	@Override
	public View initView(LayoutInflater inflater) {
		view = inflater.inflate(R.layout.home_fragment_layout, null);
		etSearchMovieTitle = (TextView) view.findViewById(R.id.etSearchMovieTitle);
		ivSearchMovie = (ImageView) view.findViewById(R.id.ivSearchMovie);
		tvMoreMovieInfo = (TextView) view.findViewById(R.id.tvMoreMovieInfo);
		setListener();
		return view;
	}

	private void setListener() {
		ivSearchMovie.setOnClickListener(this);
	}

	@Override
	public void initData(Bundle savedInstanceState) {
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
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivSearchMovie:
			if (TextUtils.isEmpty(etSearchMovieTitle.getText())) {
				Toast.makeText(context, "请输入关键字", 0).show();
			} else {
				// new
				// MovieKeySearchTask().execute(etSearchMovieTitle.getText().toString());
				movieKeySearch.search(etSearchMovieTitle.getText().toString());
			}
			break;

		default:
			break;
		}
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
