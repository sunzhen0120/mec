package com.upf.Test;



import com.alibaba.fastjson.JSON;

import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {



       // ^日本tv\d*$


        Pattern field_namePattern = Pattern.compile("^台湾\\d*tv$");
      //  Pattern field_namePattern2 = Pattern.compile("123");

        String str="台湾112tv";
        if(field_namePattern.matcher(str).matches()){
            System.out.println("qqwe");
        }

    }
}
