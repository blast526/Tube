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
	 * 聚合数据OpenID
	 */
	public static final String JuHe_Open_ID = "JH3a5883061ee1d50a28418509b3797284";
	// public static final String JuHe_Open_ID =
	// "JH4e4ef93c5816c12a98d238455737e04d";

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
	public static final String SUPPORT_CITIES_URL = "http://v.juhe.cn/movie/citys";

	// 请求参数：
	// 名称 类型 必填 说明
	// key string 是 应用APPKEY(应用详细页查询)
	// dtype string 否 返回数据的格式,xml/json，默认json

	/**********************今日放映影片*********************************/
	/**
	 * 今日放映影片接口
	 */
	public static final String MOVIES_TODAY_URL = "http://v.juhe.cn/movie/movies.today";

	// 请求参数：
	// 名称 类型 必填 说明
	// cityid int 是 城市ID
	// key string 是 应用APPKEY(应用详细页查询)
	// dtype string 否 返回数据的格式,xml/json，默认json
	public static final String MOVIE_TODAY_CITYID_KEY = "cityid";

	/**********************ID检索影片信息*********************************/
	/**
	 * ID检索影片信息接口
	 */
	public static final String MOVIE_SEARCH_BY_ID_URL = "http://v.juhe.cn/movie/query";

	// 请求参数：
	// 名称 类型 必填 说明
	// movieid string 是 需要检索的影片id
	// key string 是 应用APPKEY(应用详细页查询)
	// dtype string 否 返回数据的格式,xml/json，默认json
	public static final String MOVIE_SEARCH_BY_ID_KEY = "movieid";

	/**********************检索周边影院*********************************/
	/**
	 * 检索周边影院接口
	 */
	public static final String CINEMAS_LOCAL_SEARCH_URL = "http://v.juhe.cn/movie/cinemas.local";

	// 请求参数：
	// 名称 类型 必填 说明
	// lat string 是 纬度，百度地图坐标系
	// lon string 是 经度，百度地图坐标系
	// radius string 是 检索半径(米)，最大3000
	// key string 是 应用APPKEY(应用详细页查询)
	// dtype string 否 返回数据的格式,xml/json，默认json
	public static final String LOCATION_LAT_KEY = "lat";
	public static final String LOCATION_LON_KEY = "lon";
	public static final String LOCATION_RADIUS_KEY = "radius";
	public static final String LOCATION_RADIUS_VALUE = "3000";
}
