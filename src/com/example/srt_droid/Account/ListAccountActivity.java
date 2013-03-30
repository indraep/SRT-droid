package com.example.srt_droid.Account;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.srt_droid.R;
import com.example.srt_droid.Utilities;
import com.example.srt_droid.Controller.AccountController;
import com.example.srt_droid.R.id;
import com.example.srt_droid.R.layout;
import com.example.srt_droid.R.menu;

public class ListAccountActivity extends Activity {

	ListView list;
	List<String> model=new ArrayList<String>();
	ArrayList<User> m_data = null;
	UserAdapter m_adapter;

	AccountController accountController = new AccountController();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_account);
		init();
	}

	void init() {
		list = (ListView)findViewById(R.id.listview);
		list.setOnItemLongClickListener(new OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                showDialog(ListAccountActivity.this, "", "Apa yang ingin anda lakukan terhadap account ini?", m_data.get(pos));
            	return true;
            }
        });
		new ListAccountAsync(getApplicationContext()).execute();
	}
	
	public void showDialog(Activity activity, String title, CharSequence message, final User user) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
	    if (title != null)
	        builder.setTitle(title);
	    builder.setMessage(message);
	    builder.setPositiveButton("Hapus", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				showConfirmDialog(ListAccountActivity.this, "", "Apakah anda yakin?", user);
			}
		});
	    builder.setNegativeButton("Ubah", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Utilities.oldUser = user;
				startActivity(new Intent(ListAccountActivity.this, UbahAccountActivity.class));
			}
		});
	    builder.show();
	}
	
	public void showConfirmDialog(Activity activity, String title, CharSequence message, final User user) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
	    if (title != null)
	        builder.setTitle(title);
	    builder.setMessage(message);
	    builder.setPositiveButton("Ya", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (accountController.hapus(user)) {
					Toast.makeText(ListAccountActivity.this, "Account berhasil dihapus!", Toast.LENGTH_LONG).show();
					startActivity(new Intent(ListAccountActivity.this, ListAccountActivity.class));
					finish();
				}
			}
		});
	    builder.setNegativeButton("Tidak", null);
	    builder.show();
	}

	public void tambahAccount(View v) {
		startActivity(new Intent(this, BuatAccountActivity.class));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_account, menu);
		return true;
	}

	public class ListAccountAsync extends AsyncTask<String, Integer, String> {
		private ProgressDialog Dialog = new ProgressDialog(ListAccountActivity.this);
		private Context mContext;

		public ListAccountAsync(Context context) {
			mContext = context;
		}

		@Override
		protected void onPreExecute() {
			Dialog.setTitle("Harap tunggu...");
			Dialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			m_data = accountController.getListOfAccount();
			publishProgress();
			return "";
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			Dialog.dismiss();
			
			m_adapter = new UserAdapter(ListAccountActivity.this, R.layout.list_account_row, m_data);
			list.setAdapter(m_adapter); 
		}

		@Override
		protected void onPostExecute(String result) {
		}

	}

}

class UserAdapter extends ArrayAdapter<User> {

	private ArrayList <User> items;

	public UserAdapter(Context context, int textViewResourceId, ArrayList<User> objects) {
		super(context, textViewResourceId, objects);
		items = objects;
	}

	public View getView(int position, View convertView, ViewGroup pattern) {
		View v = convertView;

		if (v == null) {
			LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.list_account_row, null);
		}
		User o = items.get(position);
		if (o != null) {
			TextView tt = (TextView) v.findViewById(R.id.nama);
			if (tt != null) {
				tt.setText("Name: " + o.getNama());
			}

			tt = (TextView)v.findViewById(R.id.username);
			if (tt != null) {
				tt.setText("Username: " + o.getUsername());
			}
		}
		return v;
	}

}
