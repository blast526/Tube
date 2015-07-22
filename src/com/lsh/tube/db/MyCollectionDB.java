package com.lsh.tube.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lsh.tube.bean.MovieKeySearchResultBean.MovieInfo;

/**
 * 
 * @Description 我的收藏数据库操作工具类
 * @author Blast
 * @date 2015-7-21 下午5:52:01
 */
public class MyCollectionDB {

	private Context context;
	private SQLiteDatabase writableDatabase;
	private SQLiteDatabase readableDatabase;

	public MyCollectionDB(Context context) {
		this.context = context;
		writableDatabase = MyCollectionOpenHelper.getInstance(context).getWritableDatabase();
		readableDatabase = MyCollectionOpenHelper.getInstance(context).getReadableDatabase();
	}

	/**
	 * 向数据库中插入一条影片信息记录
	 * @param movieInfo 影片信息对象
	 * @param posterBytes 海报图片字节数组
	 * @return the row ID of the newly inserted row, or -1 if an error occurred
	 */
	public long insert(MovieInfo movieInfo, byte[] posterBytes) {
		ContentValues values = new ContentValues();
		values.put("movieid", movieInfo.movieid);
		values.put("actors", movieInfo.actors);
		values.put("also_known_as", movieInfo.also_known_as);
		values.put("country", movieInfo.country);
		values.put("directors", movieInfo.directors);
		values.put("film_locations", movieInfo.film_locations);
		values.put("genres", movieInfo.genres);
		values.put("language", movieInfo.language);
		values.put("plot_simple", movieInfo.plot_simple);
		values.put("poster", posterBytes);
		values.put("rated", movieInfo.rated);
		values.put("rating", movieInfo.rating);
		values.put("rating_count", movieInfo.rating_count);
		values.put("release_date", movieInfo.release_date);
		values.put("runtime", movieInfo.runtime);
		values.put("title", movieInfo.title);
		values.put("type", movieInfo.type);
		values.put("writers", movieInfo.writers);
		values.put("year", movieInfo.year);
		return writableDatabase.insert(MyCollectionOpenHelper.DATABASE_TABLE, null, values);
	}

	/**
	 * 根据影片id查询记录
	 * @param movieId 影片唯一表示ID
	 * @return 返回查询结果集对象
	 */
	public Cursor queryMovieInfoById(String movieId) {
		Cursor cursor = readableDatabase.query(MyCollectionOpenHelper.DATABASE_TABLE, null, "movieid = ?", new String[] { movieId }, null, null, null);
		return cursor;
	}

	/**
	 * 查询所有影片记录
	 * @return 返回查询结果集对象
	 */
	public Cursor queryAllMovieInfo() {
		Cursor cursor = readableDatabase.query(MyCollectionOpenHelper.DATABASE_TABLE, null, null, null, null, null, "add_time desc");
		return cursor;
	}

	/**
	 * 根据影片id删除记录
	 * @param movieId
	 * @return the number of rows affected if a whereClause is passed in, 0 otherwise
	 */
	public int deleteMovieInfoById(String movieId) {
		int deleteRow = writableDatabase.delete(MyCollectionOpenHelper.DATABASE_TABLE, "movieid = ?", new String[] { movieId });
		return deleteRow;
	}
}
