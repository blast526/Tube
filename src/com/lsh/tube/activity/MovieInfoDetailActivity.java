package com.lsh.tube.activity;

import java.io.ByteArrayOutputStream;
import java.io.File;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;
import com.lsh.tube.R;
import com.lsh.tube.bean.MovieKeySearchResultBean.MovieInfo;
import com.lsh.tube.db.MyCollectionDB;
import com.lsh.tube.util.MyLog;
import com.lsh.tube.util.ShareMovieInfoUtil;

/**
 * 
 * @Description 影片信息详情页面
 * @author Blast
 * @date 2015-7-21 下午5:47:05
 */
public class MovieInfoDetailActivity extends Activity implements OnClickListener {

	protected static final String TAG = "MovieInfoDetailActivity";

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

	private ScrollView parentScroll;
	private ScrollView childScroll;

	private BitmapUtils bitmapUtils;
	private MovieInfo movieInfo;
	private MyCollectionDB myCollectionDB;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_movie_info_detail_layout);

		init();
		fillData();
	}

	private void fillData() {
		movieInfo = (MovieInfo) getIntent().getParcelableExtra("movieInfoDetail");

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

		parentScroll = (ScrollView) findViewById(R.id.parent_scroll);
		childScroll = (ScrollView) findViewById(R.id.child_scroll);

		bitmapUtils = new BitmapUtils(this);

		myCollectionDB = new MyCollectionDB(this);

		setListener();
	}

	private void setListener() {
		ivBack.setOnClickListener(this);
		ivCollect.setOnClickListener(this);
		ivShare.setOnClickListener(this);

		// 解决ScrollView嵌套滚动事件
		parentScroll.setOnTouchListener(new View.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				MyLog.d(TAG, "PARENT TOUCH");
				findViewById(R.id.child_scroll).getParent().requestDisallowInterceptTouchEvent(false);
				return false;
			}
		});
		childScroll.setOnTouchListener(new View.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				MyLog.d(TAG, "CHILD TOUCH");
				// Disallow the touch request for parent scroll on touch of
				// child view
				v.getParent().requestDisallowInterceptTouchEvent(true);
				return false;
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ivBack:// 返回
			finish();
			break;
		case R.id.ivCollect:// 收藏
			// 查询是否已经收藏过
			if (isCollected()) {
				Toast.makeText(this, "已添加到我的收藏", 0).show();
			} else {
				// 添加收藏
				collect();
			}
			break;
		case R.id.ivShare:// 分享
			share();
			break;

		default:
			break;
		}
	}

	/**
	 * 分享图片，添加文字
	 */
	public void share() {
		// 从缓存中获取图片文件
		File bitmapFileFromDiskCache = bitmapUtils.getBitmapFileFromDiskCache(movieInfo.poster);
		// 获取图片文件路径
		String imgPath = bitmapFileFromDiskCache.getAbsolutePath();
		ShareMovieInfoUtil.shareMsg(this, "分享影片", "影片分享", "这是我喜爱的电影，强烈推荐！--" + movieInfo.title, imgPath);
	}

	/**
	 * 查询当前影片是否已添加到收藏中
	 * @return
	 */
	private boolean isCollected() {
		Cursor cursor = myCollectionDB.queryMovieInfoById(movieInfo.movieid);
		if (cursor.moveToFirst()) {
			return true;
		}
		return false;
	}

	/**
	 * 添加到我的收藏，将数据写入数据库
	 */
	public void collect() {
		long rowID = myCollectionDB.insert(movieInfo, getPosterBytes());
		if (rowID == -1) {
			Toast.makeText(this, "添加到我的收藏失败", 0).show();
		} else {
			Toast.makeText(this, "添加到我的收藏成功", 0).show();
		}
	}

	/**
	 * 添加到我的收藏，将数据写入数据库
	 */
	/*private void collect() {
		ContentValues values = new ContentValues();
		values.put("movieid", movieInfo.movieid);
		values.put("actors", movieInfo.actors);
		values.put("also_known_as", movieInfo.also_known_as);
		values.put("country", movieInfo.country);
		values.put("directors", movieInfo.directors);
		values.put("film_locations", movieInfo.film_locations);
		values.put("genres", movieInfo.genres);
		values.put("language", movieInfo.language);
		values.put("plot_simple", movieInfo.plot_simple);
		values.put("poster", getPosterBytes());
		values.put("rated", movieInfo.rated);
		values.put("rating", movieInfo.rating);
		values.put("rating_count", movieInfo.rating_count);
		values.put("release_date", movieInfo.release_date);
		values.put("runtime", movieInfo.runtime);
		values.put("title", movieInfo.title);
		values.put("type", movieInfo.type);
		values.put("writers", movieInfo.writers);
		values.put("year", movieInfo.year);
		writableDatabase.insert(MyCollectionOpenHelper.DATABASE_TABLE, null, values);
	}*/

	/**
	 * 将海报图片转为字节数组，便于存储
	 * @return
	 */
	private byte[] getPosterBytes() {
		BitmapDrawable posterDrawable = (BitmapDrawable) ivPoster.getDrawable();
		Bitmap posterBitmap = posterDrawable.getBitmap();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		posterBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
		return baos.toByteArray();
	}
}
