# Tube
> * 影音资讯搜索，个人试水项目
> * 数据支持：聚合数据，影讯API

------

## 7月21日，首次提交

1.大体界面的搭建，使用开源框架slidingmenu，配合fragment
  WelcomeActivity 欢迎界面，放大、渐显动画效果
  MainActivity  主界面framelayout，切换fragment容器
  MenuFragment  菜单界面
  HomeFragment  影音资讯检索界面
  SurroundingCinemaFragment 周边影院界面
  MyCollectionFragment  我的收藏界面
  SettingFragment 设置界面

2.MovieKeySearch 关键字检索影片信息的网络请求封装
  MovieKeySearchResultBean 关键字检索影片信息封装bean，请求返回json格式数据，使用gson解析，传递数据所需Parcelable序列化

3.MovieSearchResultListActivity 关键字检索影片信息结果列表展示，MovieSearchResultListAdapter适配器优化

4.MovieInfoDetailActivity 影片信息详情页，图片使用xUtils中的BitmapUtils缓存与展示

5.SupportCitiesSearch 支持城市列表查询的网络请求封装
  SupportCitiesSearchResultBean 支持城市列表封装bean，请求返回json格式数据，使用gson解析，传递数据所需Parcelable序列化

6.AddressConfigActivity 我的地址设置界面，自定义快速索引控件QuickIndexBar，城市列表按拼音首字母排序，返回城市信息，使用SharedPreferences存储

7.DataCleanManager 使用工具计算缓存大小，并清理

> * {目前存在的bug：1.网络判断 2.slidingmenu菜单界面滑动支持}
> * {目前想要添加的功能：1.收藏功能，数据库创建与功能 2.分享功能}

## 7月21日 下午

1.MyCollectionOpenHelper 数据库帮助类，采用单例模式构造，创建我的收藏数据库;其中图片以字节数组方式存储blob；添加时间戳

2.MyCollectionDB 数据库操作封装，已创建两个方法：向数据库中插入一条影片信息记录和根据影片id查询记录

> * {网络判断修复；slidingmenu菜单区域支持侧滑关闭，setTouchModeBehind(SlidingMenu.TOUCHMODE_FULLSCREEN)，菜单条目点击事件失效；可仿QQ菜单特效实现，未实现}

3.分享功能实现，ShareMovieInfoUtil 同时分享海报和添加文字信息；使用系统自带Intent.ACTION_SEND
  ```java
  public void share() {
    // 从缓存中获取图片文件
    File bitmapFileFromDiskCache = bitmapUtils.getBitmapFileFromDiskCache(movieInfo.poster);
    // 获取图片文件路径
    String imgPath = bitmapFileFromDiskCache.getAbsolutePath();
    ShareMovieInfoUtil.shareMsg(this, "分享影片", "影片分享", "这是我喜爱的电影，强烈推荐！--" + movieInfo.title, imgPath);
  }
  ```

4.设置点击监听，requestDisallowInterceptTouchEvent方法，解决影片信息详情页面ScrollView嵌套的滚动冲突

## 7月21日 晚上

1.MoviesTodaySearch 今日放映影片查询的网络请求封装，MoviesTodaySearchResultBean 今日放映影片查询封装bean，请求返回json格式数据，使用gson解析，传递数据所需Parcelable序列化

2.HomeFragment 中正在热映的使用GridView展示今日放映影片，MoviesTodayGridViewAdapter适配器优化

> * GridView 图片和片名布局错位，尚未处理
> * 计划HomeFragment只展示一部分，添加<更多>点击事件，跳转展示更多放映影片，考虑使用上拉加载更多，并为GridView添加item点击事件，跳转到MovieInfoDetailActivity界面
> * 我的收藏界面尚未处理，计划使用listview展示添加到数据库中的影片信息，考虑使用侧滑删除控件，添加清空功能

## 7月22日 下午

1.MoreMoviesTodayActivity 展示今日热映影片，使用开源框架PullToRefresh实现上拉加载更多

2.修复slidingmenu切换同一个Fragment时重新创建

3.MovieIDSearch 按影片id检索影片信息网络请求封装，MoviesTodaySearchResultBean id检索影片信息封装bean，其中影片信息MovieInfo 复用MovieKeySearchResultBean中的MovieInfo，因为返回字段相同

4.实现两个界面中GridView的item点击事件，并跳转

## 7月22日 晚上

1.MyCollectionFragment 我的收藏界面完善，使用开源框架SwipeMenuListView 对数据库查询收藏记录返回结果集封装bean进行展示；添加侧滑后可打开跳转到影片信息详情页面和删除数据记录的功能

2.由于收藏时海报图片采用blob存储，为了离线也可查看，在MovieInfo中添加成员变量Bitmap posterBitmap,跳转后判断设置ImageView

> * 离线打开应用，点击<更多>提示无网络，
> * 直接打开网络，连接后再点击<更多>，直接崩溃,
> * 因为上一个界面中今日放映影片的网络请求并未重新执行，
> * 所以moviesTodayResult为空

> * 存在bug，更多影片页面，刷新问题
> * 中间有一次在城市列表页面莫名崩溃，后来再测试并未出现，等待分析log

## 7月23日 上午

1.使用监听网络变化广播，处理网络切换的一些bug，在真机上测试，模拟器单独移动网络判断有网络，但实际没网络，加一步判断

2.为切换我的住址城市列表加载添加进度对话框

> * 构思周边影院，查看接口文档需要传递百度地图经纬度坐标 （╯‵□′）╯︵┴─┴
> * 一些点击滑动体验

## 7月23日 晚上

1.SurroundingCinemaFragment 周边影院，使用百度地图定位SDK，简单获取经纬度和位置信息，通过网络查询周边影院信息用列表展示

2.SurroundingCinemaSearch 周边影院信息网络请求封装，SurroudingCinemaSearchResultBean 周边影院信息信息封装bean

> * 还可继续完善各影院当日放映影片查询，然后再展示，进而跳转影片详情页，不再继续
> * 聚合数据使用次数完了 （╯‵□′）╯︵┴─┴
> * 准备做一下AboutMe界面，添加个小彩蛋