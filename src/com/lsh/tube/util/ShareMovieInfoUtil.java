package com.lsh.tube.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;

/**
 * 
 * @Description 分享图片和文字工具
 * @author Blast
 * @date 2015-7-21 下午5:45:45
 */
public class ShareMovieInfoUtil {

	/* 分享功能  
	 * @param context 上下文  
	 * @param activityTitle Activity的名字  
	 * @param msgTitle 消息标题  
	 * @param msgText 消息内容  
	 * @param imgPath 图片路径，不分享图片则传null  
	 */
	public static void shareMsg(Context context, String activityTitle, String msgTitle, String msgText, String imgPath) {
		Intent intent = new Intent(Intent.ACTION_SEND);
		if (imgPath == null || imgPath.equals("")) {
			intent.setType("text/plain"); // 纯文本
		} else {
			File f = new File(imgPath);
			if (f != null && f.exists() && f.isFile()) {
				intent.setType("image/jpg");
				Uri u = Uri.fromFile(f);
				intent.putExtra(Intent.EXTRA_STREAM, u);
			}
		}
		intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
		intent.putExtra(Intent.EXTRA_TEXT, msgText);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(Intent.createChooser(intent, activityTitle));
	}

	/**
	 * 将bitmap转为file
	 * @param bmp
	 * @param filename
	 * @return
	 */
	public static boolean saveBitmap2file(Context context, Bitmap bmp, String filename) {
		CompressFormat format = Bitmap.CompressFormat.JPEG;
		int quality = 100;
		OutputStream stream = null;
		try {
			stream = new FileOutputStream(context.getExternalCacheDir().getAbsolutePath() + filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return bmp.compress(format, quality, stream);
	}
}
