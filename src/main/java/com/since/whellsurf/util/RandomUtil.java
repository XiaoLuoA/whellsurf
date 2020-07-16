package com.since.whellsurf.util;

import java.util.Arrays;
import java.util.Random;


/**
 * @author luoxinyuan
 * 随机相关工具包
 */
public class RandomUtil {

    public static final char[] RANDOM_STR = {
        '0','1','2','3','4','5','6','7','8','9',
            'a','b','c','d','e','f','g','h','i','j','k','l',
            'm','n','o','p','q','r','s','t','u','v','w','x','y','z'
    };

    public static void main(String[] args) {
        genRandomCode(4);
        genRandomCode(10);
        genRandomCode(8);
    }

    /**
     * @author luoxinyuan
     * @param n 生成随机码的位数
     * @return n位随机码
     */
    public static String genRandomCode(int n){
        Random random = new Random();
        int[] arr = new int[n];
        String randomCode = Arrays.stream(arr).map(i->random.nextInt(36))
                .mapToObj(i->""+ RANDOM_STR[i]).reduce("",(a, b)->a+b);
        return randomCode;
    }
}
