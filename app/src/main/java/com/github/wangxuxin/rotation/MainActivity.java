package com.github.wangxuxin.rotation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent();
                //intent.putExtra("type",type+"/"+l);
                intent.setClass(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
        Switch mainSwitch = (Switch) findViewById(R.id.main_switch);
        mainSwitch.setChecked(RotationIntentService.isServiceRunning(getApplicationContext()));
        mainSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    RotationIntentService.startRotation(getApplicationContext());
                }else {
                    RotationIntentService.stopService(getApplicationContext());
                }
            }
        });
    }

}
