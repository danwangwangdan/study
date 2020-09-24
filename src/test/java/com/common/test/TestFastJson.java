package com.common.test;


import com.common.util.MathUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class TestFastJson {

    //public static void main(String[] args) {
    //    //BaseResponse baseResponse = new BaseResponse();
    //    //baseResponse.setIsSuccess(BaseResponse.SUCCESS);
    //    //ImageResponse imageResponse = new ImageResponse();
    //    //imageResponse.setId("12");
    //    //imageResponse.setFlag(false);
    //    //imageResponse.setName("dsad");
    //    //baseResponse.setData(imageResponse);
    //    //System.out.println(baseResponse.toString());
    //}

    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static void main(String[] args) {

        //double minFloatPrice = 0;
        //double maxFloatPrice = 0;
        //int minPriceScale = 0;
        //minFloatPrice = -minFloatPrice;
        //for (int i = 0; i < 40; i++) {
        //    double marketNewPrice = 100.0;
        //    double floatPrice = minFloatPrice + (int) (Math.random() * ((maxFloatPrice - (minFloatPrice)) + 1));
        //    floatPrice = floatPrice * Math.pow(10, -minPriceScale);
        //    System.out.println("floatPrice :" + floatPrice);
        //    marketNewPrice = marketNewPrice + floatPrice;
        //    System.out.println("marketNewPrice :" + marketNewPrice);
        //}

        //double minFloatPrice = 3;
        //double maxFloatPrice = 5;
        //int sign = 1;
        //int minPriceScale = 2;
        //for (int i = 0; i < 10; i++) {
        //    double marketPrice = 100.52;
        //    int digits = getNumberDecimalDigits(marketPrice);
        //    System.out.println("digits:" + digits);
        //    double floatPrice = minFloatPrice + (int) (Math.random() * ((maxFloatPrice - (minFloatPrice)) + 1));
        //    sign = Math.random() > 0.5 ? 1 : -1;
        //    System.out.println("sign :" + sign);
        //    floatPrice = floatPrice * sign;
        //    System.out.println("floatPrice :" + floatPrice);
        //    marketPrice = MathUtil.round(marketPrice + floatPrice, digits);
        //    System.out.println("marketPrice :" + marketPrice);
        //}
        double minFloatPrice = 3;
        double maxFloatPrice = 5;
        int sign = 1;
        for (int i = 0; i < 20; i++) {
            double marketPrice = 100.45;
            sign = Math.random() > 0.5 ? 1 : -1;
            int digits = getNumberDecimalDigits(marketPrice);
            double floatPrice = sign * MathUtil.round(minFloatPrice + Math.random() * ((maxFloatPrice - (minFloatPrice))), digits);
            System.out.println("floatPrice :" + floatPrice);
            marketPrice = MathUtil.round(marketPrice + floatPrice, digits);
            System.out.println("marketPrice :" + marketPrice);

        }
    }

    public static int getNumberDecimalDigits(Double balance) {
        return (balance + "").length() - (balance + "").indexOf(".") - 1;
    }

    private static double floatPrice(double marketNewPrice) {

        double minFloatPrice = 3;
        double maxFloatPrice = 5;

        int digits = getNumberDecimalDigits(marketNewPrice);
        int sign = 1;
        minFloatPrice = -minFloatPrice;
        sign = Math.random() > 0.5 ? 1 : -1;
        double floatPrice = minFloatPrice + (int) (Math.random() * ((maxFloatPrice - (minFloatPrice)) + 1));
        floatPrice = floatPrice * sign;
        marketNewPrice = MathUtil.round(marketNewPrice + floatPrice, digits);
        System.out.println("marketNewPrice :" + marketNewPrice);
        return marketNewPrice;
    }
}

