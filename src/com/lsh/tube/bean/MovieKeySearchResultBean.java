package com.lsh.tube.bean;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 
 * @Description 影片搜索返回结果封装bean
 * @author Blast
 * @date 2015-7-21 下午5:50:20
 */
public class MovieKeySearchResultBean extends BaseBean {

	public ArrayList<MovieInfo> result;

	// 名称 类型 说明
	// error_code int 返回码
	// reason string 返回说明
	// result - 返回结果集
	// movieid string 影片唯一标识ID
	// actors string 影片的演员列表。
	// also_known_as string 影片的其它名称。
	// country string 影片的拍摄国家。
	// directors string 影片的导演列表。
	// film_locations string 影片的拍摄地。
	// genres string 影片的分类。（如：戏剧，战争）
	// language string 影片的对白使用的语言。
	// plot_simple String 影片的剧情概要。
	// poster String 影片的海报。
	// rated String 影片的分类与评级。
	// rating string 影片的得分。
	// rating_count string 影片的评分人数。
	// release_date Int 影片的上映时间。
	// runtime string 影片的持续时间。
	// title string 影片的名称。
	// type string 影片类型.
	// writers string 影片的编剧列表。
	// year Int 影片的拍摄年代。
	public static class MovieInfo implements Parcelable {
		public String movieid;
		public String actors;
		public String also_known_as;
		public String country;
		public String directors;
		public String film_locations;
		public String genres;
		public String language;
		public String plot_simple;
		public String poster;
		public String rated;
		public String rating;
		public String rating_count;
		public int release_date;
		public String runtime;
		public String title;
		public String type;
		public String writers;
		public int year;

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeString(movieid);
			dest.writeString(actors);
			dest.writeString(also_known_as);
			dest.writeString(country);
			dest.writeString(directors);
			dest.writeString(film_locations);
			dest.writeString(genres);
			dest.writeString(language);
			dest.writeString(plot_simple);
			dest.writeString(poster);
			dest.writeString(rated);
			dest.writeString(rating);
			dest.writeString(rating_count);
			dest.writeInt(release_date);
			dest.writeString(runtime);
			dest.writeString(title);
			dest.writeString(type);
			dest.writeString(writers);
			dest.writeInt(year);
		}

		public static final Parcelable.Creator<MovieInfo> CREATOR = new Parcelable.Creator<MovieKeySearchResultBean.MovieInfo>() {

			@Override
			public MovieInfo[] newArray(int size) {
				return new MovieInfo[size];
			}

			@Override
			public MovieInfo createFromParcel(Parcel source) {
				MovieInfo movieInfo = new MovieInfo();
				movieInfo.movieid = source.readString();
				movieInfo.actors = source.readString();
				movieInfo.also_known_as = source.readString();
				movieInfo.country = source.readString();
				movieInfo.directors = source.readString();
				movieInfo.film_locations = source.readString();
				movieInfo.genres = source.readString();
				movieInfo.language = source.readString();
				movieInfo.plot_simple = source.readString();
				movieInfo.poster = source.readString();
				movieInfo.rated = source.readString();
				movieInfo.rating = source.readString();
				movieInfo.rating_count = source.readString();
				movieInfo.release_date = source.readInt();
				movieInfo.runtime = source.readString();
				movieInfo.title = source.readString();
				movieInfo.type = source.readString();
				movieInfo.writers = source.readString();
				movieInfo.year = source.readInt();
				return movieInfo;
			}
		};
	}
}
