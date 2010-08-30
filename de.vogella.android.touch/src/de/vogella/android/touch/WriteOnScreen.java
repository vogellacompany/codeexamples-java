package de.vogella.android.touch;

import android.app.Activity;
import android.os.Bundle;

public class WriteOnScreen extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SignatureView3Fast(this, null));
    }
}