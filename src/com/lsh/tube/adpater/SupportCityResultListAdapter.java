package com.lsh.tube.adpater;

import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lsh.tube.R;
import com.lsh.tube.bean.SupportCitiesSearchResultBean.City;

public class SupportCityResultListAdapter extends BaseAdapter {

	private Context context;
	private List<City> list;

	public SupportCityResultListAdapter(Context context, List<City> list) {
		this.context = context;
		this.list = list;
		Collections.sort(list);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public City getItem(int position) {
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
			convertView = LayoutInflater.from(context).inflate(com.lsh.tube.R.layout.support_city_result_list_item, null);
		}
		holder = getViewHolder(convertView);

		City city = list.get(position);
		String currentFirstWord = String.valueOf(city.city_pinyin.charAt(0));
		if (position > 0) {
			String nextFirstWord = String.valueOf(list.get(position - 1).city_pinyin.charAt(0));
			if (nextFirstWord.equals(currentFirstWord)) {
				holder.tvCityIndex.setVisibility(View.GONE);
			} else {
				holder.tvCityIndex.setVisibility(View.VISIBLE);
				holder.tvCityIndex.setText(currentFirstWord);
			}
		} else {
			holder.tvCityIndex.setVisibility(View.VISIBLE);
			holder.tvCityIndex.setText(currentFirstWord);
		}

		if (!TextUtils.isEmpty(city.city_name)) {
			holder.tvCityName.setText(city.city_name);
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
		TextView tvCityIndex;
		TextView tvCityName;

		public ViewHolder(View convertView) {
			this.tvCityIndex = (TextView) convertView.findViewById(R.id.tvCityIndex);
			this.tvCityName = (TextView) convertView.findViewById(R.id.tvCityName);
		}
	}

}
