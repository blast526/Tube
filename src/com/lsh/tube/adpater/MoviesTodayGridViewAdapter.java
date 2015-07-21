package com.lsh.tube.adpater;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lsh.tube.R;
import com.lsh.tube.bean.MoviesTodaySearchResultBean.Movie;

public class MoviesTodayGridViewAdapter extends BaseAdapter {

	private Context context;
	private List<Movie> list;
	private BitmapUtils bitmapUtils;

	public MoviesTodayGridViewAdapter(Context context, List<Movie> list) {
		this.context = context;
		this.list = list;
		bitmapUtils = new BitmapUtils(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Movie getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	ViewHolder holder;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(com.lsh.tube.R.layout.movies_today_gridview_item, null);
		}
		holder = getViewHolder(convertView);

		Movie movie = list.get(position);
		if (!TextUtils.isEmpty(movie.pic_url)) {
			bitmapUtils.display(holder.ivItemPoster, movie.pic_url);
		}
		if (!TextUtils.isEmpty(movie.movieName)) {
			holder.tvItemMovieTitle.setText(movie.movieName);
		}

		return convertView;
	}

	private ViewHolder getViewHolder(View convertView) {
		ViewHolder viewHolder = (ViewHolder) convertView.getTag();
		if (viewHolder == null) {
			viewHolder = new ViewHolder(convertView);
			convertView.setTag(viewHolder);
		}
		return viewHolder;
	}

	class ViewHolder {
		ImageView ivItemPoster;
		TextView tvItemMovieTitle;

		public ViewHolder(View convertView) {
			this.ivItemPoster = (ImageView) convertView.findViewById(R.id.ivItemPoster);
			this.tvItemMovieTitle = (TextView) convertView.findViewById(R.id.tvItemMovieTitle);
		}
	}
}
