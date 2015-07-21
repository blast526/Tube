package com.lsh.tube.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 * @Description 我的收藏数据库帮助类
 * @author Blast
 * @date 2015-7-21 下午5:52:24
 */
public class MyCollectionOpenHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "mycollection.db";
	public static final String DATABASE_TABLE = "mycollection";
	public static final int DATABASE_VERSION = 1;

	// 名称 类型 说明
	// error_code int 返回码
	// reason string 返回说明
	// result - 返回结果集
	// movieid string 影片唯一标识ID
	// actors string 影片的演员列表。
	// also_known_as string 影片的其它名称。
	// country string 影片的拍摄国家。
	// directors string 影片的导演列表。
	// film_locations string 影片的拍摄地。
	// genres string 影片的分类。（如：戏剧，战争）
	// language string 影片的对白使用的语言。
	// plot_simple String 影片的剧情概要。
	// poster String 影片的海报。
	// rated String 影片的分类与评级。
	// rating string 影片的得分。
	// rating_count string 影片的评分人数。
	// release_date Int 影片的上映时间。
	// runtime string 影片的持续时间。
	// title string 影片的名称。
	// type string 影片类型.
	// writers string 影片的编剧列表。
	// year Int 影片的拍摄年代。
	public static final String CREATE_TABLE = "create table " + DATABASE_TABLE + "(_id integer primary key autoincrement," + " movieid text not null, actors text, also_known_as text, country text,directors text, film_locations text, genres text, language text, plot_simple text, poster blob, rated text, rating text, rating_count text, release_date integer, runtime text, title text, type text, writers text, year integer, add_time timestamp not null default CURRENT_TIMESTAMP);";

	/**************************单例模式**************************/
	private static MyCollectionOpenHelper mInstance;

	public MyCollectionOpenHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 获得对象
	 * @param context
	 * @return
	 */
	public static MyCollectionOpenHelper getInstance(Context context) {
		if (mInstance == null) {
			synchronized (MyCollectionOpenHelper.class) {
				if (mInstance == null) {
					mInstance = new MyCollectionOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
				}
			}
		}
		return mInstance;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
