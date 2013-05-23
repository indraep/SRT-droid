package srt_droid.Menu;

import java.util.ArrayList;

import srt_droid.KokiActivity;
import srt_droid.LoginActivity;
import srt_droid.PemilikRestoranActivity;
import srt_droid.Utilities;
import srt_droid.Controller.MenuController;

import com.example.srt_droid.R;
import com.example.srt_droid.R.layout;
import com.example.srt_droid.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class ListMenuKokiActivity extends TabActivity {
	ArrayList<MenuResto> m_data = null;

	MenuController menuController = new MenuController();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_menu_koki);
		
		initTab();
	}
	
	private void initTab() {
		Resources ressources = getResources(); 
		TabHost tabHost = getTabHost(); 
		
		// Android tab
		Intent intentAndroid = new Intent().setClass(this, TabMenuTersedia.class);
		TabSpec tabSpecAndroid = tabHost
			.newTabSpec("Tersedia")
			.setIndicator("", ressources.getDrawable(R.drawable.icon_menu_ok))
			.setContent(intentAndroid);

		// Apple tab
		Intent intentApple = new Intent().setClass(this, TabMenuTidakTersedia.class);
		TabSpec tabSpecApple = tabHost
			.newTabSpec("Tidak Tersedia")
			.setIndicator("", ressources.getDrawable(R.drawable.icon_menu_no))
			.setContent(intentApple);
	
		// add all tabs 
		tabHost.addTab(tabSpecAndroid);
		tabHost.addTab(tabSpecApple);
		
		//set Windows tab as default (zero based)
		tabHost.setCurrentTab(0);
	}
	
	public void onBackPressed() {
		startActivity(new Intent(getApplicationContext(), KokiActivity.class));
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_menu_koki, menu);
		return true;
	}

}
