package com.example.history;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.displaycontent);
        //获取内容组件，设置文字以及可滑动效果
        TextView txvContent = findViewById(R.id.txContent);
        txvContent.setText(ConcreteStoryInfo.getStrContent());
        txvContent.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}
