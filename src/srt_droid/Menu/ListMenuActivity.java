package srt_droid.Menu;

import java.util.ArrayList;
import java.util.List;

import srt_droid.PelayanActivity;
import srt_droid.PemilikRestoranActivity;
import srt_droid.Utilities;
import srt_droid.Controller.MenuController;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

import com.example.srt_droid.R;
import com.example.srt_droid.R.id;
import com.example.srt_droid.R.layout;
import com.example.srt_droid.R.menu;

public class ListMenuActivity extends TabActivity {

	ArrayList<MenuResto> m_data = null;

	MenuController menuController = new MenuController();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_menu);
		
		initTab();
	}
	
	public void onBackPressed() {
		startActivity(new Intent(getApplicationContext(), PemilikRestoranActivity.class));
		finish();
	}
	
	private void initTab() {
		Resources ressources = getResources(); 
		TabHost tabHost = getTabHost(); 
		
		// Android tab
		Intent intentAndroid = new Intent().setClass(this, TabMenuAktif.class);
		TabSpec tabSpecAndroid = tabHost
			.newTabSpec("Aktif")
			.setIndicator("", ressources.getDrawable(R.drawable.icon_menu_ok))
			.setContent(intentAndroid);

		// Apple tab
		Intent intentApple = new Intent().setClass(this, TabMenuTidakAktif.class);
		TabSpec tabSpecApple = tabHost
			.newTabSpec("Tidak Aktif")
			.setIndicator("", ressources.getDrawable(R.drawable.icon_menu_no))
			.setContent(intentApple);
	
		// add all tabs 
		tabHost.addTab(tabSpecAndroid);
		tabHost.addTab(tabSpecApple);
		
		//set Windows tab as default (zero based)
		tabHost.setCurrentTab(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_menu, menu);
		return true;
	}
}