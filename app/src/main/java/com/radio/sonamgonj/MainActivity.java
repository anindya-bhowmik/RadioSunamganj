package com.radio.sonamgonj;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	Button startButton, stopButton;
	static Context context;
	boolean isPlaying;
	Intent streamService;
	SharedPreferences prefs;
	ProgressDialog progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		startButton = (Button) findViewById(R.id.startButton);
		stopButton = (Button) findViewById(R.id.stopButton);
		prefs = PreferenceManager.getDefaultSharedPreferences(context);
		getPrefs();
		streamService = new Intent(MainActivity.this, StreamService.class);		
		
		startButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startService(streamService);
				//progress.setTitle("Buffering..");
				startButton.setEnabled(false);
			}
		});
		
		stopButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stopService(streamService);
				startButton.setEnabled(true);
			}
		});
	}

	public void getPrefs() {
			isPlaying = prefs.getBoolean("isPlaying", false);
			if (isPlaying) startButton.setEnabled(false);
	}

}
