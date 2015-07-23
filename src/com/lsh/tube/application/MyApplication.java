package com.lsh.tube.application;

import android.app.Application;
import android.content.Context;

import com.thinkland.sdk.android.JuheSDKInitializer;

/**
 * 
 * @Description 聚合数据应用SDK初始化
 * @author Blast
 * @date 2015-7-21 下午5:49:13
 */
public class MyApplication extends Application {

	private static Context context;

	@Override
	public void onCreate() {
		super.onCreate();
		JuheSDKInitializer.initialize(getApplicationContext());
		context = getApplicationContext();
	}

	public static Context getContext() {
		return context;
	}
}
