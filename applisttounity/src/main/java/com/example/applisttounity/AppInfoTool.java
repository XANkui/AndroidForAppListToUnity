package com.example.applisttounity;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class AppInfoTool {

    static String TAG = "ApkTool";

    public  static List<AppInfo> mLocalInstalledApps = null;

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

    private List<AppInfo> scanLocalInstalledAppList(){
        PackageManager packageManager = getActivity().getPackageManager();
        List<AppInfo> myAppInfos  = new ArrayList<AppInfo>();
        try {
            List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
            for (int i = 0; i < packageInfos.size(); i++) {
                PackageInfo packageInfo = packageInfos.get(i);

                // 过滤掉系统应用
                if ((ApplicationInfo.FLAG_SYSTEM & packageInfo.applicationInfo.flags) != 0) {
                    continue;

                }
                AppInfo myAppInfo = new AppInfo();

                // app packageName
                myAppInfo.setPackageName(packageInfo.packageName);

                // app appName
                myAppInfo.setAppName(packageInfo.applicationInfo.loadLabel(packageManager).toString());

                // 由于applist 图片转为 byte[] 在转为 json，数据量太大，所以这个属性空着，使用另一种方式获取
                // app icon
                /*if (packageInfo.applicationInfo.loadIcon(packageManager) == null) {
                    continue;
                }
                Drawable imageIcon = packageInfo.applicationInfo.loadIcon(packageManager);
                Bitmap bitmap =FileUtils.DrawableToBitmap(imageIcon);
                byte[] bytesIcon = FileUtils.BitmapToByte(bitmap);
                myAppInfo.setImage(bytesIcon);*/

                // 添加到列表中
                myAppInfos.add(myAppInfo);
            }
        }catch (Exception e){

            Log.e(TAG,"++++++++++++++++++获取应用包信息失败！");
        }

        return myAppInfos;
    }

    private String ToJsonString(List<AppInfo> appInfos){

        if(appInfos != null){

            return JSON.toJSONString(appInfos);
        }

        return null;
    }


    // 对外接口函数
    public String GetAppInfoJsonString(){

        //Toast.makeText(getActivity()," GetAppInfoJsonString ：", Toast.LENGTH_SHORT).show();

        return ToJsonString(scanLocalInstalledAppList());
    }


    // 通过包名获取APP icon 的接口，并转为 byte[] 数据
    public byte[] getIcon(String packageName) {
        Drawable icon = getAppIcon(packageName);
        if(icon != null){
            Bitmap bitmap =FileUtils.DrawableToBitmap(icon);
            return FileUtils.BitmapToByte(bitmap);
        }

        return null;
    }

    /*
     * 获取程序 图标
     */
    public Drawable getAppIcon(String packname) {
        try {
            PackageManager pm = getActivity().getPackageManager();
            if(pm != null){

                ApplicationInfo info = pm.getApplicationInfo(packname, 0);
                if(info != null){

                    return info.loadIcon(pm);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }
}
