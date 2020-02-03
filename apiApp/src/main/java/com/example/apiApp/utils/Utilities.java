package com.example.apiApp.utils;

/**
 * ユーティリティクラスです。
 */
public class Utilities {

    /**
     * 文字列ががnullか空文字列であるとき、Trueを返します。
     * @param str 文字列です。
     * @return 結果のboolean値です。
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }
}