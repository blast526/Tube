package com.lsh.tube.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.lsh.tube.R;
import com.lsh.tube.activity.MainActivity;

/**
 * 
 * @Description SlidingMenu菜单Fragment
 * @author Blast
 * @date 2015-7-21 下午6:00:52
 */
public class MenuFragment extends BaseFragment implements OnClickListener {

	private ImageView ivAvatar;
	private TextView tvNickName;
	private TextView tvMovieInfo;
	private TextView tvSurroundingCinema;
	private TextView tvMyCollection;
	private TextView tvSetting;

	@Override
	public View initView(LayoutInflater inflater) {
		view = inflater.inflate(R.layout.menu_fragment_layout, null);
		ivAvatar = (ImageView) view.findViewById(R.id.ivAvatar);
		tvNickName = (TextView) view.findViewById(R.id.tvNickName);
		tvMovieInfo = (TextView) view.findViewById(R.id.tvMovieInfo);
		tvSurroundingCinema = (TextView) view.findViewById(R.id.tvSurroundingCinema);
		tvMyCollection = (TextView) view.findViewById(R.id.tvMyCollection);
		tvSetting = (TextView) view.findViewById(R.id.tvSetting);

		tvMovieInfo.setOnClickListener(this);
		tvSurroundingCinema.setOnClickListener(this);
		tvMyCollection.setOnClickListener(this);
		tvSetting.setOnClickListener(this);
		return view;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
	}

	@Override
	public void onClick(View v) {
		Fragment fragment = null;
		String loadCourse = "";
		switch (v.getId()) {
		case R.id.tvMovieInfo:
			fragment = new HomeFragment();
			loadCourse = tvMovieInfo.getText().toString();
			break;
		case R.id.tvSurroundingCinema:
			fragment = new SurroundingCinemaFragment();
			loadCourse = tvSurroundingCinema.getText().toString();
			break;
		case R.id.tvMyCollection:
			fragment = new MyCollectionFragment();
			loadCourse = tvMyCollection.getText().toString();
			break;
		case R.id.tvSetting:
			fragment = new SettingFragment();
			loadCourse = tvSetting.getText().toString();
			break;
		}
		switchFragment(fragment, loadCourse);
	}

	// 切换content
	private void switchFragment(Fragment fragment, String loadCourse) {
		if (fragment != null) {
			if (getActivity() instanceof MainActivity) {
				((MainActivity) getActivity()).switchFragment(fragment, loadCourse);
			}
		}
	}

}
