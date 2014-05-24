package com.example.activityexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
//import android.view.View.OnClickListener;
//ArrayList<ListEntry> members = new ArrayList<ListEntry>();

public class Screen2<ListEntry> extends Activity implements View.OnClickListener{
	String title;
	TextView msg,t1;
	ListView lv;
	Button button1,b1,b2;
	Intent intent = getIntent();
    String id ,semno;
	//ArrayList<ListEntry> members = new ArrayList<ListEntry>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
        
        
        
        Intent intent = getIntent();
         id = intent.getStringExtra("id");
    	
    //  Intent intent = new Intent(Screen2.this,PointerListAdapterClass.class);//Explicit Intent used here.
		//   startActivity(intent);  // Opening new Activity
       t1 = (TextView) findViewById(R.id.textView1);
    	//button1 = (Button) findViewById(R.id.button1);
      //  msg.setTextColor(Color.RED);
        lv = (ListView) findViewById(R.id.listView1);
        b1= (Button) findViewById(R.id.addproject);
        b2= (Button) findViewById(R.id.viewproject);
        
        PointerListAdapterClass pa = new PointerListAdapterClass(Screen2.this);
        lv.setAdapter(pa);
        //lv.isClickable();
        
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
         
		

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			t1 = (TextView) findViewById(R.id.textView1);
			semno=t1.getText().toString();
			setlistner();
			
			
            //long id = arg3;
            
          
          
           	// TODO Auto-generated method stub
			
		}
        });
    	
        //title = getIntent().getExtras().getString("text2");
      //  msg.setText("  "+ title);
       
 //       button1.setOnClickListener(new OnClickListener() {
       	 
	//		public void onClick(View arg0) {
                                         
			 
		//	}
 //
		//});
 
    
        
    }
    public void setlistner()
    {
    b1.setOnClickListener(this);
	b2.setOnClickListener( this);
    }
    
    public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.viewproject)
		{
			Intent intent = new Intent(Screen2.this,AllProductsActivity.class);//Explicit Intent used here.
			intent.putExtra("id", id);
	          intent.putExtra("semno", semno);
	          
			//intent.putExtra("start",text1.getText().toString());//Sending values to new Activity
				    startActivity(intent);  // Opening new Activity
		}
		if(v.getId() == R.id.addproject)
		{
			Intent intent = new Intent(Screen2.this,NewProductActivity.class);//Explicit Intent used here.
			intent.putExtra("id", id);
	          intent.putExtra("semno", semno);
	            
			//  intent.putExtra("text2",text1.getText().toString());//Sending values to new Activity
	    			  startActivity(intent);  // Opening new Activity

		}


	}

    
    
    
}
    
    
    
    

   





