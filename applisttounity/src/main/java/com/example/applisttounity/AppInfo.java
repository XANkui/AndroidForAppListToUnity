package com.example.applisttounity;

public class AppInfo {

    private byte[] image;       // 由于applist 图片转为 byte[] 在转为 json，数据量太大，所以这个属性空着，使用另一种方式获取
    private String appName;
    private String packageName;

    public AppInfo(byte[] image, String appName,String packageName){
        this.image = image;
        this.appName = appName;
        this.packageName = packageName;

    }

    public  AppInfo(){}

    public byte[] getImage(){

        return image;
    }

    public  void setImage(byte[] image){
        this.image = image;

    }

    public String getAppName(){

        return appName;
    }

    public void setAppName(String appName){

        this.appName = appName;
    }

    public String getPackageName(){

        return packageName;
    }

    public void setPackageName(String packageName){

        this.packageName = packageName;
    }
}
