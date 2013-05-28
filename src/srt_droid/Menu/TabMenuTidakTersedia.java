package srt_droid.Menu;

import java.util.ArrayList;

import srt_droid.Utilities;
import srt_droid.Controller.MenuController;

import com.example.srt_droid.R;
import com.example.srt_droid.R.layout;
import com.example.srt_droid.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TabMenuTidakTersedia extends Activity {
	ArrayList<MenuResto> m_data = null;

	MenuController menuController = new MenuController();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_menu_tidak_tersedia);
	
		init();
	}
	
	private void init() {
		m_data = menuController.getListOfMenuAktifTersedia(1, 0);

		LinearLayout listMenuLayout = (LinearLayout)findViewById(R.id.layout);
		String prevCategory = "";

		for (int i = 0; i < m_data.size(); i++) {
			LayoutInflater inflater;
			inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.list_menu_row, null);

			if (!m_data.get(i).getNamaKategori().equals(prevCategory)) {
				prevCategory = m_data.get(i).getNamaKategori();
				TextView cat = new TextView(getApplicationContext());
				cat.setText(prevCategory.toUpperCase());
				cat.setTextSize(17);
				cat.setTextColor(Color.parseColor("#33b5e5"));
				
				listMenuLayout.addView(cat);
			}

			final int pos = i;

			layout.setOnLongClickListener(new OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					showDialog(m_data.get(pos));
					return false;
				}
			});
			
			layout.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Utilities.menu = m_data.get(pos);
					startActivity(new Intent(getApplicationContext(), DeskripsiMenuActivity.class));
				}
			});

			TextView nama = (TextView)layout.getChildAt(0);
			nama.setText(m_data.get(i).getNama());

			TextView harga = (TextView)layout.getChildAt(1);
			harga.setText("" + m_data.get(i).getHarga());

			listMenuLayout.addView(layout);
		}
	}
	
	private void showDialog(final MenuResto menu) {
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.dialog_ubah_ketersediaan);
		dialog.setTitle("Pilihan anda?");
		
		Button ubah = (Button) dialog.findViewById(R.id.ubahKetersediaan);
		ubah.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				menuController.ubahKetersediaan(menu, 1);
				Toast.makeText(getApplicationContext(), "Menu " + menu.getNama() + " telah tersedia kembali", Toast.LENGTH_LONG).show();
				dialog.cancel();
				startActivity(new Intent(getApplicationContext(), ListMenuKokiActivity.class));
				finish();
			}
		});
		
		dialog.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tab_menu_tidak_tersedia, menu);
		return true;
	}

}
