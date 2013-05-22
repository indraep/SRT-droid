package com.example.srt_droid.Pesanan;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.srt_droid.R;
import com.example.srt_droid.Utilities;
import com.example.srt_droid.Controller.PesananController;

public class FormPembayaranActivity extends Activity {
	ArrayList<DetailPesanan> m_data = null;
	PesananController pesananController = new PesananController();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form_pembayaran);
		
		init();
	}
	
	private void init() {
		m_data = pesananController.getListOfDetailPesanan(Utilities.oldPesanan.getId());
		
		TextView tv;
		LinearLayout listLayout = (LinearLayout)findViewById(R.id.listLayout);
		
		for (int i = 0; i < m_data.size(); i++) {
			LayoutInflater inflater;
			inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.list_detail_pembayaran_row, null);
			
			tv = (TextView)layout.getChildAt(0);
			tv.setText((i + 1) + ". " + m_data.get(i).getNama());
			
			LinearLayout ll = (LinearLayout)layout.getChildAt(1);
			tv = (TextView)ll.getChildAt(0);
			tv.setText(m_data.get(i).getJumlah() + " x @ Rp." + m_data.get(i).getHarga());
			
			tv = (TextView)ll.getChildAt(1);
			tv.setText("= Rp." + (m_data.get(i).getJumlah() * m_data.get(i).getHarga()));
			
			listLayout.addView(layout);
		}
		
		LayoutInflater inflater;
		inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.list_detail_pembayaran_total_payment_row, null);
		LinearLayout ll = (LinearLayout)layout.getChildAt(0);
		
		tv = (TextView)ll.getChildAt(1);
		tv.setText("= Rp." + Utilities.oldPesanan.getTotalHarga());
		listLayout.addView(layout);
		
		ll = (LinearLayout)layout.getChildAt(1);
		final EditText uangBayar = (EditText)ll.getChildAt(1);
		
		ll = (LinearLayout)layout.getChildAt(2);
		final TextView uangKembalian = (TextView)ll.getChildAt(1);
		
		Button buttonBayar = (Button)layout.getChildAt(3);
		
		final int totalHarga = Utilities.oldPesanan.getTotalHarga();
		uangBayar.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				String temp = ((EditText)v).getText().toString();
				if (temp.length() > 0) {
					int total = Integer.parseInt(temp);
					uangKembalian.setText("Rp. " + Math.max(0, total - totalHarga));
				}
				return false;
			}
		});
		
		buttonBayar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String temp = uangBayar.getText().toString();
				if (temp.length() > 0 && Integer.parseInt(temp) >= totalHarga) {
					pesananController.ubahStatus(Utilities.oldPesanan, Utilities.oldPesanan.getStatus() + 1);
					Toast.makeText(getApplicationContext(), "Pembayaran berhasil", Toast.LENGTH_LONG).show();
					startActivity(new Intent(getApplicationContext(), ListPesananKasirActivity.class));
					finish();
				}
				else {
					Toast.makeText(getApplicationContext(), "Uang yang anda masukkan tidak mencukupi", Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.form_pembayaran, menu);
		return true;
	}

}
