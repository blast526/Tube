package com.lsh.tube.bean;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class SupportCitiesSearchResultBean extends BaseBean {

	public ArrayList<City> result;

	// 名称 类型 说明
	// error_code int 返回码
	// id string 城市ID
	// city_name string 城市名称
	// city_pre string 城市前缀
	// city_pinyin string 城市拼音
	// city_short string 城市简写
	// count string 影院数量
	public static class City implements Parcelable, Comparable<City> {
		public String id;
		public String city_name;
		public String city_pre;
		public String city_pinyin;
		public String city_short;
		public String count;

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeString(id);
			dest.writeString(city_name);
			dest.writeString(city_pre);
			dest.writeString(city_pinyin);
			dest.writeString(city_short);
			dest.writeString(count);
		}

		public static final Parcelable.Creator<City> CREATOR = new Parcelable.Creator<SupportCitiesSearchResultBean.City>() {

			@Override
			public City[] newArray(int size) {
				return new City[size];
			}

			@Override
			public City createFromParcel(Parcel source) {
				City city = new City();
				city.id = source.readString();
				city.city_name = source.readString();
				city.city_pre = source.readString();
				city.city_pinyin = source.readString();
				city.city_short = source.readString();
				city.count = source.readString();
				return city;
			}
		};

		@Override
		public int compareTo(City another) {
			return this.city_pinyin.compareTo(another.city_pinyin);
		}
	}
}
