package com.lsh.tube.util;

import android.util.Log;

/**
 * 
 * @Description 自定义日志工具
 * @author Blast
 * @date 2015-7-21 下午5:58:43
 */
public class MyLog {

	public static void d(String tag, String msg) {
		if (Config.IS_DEBUG) {
			Log.d(tag, msg);
		}
	}

	public static void d(String tag, String msg, Throwable e) {
		if (Config.IS_DEBUG) {
			Log.d(tag, msg, e);
		}
	}

	public static void i(String tag, String msg) {
		if (Config.IS_DEBUG) {
			Log.i(tag, msg);
		}
	}

	public static void v(String tag, String msg) {
		if (Config.IS_DEBUG) {
			Log.v(tag, msg);
		}
	}

	public static void e(String tag, String msg) {
		if (Config.IS_DEBUG) {
			Log.e(tag, msg);
		}
	}

	public static void e(String tag, String msg, Throwable e) {
		if (Config.IS_DEBUG) {
			Log.e(tag, msg, e);
		}
	}

	public static void w(String tag, String msg) {
		if (Config.IS_DEBUG) {
			Log.w(tag, msg);
		}
	}

	public static void w(String tag, String msg, Throwable e) {
		if (Config.IS_DEBUG) {
			Log.w(tag, msg, e);
		}
	}

}