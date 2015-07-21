package com.lsh.tube.application;

import android.app.Application;

import com.thinkland.sdk.android.JuheSDKInitializer;

/**
 * 
 * @Description 聚合数据应用SDK初始化
 * @author Blast
 * @date 2015-7-21 下午5:49:13
 */
public class MyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		JuheSDKInitializer.initialize(getApplicationContext());
	}
}
