package srt_droid;

import srt_droid.Menu.TabMenuAktif;
import srt_droid.Menu.TabMenuTidakAktif;

import com.example.srt_droid.R;
import com.example.srt_droid.R.layout;
import com.example.srt_droid.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class ResultSejarahPenjualanActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result_sejarah_penjualan);
		
		initTab();
	}
	
	private void initTab() {
		Resources ressources = getResources(); 
		TabHost tabHost = getTabHost(); 
		
		// Android tab
		Intent intentAndroid = new Intent().setClass(this, ListPesananSejarahActivity.class);
		TabSpec tabSpecAndroid = tabHost
			.newTabSpec("Detail")
			.setIndicator("", ressources.getDrawable(R.drawable.icon_menu_detail))
			.setContent(intentAndroid);

		// Apple tab
		Intent intentApple = new Intent().setClass(this, SejarahPenjualanMenuActivity.class);
		TabSpec tabSpecApple = tabHost
			.newTabSpec("Rekap")
			.setIndicator("", ressources.getDrawable(R.drawable.icon_menu_rekap))
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
		getMenuInflater().inflate(R.menu.result_sejarah_penjualan, menu);
		return true;
	}

}
