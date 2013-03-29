package com.example.srt_droid;

import java.util.ArrayList;
import java.util.List;

import com.example.srt_droid.Controller.MenuController;
import com.example.srt_droid.ListAccountActivity.ListAccountAsync;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListMenuActivity extends Activity {

	MenuController menuController = new MenuController();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_menu);
		init();
	}

	void init() {
	}

	public void tambahMenu(View v) {
		Toast.makeText(getBaseContext(), "Tambah Menu", 2000).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_menu, menu);
		return true;
	}
}