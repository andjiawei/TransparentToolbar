package com.netsite.titlefading;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_listView).setOnClickListener(this);
        findViewById(R.id.btn_scrollView).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_listView :
                startActivity(new Intent(this,ListViewActivity.class));
                break;

            case R.id.btn_scrollView :
                startActivity(new Intent(this, ScrollViewActivity.class));
                break;
        }
    }
}
