package com.lsh.tube.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.lsh.tube.R;

/**
 * 
 * @Description 启动页面
 * @author Blast
 * @date 2015-7-21 下午5:47:56
 */
public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_layout);

		// 1.拿到当前activity的layout的view
		// 2.绑定透明度变化的动画
		// 3.动画执行完毕后，进入主页面

		ivWelcome = (ImageView) findViewById(R.id.ivWelcome);
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.welcome_anim);
		ivWelcome.startAnimation(animation);
		animation.setAnimationListener(animationListener);

	}

	private AnimationListener animationListener = new AnimationListener() {

		@Override
		public void onAnimationStart(Animation animation) {
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
		}

		@Override
		public void onAnimationEnd(Animation animation) {
			// 动画执行结束的时候去主页面
			Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		}
	};
	private ImageView ivWelcome;

	/**
	 * 欢迎界面禁止返回键
	 */
	public void onBackPressed() {
	};
}
