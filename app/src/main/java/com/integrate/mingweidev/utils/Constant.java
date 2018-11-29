package com.integrate.mingweidev.utils;

/**
 * Created by Liang_Lu on 2017/11/22.
 */

public class Constant {
//    public static String BASE_URL = "http://api.zhuishushenqi.com";

    public static final String ZHUISHU_IMAGE_URL = "http://statics.zhuishushenqi.com";
    //Book Date Convert Format
    public static final String FORMAT_BOOK_DATE = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String FORMAT_TIME = "HH:mm";
    public static final String FORMAT_FILE_DATE = "yyyy-MM-dd";

    //BookCachePath (因为getCachePath引用了Context，所以必须是静态变量，不能够是静态常量)
//    public static String BOOK_CACHE_PATH = FileUtils.getCachePath() + File.separator
//            + "book_cache" + File.separator;

    /**
     * 保存本地token
     */
    public static String TOKEN = "token";

    public static String CONTENT_KEY = "mall_content_key";
    //fragment传值中的key值
    public static String DATA_KEY = "data_key";

    /*****************fragment传值*******************/

    //文件选择回带结果
    public static String TAG_FILERESULT = "fragment_files";


    /****************联网参数***********************/

    public static final String BASE_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting/";
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
