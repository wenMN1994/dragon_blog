package com.dragon.admin.common.utils;

/**
 * Redis所有Keys
 *
 * @author Dragon Wen
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }
}
