

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

public class login extends Activity {
	String title;
	EditText usernamel;
	static EditText passl;
	//static EditText confpass;
	//ListView lv;
	Button crtupl;
	JSONParser jsonParser1 = new JSONParser();
	

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	//private static String check_login = ;
	//ArrayList<ListEntry> members = new ArrayList<ListEntry>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        usernamel = (EditText) findViewById(R.id.loginl);
passl = (EditText) findViewById(R.id.passl);
//confpass = (EditText) findViewById(R.id.editText3);
crtupl = (Button) findViewById(R.id.submitl);
        
        

    
    crtupl.setOnClickListener(new View.OnClickListener() {

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
		
		pDialog = new ProgressDialog(login.this);
		pDialog.setMessage("Authenticating..");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(true);
		pDialog.show();
	}
	
	/**
	 * Creating product
	 * */
	protected String doInBackground(String... args) {
		String passwl,namel;
		namel=usernamel.getText().toString();
		passwl=passl.getText().toString();
		//passw2=confpass.getText().toString();
		System.out.println("not here");
		//if(passw==passw2)
		//{
			
		System.out.println("yes it is here");
		// Building Parameters
		List<NameValuePair> paramsl = new ArrayList<NameValuePair>();
		paramsl.add(new BasicNameValuePair("namel", namel));
		paramsl.add(new BasicNameValuePair("passwl", passwl));
		//params.add(new BasicNameValuePair("description", description));

		// getting JSON Object
		// Note that create product url accepts POST method
		JSONObject json1 = jsonParser1.makeHttpRequest("http://192.168.64.1/seminar/chaeckloginpass.php",
				"POST", paramsl);
		
		// check log cat fro response
		Log.d("Create Response", json1.toString());

		// check for success tag
		try {
			int success = json1.getInt(TAG_SUCCESS);

			if (success == 1) {
				// successfully created product
				Intent i = new Intent(getApplicationContext(), Screen2.class);
				i.putExtra("id",namel);
				startActivity(i);
				
				// closing this screen
				finish();
			} else {
				Intent i = new Intent(getApplicationContext(), login.class);
				startActivity(i);
				// failed to create product
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	//}
		//else
		//{
        //return null;		
		//}
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

    

