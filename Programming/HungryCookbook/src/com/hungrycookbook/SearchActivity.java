package com.hungrycookbook;

import Helpers.Sliding;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class SearchActivity extends Activity {

	private int key = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		final Sliding popup = (Sliding) findViewById(R.id.sliding1);
		popup.setVisibility(View.GONE);

		final Button btn = (Button) findViewById(R.id.show1);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (key == 0) {
					key = 1;
					popup.setVisibility(View.VISIBLE);
					// btn.setBackgroundResource(R.drawable.next_item);
				} else if (key == 1) {
					key = 0;
					popup.setVisibility(View.GONE);
					// btn.setBackgroundResource(R.drawable.next_item);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

}
