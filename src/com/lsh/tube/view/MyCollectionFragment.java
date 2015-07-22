package com.lsh.tube.view;

import java.util.ArrayList;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.baoyz.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.baoyz.swipemenulistview.SwipeMenuListView.OnSwipeListener;
import com.lsh.tube.R;
import com.lsh.tube.activity.MovieInfoDetailActivity;
import com.lsh.tube.adpater.MyCollectionListAdapter;
import com.lsh.tube.bean.MovieKeySearchResultBean.MovieInfo;
import com.lsh.tube.db.MyCollectionDB;
import com.lsh.tube.util.CommonUtil;
import com.lsh.tube.util.MyLog;

/**
 * 
 * @Description 我的收藏Fragment
 * @author Blast
 * @date 2015-7-21 下午6:01:16
 */
public class MyCollectionFragment extends BaseFragment {

	private static final String TAG = "MyCollectionFragment";

	private ArrayList<MovieInfo> myCollectionMoviesList;
	private MyCollectionListAdapter adapter;
	private SwipeMenuListView myCollectionListView;

	private MyCollectionDB myCollectionDB;

	@Override
	public View initView(LayoutInflater inflater) {
		view = inflater.inflate(R.layout.my_collection_fragment_layout, null);
		myCollectionListView = (SwipeMenuListView) view.findViewById(R.id.myCollectionListView);
		return view;
		// TODO Auto-generated method stub
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		myCollectionDB = new MyCollectionDB(context);
		myCollectionMoviesList = new ArrayList<MovieInfo>();
		fillList();

		adapter = new MyCollectionListAdapter(context, myCollectionMoviesList);
		myCollectionListView.setAdapter(adapter);

		// step 1. create a MenuCreator
		SwipeMenuCreator creator = new SwipeMenuCreator() {

			@Override
			public void create(SwipeMenu menu) {
				// create "open" item
				SwipeMenuItem openItem = new SwipeMenuItem(context);
				// set item background
				openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
				// set item width
				openItem.setWidth(CommonUtil.dip2px(context, 90));
				// set item title
				openItem.setTitle("Open");
				// set item title fontsize
				openItem.setTitleSize(18);
				// set item title font color
				openItem.setTitleColor(Color.WHITE);
				// add to menu
				menu.addMenuItem(openItem);

				// create "delete" item
				SwipeMenuItem deleteItem = new SwipeMenuItem(context);
				// set item background
				deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
				// set item width
				deleteItem.setWidth(CommonUtil.dip2px(context, 90));
				// set a icon
				deleteItem.setIcon(R.drawable.ic_delete);
				// add to menu
				menu.addMenuItem(deleteItem);
			}
		};
		// set creator
		myCollectionListView.setMenuCreator(creator);

		// step 2. listener item click event
		myCollectionListView.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public void onMenuItemClick(int position, SwipeMenu menu, int index) {
				MovieInfo movieInfo = adapter.getItem(position);
				switch (index) {
				case 0:
					if (movieInfo == null) {
						MyLog.d(TAG, "movieInfo is null");
					}
					// open
					MyLog.d(TAG, "open is on click");
					open(movieInfo);
					break;
				case 1:
					if (movieInfo == null) {
						MyLog.d(TAG, "movieInfo is null");
					}
					// delete
					MyLog.d(TAG, "delete is on click");
					delete(movieInfo);
					myCollectionMoviesList.remove(position);
					adapter.notifyDataSetChanged();
					break;
				}
			}
		});

		// set SwipeListener
		myCollectionListView.setOnSwipeListener(new OnSwipeListener() {

			@Override
			public void onSwipeStart(int position) {
				// swipe start
			}

			@Override
			public void onSwipeEnd(int position) {
				// swipe end
			}
		});

		// other setting
		// listView.setCloseInterpolator(new BounceInterpolator());

		// test item long click
		// myCollectionListView.setOnItemLongClickListener(new
		// OnItemLongClickListener() {
		//
		// @Override
		// public boolean onItemLongClick(AdapterView<?> parent, View view, int
		// position, long id) {
		// Toast.makeText(context, position + " long click", 0).show();
		// return false;
		// }
		// });
	}

	private void fillList() {
		Cursor cursor = myCollectionDB.queryAllMovieInfo();
		MovieInfo movieInfo;
		while (cursor.moveToNext()) {
			movieInfo = new MovieInfo();
			movieInfo.movieid = cursor.getString(cursor.getColumnIndex("movieid"));
			movieInfo.actors = cursor.getString(cursor.getColumnIndex("actors"));
			movieInfo.also_known_as = cursor.getString(cursor.getColumnIndex("also_known_as"));
			movieInfo.country = cursor.getString(cursor.getColumnIndex("country"));
			movieInfo.directors = cursor.getString(cursor.getColumnIndex("directors"));
			movieInfo.film_locations = cursor.getString(cursor.getColumnIndex("film_locations"));
			movieInfo.genres = cursor.getString(cursor.getColumnIndex("genres"));
			movieInfo.language = cursor.getString(cursor.getColumnIndex("language"));
			movieInfo.plot_simple = cursor.getString(cursor.getColumnIndex("plot_simple"));
			movieInfo.rated = cursor.getString(cursor.getColumnIndex("rated"));
			movieInfo.rating = cursor.getString(cursor.getColumnIndex("rating"));
			movieInfo.rating_count = cursor.getString(cursor.getColumnIndex("rating_count"));
			movieInfo.release_date = cursor.getInt(cursor.getColumnIndex("release_date"));
			movieInfo.runtime = cursor.getString(cursor.getColumnIndex("runtime"));
			movieInfo.title = cursor.getString(cursor.getColumnIndex("title"));
			movieInfo.type = cursor.getString(cursor.getColumnIndex("type"));
			movieInfo.writers = cursor.getString(cursor.getColumnIndex("writers"));
			movieInfo.year = cursor.getInt(cursor.getColumnIndex("year"));

			byte[] posterBytes = cursor.getBlob(cursor.getColumnIndex("poster"));
			Bitmap posterBitmap = BitmapFactory.decodeByteArray(posterBytes, 0, posterBytes.length);
			movieInfo.posterBitmap = posterBitmap;
			myCollectionMoviesList.add(movieInfo);
		}
	}

	private void delete(MovieInfo movieInfo) {
		MyLog.d(TAG, movieInfo.movieid);
		int deleteRow = myCollectionDB.deleteMovieInfoById(movieInfo.movieid);
		MyLog.d(TAG, "delete row " + deleteRow);
	}

	private void open(MovieInfo movieInfo) {
		Intent intent = new Intent(context, MovieInfoDetailActivity.class);
		intent.putExtra("movieInfoDetail", movieInfo);
		startActivity(intent);
	}

}
