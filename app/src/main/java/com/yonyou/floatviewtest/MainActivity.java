package com.yonyou.floatviewtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatView floatView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFloatView();
        initView();

    }

    private void initFloatView() {
        floatView = new FloatView(this);

        View view = View.inflate(this, R.layout.floatview, null);
        ImageView img = (ImageView) view.findViewById(R.id.img);
        TextView textView = (TextView) view.findViewById(R.id.textview);

        textView.setText("烟花易冷");
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });
        floatView.addFloatView(view);
    }

    private void initView() {
        findViewById(R.id.bt1).setOnClickListener(this);
        findViewById(R.id.bt2).setOnClickListener(this);
        findViewById(R.id.bt3).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.bt1:
                floatView.showView();
                break;
            case R.id.bt2:
                floatView.hideView();
                break;
            case R.id.bt3:
                Intent intent = new Intent(this, Main2Activity.class);
                startActivity(intent);
                break;
        }
    }
}
