package com.vishop.core.util;

/**
 * Created with vishop.
 * User : Homiss
 * Date : 2016/1/13
 * Time : 21:41
 */
public class SaltUtil {

    public static String makeSalt() {
        return String.format("%08d", System.currentTimeMillis() % 100000000);
    }

}