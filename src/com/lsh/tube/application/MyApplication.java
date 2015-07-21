package com.lsh.tube.application;

import android.app.Application;

import com.thinkland.sdk.android.JuheSDKInitializer;

public class MyApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		JuheSDKInitializer.initialize(getApplicationContext());
	}
}
