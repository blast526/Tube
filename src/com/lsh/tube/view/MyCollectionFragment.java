package com.lsh.tube.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.lsh.tube.R;

/**
 * 
 * @Description 我的收藏Fragment
 * @author Blast
 * @date 2015-7-21 下午6:01:16
 */
public class MyCollectionFragment extends BaseFragment {

	@Override
	public View initView(LayoutInflater inflater) {
		view = inflater.inflate(R.layout.my_collection_fragment_layout, null);
		return view;
		// TODO Auto-generated method stub
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	}

}
