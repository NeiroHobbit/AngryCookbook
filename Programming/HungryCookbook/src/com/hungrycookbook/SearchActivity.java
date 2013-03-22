package com.hungrycookbook;

import java.util.ArrayList;

import Helpers.Sliding;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Model.Product;
import com.Model.Recept;
import com.Model.ReceptEngine;
import com.Model.ReceptWithPriority;

public class SearchActivity extends Activity {

	private boolean key = false;
	AutoCompleteTextView productAutoComplete;
	ArrayAdapter<String> productAutoCompleteAdapter;
	private ReceptEngine engine;
	private Activity rootView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		rootView = this;

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

		productAutoCompleteAdapter = new ArrayAdapter<String>(
				getApplicationContext(),
				android.R.layout.simple_dropdown_item_1line, prodNames);

		productAutoComplete.setAdapter(productAutoCompleteAdapter);

		newItemProduce();

		Button searchButton = (Button) findViewById(R.id.searchButton);
		searchButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				ArrayList<Product> prodList = new ArrayList<Product>();
				LinearLayout prLayout = (LinearLayout) rootView
						.findViewById(R.id.productsLayout);
				ArrayList<TextView> productTextView = new ArrayList<TextView>();
				for (int i = 0; i < prLayout.getChildCount(); i++) {
					// productTextView.add((TextView) prLayout.getChildAt(i));
					Product nProd = engine
							.getProductByName(((TextView) prLayout
									.getChildAt(i)).getText().toString());
					if (nProd != null) {
						prodList.add(nProd);
					}
				}
				ArrayList<ReceptWithPriority> recepts = engine.findRecepts(prodList);
				int a=0;

			}
		});

	}

	private void newItemProduce() {
		productAutoComplete.setOnItemClickListener(new OnItemClickListener() {	

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				LinearLayout prLayout = (LinearLayout) rootView
						.findViewById(R.id.productsLayout);
				TextView prText = new TextView(rootView);
				prText.setText(productAutoComplete.getText());
				prLayout.addView(prText);

				productAutoComplete.setText("");
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(
						productAutoComplete.getWindowToken(), 0);

			}

		});
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
