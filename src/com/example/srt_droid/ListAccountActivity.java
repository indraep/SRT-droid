package com.example.srt_droid;

import java.util.ArrayList;
import java.util.List;

import com.example.srt_droid.Controller.AccountController;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts.Data;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
		m_data = accountController.getListOfAccount();
		m_adapter = new UserAdapter(this, R.layout.list_account_row, m_data);
		list.setAdapter(m_adapter);
		
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
				Toast.makeText(ListAccountActivity.this, m_data.get(position).toString(), 2000).show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_account, menu);
		return true;
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
