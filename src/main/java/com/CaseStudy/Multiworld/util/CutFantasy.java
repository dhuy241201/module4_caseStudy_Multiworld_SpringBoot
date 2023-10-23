package com.CaseStudy.Multiworld.util;

public class CutFantasy {
    public static String cutFantasy(String fantasy){
        if (fantasy.length() < 130){
            return fantasy;
        }
        String cuttedFantasy = fantasy.substring(0,131) + "...";
        return cuttedFantasy;
    }
}
