package com.example.history;

import android.content.Intent;
import android.content.res.AssetManager;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class OpenUtils {
    public static AssetManager assetManager = null;
    public static InputStream inputStream = null;
    public static byte [] bytes = null;
    //把新内容页面的内容传到ConcreteStoryInfo中,strVarify是分辨这些文件在哪个子目录下
    public static void setContentPage(String strVarify,TextView v,AssetManager asm){
        String strFileName = strVarify + (v.getText().toString())+".txt";
        try {
            assetManager = asm;
            inputStream = assetManager.open(strFileName);
            bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            System.out.println(inputStream);
            ConcreteStoryInfo.setStrContent(bytes);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                inputStream.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
