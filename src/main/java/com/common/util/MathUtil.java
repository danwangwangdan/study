package com.common.util;

import java.math.BigDecimal;

public class MathUtil {
    /**
     * @param v1
     * @param v2
     * @return
     */
    public static double multiply(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.multiply(b2).doubleValue();
    }

    /**
     * @param v1
     * @param v2
     * @param scale
     * @return
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * @param v1
     * @param v2
     * @param v3
     * @return
     */
    public static double multiply(double v1, double v2, double v3) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        BigDecimal b3 = new BigDecimal(Double.toString(v3));

        return b1.multiply(b2).multiply(b3).doubleValue();
    }

    /**
     * @param v1
     * @param v2
     * @param v3
     * @param v4
     * @return
     */
    public static double multiply(double v1, double v2, double v3, double v4) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        BigDecimal b3 = new BigDecimal(Double.toString(v3));
        BigDecimal b4 = new BigDecimal(Double.toString(v4));

        return b1.multiply(b2).multiply(b3).multiply(b4).doubleValue();
    }

    /**
     * @param v1
     * @param v2
     * @param v3
     * @param v4
     * @param v5
     * @return
     */
    public static double multiply(double v1, double v2, double v3, double v4,
                                  double v5) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        BigDecimal b3 = new BigDecimal(Double.toString(v3));
        BigDecimal b4 = new BigDecimal(Double.toString(v4));
        BigDecimal b5 = new BigDecimal(Double.toString(v5));

        return b1.multiply(b2).multiply(b3).multiply(b4).multiply(b5)
                .doubleValue();
    }

    /**
     * @param v1
     * @param v2
     * @return
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.add(b2).doubleValue();
    }

    /**
     * @param v1
     * @param v2
     * @return
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.subtract(b2).doubleValue();
    }

    /**
     * @param v
     * @param scale
     * @return
     */
    public static double round(double v, int scale) {
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");

        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static void main(String[] args) {
        //String s = "90483@qq.com";
        //int i = s.indexOf("@");
        //String result = s.substring(0, 1) + "*****" + s.substring(i-2, s.length());
        //StringBuilder s1 = new StringBuilder(s);
        //s1.replace(3, 3, "*");
        //System.out.println(result);

        //String s = "http://192.168.2.66:8080/dataweb/img/c7/30/c7305df8.PNG";
        //String a = "/dataweb/img";
        //int i = s.indexOf(a);
        //String reA = s.substring(i + a.length(), s.length());
        //System.out.println(reA);

    }
}
