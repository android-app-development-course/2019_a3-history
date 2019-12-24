package com.example.history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
    private final String dangshiPath = "../../assets/dangshigushi";
    private final String shenhuaPath = "../../assets/shenhuagushi";
    private final String wenhuaPath = "../../assets/wenhuagushi";
    File myFile = null;
    //四个name数组分别存放四种不同故事的文件名称
    String[] strDangshiName = null;
    String[] strWenhuaName = null;
    String[] strShenhuaName = null;
    String[] strMinjianName = null;
    ArrayList<ListItem> listItemsDangshi = new ArrayList<ListItem>();
    ArrayList<ListItem> listItemsWenhua = new ArrayList<ListItem>();
    ArrayList<ListItem> listItemsShenhua = new ArrayList<ListItem>();
    ArrayList<ListItem> listItemsMinjian = new ArrayList<ListItem>();

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
        ConstraintLayout constraintlayout = findViewById(R.id.storyConstraintlayout);
        LinearLayout minjianLayout = constraintlayout.findViewById(R.id.minjianlinearLayout);
        LinearLayout shenhuaLayout = constraintlayout.findViewById(R.id.shenhualinearLayout);
        LinearLayout dangshiLayout = constraintlayout.findViewById(R.id.dangshilinearLayout);
        LinearLayout wenhuaLayout = constraintlayout.findViewById(R.id.wenhualinearLayout);
        try {
            assetManager = getAssets();
            strMinjianName = assetManager.list("minjiangushi");
            strShenhuaName = assetManager.list("shenhuagushi");
            strDangshiName = assetManager.list("dangshigushi");
            strWenhuaName = assetManager.list("wenhuagushi");
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (NullPointerException e2) {
            e2.printStackTrace();
        }
        for (int i = 0;i < strMinjianName.length;i++){
            strMinjianName[i] = strMinjianName[i].replace(".txt","");
        }
        for (int i = 0;i < strDangshiName.length;i++){
            strDangshiName[i] = strDangshiName[i].replace(".txt","");
        }
        for (int i = 0;i < strWenhuaName.length;i++){
            strWenhuaName[i] = strWenhuaName[i].replace(".txt","");
        }
        for (int i = 0;i < strShenhuaName.length;i++){
            strShenhuaName[i] = strShenhuaName[i].replace(".txt","");
        }
        //然后在activity中动态设置这些textview所要显示的内容
        TextView minjianHead = (TextView) minjianLayout.findViewById(R.id.minjiantextView);
        minjianHead.setText("民间故事(点击查看更多)");
        TextView dangshiHead = (TextView) dangshiLayout.findViewById(R.id.dangshitextView);
        dangshiHead.setText("党史故事(点击查看更多)");
        TextView wenhuaHead = (TextView) wenhuaLayout.findViewById(R.id.wenhuatextView);
        wenhuaHead.setText("文化故事(点击查看更多)");
        TextView shenhuaHead = (TextView) shenhuaLayout.findViewById(R.id.shenhuatextView);
        shenhuaHead.setText("神话故事（点击查看更多)");
        //取得四个故事布局里面的textview并放进数组
        TextView txvminjian[] = new TextView[8];
        TextView txvdangshi[] = new TextView[8];
        TextView txvwenhua[] = new TextView[8];
        TextView txvshenhua[] = new TextView[8];
        //设置标题
        for (int i = 1;i < minjianLayout.getChildCount();++i ) {
            txvminjian[i] = (TextView)minjianLayout.getChildAt(i);
            txvminjian[i].setText(strMinjianName[i]);
            ((View) txvminjian[i]).setOnClickListener(this);
        }
        for (int i = 1;i < shenhuaLayout.getChildCount();++i ) {
            txvshenhua[i] = (TextView)shenhuaLayout.getChildAt(i);
            txvshenhua[i].setText(strShenhuaName[i]);
            ((View)txvshenhua[i]).setOnClickListener(this);
        }
        for (int i = 1;i < wenhuaLayout.getChildCount();++i ) {
            txvwenhua[i] = (TextView)wenhuaLayout.getChildAt(i);
            txvwenhua[i].setText(strWenhuaName[i]);
            ((View)txvwenhua[i]).setOnClickListener(this);
        }
        for (int i = 1;i < dangshiLayout.getChildCount();++i ) {
            txvdangshi[i] = (TextView)dangshiLayout.getChildAt(i);
            txvdangshi[i].setText(strDangshiName[i]);
            ((View)txvdangshi[i]).setOnClickListener(this);
        }
        findViewById(R.id.dangshitextView).setOnClickListener(this);
        findViewById(R.id.dangshitextView2).setOnClickListener(this);
        findViewById(R.id.dangshitextView4).setOnClickListener(this);
        findViewById(R.id.dangshitextView5).setOnClickListener(this);
        findViewById(R.id.dangshitextView6).setOnClickListener(this);
        findViewById(R.id.minjiantextView).setOnClickListener(this);
        findViewById(R.id.minjiantextView2).setOnClickListener(this);
        findViewById(R.id.minjiantextView4).setOnClickListener(this);
        findViewById(R.id.minjiantextView5).setOnClickListener(this);
        findViewById(R.id.minjiantextView6).setOnClickListener(this);
        findViewById(R.id.wenhuatextView).setOnClickListener(this);
        findViewById(R.id.wenhuatextView2).setOnClickListener(this);
        findViewById(R.id.wenhuatextView4).setOnClickListener(this);
        findViewById(R.id.wenhuatextView5).setOnClickListener(this);
        findViewById(R.id.wenhuatextView6).setOnClickListener(this);
        findViewById(R.id.shenhuatextView).setOnClickListener(this);
        findViewById(R.id.shenhuatextView2).setOnClickListener(this);
        findViewById(R.id.shenhuatextView4).setOnClickListener(this);
        findViewById(R.id.shenhuatextView5).setOnClickListener(this);
        findViewById(R.id.shenhuatextView6).setOnClickListener(this);


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
                break;
            }
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
            case R.id.dangshitextView:{
                ConcreteStoryInfo.setStrCurSubDirName("dangshigushi");
                Intent intent = new Intent(this,ConcreteStoryListActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.dangshitextView2:{
                OpenUtils.setContentPage("dangshigushi/",(TextView) findViewById(R.id.dangshitextView2),assetManager);
                Intent intent = new Intent(this,DisplayActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.dangshitextView4:{
                OpenUtils.setContentPage("dangshigushi/",(TextView) findViewById(R.id.dangshitextView4),assetManager);
                Intent intent = new Intent(this,DisplayActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.dangshitextView5:{
                OpenUtils.setContentPage("dangshigushi/",(TextView) findViewById(R.id.dangshitextView5),assetManager);
                Intent intent = new Intent(this,DisplayActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.dangshitextView6:{
                OpenUtils.setContentPage("dangshigushi/",(TextView) findViewById(R.id.dangshitextView6),assetManager);
                Intent intent = new Intent(this,DisplayActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.minjiantextView:{
                ConcreteStoryInfo.setStrCurSubDirName("minjiangushi");
                Intent intent = new Intent(this,ConcreteStoryListActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.minjiantextView2:{
                OpenUtils.setContentPage("minjiangushi/",(TextView) findViewById(R.id.minjiantextView2),assetManager);
                Intent intent = new Intent(this,DisplayActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.minjiantextView4:{
                OpenUtils.setContentPage("minjiangushi/",(TextView) findViewById(R.id.minjiantextView4),assetManager);
                Intent intent = new Intent(this,DisplayActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.minjiantextView5:{
                OpenUtils.setContentPage("minjiangushi/",(TextView) findViewById(R.id.minjiantextView5),assetManager);
                Intent intent = new Intent(this,DisplayActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.minjiantextView6:{
                OpenUtils.setContentPage("minjiangushi/",(TextView) findViewById(R.id.minjiantextView6),assetManager);
                Intent intent = new Intent(this,DisplayActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.shenhuatextView:{
                ConcreteStoryInfo.setStrCurSubDirName("shenhuagushi");
                Intent intent = new Intent(this,ConcreteStoryListActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.shenhuatextView2:{
                OpenUtils.setContentPage("shenhuagushi/",(TextView) findViewById(R.id.shenhuatextView2),assetManager);
                Intent intent = new Intent(this,DisplayActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.shenhuatextView4:{
                OpenUtils.setContentPage("shenhuagushi/",(TextView) findViewById(R.id.shenhuatextView4),assetManager);
                Intent intent = new Intent(this,DisplayActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.shenhuatextView5:{
                OpenUtils.setContentPage("shenhuagushi/",(TextView) findViewById(R.id.shenhuatextView5),assetManager);
                Intent intent = new Intent(this,DisplayActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.shenhuatextView6:{
                OpenUtils.setContentPage("shenhuagushi/",(TextView) findViewById(R.id.shenhuatextView6),assetManager);
                Intent intent = new Intent(this,DisplayActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.wenhuatextView:{
                ConcreteStoryInfo.setStrCurSubDirName("wenhuagushi");
                Intent intent = new Intent(this,ConcreteStoryListActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.wenhuatextView2:{
                OpenUtils.setContentPage("wenhuagushi/",(TextView) findViewById(R.id.wenhuatextView2),assetManager);
                Intent intent = new Intent(this,DisplayActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.wenhuatextView4:{
                OpenUtils.setContentPage("wenhuagushi/",(TextView) findViewById(R.id.wenhuatextView4),assetManager);
                Intent intent = new Intent(this,DisplayActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.wenhuatextView5:{
                OpenUtils.setContentPage("wenhuagushi/",(TextView) findViewById(R.id.wenhuatextView5),assetManager);
                Intent intent = new Intent(this,DisplayActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.wenhuatextView6:{
                OpenUtils.setContentPage("wenhuagushi/",(TextView) findViewById(R.id.wenhuatextView6),assetManager);
                Intent intent = new Intent(this,DisplayActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}

