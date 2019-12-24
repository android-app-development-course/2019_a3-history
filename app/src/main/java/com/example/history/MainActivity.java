package com.example.history;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

/**
 *  四个板块,有四个页面的java类文件,四个对应的xml文件,这四个xml文件是一样的,里面的控件id也是一样的
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton people;
    private ImageButton story;
    private ImageButton phi;
    private ImageButton ido;

    private void resetImg(){
        people.setImageResource(R.drawable.renwu_pressed);
        story.setImageResource(R.drawable.gushi_pressed);
        phi.setImageResource(R.drawable.zhexue_pressed);
        ido.setImageResource(R.drawable.chengyu_pressed);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        people=(ImageButton) findViewById(R.id.his_people);
        story=(ImageButton) findViewById(R.id.his_story);
        phi=(ImageButton) findViewById(R.id.his_phi);
        ido=(ImageButton) findViewById(R.id.his_idiom);
        resetImg();
        story.setOnClickListener(this);
        ido.setOnClickListener(this);
        people.setOnClickListener(this);
        phi.setOnClickListener(this);
        findViewById(R.id.top).setOnClickListener(this);


    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top:{
                Intent intent = new Intent(this, MainActivity .class);
//                this.onPause();
                startActivity(intent);
                break;
            }
            case R.id.his_story : {
                Intent intent = new Intent(this, Story_pageActivity .class);
//                this.onPause();
                startActivity(intent);
                break;//随便搞一个注释
            }//随便在搞一个
            case R.id.his_people : {
                ConcreteStoryInfo.setStrCurSubDirName("peoples");
                Intent intent = new Intent(this, People_pageActivity .class);
//                this.onPause();
                startActivity(intent);
                break;
            }
            case R.id.his_phi : {
                Intent intent = new Intent(this, Philosophy_pageActivity .class);
//                this.onPause();
                startActivity(intent);
                break;
            }
            case R.id.his_idiom : {
                Intent intent = new Intent(this, Idiom_pageActivity .class);
//                this.onPause();
                startActivity(intent);
                break;
            }
        }
    };
}
