package com.example.history;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class ConcreteStoryListActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<ListItem> listItems = new ArrayList<ListItem>();
    InputStream inputStream = null;
    AssetManager assetManager = null;
    Activity activity = null;
    File myFile = null;
    //strFileName存放stories文件下的所有故事的文件名称
    String[] strFileName = null;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.concretestorylist);
        activity = this.activity;
        try {
            //获取相应类型故事的文件名列表
            assetManager = getAssets();
            strFileName = assetManager.list(ConcreteStoryInfo.getstrCurSubDirName());
        }catch (IOException e){
            e.printStackTrace();
        }
        for (int i = 0;i < strFileName.length;i++){
            strFileName[i] = strFileName[i].replace(".txt","");
        }
        for (String strName:strFileName
             ) {
            listItems.add(new ListItem(strName));
        }
        ListItemAdapter listItemAdapter = new ListItemAdapter(ConcreteStoryListActivity.this,
                R.layout.item, listItems);
        ListView listView = (ListView) findViewById(R.id.concretestorylv);
        listView.setAdapter(listItemAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.name);
                //获取被点击的列表项的标题文字，然后再在相应的路径中根据文件名字打开文件
                String strFileName = ConcreteStoryInfo.getstrCurSubDirName() + "/" + textView.getText().toString() + ".txt";
                System.out.println(strFileName);
                try {
                    inputStream = assetManager.open(strFileName);
                    byte[] bytes = new byte[inputStream.available()];
                    inputStream.read(bytes);
                    ConcreteStoryInfo.setStrContent(bytes);
                    System.out.println(ConcreteStoryInfo.getStrContent());
                }catch(IOException e){
                    e.printStackTrace();
                }finally {
                    try {
                        inputStream.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                if (MyApplication.getContext() == null) {
                    Log.d(null,"返回了一个空的context");
                } else {
                    Intent intent = new Intent(MyApplication.getContext(),DisplayActivity.class);
                    startActivity(intent);
                }
                //然后打开显示页面

            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
