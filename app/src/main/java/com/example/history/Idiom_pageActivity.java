package com.example.history;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.example.history.OpenUtils.inputStream;

public class Idiom_pageActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<ListItem> listItems = new ArrayList<ListItem>();
    AssetManager assetManager = null;
    private final String relePath = "../../assets/idioms";
    File myFile = null;
    String strNew = null;
    //strFileName存放stories文件下的所有故事的文件名称
    String[] strFileName = null;
    private ImageButton people;
    private ImageButton story;
    private ImageButton phi;
    private ImageButton ido;;

    private void resetImg(){
        people.setImageResource(R.drawable.renwu_pressed);
        story.setImageResource(R.drawable.gushi_pressed);
        phi.setImageResource(R.drawable.zhexue_pressed);
        ido.setImageResource(R.drawable.chengyu_pressed);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idiom_page);
        people=(ImageButton) findViewById(R.id.his_people);
        story=(ImageButton) findViewById(R.id.his_story);
        phi=(ImageButton) findViewById(R.id.his_phi);
        ido=(ImageButton) findViewById(R.id.his_idiom);
        resetImg();
        ido.setImageResource(R.drawable.chengyu);
        story.setOnClickListener(this);
        ido.setOnClickListener(this);
        people.setOnClickListener(this);
        phi.setOnClickListener(this);
        findViewById(R.id.top).setOnClickListener(this);
        try {
            assetManager = getAssets();
            strFileName = assetManager.list("idioms");
        }catch (IOException e){
            e.printStackTrace();
        }
        for (int i = 0;i < strFileName.length;i++){
            strFileName[i] = strFileName[i].replace(".txt","");
        }
        for (String filename: strFileName
        ) {
            listItems.add(new ListItem(filename));
        }
        ListItemAdapter listItemAdapter = new ListItemAdapter(Idiom_pageActivity.this,
                R.layout.item, listItems);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(listItemAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.name);
                //获取被点击的列表项的标题文字，然后再在相应的路径中根据文件名字打开文件
                ConcreteStoryInfo.setStrCurSubDirName("idioms");
                String strFileName = ConcreteStoryInfo.getstrCurSubDirName() + "/" + textView.getText() + ".txt";
                System.out.println(strFileName+"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                try {
                    inputStream = assetManager.open(strFileName);
                    byte[] bytes = new byte[inputStream.available()];
                    System.out.println(inputStream.available());
                    inputStream.read(bytes);
                    System.out.println(bytes);
                    ConcreteStoryInfo.setStrContent(bytes);
                }catch(IOException e){
                    e.printStackTrace();
                }finally {
                    try {
                        inputStream.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                //然后打开显示页面
                Intent intent = new Intent(MyApplication.getContext(),DisplayActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.top:{
                Intent intent = new Intent(this, MainActivity .class);
//                this.onPause();
                startActivity(intent);
                break;
            }
            case R.id.his_story : {
                Intent intent = new Intent(this, Story_pageActivity.class);
//                this.onPause();
                startActivity(intent);
                break;
            }
            case R.id.his_people : {
                Intent intent = new Intent(this, People_pageActivity.class);
//                this.onPause();
                startActivity(intent);
                break;
            }
            case R.id.his_phi : {
                Intent intent = new Intent(this, Philosophy_pageActivity.class);
//                this.onPause();
                startActivity(intent);
                break;
            }
            case R.id.his_idiom : {
                break;
            }
        }
    }
}
