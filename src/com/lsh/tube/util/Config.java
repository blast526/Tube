package com.lsh.tube.util;

/**
 * 
 * @Description 常量配置类
 * @author Blast
 * @date 2015-7-21 下午5:56:56
 */
public class Config {

	/**
	 * 调试日志输出
	 */
	public static final boolean IS_DEBUG = true;

	/**
	 * 影讯API合集APPKEY
	 */
	public static final String MOVIE_API_APPKEY = "e82b8542c44c4c77b5abe72c995fde5b";

	/**
	 * 影讯API合集接口ID
	 */
	public static final int MOVIE_API_ID = 42;

	/**
	 * key string 是 应用APPKEY(应用详细页查询)
	 */
	public static final String SEARCH_KEY_KEY = "key";

	/**
	 * 返回数据的格式,xml/json，默认json
	 */
	public static final String DTYPE_KEY = "dtype";

	/**********************关键字检索影片信息*********************************/
	/**
	 * 关键字检索影片信息接口
	 */
	public static final String MOVIE_KEY_SEARCH_URL = "http://v.juhe.cn/movie/index";

	// 请求参数：
	// 名称 类型 必填 说明
	// title string 是 需要检索的影片标题,utf8编码的urlencode
	// smode int 否 是否精确查找，精确:1 模糊:0 默认1
	// pagesize int 否 每次返回条数，默认20,最大50
	// offset int 否 偏移量，默认0,最大760
	// key string 是 应用APPKEY(应用详细页查询)
	// dtype string 否 返回数据的格式,xml/json，默认json
	public static final String MOVIE_SEARCH_TITLE_KEY = "title";
	public static final String MOVIE_SEARCH_SMODE_KEY = "smode";
	public static final String MOVIE_SEARCH_PAGESIZE_KEY = "pagesize";
	public static final String MOVIE_SEARCH_OFFSET_KEY = "offset";

	/**********************支持城市列表*********************************/
	/**
	 * 支持城市列表接口
	 */
	public static final String SUPPRT_CITIES_URL = "http://v.juhe.cn/movie/citys";

	// 请求参数：
	// 名称 类型 必填 说明
	// key string 是 应用APPKEY(应用详细页查询)
	// dtype string 否 返回数据的格式,xml/json，默认json
}
