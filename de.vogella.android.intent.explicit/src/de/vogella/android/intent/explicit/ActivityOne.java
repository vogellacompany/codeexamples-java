package de.vogella.android.intent.explicit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ActivityOne extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Bundle extras = getIntent().getExtras();
        if (extras== null){
        	return;
        }
        String value1 = extras.getString("Value1");
	    String value2 = extras.getString("Value2");
	    if (value1 != null && value2 !=null){
	    	EditText text1= (EditText) findViewById(R.id.EditText01);
	 	    EditText text2 = (EditText) findViewById(R.id.EditText02);
	 	    text1.setText(value1);
	 	    text2.setText(value2);
	    }
    }
    
    public void onClick(View view){
		Intent i = new Intent(this, ActivityTwo.class);
		i.putExtra("Value1", "This value one for ActivityTwo ");
		i.putExtra("Value2", "This value two ActivityTwo");
		startActivity(i);
	}
}