

package com.example.activityexample;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
//import android.view.View.OnClickListener;
//ArrayList<ListEntry> members = new ArrayList<ListEntry>();

public class register extends Activity {
	String title;
	EditText username;
	static EditText pass;
	static EditText confpass;
	ListView lv;
	Button crtup;
	JSONParser jsonParser = new JSONParser();
	

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static String url_create_product = "http://192.168.64.1/seminar/crtloginpass.php";
	//ArrayList<ListEntry> members = new ArrayList<ListEntry>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        
        username = (EditText) findViewById(R.id.editText1);
pass = (EditText) findViewById(R.id.editText2);
confpass = (EditText) findViewById(R.id.editText3);
crtup = (Button) findViewById(R.id.crtup);
        
        

    
    crtup.setOnClickListener(new View.OnClickListener() {

		@Override
		public void onClick(View view) {
			// creating new product in background thread
			 new crtup().execute();
		}
	});
}
class crtup extends AsyncTask<String, String, String> 
     {
	private ProgressDialog pDialog;
	/**
	 * Before starting background thread Show Progress Dialog
	 * */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		
		pDialog = new ProgressDialog(register.this);
		pDialog.setMessage("Registering..");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(true);
		pDialog.show();
	}
	
	/**
	 * Creating product
	 * */
	protected String doInBackground(String... args) {
		String passw,passw2,name;
		name=username.getText().toString();
		passw=pass.getText().toString();
		passw2=confpass.getText().toString();
		System.out.println("not here");
		if(passw.equals(passw2))
		{
			
		System.out.println("yes it is here");
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("passw", passw));
		//params.add(new BasicNameValuePair("description", description));

		// getting JSON Object
		// Note that create product url accepts POST method
		JSONObject json = jsonParser.makeHttpRequest(url_create_product,
				"POST", params);
		
		// check log cat fro response
		Log.d("Create Response", json.toString());

		// check for success tag
		try {
			int success = json.getInt(TAG_SUCCESS);

			if (success == 1) {
				// successfully created product
				Intent i = new Intent(getApplicationContext(), Screen2.class);
				i.putExtra("id",name );
				startActivity(i);
				
				// closing this screen
				finish();
			} else {
				// failed to create product
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}
		else
		{
        return null;		
		}
	}
	
	/**
	 * After completing background task Dismiss the progress dialog
	 * **/
	protected void onPostExecute(String file_url) {
		// dismiss the dialog once done
		pDialog.dismiss();
	}


	
	
	  
   }


}

    

