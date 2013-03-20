package com.hungrycookbook;

import java.util.ArrayList;

import Helpers.Sliding;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.Model.Product;
import com.Model.ReceptEngine;

public class SearchActivity extends Activity {

	private boolean key = false;
	AutoCompleteTextView productAutoComplete;
	ArrayAdapter<String> productAutoCompleteAdapter;
	private ReceptEngine engine;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		engine = ReceptEngine.getInstance(getAssets());

		final Sliding popup = (Sliding) findViewById(R.id.sliding1);
		popup.setVisibility(View.GONE);

		final Button btn = (Button) findViewById(R.id.show1);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (!key) {
					key = true;
					popup.setVisibility(View.VISIBLE);
					// btn.setBackgroundResource(R.drawable.next_item);
				} else if (key) {
					key = false;
					popup.setVisibility(View.GONE);
					// btn.setBackgroundResource(R.drawable.next_item);
				}
			}
		});

		productAutoComplete = (AutoCompleteTextView) findViewById(R.id.productTextView);

		String[] prodNames = productNames();
		
		
		
		//productAutoComplete.addTextChangedListener(getApplicationContext());

		productAutoCompleteAdapter = new ArrayAdapter<String>(getApplicationContext(),
				android.R.layout.simple_dropdown_item_1line, prodNames);

		productAutoComplete.setAdapter(productAutoCompleteAdapter);
	}

	private String[] productNames() {
		ArrayList<Product> products = engine.getProducts();
		ArrayList<String> productNames = new ArrayList<String>();
		for (Product product : products) {
			productNames.add(product.getNameProduct());
		}
		return productNames.toArray(new String[productNames.size()]);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search, menu);
		return true;
	}

}
