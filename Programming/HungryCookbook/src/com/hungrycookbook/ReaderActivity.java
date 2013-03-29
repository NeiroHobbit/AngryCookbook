package com.hungrycookbook;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.Model.ReceptEngine;

public class ReaderActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reader);

		String str = getIntent().getExtras().getString("Recept_name");
		ReceptEngine engine = ReceptEngine.getInstance(getAssets());
		String[] rec = engine.getReceptByNameForReader(str);
		int a = 0;
		
		TextView reader_name = (TextView) findViewById(R.id.reader_name);
		reader_name.setText(rec[0]);
		TextView reader_ingr = (TextView) findViewById(R.id.reader_ingr);
		reader_ingr.setText(rec[1]);
		TextView reader_text = (TextView) findViewById(R.id.reader_text);
		reader_text.setText(rec[2]);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.reader, menu);
		return true;
	}

}
