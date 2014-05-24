

package com.example.activityexample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
//import android.view.View.OnClickListener;
//ArrayList<ListEntry> members = new ArrayList<ListEntry>();

public class credits extends Activity {
	String title;
	TextView msg,t1;
	ListView lv;
	Button button1;
	//ArrayList<ListEntry> members = new ArrayList<ListEntry>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credits);

}
    
}