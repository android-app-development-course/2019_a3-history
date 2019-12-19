package com.example.history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.ConstantCallSite;
import java.util.ArrayList;

public class Story_pageActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton people;
    private ImageButton story;
    private ImageButton phi;
    private ImageButton ido;
    ArrayList<ListItem> listItems = new ArrayList<ListItem>();
    AssetManager assetManager = null;
    //四个路径分别用来取四种故事
    private final String minjianPath = "../../assets/minjiangushi";
    private final String huangdiPath = "../../assets/huangdigushi";
    private final String shenhuaPath = "../../assets/shenhuagushi";
    private final String wenhuaPath = "../../assets/wenhuagushi";
    File myFile = null;
    //四个name数组分别存放四种不同故事的文件名称
    String[] strHuangdiName = null;
    String[] strWenhuaName = null;
    String[] strShenhuaName = null;
    String[] strMinjianName = null;

    private void resetImg() {
        people.setImageResource(R.drawable.renwu_pressed);
        story.setImageResource(R.drawable.gushi_pressed);
        phi.setImageResource(R.drawable.zhexue_pressed);
        ido.setImageResource(R.drawable.chengyu_pressed);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_page);
        people = (ImageButton) findViewById(R.id.his_people);
        story = (ImageButton) findViewById(R.id.his_story);
        phi = (ImageButton) findViewById(R.id.his_phi);
        ido = (ImageButton) findViewById(R.id.his_idiom);
        resetImg();
        story.setImageResource(R.drawable.gushi);
        story.setOnClickListener(this);
        ido.setOnClickListener(this);
        people.setOnClickListener(this);
        phi.setOnClickListener(this);
        findViewById(R.id.top).setOnClickListener(this);
        //下面的四个linearlayout变量用来辅助获取四种不同故事中的textview,另外一个constraintlayout是包含这四个线性布局的外层布局
        @SuppressWarnings("ResourceType")
        ConstraintLayout constraintlayout = (ConstraintLayout) findViewById(R.id.storyConstraintlayout);
        if (constraintlayout == null){
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        }
        System.out.println(constraintlayout.getChildCount());
        LinearLayout minjianLayout = (LinearLayout) constraintlayout.findViewById(R.id.minjianlinearLayout);
        LinearLayout shenhuaLayout = (LinearLayout) constraintlayout.findViewById(R.id.shenhualinearLayout);
        LinearLayout huangdiLayout = (LinearLayout) constraintlayout.findViewById(R.id.dangshilinearLayout);
        LinearLayout wenhuaLayout = (LinearLayout) constraintlayout.findViewById(R.id.wenhualinearLayout);
        try {
            assetManager = getAssets();
            strMinjianName = assetManager.list("minjiangushgi");
            strShenhuaName = assetManager.list("shenhuagushi");
            strHuangdiName = assetManager.list("dangshigushi");
            strWenhuaName = assetManager.list("wenhuagushi");
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (NullPointerException e2) {
            e2.printStackTrace();
        }
        //然后在activity中动态设置这些textview所要显示的内容
        TextView minjianHead = (TextView) minjianLayout.findViewById(R.id.minjiantextView);
        minjianHead.setText("民间故事(点击查看更多)");
        TextView huangdiHead = (TextView) huangdiLayout.findViewById(R.id.dangshitextView);
        huangdiHead.setText("党史故事(点击查看更多)");
        TextView wenhuaHead = (TextView) wenhuaLayout.findViewById(R.id.wenhuatextView);
        wenhuaHead.setText("文化故事(点击查看更多)");
        TextView shenhuaHead = (TextView) shenhuaLayout.findViewById(R.id.shenhuatextView);
        shenhuaHead.setText("神话故事(点击查看更多)");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top: {
                Intent intent = new Intent(this, MainActivity.class);
//                this.onPause();
                startActivity(intent);
                break;
            }
            case R.id.his_story: {
                break;//随便搞一个注释
            }//随便在搞一个
            case R.id.his_people: {
                Intent intent = new Intent(this, People_pageActivity.class);
//                this.onPause();
                startActivity(intent);
                break;
            }
            case R.id.his_phi: {
                Intent intent = new Intent(this, Philosophy_pageActivity.class);
//                this.onPause();
                startActivity(intent);
                break;
            }
            case R.id.his_idiom: {
                Intent intent = new Intent(this, Idiom_pageActivity.class);
//                this.onPause();
                startActivity(intent);
                break;
            }
        }
    }
}

