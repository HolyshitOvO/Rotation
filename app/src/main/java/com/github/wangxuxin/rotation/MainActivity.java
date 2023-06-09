package com.github.wangxuxin.rotation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MaterialToolbar toolbar = (MaterialToolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
				//                        .setAction("Action", null).show();
				Intent intent = new Intent();
				//intent.putExtra("type",type+"/"+l);
				intent.setClass(MainActivity.this,AboutActivity.class);
				startActivity(intent);
			}
		});
		SwitchCompat mainSwitch = (SwitchCompat) findViewById(R.id.main_switch);
		mainSwitch.setChecked(RotationIntentService.isServiceRunning(getApplicationContext()));
		mainSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
				if (isChecked) {
					RotationIntentService.startRotation(getApplicationContext());
				} else {
					RotationIntentService.stopService(getApplicationContext());
				}
			}
		});

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			if (!Settings.System.canWrite(MainActivity.this)) {
				Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS,
						Uri.parse("package:" + getPackageName()));
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		}


	}

}
