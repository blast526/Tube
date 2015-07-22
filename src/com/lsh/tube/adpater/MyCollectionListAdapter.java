package com.lsh.tube.adpater;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lsh.tube.R;
import com.lsh.tube.bean.MovieKeySearchResultBean.MovieInfo;

/**
 * 
 * @Description 我的收藏列表适配器
 * @author Blast
 * @date 2015-7-22 下午10:46:20
 */
public class MyCollectionListAdapter extends BaseAdapter {

	private Context context;
	private List<MovieInfo> list;

	public MyCollectionListAdapter(Context context, List<MovieInfo> list) {
		this.context = context;
		this.list = list;
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
			convertView = LayoutInflater.from(context).inflate(com.lsh.tube.R.layout.my_collection_movies_list_item, null);
		}
		holder = getViewHolder(convertView);

		MovieInfo movieInfo = list.get(position);
		holder.ivMoviePoster.setImageBitmap(movieInfo.posterBitmap);
		holder.tvMovieTitle.setText(movieInfo.title);

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
		ImageView ivMoviePoster;
		TextView tvMovieTitle;

		public ViewHolder(View convertView) {
			this.ivMoviePoster = (ImageView) convertView.findViewById(R.id.ivMoviePoster);
			this.tvMovieTitle = (TextView) convertView.findViewById(R.id.tvMovieTitle);
		}
	}
}
