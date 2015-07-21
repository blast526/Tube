# Tube
影音资讯搜索，个人试水项目
数据支持：聚合数据，影讯API

7月21日，首次提交

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

{目前存在的bug：1.网络判断 2.slidingmenu菜单界面滑动支持}
{目前想要添加的功能：1.收藏功能，数据库创建与功能 2.分享功能}

7月21日 下午

1.MyCollectionOpenHelper 数据库帮助类，采用单例模式构造，创建我的收藏数据库;其中图片以字节数组方式存储blob；添加时间戳

2.MyCollectionDB 数据库操作封装，已创建两个方法：向数据库中插入一条影片信息记录和根据影片id查询记录

{网络判断修复；slidingmenu菜单区域支持侧滑关闭，setTouchModeBehind(SlidingMenu.TOUCHMODE_FULLSCREEN)，菜单条目点击事件失效；可仿QQ菜单特效实现，未实现}