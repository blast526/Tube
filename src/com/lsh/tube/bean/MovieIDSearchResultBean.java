package com.lsh.tube.bean;

import com.lsh.tube.bean.MovieKeySearchResultBean.MovieInfo;

/**
 * 
 * @Description 影片搜索返回结果封装bean
 * @author Blast
 * @date 2015-7-21 下午5:50:20
 */
public class MovieIDSearchResultBean extends BaseBean {

	public MovieInfo result;

	/**
	 * 
	 * @Description 按ID检索影片返回的影片信息字段与按关键字检索返回字段相同
	 */
	// 返回字段：
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
}
