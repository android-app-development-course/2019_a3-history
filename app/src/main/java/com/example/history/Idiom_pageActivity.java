package com.example.history;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Idiom_pageActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton people;
    private ImageButton story;
    private ImageButton phi;
    private ImageButton ido;
    ArrayList<ListItem> listItems = new ArrayList<ListItem>();
    AssetManager assetManager = null;
    private final String relePath = "../../assets/stories";
    File myFile = null;
    //strFileName存放stories文件下的所有故事的文件名称
    String[] strFileName = null;
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
            System.out.println(strFileName.length);
        }catch (IOException e){
            e.printStackTrace();
        }
        for (String str: strFileName) {
            listItems.add(new ListItem("test"));
        }
        ListItemAdapter listItemAdapter = new ListItemAdapter(Idiom_pageActivity.this,
                R.layout.item, listItems);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(listItemAdapter);
    }
    @Override
    public  boolean onKeyDown(int code, KeyEvent keyEvent){
        if (code == keyEvent.KEYCODE_BACK){
            this.finish();
        }
        return true;
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
                break;//随便搞一个注释
            }//随便在搞一个
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
