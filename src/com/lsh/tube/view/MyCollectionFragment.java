package com.lsh.tube.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.lsh.tube.R;

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
