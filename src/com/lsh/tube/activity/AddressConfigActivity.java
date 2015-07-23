package com.lsh.tube.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.lsh.tube.R;
import com.lsh.tube.adpater.SupportCityResultListAdapter;
import com.lsh.tube.bean.SupportCitiesSearchResultBean;
import com.lsh.tube.bean.SupportCitiesSearchResultBean.City;
import com.lsh.tube.net.SupportCitiesSearch;
import com.lsh.tube.util.GsonTools;
import com.lsh.tube.util.MyLog;
import com.lsh.tube.widget.QuickIndexBar;

/**
 * 
 * @Description 我的地址设置界面
 * @author Blast
 * @date 2015-7-21 下午5:46:14
 */
public class AddressConfigActivity extends Activity {

	protected static final String TAG = "AddressConfigActivity";

	private SupportCitiesSearch supportCitiesSearch;
	private QuickIndexBar quickIndexBar;
	private ListView listView;
	private TextView currentIndex;
	private ArrayList<City> cities;
	private SupportCityResultListAdapter adapter;
	private Handler handler = new Handler();
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address_config_layout);
		initView();
		initData();
	}

	private void initData() {
		showProgressDialog();
		supportCitiesSearch = new SupportCitiesSearch(this);
		supportCitiesSearch.setSearchSuccessCallback(new SupportCitiesSearch.SearchSuccessCallback() {

			@Override
			public void onSuccess(String response) {
				closeProgressDialog();
				MyLog.d(TAG, response);
				// tvSupportCities.setText(response);
				SupportCitiesSearchResultBean supportCitiesSearchResultBean = GsonTools.changeGsonToBean(response, SupportCitiesSearchResultBean.class);
				cities = supportCitiesSearchResultBean.result;
				adapter = new SupportCityResultListAdapter(AddressConfigActivity.this, cities);
				listView.setAdapter(adapter);
			}
		});
		supportCitiesSearch.search();

	}

	private void initView() {
		listView = (ListView) findViewById(R.id.listview);
		currentIndex = (TextView) findViewById(R.id.currentIndex);
		quickIndexBar = (QuickIndexBar) findViewById(R.id.quickIndexBar);
		quickIndexBar.setOnTouchIndexListener(new QuickIndexBar.OnTouchIndexListener() {

			@Override
			public void onTouchIndex(String word) {
				showIndex(word);

				for (int i = 0; i < cities.size(); i++) {
					String firstWord = String.valueOf(cities.get(i).city_pinyin.charAt(0));
					if (firstWord.equals(word)) {
						listView.setSelection(i);
						break;
					}
				}
			}
		});
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				City city = adapter.getItem(position);
				Intent intent = new Intent();
				intent.putExtra("cityInfo", city);
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}

	protected void showIndex(String word) {
		currentIndex.setVisibility(View.VISIBLE);
		currentIndex.setText(word);

		handler.removeCallbacksAndMessages(null);
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				currentIndex.setVisibility(View.GONE);
			}
		}, 2015);
	}

	/**
	 * 显示进度对话框
	 */
	private void showProgressDialog() {
		if (progressDialog == null) {
			progressDialog = new ProgressDialog(this);
			progressDialog.setMessage("勺飞正在屁颠屁颠地加载。。。");
			progressDialog.setCanceledOnTouchOutside(false);
		}
		progressDialog.show();
	}

	/**
	 * 关闭进度对话框
	 */
	private void closeProgressDialog() {
		if (progressDialog != null) {
			progressDialog.dismiss();
		}
	}
}
