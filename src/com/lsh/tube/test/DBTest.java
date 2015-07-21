package com.lsh.tube.test;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.lsh.tube.db.MyCollectionOpenHelper;

public class DBTest extends AndroidTestCase {

	public void testDBCreate() {
		MyCollectionOpenHelper myCollectionOpenhelper = MyCollectionOpenHelper.getInstance(mContext);
		SQLiteDatabase writableDatabase = myCollectionOpenhelper.getWritableDatabase();

	}
}
