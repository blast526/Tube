package com.lsh.tube.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.lsh.tube.R;

/**
 * 
 * @Description 周边影院Fragment
 * @author Blast
 * @date 2015-7-21 下午6:02:38
 */
public class SurroundingCinemaFragment extends BaseFragment {

	@Override
	public View initView(LayoutInflater inflater) {
		view = inflater.inflate(R.layout.surrounding_cinema_fragment_layout, null);
		return view;
		// TODO Auto-generated method stub
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	}

}
