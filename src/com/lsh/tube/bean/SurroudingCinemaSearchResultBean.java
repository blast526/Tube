package com.lsh.tube.bean;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 
 * @Description 周边影院搜索返回结果封装bean
 * @author Blast
 * @date 2015-7-21 下午5:50:20
 */
public class SurroudingCinemaSearchResultBean extends BaseBean {

	public ArrayList<Cinema> result;

	// 返回字段：
	// 名称 类型 说明
	// error_code int 返回码
	// id string 电影院ID
	// cityName string 影院所属城市
	// cinemaName string 影院名称
	// address string 影院地址
	// telephone string 联系电话
	// latitude double 纬度，适合百度地图
	// longitude double 经度，适合百度地图
	// trafficRoutes string 交通路线
	// distance string 大概距离(米)
	public static class Cinema implements Parcelable {
		public String id;
		public String cityName;
		public String cinemaName;
		public String address;
		public String telephone;
		public double latitude;
		public double longitude;
		public String trafficRoutes;
		public String distance;

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeString(id);
			dest.writeString(cityName);
			dest.writeString(cinemaName);
			dest.writeString(address);
			dest.writeString(telephone);
			dest.writeDouble(latitude);
			dest.writeDouble(longitude);
			dest.writeString(trafficRoutes);
			dest.writeString(distance);
		}

		public static final Parcelable.Creator<Cinema> CREATOR = new Parcelable.Creator<SurroudingCinemaSearchResultBean.Cinema>() {

			@Override
			public Cinema[] newArray(int size) {
				return new Cinema[size];
			}

			@Override
			public Cinema createFromParcel(Parcel source) {
				Cinema cinema = new Cinema();
				cinema.id = source.readString();
				cinema.cityName = source.readString();
				cinema.cinemaName = source.readString();
				cinema.address = source.readString();
				cinema.telephone = source.readString();
				cinema.latitude = source.readDouble();
				cinema.longitude = source.readDouble();
				cinema.trafficRoutes = source.readString();
				cinema.distance = source.readString();

				return cinema;
			}
		};
	}
}
