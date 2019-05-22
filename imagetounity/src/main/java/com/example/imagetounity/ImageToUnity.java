package com.example.imagetounity;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class ImageToUnity {

    // 设置一个 Activity 参数

    private Activity _unityActivity;

    // 通过反射获取 Unity 的 Activity 的上下文

    Activity getActivity(){

        if(null == _unityActivity){

            try{

                Class<?> classtype = Class.forName("com.unity3d.player.UnityPlayer");

                Activity activity = (Activity) classtype.getDeclaredField("currentActivity").get(classtype);

                _unityActivity = activity;

            }catch (ClassNotFoundException e){

                e.printStackTrace();

            }catch (IllegalAccessException e){

                e.printStackTrace();

            }catch (NoSuchFieldException e){

                e.printStackTrace();

            }

        }

        return _unityActivity;

    }


    public byte[] getIcon(String packageName) {
        Drawable icon = getAppIcon(packageName);
        Bitmap bitmap =FileUtils.DrawableToBitmap(icon);
        return FileUtils.BitmapToByte(bitmap);
    }

    /*
     * 获取程序 图标
     */
    public Drawable getAppIcon(String packname) {
        try {
            PackageManager pm = getActivity().getPackageManager();
            ApplicationInfo info = pm.getApplicationInfo(packname, 0);
            return info.loadIcon(pm);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }


    /*public byte[] getIcon(String packageName) {

        Toast.makeText(getActivity()," getIcon packname："+packageName, Toast.LENGTH_SHORT).show();

        Drawable imageIcon = getAppIcon(packageName);
        int w = imageIcon.getIntrinsicWidth();
        int h = imageIcon.getIntrinsicHeight();

        // 取 drawable 的颜色格式
        Bitmap.Config config = imageIcon.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;

        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytesIcon = baos.toByteArray();

        return  bytesIcon;
    }

    *//*
     * 获取程序 图标
     *//*
    public Drawable getAppIcon(String packname) {

        try {
            PackageManager pm = getActivity().getPackageManager();
            ApplicationInfo info = pm.getApplicationInfo(packname, 0);
            Toast.makeText(getActivity()," getAppIcon packname："+packname, Toast.LENGTH_SHORT).show();

            return info.loadIcon(pm);
        } catch (Exception e) {

            Toast.makeText(getActivity(),"packname Exception："+packname, Toast.LENGTH_SHORT).show();
            e.printStackTrace();

        }
        return null;
    }*/

    public int Add(int a, int b){

        int r = a+b;

        String result = "Add:   "+r ;

        Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();

        return r;

    }

}
