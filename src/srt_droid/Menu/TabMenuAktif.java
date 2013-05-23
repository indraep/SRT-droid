package srt_droid.Menu;

import java.util.ArrayList;

import srt_droid.PemilikRestoranActivity;
import srt_droid.Utilities;
import srt_droid.Controller.MenuController;

import com.example.srt_droid.R;
import com.example.srt_droid.R.layout;
import com.example.srt_droid.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TabMenuAktif extends Activity {
	ArrayList<MenuResto> m_data = null;

	MenuController menuController = new MenuController();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_menu_aktif);
		
		init();
	}
	
	private void init() {
		m_data = menuController.getListOfMenuAktif(1);

		LinearLayout listMenuLayout = (LinearLayout)findViewById(R.id.layout);
		String prevCategory = "";

		for (int i = 0; i < m_data.size(); i++) {
			LayoutInflater inflater;
			inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.list_menu_row, null);

			if (!m_data.get(i).getNamaKategori().equals(prevCategory)) {
				prevCategory = m_data.get(i).getNamaKategori();
				TextView cat = new TextView(getApplicationContext());
				cat.setText(prevCategory);
				listMenuLayout.addView(cat);
			}

			final int pos = i;

			layout.setOnLongClickListener(new OnLongClickListener() {
				@Override
				public boolean onLongClick(View v) {
					showDialog(TabMenuAktif.this, "", "Apa yang ingin anda lakukan terhadap menu ini?", m_data.get(pos));
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
	
	public void onBackPressed() {
		startActivity(new Intent(getApplicationContext(), PemilikRestoranActivity.class));
		finish();
	}
	
	public void showDialog(Activity activity, String title, CharSequence message, final MenuResto menu) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		if (title != null)
			builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton("Non Aktifkkan", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (menuController.ubahAktif(menu, 0)) {
					Toast.makeText(getApplicationContext(), "Menu berhasil dinon-aktifkan!", Toast.LENGTH_LONG).show();
					startActivity(new Intent(getApplicationContext(), ListMenuActivity.class));
					finish();
				}
			}
		});
		builder.setNegativeButton("Ubah", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Utilities.oldMenu = menu;
				startActivity(new Intent(getApplicationContext(), UbahMenuActivity.class));
				finish();
			}
		});
		builder.show();
	}
	
	public void tambahMenu(View v) {
		startActivity(new Intent(this, BuatMenuActivity.class));
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tab_menu_aktif, menu);
		return true;
	}

}
