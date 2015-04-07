package cn.byr.bbs.sdkdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import cn.byr.bbs.sdkdemo.openAPI.OpenAPIActivity;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // bbs authorization feature
        this.findViewById(R.id.feature_oauth).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AuthActivity.class));
            }
        });

        // API feature
        this.findViewById(R.id.feature_openAPI).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OpenAPIActivity.class));
            }
        });
    }
}
