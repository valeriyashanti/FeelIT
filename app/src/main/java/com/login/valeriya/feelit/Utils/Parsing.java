package com.login.valeriya.feelit.Utils;

public class Parsing {

    public static final String addressRegister = "http://10.8.24.29:8080/register?";
    public static final String addressGetPackages = "http://10.8.24.29:8080/getset?";

    public static String getUrl(User user)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(addressRegister);
        stringBuilder.append("uid=");
        stringBuilder.append(user.getUid());
        stringBuilder.append("&year=");
        stringBuilder.append(user.getBirth_year());
        stringBuilder.append("&sex=");
        stringBuilder.append(user.getSex());
        stringBuilder.append("&lifemode=");
        stringBuilder.append(user.getLifestyle());
        stringBuilder.append("&foodstyle=");
        stringBuilder.append(user.getFoodstyle());
        stringBuilder.append("&weight=");
        stringBuilder.append(user.getWeight());
        stringBuilder.append("&height=");
        stringBuilder.append(user.getHeight());

        return stringBuilder.toString();
    }

    public static String getUrlPackage(User user)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(addressGetPackages);
        stringBuilder.append("uid=");
        stringBuilder.append(user.getUid());
        return stringBuilder.toString();
    }


}
