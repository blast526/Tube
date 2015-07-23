package com.lsh.tube.adpater;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lsh.tube.R;
import com.lsh.tube.bean.SurroudingCinemaSearchResultBean.Cinema;

/**
 * 
 * @Description 周边影院搜索结果列表适配器
 * @author Blast
 * @date 2015-7-21 下午5:48:14
 */
public class SurroundingCinemaSearchResultListAdapter extends BaseAdapter {

	private Context context;
	private List<Cinema> list;

	public SurroundingCinemaSearchResultListAdapter(Context context, List<Cinema> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Cinema getItem(int position) {
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
			convertView = LayoutInflater.from(context).inflate(com.lsh.tube.R.layout.surrounding_cinema_list_item, null);
		}
		holder = getViewHolder(convertView);

		Cinema cinema = list.get(position);
		if (!TextUtils.isEmpty(cinema.cinemaName)) {
			holder.tvCinemaName.setText(cinema.cinemaName);
		}
		if (!TextUtils.isEmpty(cinema.telephone)) {
			holder.tvCinemaTelephone.setText("电话：" + cinema.telephone);
		} else {
			holder.tvCinemaTelephone.setText("电话：未知");
		}
		if (!TextUtils.isEmpty(cinema.address)) {
			holder.tvCinemaAddress.setText("地址:" + cinema.address);
		} else {
			holder.tvCinemaAddress.setText("地址：未知");
		}
		if (!TextUtils.isEmpty(cinema.distance)) {
			holder.tvCinemaDistance.setText(cinema.distance + "米");
		} else {
			holder.tvCinemaDistance.setText("未知");
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
		TextView tvCinemaName;
		TextView tvCinemaTelephone;
		TextView tvCinemaAddress;
		TextView tvCinemaDistance;

		public ViewHolder(View convertView) {
			this.tvCinemaName = (TextView) convertView.findViewById(R.id.tvCinemaName);
			this.tvCinemaTelephone = (TextView) convertView.findViewById(R.id.tvCinemaTelephone);
			this.tvCinemaAddress = (TextView) convertView.findViewById(R.id.tvCinemaAddress);
			this.tvCinemaDistance = (TextView) convertView.findViewById(R.id.tvCinemaDistance);
		}
	}

}
