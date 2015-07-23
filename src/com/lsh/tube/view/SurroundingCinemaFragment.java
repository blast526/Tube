package com.lsh.tube.view;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.location.Poi;
import com.lsh.tube.R;
import com.lsh.tube.adpater.SurroundingCinemaSearchResultListAdapter;
import com.lsh.tube.application.MyApplication;
import com.lsh.tube.bean.SurroudingCinemaSearchResultBean;
import com.lsh.tube.bean.SurroudingCinemaSearchResultBean.Cinema;
import com.lsh.tube.net.SurroundingCinemaSearch;
import com.lsh.tube.util.CommonUtil;
import com.lsh.tube.util.GsonTools;
import com.lsh.tube.util.MyLog;

/**
 * 
 * @Description 周边影院Fragment
 * @author Blast
 * @date 2015-7-21 下午6:02:38
 */
public class SurroundingCinemaFragment extends BaseFragment {

	public static final String TAG = "SurroundingCinemaFragment";

	private LocationClient mLocationClient;
	private BDLocationListener myListener;

	private TextView tvSearchLocation;
	private ListView lvCinemaListView;
	private SurroundingCinemaSearch surroundingCinemaSearch;
	private ArrayList<Cinema> surroundingCinemas;
	private SurroundingCinemaSearchResultListAdapter adapter;
	private ProgressDialog progressDialog;

	private String lat;// 纬度，百度地图坐标系
	private String lon;// 经度，百度地图坐标系
	private String addr;// 具体地址
	private String locationdescribe;// 地址描述
	private String describe;// 定位描述

	@Override
	public View initView(LayoutInflater inflater) {
		view = inflater.inflate(R.layout.surrounding_cinema_fragment_layout, null);
		tvSearchLocation = (TextView) view.findViewById(R.id.tvSearchLocation);
		lvCinemaListView = (ListView) view.findViewById(R.id.lvCinemaListView);
		return view;
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		// 显示进度对话框
		showProgressDialog();

		mLocationClient = new LocationClient(MyApplication.getContext()); // 声明LocationClient类
		myListener = new MyLocationListener();
		mLocationClient.registerLocationListener(myListener);
		initLocation();
		mLocationClient.start();
		surroundingCinemaSearch = new SurroundingCinemaSearch(context);
		surroundingCinemaSearch.setSearchSuccessCallback(new SurroundingCinemaSearch.SearchSuccessCallback() {

			@Override
			public void onSuccess(String response) {
				// 关闭进度对话框
				closeProgressDialog();

				MyLog.d(TAG, response);
				SurroudingCinemaSearchResultBean surroudingCinemaSearchResultBean = GsonTools.changeGsonToBean(response, SurroudingCinemaSearchResultBean.class);
				surroundingCinemas = surroudingCinemaSearchResultBean.result;
				if (surroundingCinemas != null && (!tvSearchLocation.getText().toString().equals("请确认网络畅通"))) {
					mLocationClient.stop();
					adapter = new SurroundingCinemaSearchResultListAdapter(context, surroundingCinemas);
					lvCinemaListView.setAdapter(adapter);
				}
			}
		});
	}

	private void initLocation() {
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(LocationMode.Hight_Accuracy);// 可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
		option.setCoorType("bd09ll");// 可选，默认gcj02，设置返回的定位结果坐标系
		int span = 1000;
		option.setScanSpan(span);// 可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
		option.setIsNeedAddress(true);// 可选，设置是否需要地址信息，默认不需要
		option.setOpenGps(true);// 可选，默认false,设置是否使用gps
		option.setLocationNotify(false);// 可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
		option.setIsNeedLocationDescribe(true);// 可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
		option.setIsNeedLocationPoiList(false);// 可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
		option.setIgnoreKillProcess(false);// 可选，默认false，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认杀死
		option.SetIgnoreCacheException(false);// 可选，默认false，设置是否收集CRASH信息，默认收集
		option.setEnableSimulateGps(false);// 可选，默认false，设置是否需要过滤gps仿真结果，默认需要
		mLocationClient.setLocOption(option);
	}

