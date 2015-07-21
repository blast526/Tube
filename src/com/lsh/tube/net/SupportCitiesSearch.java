package com.lsh.tube.net;

import android.content.Context;

import com.lsh.tube.util.Config;
import com.lsh.tube.util.MyLog;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

/**
 * 
 * @Description 支持城市查询网络请求封装
 * @author Blast
 * @date 2015-7-21 下午5:53:16
 */
public class SupportCitiesSearch {

	private static final String TAG = "SupportCitiesSearch";

	private Context context;
	private SearchSuccessCallback searchSuccessCallback;
	private SearchFailCallback searchFailCallback;

	public SupportCitiesSearch(Context context) {
		this.context = context;
	}

	public void setSearchSuccessCallback(SearchSuccessCallback searchSuccessCallback) {
		this.searchSuccessCallback = searchSuccessCallback;
	}

	public void setSearchFailCallback(SearchFailCallback searchFailCallback) {
		this.searchFailCallback = searchFailCallback;
	}

	public void search() {
		Parameters params = new Parameters();
		params.add(Config.SEARCH_KEY_KEY, Config.MOVIE_API_APPKEY);

		JuheData.executeWithAPI(context, Config.MOVIE_API_ID, Config.SUPPRT_CITIES_URL, JuheData.GET, params, new DataCallBack() {

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
