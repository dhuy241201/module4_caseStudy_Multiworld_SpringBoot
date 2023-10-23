package com.CaseStudy.Multiworld.util;

public class HideUserInformation {
    public static String hidePassword(String password){
        String passwordString = "";
        for (int i=0; i<password.length(); i++){
            passwordString += "&#x2022";
        }
        return passwordString;
    }

    public static String hideEmail(String email){
        String[] string = email.split("@");
        String emailString;
        String headEmailString = string[0];
        String botEmailString = '@' + string[1];
        String[] subHeadEmailString = headEmailString.split("");
        if (headEmailString.length() <6){
            emailString = subHeadEmailString[0];
            for (int i=1; i<headEmailString.length(); i++){
                emailString += "&#x2022";
            }
        }else {
            emailString = subHeadEmailString[1] + subHeadEmailString[2];
            for (int i=2; i<headEmailString.length(); i++){
                emailString += "&#x2022";
            }
        }
        emailString += botEmailString;
        return emailString;
    }
}
