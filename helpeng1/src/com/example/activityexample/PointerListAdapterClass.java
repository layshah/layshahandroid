package com.example.activityexample;

import java.util.ArrayList;

import android.R.string;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.Spinner;

import android.widget.TextView;

public class PointerListAdapterClass extends BaseAdapter implements ListAdapter {

	
	
	String[] sem ={"sem1","sem2","sem3","sem4","sem5","sem6","sem7","sem8"}; 
	Context context;
	
	public PointerListAdapterClass(Context ctx) {
		
	this.context = ctx;
	}

	@Override
	public int getCount() {
		return sem.length;
	}

	@Override
	public Object getItem(int arg0) {
		
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		
		View v = arg1;
		LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if(v==null){
			v = inflator.inflate(R.layout.listviewformat, null);
		}
		
		TextView tv = (TextView) v.findViewById(R.id.textView1);		
		tv.setText(sem[arg0]);
		return v;
	}
	
	}

