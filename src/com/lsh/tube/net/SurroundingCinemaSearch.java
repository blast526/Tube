package com.lsh.tube.net;

import android.content.Context;

import com.lsh.tube.util.Config;
import com.lsh.tube.util.MyLog;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

/**
 * 
 * @Description 周边影院查询网络请求封装
 * @author Blast
 * @date 2015-7-21 下午5:53:16
 */
public class SurroundingCinemaSearch {

	private static final String TAG = "SurroundingCinemaSearch";

	private Context context;
	private SearchSuccessCallback searchSuccessCallback;
	private SearchFailCallback searchFailCallback;

	public SurroundingCinemaSearch(Context context) {
		this.context = context;
	}

	public void setSearchSuccessCallback(SearchSuccessCallback searchSuccessCallback) {
		this.searchSuccessCallback = searchSuccessCallback;
	}

	public void setSearchFailCallback(SearchFailCallback searchFailCallback) {
		this.searchFailCallback = searchFailCallback;
	}

	public void search(String lat, String lon) {
		Parameters params = new Parameters();
		// params.add(Config.SEARCH_KEY_KEY, Config.MOVIE_API_APPKEY);
		params.add(Config.LOCATION_LAT_KEY, lat);
		params.add(Config.LOCATION_LON_KEY, lon);
		params.add(Config.LOCATION_RADIUS_KEY, Config.LOCATION_RADIUS_VALUE);

		JuheData.executeWithAPI(context, Config.MOVIE_API_ID, Config.CINEMAS_LOCAL_SEARCH_URL, JuheData.GET, params, new DataCallBack() {

			@Override
			public void onSuccess(int statusCode, String responseString) {
				if (searchSuccessCallback != null) {
					MyLog.d(TAG, responseString);
					searchSuccessCallback.onSuccess(responseString);
				}
			}

			@Override
			public void onFinish() {
			}

			@Override
			public void onFailure(int statusCode, String responseString, Throwable throwable) {
				if (searchFailCallback != null) {
					MyLog.d(TAG, responseString);
					searchFailCallback.onFail();
				}
			}
		});
	}

	public static interface SearchSuccessCallback {
		void onSuccess(String response);
	}

	public static interface SearchFailCallback {
		void onFail();
	}
}
