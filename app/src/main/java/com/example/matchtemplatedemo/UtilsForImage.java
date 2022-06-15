package com.example.matchtemplatedemo;

import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class UtilsForImage {
    private static char[][] chars;

    /**
     * 通过模板匹配截图返回模板在截图上的坐标
     * @param screen 截屏下来的图片
     * @param template 要匹配的模板
     * @return 返回坐标数组
     */
    public  static int[] machTemplate(Bitmap screen,Bitmap template){
        //将template转化为二维数组
        bitmapTo2ds();

    }

    private static void bitmapTo2ds(Bitmap bitmap) {
        //hashMap添加键值对，key对应颜色值，value对应颜色值数量
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //创建二维数组存放颜色值
        char[][] chars = new char[width][height];
        for (int w =0;w<width;w=w+5){
            for (int h = 0;h<height;h=h+5){
                int colorInt = bitmap.getPixel(w,h);
                int count = 1;
                //先判断hasmap里是否包含该颜色值
                if (hashMap.containsKey(colorInt)) {
                    count = hashMap.get(colorInt);
                    count+=1;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        hashMap.replace(colorInt,count);
                    }
                }
                hashMap.put(colorInt,1);
            }
        }
        //对颜色数组排序找出前三位颜色值组成数组
        List<Integer> colorCount =  new ArrayList<>(hashMap.values());
        Collections.sort(colorCount);
        int[] moreColors = new int[3];
        for (int i = 0;i>colorCount.size()-i-1;i++){
            moreColors[i] = colorCount.get(colorCount.size()-i-1);
        }
        Log.e("test","数量最多的前三位颜色数组是："+moreColors);
    }
}
