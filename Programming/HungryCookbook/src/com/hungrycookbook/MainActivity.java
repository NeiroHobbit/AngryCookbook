package com.hungrycookbook;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
	
	private Context cntxt;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		cntxt = this;

		

	}

	@Override
	public void onStart() {
		super.onStart();
		try {
			Thread.sleep(1000);
			MenuFragment menuFragment = new MenuFragment();
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager
					.beginTransaction();
			fragmentTransaction.add(R.id.activity_main_layout, menuFragment,
					"menu");
			fragmentTransaction.commit();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
