package srt_droid;

import java.util.ArrayList;

import srt_droid.Controller.PesananController;

import com.example.srt_droid.R;
import com.example.srt_droid.R.layout;
import com.example.srt_droid.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SejarahPenjualanMenuActivity extends Activity {
	String[] month = new String[]{"", "Jan", "Feb", "Mar", "Apr", "Mei", "Jun", "Jul", "Agu", "Sep", "Okt", "Nov", "Des"};
	
	String dateAwal, dateAkhir;
	
	TextView title;
	ArrayList <SejarahPenjualan> data;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sejarah_penjualan_menu);
	
		init();
	}
	
	private void init() {
		
		dateAwal = Utilities.date1[2] + "-" + Utilities.date1[1] + "-" + Utilities.date1[0];
		dateAkhir = Utilities.date2[2] + "-" + Utilities.date2[1] + "-" + Utilities.date2[0];
		
		data = new PesananController().getSejarahPenjualan(dateAwal, dateAkhir);
		
		title = (TextView)findViewById(R.id.title);
		title.setText("Sejarah Penjualan\n" + Utilities.date1[0] + " " + month[Utilities.date1[1]] + " " + Utilities.date1[2] + " - " +
				Utilities.date2[0] + " " + month[Utilities.date2[1]] + " " + Utilities.date2[2]);
		
		LinearLayout layout = (LinearLayout)findViewById(R.id.listLayout);
		LayoutInflater inflater;
		inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout listLayout = (LinearLayout) inflater.inflate(R.layout.sejarah_penjualan_row, null);
		listLayout.setPadding(0, 15, 0, 15);
		//listLayout.setBackgroundColor(Color.GRAY);
		
		TextView tv = (TextView)listLayout.getChildAt(0);
		tv.setText("Nama Menu");
		tv.setTextColor(Color.parseColor("#33b5e5"));
		tv.setTextSize(17);
		
		tv = (TextView)listLayout.getChildAt(1);
		tv.setText("Terjual");
		tv.setTextColor(Color.parseColor("#33b5e5"));
		tv.setTextSize(16);
		
		tv = (TextView)listLayout.getChildAt(2);
		tv.setText("Keuntungan");
		tv.setTextColor(Color.parseColor("#33b5e5"));
		tv.setTextSize(17);
		
		layout.addView(listLayout);
		
		long total = 0;
		for (int i = 0; i < data.size(); i++) {
			inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			listLayout = (LinearLayout) inflater.inflate(R.layout.sejarah_penjualan_row, null);
			
			tv = (TextView)listLayout.getChildAt(0);
			tv.setText(data.get(i).getNama());
			tv.setTextColor(Color.WHITE);
			
			tv = (TextView)listLayout.getChildAt(1);
			tv.setText("" + data.get(i).getTotalTerjual());
			tv.setTextColor(Color.WHITE);
			
			tv = (TextView)listLayout.getChildAt(2);
			tv.setText("Rp. " + data.get(i).getTotalUntung());
			tv.setTextColor(Color.WHITE);
			total += data.get(i).getTotalUntung();
			
			layout.addView(listLayout);
		}
		
		inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		listLayout = (LinearLayout) inflater.inflate(R.layout.sejarah_penjualan_row, null);
		listLayout.setPadding(0, 15, 0, 0);
		//listLayout.setBackgroundColor(Color.MAGENTA);
		
		tv = (TextView)listLayout.getChildAt(0);
		tv.setText("Total Keuntungan:");
		tv.setTextColor(Color.parseColor("#33b5e5"));
		tv.setTextSize(17);
		
		tv = (TextView)listLayout.getChildAt(2);
		tv.setText("Rp. " + total);
		tv.setTextColor(Color.parseColor("#33b5e5"));
		tv.setTextSize(17);
		
		layout.addView(listLayout);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result_sejarah_penjualan, menu);
		return true;
	}

}
