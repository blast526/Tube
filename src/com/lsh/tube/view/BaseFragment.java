package com.lsh.tube.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lsh.tube.activity.MainActivity;

public abstract class BaseFragment extends Fragment {

	public View view;
	public Context context;
	public SlidingMenu slidingMenu;

	// 是否切换重新联网请求数据
	// public boolean flag = false;

	public View getRootView() {
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initData(savedInstanceState);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity();

		slidingMenu = ((MainActivity) getActivity()).getSlidingMenu();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = initView(inflater);
		return view;
	}

	/**
	 * 初始化界面
	 * @param inflater
	 * @return
	 */
	public abstract View initView(LayoutInflater inflater);

	/**
	 * 初始化数据
	 * @param savedInstanceState
	 */
	public abstract void initData(Bundle savedInstanceState);
}
