package com.example.history;

import android.content.res.AssetManager;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

//这是一个静态类，用来向显示内容的页面传输数据，以及向ConcreteStoryListActivity类传输要显示哪一类故事
public class ConcreteStoryInfo {
    //strCurSubDirName是子目录的名称
    private static String strCurSubDirName;
    private static String strContent;

    public static void setStrCurSubDirName(String name) {
        strCurSubDirName = name;
    }

    public static void setStrContent(byte[] content) {
        strContent = new String(content);
    }

    public static String getstrCurSubDirName() {
        return strCurSubDirName;
    }

    public static String getStrContent() {
        return strContent;
    }
}
