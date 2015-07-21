package com.lsh.tube.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.lsh.tube.R;
import com.lsh.tube.util.CommonUtil;
import com.lsh.tube.view.HomeFragment;
import com.lsh.tube.view.MenuFragment;

public class MainActivity extends SlidingFragmentActivity implements OnClickListener {

	private static final String TAG = "MainActivity";

	private SlidingMenu slidingMenu;
	private MenuFragment menuFragment;
	private ImageButton ibMenuLeft;
	private TextView tvLoadCourse;

	private long exitTime = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBehindContentView(R.layout.menu_frame_layout);
		setContentView(R.layout.content_frame_layout);
		checkNet();
		initSlidingMenu();
		initView();
	}

	private void checkNet() {
		if (!CommonUtil.checkNet(this)) {
			Toast.makeText(this, "无法连接到服务器或网络", 1).show();
		}
	}

	private void initView() {
		ibMenuLeft = (ImageButton) findViewById(R.id.ibMenuLeft);
		tvLoadCourse = (TextView) findViewById(R.id.tvLoadCourse);
		ibMenuLeft.setOnClickListener(this);
	}

	private void initSlidingMenu() {
		// 1 得到滑动菜单
		slidingMenu = getSlidingMenu();
		// 2 设置滑动菜单是在左边出来还是右边出来
		// 参数可以设置左边LEFT，也可以设置右边RIGHT，还能设置左右LEFT_RIGHT
		slidingMenu.setMode(SlidingMenu.LEFT);
		// 3 设置滑动菜单出来之后，内容页，显示的剩余宽度
		slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		// 4 设置滑动菜单的阴影 设置阴影，阴影需要在开始的时候，特别暗，慢慢的变淡
		slidingMenu.setShadowDrawable(R.drawable.shadow);
		// 5 设置阴影的宽度
		slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		// 6 设置滑动菜单的范围
		// 参数可设置全屏、边缘、禁止滑动
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		// slidingMenu.setTouchModeBehind(SlidingMenu.TOUCHMODE_FULLSCREEN);

		menuFragment = new MenuFragment();
		getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, menuFragment, "Menu").commit();
		HomeFragment homeFragment = new HomeFragment();
		getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, homeFragment, "Home").commit();

	}

	/**
	 * 回调方法
	 * @param fragment
	 * @param loadCourse 
	 */
	public void switchFragment(Fragment fragment, String loadCourse) {
		getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
		tvLoadCourse.setText(loadCourse);
		// 隐藏slidingmenu
		slidingMenu.toggle();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ibMenuLeft:
			slidingMenu.toggle();
			break;

		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exit();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void exit() {
		if ((System.currentTimeMillis() - exitTime) > 1500) {
			Toast.makeText(this, "再按一次退出程序", 0).show();
			exitTime = System.currentTimeMillis();
		} else {
			finish();
			System.exit(0);
		}
	}
}
