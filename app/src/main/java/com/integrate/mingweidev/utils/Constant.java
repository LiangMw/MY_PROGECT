package com.integrate.mingweidev.utils;

/**
 * Created by Liang_Lu on 2017/11/22.
 */

public class Constant {
    public static String BASE_URL = "http://118.24.213.53:8080/jfinal_demo/";

    public static final String ZHUISHU_IMAGE_URL = "http://statics.zhuishushenqi.com";
    //Book Date Convert Format
    public static final String FORMAT_BOOK_DATE = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String FORMAT_TIME = "HH:mm";
    public static final String FORMAT_FILE_DATE = "yyyy-MM-dd";
    public static final String TAG_USERINFO = "tag_userinfo";
    public static final String TAG_GUID = "tag_guid";
    public static final String TAG_TOKEN = "tag_token";

    public static  String TOKEN = "";
    public static  String GUID = "";

    //联网返回Code

    //成功
    public static final String SUCCESSCODE = "0";
    //出错
    public static final String SUCCESSERROR = "-1";

    //BookCachePath (因为getCachePath引用了Context，所以必须是静态变量，不能够是静态常量)
//    public static String BOOK_CACHE_PATH = FileUtils.getCachePath() + File.separator
//            + "book_cache" + File.separator;

    /**
     * 保存本地token
     */

    public static String CONTENT_KEY = "mall_content_key";
    //fragment传值中的key值
    public static String DATA_KEY = "data_key";

    /*****************fragment传值*******************/

    //文件选择回带结果
    public static String TAG_FILERESULT = "fragment_files";
    //音乐列表类型
    public static String MUSIC_LIST_TYPE = "music_list_type";

    /*****************RecyclerView多类型*******************/
    //音乐列表文字类型
    public static final int TYPE_PROFILE = 0;
    //音乐列表分类类型
    public static final int TYPE_MUSIC_LIST = 1;


    /****************联网参数***********************/

//    public static final String BASE_URL = "http://tingapi.ting.baidu.com/v1/";
    public static final String SPLASH_URL = "http://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1";
    public static final String METHOD_GET_MUSIC_LIST = "baidu.ting.billboard.billList";
    public static final String METHOD_DOWNLOAD_MUSIC = "baidu.ting.song.play";
    public static final String METHOD_ARTIST_INFO = "baidu.ting.artist.getInfo";
    public static final String METHOD_SEARCH_MUSIC = "baidu.ting.search.catalogSug";
    public static final String METHOD_LRC = "baidu.ting.song.lry";
    public static final String PARAM_METHOD = "method";
    public static final String PARAM_TYPE = "type";
    public static final String PARAM_SIZE = "size";
    public static final String PARAM_OFFSET = "offset";
    public static final String PARAM_SONG_ID = "songid";
    public static final String PARAM_TING_UID = "tinguid";
    public static final String PARAM_QUERY = "query";

}
