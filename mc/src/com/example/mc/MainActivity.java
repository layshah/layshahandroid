package com.example.mc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.telephony.SmsManager;
import android.net.Uri;
import android.content.ContentValues;
import android.content.Intent;


import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends ListActivity {

	// Progress Dialog
	private ProgressDialog pDialog;

	// Creating JSON Parser object
	JSONParser jParser = new JSONParser();

	ArrayList<HashMap<String, String>> productsList;

	// url to get all products list
	private static String url_all_products = "http://10.0.2.2/mc/get_all_products.php";
	private SparseBooleanArray checkStatus;
	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PRODUCTS = "products";
	private static final String TAG_PID = "pid";
	private static final String TAG_NAME = "name";
	private LayoutInflater mInflater;
	
	Button btnsubmit;
	// products JSONArray
	JSONArray products = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Hashmap for ListView
		productsList = new ArrayList<HashMap<String, String>>();

		// Loading products in Background Thread
		new LoadAllProducts().execute();
btnsubmit  = (Button) findViewById(R.id.button1);
		// Get listview
		ListView lv = (ListView) findViewById(android.R.id.list);
		
		// on seleting single product
		// launching Edit Product Screen
		lv.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String pid = ((TextView) view.findViewById(R.id.pid1)).getText()
						.toString();
        //        getView(position, view, parent);
				// Starting new intent
				
				// sending pid to next activity
				//in.putExtra(TAG_PID, pid);
				
				// starting new activity and expecting some response back
			
			}
		
		
		});
	
		
		
		
			
		
			
		btnsubmit.setOnClickListener(new OnClickListener(){
			    public void onClick(View arg0) {   
			    	ListView lv = (ListView) findViewById(android.R.id.list);
			    	int itemCount = (int) ((ListView) findViewById(android.R.id.list)).getCount();
			    	ArrayList<Object> myCheckedItems = new ArrayList<Object>();
			    	lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
			    	int[] checkedItemPositions = {0,0,0,0,0,0,0,0};
			   
			        
			       System.out.print("hello");
			       
			       CheckBox cbx = (CheckBox)lv.findViewById(R.id.checkBox1);
		            int firstPosition = lv.getFirstVisiblePosition();
		            int j=0;
		                for (int i = firstPosition; i < lv.getCount(); i++) {

		                    View v1 = lv.getChildAt(i);

		                    cbx = (CheckBox)v1.findViewById(R.id.checkBox1);
		                    if(cbx.isChecked())
		                        checkedItemPositions[i]=i;
		                    
		                }
		                
		        String ph,ph2;
			       String message="your son/daughter was absent today";
			        for(int i=itemCount-1; i > 0; i--){
			            if(checkedItemPositions[i]==i){ 
			                myCheckedItems.add((lv.getTag(i)));
			                View v2 = lv.getChildAt(i);
			                ph = (String)((TextView) v2.findViewById(R.id.phno1)).getText().toString();
			                SmsManager smsManager = SmsManager.getDefault();
			                System.out.println(ph);
			                
			           
			              
			             
					        smsManager.sendTextMessage(ph, null, message, null, null);		               
			               }
			        }   
			        int a=myCheckedItems.size();
			        System.out.printf("%d",a);
			        

			        //checkedItemPositions.clear();
			       // adapter.notifyDataSetChanged();
			        Intent in = new Intent(getApplicationContext(),
							next.class);
			        startActivity(in);
			    }
			});            
			
			

		
		
		
	
	}
		// Response from Edit Product Activity
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// if result code 100
		if (resultCode == 100) {
			// if result code 100 is received 
			// means user edited/deleted product
			// reload this screen again
			Intent intent = getIntent();
			finish();
			
		}

	}


	/**
	 * Background Async Task to Load all product by making HTTP Request
	 * */
	class LoadAllProducts extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("Loading numbers. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting All products from url
		 * */
		protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);
			
			// Check your log cat for JSON reponse
			Log.d("All Products: ", json.toString());

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// products found
					// Getting Array of Products
					products = json.getJSONArray(TAG_PRODUCTS);

					// looping through All Products
					for (int i = 0; i < products.length(); i++) {
						JSONObject c = products.getJSONObject(i);

						// Storing each json item in variable
						String id = c.getString(TAG_PID);
						String phno = c.getString(TAG_NAME);

						// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						map.put(TAG_PID, id);
						map.put(TAG_NAME, phno);

						// adding HashList to ArrayList
						productsList.add(map);
					}
				} else {
					// no products found
					// Launch Add New product Activity
					Intent i = new Intent(getApplicationContext(),
							MainActivity.class);
					// Closing all previous activities
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}
		
		
		
		

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					/**
					 * Updating parsed JSON data into ListView
					 * */
					ListAdapter adapter = new SimpleAdapter(
							MainActivity.this, productsList,
							R.layout.listviewformat, new String[] {TAG_PID,TAG_NAME}, 
							new int[] { R.id.pid1,R.id.phno1});
					// updating listview
					setListAdapter(adapter);
				}
			});
			
		}
			}

}