package srt_droid.Menu;

import java.io.InputStream;
import java.net.URL;

import srt_droid.PemilikRestoranActivity;
import srt_droid.Utilities;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.srt_droid.R;

public class DeskripsiMenuActivity extends Activity {

	TextView nama, harga, deskripsi;
	ImageView image;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deskripsi_menu);
	
		init();
	}	
	
	private void init() {
		image = (ImageView)findViewById(R.id.image);
		
		nama = (TextView) findViewById(R.id.namaMenu);
		nama.setText(Utilities.menu.getNama());
		
		harga = (TextView) findViewById(R.id.hargaMenu);
		harga.setText("Rp " + Utilities.menu.getHarga());
		
		deskripsi = (TextView) findViewById(R.id.deskripsiMenu);
		deskripsi.setText(Utilities.menu.getDeskripsi());
	
		URL Url;
		try {
			Url = new URL(Utilities.URL + "image/" + Utilities.menu.getImage());
			InputStream content = (InputStream)Url.getContent();
			Drawable d = Drawable.createFromStream(content , "src"); 
			image.setImageDrawable(d);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.deskripsi_menu, menu);
		return true;
	}

}
