package com.lsh.tube.net;

import android.content.Context;

import com.lsh.tube.util.Config;
import com.lsh.tube.util.MyLog;
import com.thinkland.sdk.android.DataCallBack;
import com.thinkland.sdk.android.JuheData;
import com.thinkland.sdk.android.Parameters;

/**
 * 
 * @Description 按ID检索影片信息网络请求封装
 * @author Blast
 * @date 2015-7-21 下午5:53:16
 */
public class MovieIDSearch {

	private static final String TAG = "MovieIDSearch";

	private Context context;
	private SearchSuccessCallback searchSuccessCallback;
	private SearchFailCallback searchFailCallback;

	public MovieIDSearch(Context context) {
		this.context = context;
	}

	public void setSearchSuccessCallback(SearchSuccessCallback searchSuccessCallback) {
		this.searchSuccessCallback = searchSuccessCallback;
	}

	public void setSearchFailCallback(SearchFailCallback searchFailCallback) {
		this.searchFailCallback = searchFailCallback;
	}

	public void search(String movieid) {
		Parameters params = new Parameters();
		// params.add(Config.SEARCH_KEY_KEY, Config.MOVIE_API_APPKEY);
		params.add(Config.MOVIE_SEARCH_BY_ID_KEY, movieid);

		JuheData.executeWithAPI(context, Config.MOVIE_API_ID, Config.MOVIE_SEARCH_BY_ID_URL, JuheData.GET, params, new DataCallBack() {

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
