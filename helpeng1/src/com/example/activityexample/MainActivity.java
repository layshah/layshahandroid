package com.example.activityexample;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.BluetoothChat.BluetoothChat;
import com.example.mc.*;
public class MainActivity extends Activity implements OnClickListener {
	Button btnview,btnadd,btncredits,btnregister,btnmychat,btnams;
	EditText text1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
    }
    public void addListenerOnButton() {
   	 
		
    	 
    	btnview = (Button) findViewById(R.id.btnstart);
    	btncredits = (Button) findViewById(R.id.btncrd);
    	btnregister = (Button) findViewById(R.id.btnreg);
    	btnmychat = (Button) findViewById(R.id.btnmychat);
    	btnams = (Button) findViewById(R.id.btnams);
    	btnview.setOnClickListener(this);
    	btncredits.setOnClickListener(this);
    	btnregister.setOnClickListener(this);
    	btnmychat.setOnClickListener(this);
    	btnams.setOnClickListener(this);
    	//text1 = (EditText) findViewById(R.id.editText1);
	
    			
//    			btnview.setOnClickListener(new OnClickListener() {
  //  	 
    //				public void onClick(View arg0) {
    	                                         
    	//			    Intent intent = new Intent(MainActivity.this,Screen2.class);//Explicit Intent used here.
    			    //intent.putExtra("start",text1.getText().toString());//Sending values to new Activity
     		//		    startActivity(intent);  // Opening new Activity
    	 
    			//	}
    	 
    	//		});
    	 
    		
    /*
	btncredits.setOnClickListener(new OnClickListener() {
   	 
	public void onClick(View arg0) {
                                     
		   Intent intent = new Intent(MainActivity.this,credits.class);//Explicit Intent used here.
	    //intent.putExtra("text2",text1.getText().toString());//Sending values to new Activity
			  startActivity(intent);  // Opening new Activity

		}

	});



    btnregister.setOnClickListener(new OnClickListener() {
      	 
    	public void onClick(View arg0) {
                                         
    		   Intent intent = new Intent(MainActivity.this,register.class);//Explicit Intent used here.
    	  //  intent.putExtra("text2",text1.getText().toString());//Sending values to new Activity
    			  startActivity(intent);  // Opening new Activity

    		}

    	});
*/
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.btnstart)
		{
			Intent intent = new Intent(MainActivity.this,login.class);//Explicit Intent used here.
		    //intent.putExtra("start",text1.getText().toString());//Sending values to new Activity
				    startActivity(intent);  // Opening new Activity
		}
		if(v.getId() == R.id.btnreg)
		{
			Intent intent = new Intent(MainActivity.this,register.class);//Explicit Intent used here.
	    	  //  intent.putExtra("text2",text1.getText().toString());//Sending values to new Activity
	    			  startActivity(intent);  // Opening new Activity

		}
		if(v.getId() == R.id.btncrd)
		{
			Intent intent = new Intent(MainActivity.this,credits.class);//Explicit Intent used here.
	    	  //  intent.putExtra("text2",text1.getText().toString());//Sending values to new Activity
	    			  startActivity(intent);  // Opening new Activity

		}

		if(v.getId() == R.id.btnmychat)
		{
			Intent intent = new Intent(MainActivity.this,BluetoothChat.class);//Explicit Intent used here.
	    	  //  intent.putExtra("text2",text1.getText().toString());//Sending values to new Activity
	    			  startActivity(intent);  // Opening new Activity

		}
		if(v.getId() == R.id.btnams)
		{
			PackageManager manger = getPackageManager();
			Intent intent = new Intent(MainActivity.this,com.example.mc.MainActivity.class);//Explicit Intent used here.
	    intent=manger.getLaunchIntentForPackage("com.example.mc");
	    intent.addCategory("android.intent.category.Launcher");
	    intent.setComponent(new ComponentName("com.example.mc","name"));
	    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
	   // intent = new Intent(MainActivity.this,com.example.mc.MainActivity.class);
			//  intent.putExtra("text2",text1.getText().toString());//Sending values to new Activity
	    			  startActivity(intent);  // Opening new Activity

		}

		
	}
   
    
    
}
