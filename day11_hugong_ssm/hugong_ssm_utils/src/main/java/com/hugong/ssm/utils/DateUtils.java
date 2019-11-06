package com.hugong.ssm.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换的工具类
 */
public class DateUtils {

    //日期转字符串(patt(pattern)为所指定的转换格式：一般为：yyyy-MM-dd HH:mm:ss)
    public static String dateToString(Date date,String patt) {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        String format = sdf.format(date);
        return format;
    }

    //字符串转为日期
    public static Date stringToDate(String str,String patt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(patt);
        Date date = sdf.parse(str);
        return date;
    }
}
