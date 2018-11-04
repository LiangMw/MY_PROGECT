package com.integrate.mingweidev.utils;

/**
 * Created by Liang_Lu on 2017/11/22.
 */

public class Constant {
    public static String BASE_URL = "http://api.zhuishushenqi.com";

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
}
