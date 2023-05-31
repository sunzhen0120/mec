package com.upf.utils;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

public interface Judge {
    /**
     * 一个专门用来判断格式的类
     * @param userName
     * @return
     */
    public static boolean JudegeUserName(String userName)
    {
        //长度为1到32
        if (1<=userName.length() &&userName.length() <=32) {
            for (int i = 0; i < userName.length(); i++) {
                char c = userName.charAt(i);
                if (!isLetter(c) &&! isDigit(c)) {//只能由数字和英文字母组成
                    return false;
                }
            }
            return true;//符合条件的
        }
        return false;//else其他都为false
    }


    //加密后的密文格式判断
    public static boolean JudegePassword(String userPassword)
    {
        //密码长度待定
        if (userPassword.length()==64) {
            for (int i = 0; i < userPassword.length(); i++) {
                char c = userPassword.charAt(i);
                if (!isLetter(c) &&! isDigit(c)) {//只能由数字和英文字母组成
                    return false;
                }
            }
            return true;//符合条件的
        }
        return false;//else其他都为false
    }
    public static boolean Uint8(int  x)
    {
        /**
         * 判断x是不是Uint8类型
         */
       return 0 <= x && x <= 255;
    }
    public static boolean Uint16(int  x)
    {
        /***
         * 判断x是不是Uint16类型
         */
        return 0 <= x && x <= 65535;
    }

    /**
     * 判断timer_value的范围
     * @param timer_value timer_value
     * @return boolen
     */
    public static boolean JudgeTimerValue(int timer_value){
        return 0<=timer_value && timer_value<=31;
    }

    /**
     * 判断timer_unit范围 0-7
     * @param timer_unit timer_unit
     * @return boolen
     */
    public static boolean JudgeTimerUnit(int timer_unit){
        return 0<=timer_unit &&timer_unit<=7;
    }

    public static boolean Two(int x)
    {
        return x==1||x==0;
    }

}
