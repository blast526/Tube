package com.lsh.tube.bean;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 
 * @Description 今日放映影片查询结果封装bean
 * @author Blast
 * @date 2015-7-21 下午5:50:54
 */
public class MoviesTodaySearchResultBean extends BaseBean {

	public ArrayList<Movie> result;

	// 返回字段：
	// 名称 类型 说明
	// error_code int 返回码
	// movieId string 电影ID
	// movieName string 影片名称
	// pic_url string 影片图片
	public static class Movie implements Parcelable {
		public String movieId;
		public String movieName;
		public String pic_url;

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeString(movieId);
			dest.writeString(movieName);
			dest.writeString(pic_url);
		}

		public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<MoviesTodaySearchResultBean.Movie>() {

			@Override
			public Movie[] newArray(int size) {
				return new Movie[size];
			}

			@Override
			public Movie createFromParcel(Parcel source) {
				Movie city = new Movie();
				city.movieId = source.readString();
				city.movieName = source.readString();
				city.pic_url = source.readString();
				return city;
			}
		};
	}
}
