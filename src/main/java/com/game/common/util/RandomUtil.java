package com.game.common.util;

import java.util.Random;


/**
 * 生成随机验证码,去除易混淆字符
 *
 * @author yinxunlian
 * @since 2018-04-03
 */
public class RandomUtil {

    public static String generateRandomCode(int verifySize) {
        String sources = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
        int codesLen = sources.length();
        Random rand = new Random();
        StringBuilder verifyCode = new StringBuilder(verifySize);
        for (int i = 0; i < verifySize; i++) {
            verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
        }
        return verifyCode.toString();
    }
}
