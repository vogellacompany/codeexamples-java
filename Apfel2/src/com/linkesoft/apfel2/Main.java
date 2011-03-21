package com.linkesoft.apfel2;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	// Zoom-Out mit Back-Taste
    	ApfelView apfelView=((ApfelView)findViewById(R.id.ApfelView));
        if (keyCode == KeyEvent.KEYCODE_BACK && apfelView.xwidth<4)
        {
        	apfelView.zoom(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}