	class MyLocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// 关闭进度对话框
			closeProgressDialog();
			// Receive Location
			StringBuffer sb = new StringBuffer(256);
			sb.append("time : ");
			sb.append(location.getTime());
			sb.append("\nerror code : ");
			sb.append(location.getLocType());
			sb.append("\nlatitude : ");
			sb.append(location.getLatitude());
			sb.append("\nlontitude : ");
			sb.append(location.getLongitude());
			sb.append("\nradius : ");
			sb.append(location.getRadius());

			if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
				sb.append("\nspeed : ");
				sb.append(location.getSpeed());// 单位：公里每小时
				sb.append("\nsatellite : ");
				sb.append(location.getSatelliteNumber());
				sb.append("\nheight : ");
				sb.append(location.getAltitude());// 单位：米
				sb.append("\ndirection : ");
				sb.append(location.getDirection());// 单位度
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				sb.append("\ndescribe : ");
				sb.append("gps定位成功");

				// 设置定位结果和描述
				addr = location.getAddrStr();
				describe = "gps定位成功";

			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				// 运营商信息
				sb.append("\noperationers : ");
				sb.append(location.getOperators());
				sb.append("\ndescribe : ");
				sb.append("网络定位成功");

				// 设置定位结果和描述
				addr = location.getAddrStr();
				describe = "网络定位成功";

			} else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
				sb.append("\ndescribe : ");
				sb.append("离线定位成功，离线定位结果也是有效的");

				// 设置定位结果和描述
				describe = "离线定位成功";
			} else if (location.getLocType() == BDLocation.TypeServerError) {
				sb.append("\ndescribe : ");
				sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");

				// 设置定位结果和描述
				describe = "服务端网络定位失败";
			} else if (location.getLocType() == BDLocation.TypeNetWorkException) {
				sb.append("\ndescribe : ");
				sb.append("网络不同导致定位失败，请检查网络是否通畅");

				// 设置定位结果和描述
				describe = "网络不同导致定位失败，请检查网络是否通畅";
			} else if (location.getLocType() == BDLocation.TypeCriteriaException) {
				sb.append("\ndescribe : ");
				sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");

				// 设置定位结果和描述
				describe = "无法获取有效定位依据导致定位失败";
			}
			sb.append("\nlocationdescribe : ");
			sb.append(location.getLocationDescribe());// 位置语义化信息
			List<Poi> list = location.getPoiList();// POI数据
			if (list != null) {
				sb.append("\npoilist size = : ");
				sb.append(list.size());
				for (Poi p : list) {
					sb.append("\npoi= : ");
					sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
				}
			}
			MyLog.d("BaiduLocationApiDem", sb.toString());

			// 设置经纬度
			lat = String.valueOf(location.getLatitude());
			lon = String.valueOf(location.getLongitude());
			// 设置地址描述
			locationdescribe = location.getLocationDescribe();

			MyLog.d(TAG, "纬度:" + lat + "--经度:" + lon + "--地址:" + locationdescribe);
			MyLog.d(TAG, "详细地址:" + addr + "--定位结果:" + describe);
			// 设置显示地址，根据经纬度查询周边影院
			if (addr == null && locationdescribe == null) {
				locationdescribe = "请确认网络畅通";
			} else {
				searchSurroundingCinema();
			}
			setView();
		}

	}

	public void setView() {
		if (!TextUtils.isEmpty(locationdescribe)) {
			tvSearchLocation.setText(locationdescribe);
		} else if (!TextUtils.isEmpty(addr)) {
			tvSearchLocation.setText(addr);
		}
	}

	public void searchSurroundingCinema() {
		if (CommonUtil.checkNet(context)) {
			surroundingCinemaSearch.search(lat, lon);
		}
	}

	/**
	 * 显示进度对话框
	 */
	private void showProgressDialog() {
		if (progressDialog == null) {
			progressDialog = new ProgressDialog(context);
			progressDialog.setMessage("正在定位并搜索附近影院。。。");
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
