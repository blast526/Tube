package com.lsh.tube.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lsh.tube.R;
import com.lsh.tube.activity.AddressConfigActivity;
import com.lsh.tube.bean.SupportCitiesSearchResultBean.City;
import com.lsh.tube.util.CommonUtil;
import com.lsh.tube.util.DataCleanManager;
import com.lsh.tube.util.MyLog;
import com.lsh.tube.util.SharedPreferencesUtils;

/**
 * 
 * @Description 设置Fragment
 * @author Blast
 * @date 2015-7-21 下午6:02:23
 */
public class SettingFragment extends BaseFragment implements OnClickListener {

	public static final String TAG = "SettingFagment";

	private LinearLayout llAddressConfig;
	private LinearLayout llClearCache;
	private TextView tvMyAddress;
	private TextView tvCacheSize;
	private TextView tvAboutMe;

	@Override
	public View initView(LayoutInflater inflater) {
		view = inflater.inflate(R.layout.setting_fragment_layout, null);
		llAddressConfig = (LinearLayout) view.findViewById(R.id.llAddressConfig);
		llClearCache = (LinearLayout) view.findViewById(R.id.llClearCache);
		tvMyAddress = (TextView) view.findViewById(R.id.tvMyAddress);
		tvCacheSize = (TextView) view.findViewById(R.id.tvCacheSize);
		tvAboutMe = (TextView) view.findViewById(R.id.tvAboutMe);
		setListener();
		return view;
	}

	private void setListener() {
		llAddressConfig.setOnClickListener(this);
		llClearCache.setOnClickListener(this);
		tvAboutMe.setOnClickListener(this);
	}

	@Override
	public void initData(Bundle savedInstanceState) {
		// String packageName = context.getPackageName();
		// queryPacakgeSize(packageName);
		try {
			String totalCacheSize = DataCleanManager.getTotalCacheSize(context);
			tvCacheSize.setText(totalCacheSize);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String city_name = SharedPreferencesUtils.getString(context, "city_name");
		tvMyAddress.setText(city_name);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.llAddressConfig:
			// 网络判断
			if (CommonUtil.checkNet(context)) {
				Intent intent = new Intent(context, AddressConfigActivity.class);
				startActivityForResult(intent, 1);
			} else {
				Toast.makeText(context, "无法连接到服务器或网络", 1).show();
			}
			break;
		case R.id.llClearCache:
			DataCleanManager.clearAllCache(context);
			tvCacheSize.setText("0KB");
			Toast.makeText(context, "清理缓存完毕", 0).show();
			break;
		case R.id.tvAboutMe:

			break;

		default:
			break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case 1:
			if (resultCode == Activity.RESULT_OK) {
				City city = data.getParcelableExtra("cityInfo");
				MyLog.d(TAG, city.city_name);
				tvMyAddress.setText(city.city_name);
				SharedPreferencesUtils.saveString(context, "city_name", city.city_name);
				SharedPreferencesUtils.saveString(context, "city_id", city.id);
			}
			break;

		default:
			break;
		}
	}

	/*public void queryPacakgeSize(String pkgName) {
		if (pkgName != null) {
			// 使用放射机制得到PackageManager类的隐藏函数getPackageSizeInfo
			PackageManager pm = context.getPackageManager(); // 得到pm对象
			try {
				// 通过反射机制获得该隐藏函数
				Method getPackageSizeInfo = pm.getClass().getDeclaredMethod("getPackageSizeInfo", String.class, IPackageStatsObserver.class);
				// 调用该函数，并且给其分配参数 ，待调用流程完成后会回调PkgSizeObserver类的函数
				getPackageSizeInfo.invoke(pm, pkgName, new PkgSizeObserver());
			} catch (Exception e) {
				MyLog.d(TAG, "NoSuchMethodException");
				e.printStackTrace();
			}
		}
	}

	// aidl文件形成的Bindler机制服务类
	public class PkgSizeObserver extends IPackageStatsObserver.Stub {
		*//*** 回调函数，
		* @param pStatus ,返回数据封装在PackageStats对象中
		* @param succeeded  代表回调成功
		*/
	/*
	@Override
	public void onGetStatsCompleted(PackageStats pStats, boolean succeeded) throws RemoteException {
	// TODO Auto-generated method stub
	cachesize = pStats.cacheSize; // 缓存大小
	MyLog.d(TAG, "cachesize--->" + cachesize);
	tvCacheSize.setText(formateFileSize(cachesize));
	}
	}

	// 系统函数，字符串转换 long -String (kb)
	private String formateFileSize(long size) {
	return Formatter.formatFileSize(context, size);
	}*/
}
