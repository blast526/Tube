package com.lsh.tube.test;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.lsh.tube.db.MyCollectionOpenHelper;

/**
 * 
 * @Description 数据库测试工具
 * @author Blast
 * @date 2015-7-21 下午5:53:42
 */
public class DBTest extends AndroidTestCase {

	/**
	 * 数据库创建测试
	 */
	public void testDBCreate() {
		MyCollectionOpenHelper myCollectionOpenhelper = MyCollectionOpenHelper.getInstance(mContext);
		SQLiteDatabase writableDatabase = myCollectionOpenhelper.getWritableDatabase();

	}
}
