package com.lsh.tube.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lsh.tube.R;
import com.lsh.tube.bean.MovieKeySearchResultBean.MovieInfo;

public class MovieInfoDetailActivity extends Activity implements OnClickListener {

	private ImageView ivBack;
	private ImageView ivCollect;
	private ImageView ivShare;

	private TextView tvFilmTitle;
	private ImageView ivPoster;
	private TextView tvRating;
	private TextView tvRatingCount;
	private TextView tvDirectors;
	private TextView tvWriters;
	private TextView tvActors;
	private TextView tvGenres;
	private TextView tvCountry;
	private TextView tvLanguage;
	private TextView tvReleaseDate;
	private TextView tvRuntime;
	private TextView tvAlsoKnownAs;
	private TextView tvPlotSimple;

	private BitmapUtils bitmapUtils;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_info_detail_layout);

		init();
		fillData();
	}

	private void fillData() {
		MovieInfo movieInfo = (MovieInfo) getIntent().getParcelableExtra("movieInfoDetail");

		if (!TextUtils.isEmpty(movieInfo.title)) {
			tvFilmTitle.setText(movieInfo.title);
		}
		if (!TextUtils.isEmpty(movieInfo.poster)) {
			bitmapUtils.display(ivPoster, movieInfo.poster);
		}
		if (!TextUtils.isEmpty(movieInfo.rating)) {
			tvRating.setText(movieInfo.rating);
		} else {
			tvRating.setText("未评");
		}
		if (!TextUtils.isEmpty(movieInfo.rating_count)) {
			tvRatingCount.setText("评分人数：" + movieInfo.rating_count);
		} else {
			tvRatingCount.setText("评分人数：无");
		}
		if (!TextUtils.isEmpty(movieInfo.directors)) {
			tvDirectors.setText("导演：" + movieInfo.directors);
		} else {
			tvDirectors.setText("导演：未知");
		}
		if (!TextUtils.isEmpty(movieInfo.writers)) {
			tvWriters.setText("编剧：" + movieInfo.writers);
		} else {
			tvWriters.setText("编剧：未知");
		}
		if (!TextUtils.isEmpty(movieInfo.actors)) {
			tvActors.setText("主演：" + movieInfo.actors);
		} else {
			tvActors.setText("主演：未知");
		}
		if (!TextUtils.isEmpty(movieInfo.genres)) {
			tvGenres.setText("分类：" + movieInfo.genres);
		} else {
			tvGenres.setText("分类：未知");
		}
		if (!TextUtils.isEmpty(movieInfo.country)) {
			tvCountry.setText("国家：" + movieInfo.country);
		} else {
			tvCountry.setText("国家：未知");
		}
		if (!TextUtils.isEmpty(movieInfo.language)) {
			tvLanguage.setText("语言：" + movieInfo.language);
		} else {
			tvLanguage.setText("语言：未知");
		}
		if (!TextUtils.isEmpty(String.valueOf(movieInfo.release_date))) {
			tvReleaseDate.setText("上映时间：" + String.valueOf(movieInfo.release_date));
		} else {
			tvReleaseDate.setText("上映时间：位置");
		}
		if (!TextUtils.isEmpty(movieInfo.runtime)) {
			tvRuntime.setText("片长：" + movieInfo.runtime);
		} else {
			tvRuntime.setText("片长：未知");
		}
		if (!TextUtils.isEmpty(movieInfo.also_known_as)) {
			tvAlsoKnownAs.setText("别名：" + movieInfo.also_known_as);
		} else {
			tvAlsoKnownAs.setText("别名：无");
		}
		if (!TextUtils.isEmpty(movieInfo.plot_simple)) {
			tvPlotSimple.setText("剧情概要：" + movieInfo.plot_simple);
		} else {
			tvPlotSimple.setText("剧情概要：无");
		}

	}

	private void init() {
		ivBack = (ImageView) findViewById(R.id.ivBack);
		ivCollect = (ImageView) findViewById(R.id.ivCollect);
		ivShare = (ImageView) findViewById(R.id.ivShare);

		tvFilmTitle = (TextView) findViewById(R.id.tvFilmTitle);
		ivPoster = (ImageView) findViewById(R.id.ivPoster);
		tvRating = (TextView) findViewById(R.id.tvRating);
		tvRatingCount = (TextView) findViewById(R.id.tvRatingCount);
		tvDirectors = (TextView) findViewById(R.id.tvDirectors);
		tvWriters = (TextView) findViewById(R.id.tvWriters);
		tvActors = (TextView) findViewById(R.id.tvActors);
		tvGenres = (TextView) findViewById(R.id.tvGenres);
		tvCountry = (TextView) findViewById(R.id.tvCountry);
		tvLanguage = (TextView) findViewById(R.id.tvLanguage);
		tvReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);
		tvRuntime = (TextView) findViewById(R.id.tvRuntime);
		tvAlsoKnownAs = (TextView) findViewById(R.id.tvAlsoKnownAs);
		tvPlotSimple = (TextView) findViewById(R.id.tvPlotSimple);

		bitmapUtils = new BitmapUtils(this);

		setListener();
	}

	private void setListener() {
		ivBack.setOnClickListener(this);
		ivCollect.setOnClickListener(this);
		ivShare.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivBack:
			finish();
			break;
		case R.id.ivCollect:
			break;
		case R.id.ivShare:
			break;

		default:
			break;
		}
	}
}
