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
import com.lsh.tube.bean.MovieKeySearchResultBean.MovieInfo;

/**
 * 
 * @Description 影片搜索结果列表适配器
 * @author Blast
 * @date 2015-7-21 下午5:48:14
 */
public class MovieSearchResultListAdapter extends BaseAdapter {

	private Context context;
	private List<MovieInfo> list;
	private BitmapUtils bitmapUtils;

	public MovieSearchResultListAdapter(Context context, List<MovieInfo> list) {
		this.context = context;
		this.list = list;
		bitmapUtils = new BitmapUtils(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public MovieInfo getItem(int position) {
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
			convertView = LayoutInflater.from(context).inflate(com.lsh.tube.R.layout.movie_search_result_list_item, null);
		}
		holder = getViewHolder(convertView);

		MovieInfo movieInfo = list.get(position);
		if (!TextUtils.isEmpty(movieInfo.poster)) {
			bitmapUtils.display(holder.ivPoster, movieInfo.poster);
		}
		if (!TextUtils.isEmpty(movieInfo.title)) {
			holder.tvFilmTitle.setText(movieInfo.title);
		}
		if (!TextUtils.isEmpty(movieInfo.actors)) {
			holder.tvActors.setText("主演：" + movieInfo.actors);
		} else {
			holder.tvActors.setText("主演：未知");
		}
		if (!TextUtils.isEmpty(movieInfo.rating)) {
			holder.tvRating.setText(movieInfo.rating);
		} else {
			holder.tvRating.setText("未评");
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
		ImageView ivPoster;
		TextView tvFilmTitle;
		TextView tvActors;
		TextView tvRating;

		public ViewHolder(View convertView) {
			this.ivPoster = (ImageView) convertView.findViewById(R.id.ivPoster);
			this.tvFilmTitle = (TextView) convertView.findViewById(R.id.tvFilmTitle);
			this.tvActors = (TextView) convertView.findViewById(R.id.tvActors);
			this.tvRating = (TextView) convertView.findViewById(R.id.tvRating);
		}
	}

}
