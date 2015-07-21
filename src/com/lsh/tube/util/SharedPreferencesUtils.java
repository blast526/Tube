package com.lsh.tube.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 
 * @Description SharedPreferences存储工具
 * @author Blast
 * @date 2015-7-21 下午5:59:09
 */
public class SharedPreferencesUtils {

	private static SharedPreferences sp;
	private static String SP_NAME = "config";

	public static void saveString(Context context, String key, String value) {
		if (sp == null) {
			sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
		}
		Editor editor = sp.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static String getString(Context context, String key) {
		if (sp == null) {
			sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
		}
		return sp.getString(key, "");

	}
}
